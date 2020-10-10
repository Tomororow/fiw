package com.fourfaith.task.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.fourfaith.statisticAnalysis.model.*;
import com.fourfaith.statisticAnalysis.service.IndustrialdeviceService;
import com.fourfaith.utils.*;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fourfaith.alarmManage.model.WarningRecordDetail;
import com.fourfaith.alarmManage.service.WarningRecordDetailService;
import com.fourfaith.baseManager.controller.RemoteController;
import com.fourfaith.paramterManage.model.ParamPumpingWell;
import com.fourfaith.paramterManage.model.Warnerrortype;
import com.fourfaith.paramterManage.service.ParamPumpingWellService;
import com.fourfaith.paramterManage.service.WarnerrortypeService;
import com.fourfaith.statisticAnalysis.service.UseWaterService;
import com.fourfaith.sysManage.model.BaseDeviceDynamicInfo;
import com.fourfaith.sysManage.model.Tripshistory;
import com.fourfaith.sysManage.service.BaseDeviceDynamicInfoService;
import com.fourfaith.sysManage.service.TripshistoryService;

/**
 *
 * 计算日用水、月用水、年用水
 * @author Admin
 */
public class TaskWaterScheduler {

	static Logger logger = Logger.getLogger(TaskWaterScheduler.class);

	private ScheduledExecutorService scheduExec;

	@Autowired
	private UseWaterService useWaterService;

	@Autowired
	private BaseDeviceDynamicInfoService baseDeviceDynamicInfoService;

	@Autowired
	private ParamPumpingWellService paramWellService;

	@Autowired
	private WarningRecordDetailService warningRecordDetailService;

	@Autowired
	private WarnerrortypeService warnerrortypeService;

	@Autowired
	private TripshistoryService tripshistoryService;

	@Autowired
	private IndustrialdeviceService industrialdeviceService;

	public TaskWaterScheduler() {
		scheduExec = Executors.newScheduledThreadPool(6);
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(
				new Supplier<Integer>() {
					@Override
					public Integer get() {
						ARuntime();//项目初始化加载的统一方法
						findHourWater();//开始计算水量
						paramWellWater();//计算灌溉期（机井不在线天数和用水量最小不得小于多少）
						Abnormaltrip();//监测异常跳闸信息
						IndustrialSate();//工业机井设备状态实时监测
						return null;
					}
				}, scheduExec);
	}
	/**
	 * 项目初始化加载的统一方法
	 */
	public void ARuntime(){
		try {
			Thread.sleep(3000);
			int sign = baseDeviceDynamicInfoService.updateDeviceType();//项目启动即初始化设备状态
			logger.info("===初始化设备状态已执行，设备数量===【"+sign+"】");
		} catch (Exception e) {
			logger.error("===初始化设备状态执行失败==="+e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 开始计算水量（每隔一分钟执行一次）
	 */
	public void findHourWater(){

		scheduExec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				logger.info("==计算水量线程池开始工作==当前时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			    boolean vity = false;

				try {
					//=================第一步：首先查询《rptusewaterdetail》时段水量表的数据
					List<RptUseWaterDetail> useList = useWaterService.findDataFlagZero();
					logger.info("==当前共查询到【"+useList.size()+"】条未计算的水量数据==");
					if(useList.size()>0){
						int i=0;
						for(RptUseWaterDetail rpt:useList ){
							//除去2010脏数据
							if(rpt.getInYear()==2010){
								useWaterService.delDirty();
								i++;
								logger.info("删除脏数据"+i+"条");
							}else {
								//第二步：开始进入日统计表
								vity = enterDataOfDay(rpt.getDeviceCode(),rpt.getCardCode(),rpt.getUseWater(),rpt.getUpTime(),rpt.getInYear(),rpt.getInMonth(),rpt.getInDay());
								//第三步：开始进入月统计表
								vity = enterDataOfMonth(rpt.getDeviceCode(),rpt.getCardCode(),rpt.getUseWater(),rpt.getUpTime(),rpt.getInYear(),rpt.getInMonth());
								//第四步：开始进入年统计表
								vity = enterDataOfYear(rpt.getDeviceCode(),rpt.getCardCode(),rpt.getUseWater(),rpt.getUpTime(),rpt.getInYear());
								if(vity){
									useWaterService.updateChilter(rpt.getId());
									logger.info("正在补录当前水量数据==【"+rpt.getId()+"】");
								}
							}

						}
					}
				} catch (Exception e) {
					logger.error("水量计算线程报错==="+e.getMessage());
					e.printStackTrace();
				}
			}

		},1,15, TimeUnit.MINUTES);
	}

	/**
	 * 监测异常跳闸信息
	 */
	public void Abnormaltrip(){

		scheduExec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				logger.info("监测异常跳闸信息开始："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				try {
					//查询跳闸异常信息
					List<Tripshistory> abnormalTripList = tripshistoryService.abnormalTrip();
					//对异常信息表进行查询
					List<WarningRecordDetail> warnAllList = warningRecordDetailService.warnAll();
					WarningRecordDetail warn = new WarningRecordDetail();
					//去除重复数据
					if(abnormalTripList.size()>0){
						for (Tripshistory trip : abnormalTripList) {
							boolean flag = false;
							if(warnAllList.size()>0){
								SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								for(WarningRecordDetail record : warnAllList){
									if(trip.getDevicecode().equals(record.getDeviceCode())&&trip.getTripstype()==record.getWarnCode()
											&& sf.format(trip.getTripstime()).equals(sf.format(record.getWarnTime()))){
										flag = true;
									}
								}
								if(!flag){
									warn.setDeviceCode(trip.getDevicecode());
									warn.setDeviceName(trip.getDevicename());
									warn.setWarnType(trip.getWabnormaltype());
									warn.setWarnCode(trip.getTripstype());
									warn.setWarnDetail(trip.getWabnormaltype());
									warn.setWarnTime(trip.getTripstime());
									warn.setWarnState(0);
									warningRecordDetailService.insertSelective(warn);
								}
							}else{//异常信息表为空时
								warn.setDeviceCode(trip.getDevicecode());
								warn.setDeviceName(trip.getDevicename());
								warn.setWarnType(trip.getWabnormaltype());
								warn.setWarnCode(trip.getTripstype());
								warn.setWarnDetail(trip.getWabnormaltype());
								warn.setWarnTime(trip.getTripstime());
								warn.setWarnState(0);
								warningRecordDetailService.insertSelective(warn);
							}

						}
					}else{
						logger.info("查询无果！！！！");
					}
				} catch (Exception e) {
					logger.error("查询over");
					e.printStackTrace();
				}
			}

		},1,15, TimeUnit.MINUTES);
	}


	/**
	 * 计算灌溉期（机井不在线天数和高峰期用水量异常）
	 */
	public void paramWellWater(){

		scheduExec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Date date = new Date();
				DateTimeFormatter dataTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				//时间转字符串
				String dtStr = dataTime.format(LocalDateTime.now());
				String prevDay = dataTime.format(LocalDateTime.of(LocalDateTime.now().minusDays(1).toLocalDate(), LocalTime.MIN));
				String nowDay = dataTime.format(LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN));
				//查询智能分析预警表信息

				logger.info("==计算灌溉期用水量线程池开始工作==当前时间："+dtStr);
				int result=0;
				String msg = null;
				String regex = "^,*|,*$";
				try {
					//==逻辑业务
					LocalDateTime ldt = LocalDateTime.now();
					//ldt.getHour()==0
					if(ldt.getHour()==0){//代表晚上0点开始计算（前一天0点到现在0点的数据）
						//第一步:查询当前时间在起始时间与结束时间之间的参数
						List<ParamPumpingWell> parmsList = paramWellService.selectwatertime();
//						WarningRecordDetail warn = new WarningRecordDetail();
						StringBuilder stringBuilder = null;
						Map<Integer,String> pageInfo = new HashMap<>();
						if(parmsList.size()>0){
							for(ParamPumpingWell pam:parmsList){
								if(pam.getCommTime()==null){
									pageInfo.put(55,"在线天数异常");
									insertSelective(pageInfo,pam.getDeviceCode());
								}else{
									LocalDateTime time = pam.getCommTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
									long days = Duration.between(time,LocalDateTime.now()).toDays();
									if(days>= pam.getOnnetstate()){//不在线天数超标
										pageInfo.put(55,days+"天不在线");
										insertSelective(pageInfo,pam.getDeviceCode());
									}
								}
							/*	if(pam.getUseWater().compareTo(pam.getWaterlow())==-1){
									pageInfo.put(56,"上一天累计用水量为"+pam.getUseWater()+"m³，未达到最低用水量");
								}*///用水量异常
							}
						}else{
							logger.info("======未查询到【"+dtStr+"】时间区间内的预警参数，跳过定时任务...");
						}
						/*-------------------------------------------------------------------------------------------------------------------*/
						//高峰期用水量查询
						List<ParamPumpingWell> peckwaterlist = paramWellService.selectwaterpeckwater();
						if(peckwaterlist.size()>0){
							for (ParamPumpingWell peck : peckwaterlist) {
								//查询用水量数据
								//查询
								List<UseWaterDataOfDay> peaklist = useWaterService.selectpeakwater(peck.getWaterlow(),peck.getDeviceCode());
								BigDecimal daywater=new BigDecimal(0);
								boolean flag = true;
								if(peaklist.size()>0){
									for (int i = 0; i< peaklist.size(); i++) {
										UseWaterDataOfDay WaterOfDay = peaklist.get(i);
										if(i !=0 && daywater.compareTo(WaterOfDay.getUseWaterOfDay())==0){
											flag=false;
											break;
										}
										daywater=WaterOfDay.getUseWaterOfDay();
									}
								}else{
									pageInfo.put(56,"高峰期用水量异常");
									insertSelective(pageInfo,peck.getDeviceCode());
								}


								if(!flag){
									pageInfo.put(56,"高峰期用水量异常");
									insertSelective(pageInfo,peck.getDeviceCode());

								}
							}
						}else{
							logger.info("======未查询到【"+dtStr+"】时间区间内的预警参数，跳过定时任务...");
						}
					}

				} catch (Exception e) {
					logger.error("计算不在线天数、高峰期用水量线程报错==="+e.getMessage());
					e.printStackTrace();
				}
			}
		},5,22, TimeUnit.MINUTES);
	}

	/**
	 * 工业机井设备状态
	 */
	public void IndustrialSate(){
		scheduExec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.HOUR_OF_DAY,-1);
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Map<String,Object> pageInfo = new HashedMap();
				pageInfo.put("table","industrialdevice_current");
				List<IndustrialDevice> baseList = industrialdeviceService.selectByPrimaryKey(pageInfo);
				if(baseList.size()>0){
					for(IndustrialDevice ib:baseList){
						pageInfo.put("table","industrialdevice");
						pageInfo.put("startTime", fmt.format(calendar.getTime()));
						pageInfo.put("endTime", fmt.format(new Date()) );
						pageInfo.put("deviceport", ib.getDeviceport());
						List<IndustrialDevice> bases = industrialdeviceService.selectByPrimaryKey(pageInfo);
						int i = industrialdeviceService.updateByKeyNameState(bases.size()>0?"1":"0",ib.getDeviceport());
						logger.info("----工业机井【"+ib.getDevicename()+"】状态修改完毕,:"+(bases.size()>0?"在线":"离线"));
					}
				}
			}
		},1,5, TimeUnit.MINUTES);
	}
	/**
	 * 预警入库
	 * @param pageInfo
	 * @param deviceCode
	 * @return
	 */
	private void insertSelective(Map<Integer,String> pageInfo, String deviceCode){
		int mapKey = pageInfo.keySet().iterator().next();

		//对异常信息表进行查询
		List<WarningRecordDetail> warnAllList = warningRecordDetailService.warnAll();
		boolean flag = false;
		for(WarningRecordDetail record : warnAllList){

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(deviceCode.equals(record.getDeviceCode())&&mapKey==record.getWarnCode()
					&& sf.format(new Date()).equals(sf.format(record.getWarnTime()))){
				flag = true;
			}

		}
		if(!flag){
			WarningRecordDetail warn = new WarningRecordDetail();
			warn.setDeviceCode(deviceCode);
			warn.setWarnType(pageInfo.get(mapKey));
			warn.setWarnDetail(pageInfo.get(mapKey));
			warn.setWarnCode(mapKey);
			warn.setWarnTime(new Date());
			warn.setWarnState(0);
			int status = warningRecordDetailService.insertSelective(warn);
			if(status == 0){
				logger.info(deviceCode + "预警入库失败");
			}
		}


		return;
	}
	/**
	 * 数据进入日统计数据表
	 */
	public boolean enterDataOfDay(String deviceCode,String cardCode,BigDecimal water,Date date,int year,int month,int day){
		boolean vity = false;
		if(StringUtils.isNotBlank(deviceCode)&&StringUtils.isNotBlank(cardCode)&&year>0&&month>0&&day>0){
			UseWaterDataOfDay uwdd = useWaterService.findDataOfDay(deviceCode, cardCode, year, month, day);
			UseWaterDataOfDay useWaterDataOfDay = new UseWaterDataOfDay();
			if(uwdd==null){//需要插入(表示数据库没有这条数据)
				useWaterDataOfDay = entouryOfDay(deviceCode,cardCode,water,date,year,month,day,0);
				int i = useWaterService.addWaterDataOfDay(useWaterDataOfDay);
				vity = true;
			}else{//需要修改
				BigDecimal waterSum = uwdd.getUseWaterOfDay().add(water);//修改：将水量相加起来
				useWaterDataOfDay = entouryOfDay(deviceCode,cardCode,waterSum,date,year,month,day,uwdd.getId());
				int i = useWaterService.updateWaterDataOfDay(useWaterDataOfDay);
				vity = true;
			}
		}
		return vity;
	}

	/**
	 * 数据进入月统计数据表
	 */
	public boolean enterDataOfMonth(String deviceCode,String cardCode,BigDecimal water,Date date,int year,int month){
		boolean vity = false;
		if(StringUtils.isNotBlank(deviceCode)&&StringUtils.isNotBlank(cardCode)&&year>0&&month>0){
			UseWaterDataOfMonth uwdm = useWaterService.findDataOfMonth(deviceCode, cardCode, year, month);
			UseWaterDataOfMonth useWaterDataOfMonth = new UseWaterDataOfMonth();
			if(uwdm==null){//需要插入
				useWaterDataOfMonth = entouryOfMonth(deviceCode,cardCode,water,date,year,month,0);
				int i = useWaterService.addWaterDataOfMonth(useWaterDataOfMonth);
				vity = true;
			}else{//需要修改
				BigDecimal waterSum = uwdm.getUseWaterOfMonth().add(water);//修改：将水量相加起来
				useWaterDataOfMonth = entouryOfMonth(deviceCode,cardCode,waterSum,date,year,month,uwdm.getId());
				int i = useWaterService.updateWaterDataOfMonth(useWaterDataOfMonth);
				vity = true;
			}
		}
		return vity;
	}

	/**
	 * 数据进入年统计数据表
	 */
	public boolean enterDataOfYear(String deviceCode,String cardCode,BigDecimal water,Date date,int year){
		boolean vity = false;
		if(StringUtils.isNotBlank(deviceCode)&&StringUtils.isNotBlank(cardCode)&&year>0){
			UseWaterDataOfYear uwdy = useWaterService.findDataOfYear(deviceCode, cardCode, year);
			UseWaterDataOfYear useWaterDataOfYear = new UseWaterDataOfYear();
			if(uwdy==null){//需要插入
				useWaterDataOfYear = entouryOfYear(deviceCode,cardCode,water,date,year,0);
				int i = useWaterService.addWaterDataOfYear(useWaterDataOfYear);
				vity = true;
			}else{//需要修改
				BigDecimal waterSum = uwdy.getUseWaterOfYear().add(water);//修改：将水量相加起来
				useWaterDataOfYear = entouryOfYear(deviceCode,cardCode,waterSum,date,year,uwdy.getId());
				int i = useWaterService.updateWaterDataOfYear(useWaterDataOfYear);
				vity = true;
			}
		}
		BigDecimal sumWater = useWaterService.findSumWaterOfYear(deviceCode);
		if(sumWater!=null){
			BaseDeviceDynamicInfo bdi = baseDeviceDynamicInfoService.findDynamicInfo(deviceCode);
			if(bdi!=null){
				baseDeviceDynamicInfoService.updateDynamicWater(sumWater.setScale(2, BigDecimal.ROUND_HALF_UP), bdi.getDeviceId());
			}
		}
		return vity;
	}

	/**
	 * 生成日用水量统计表的实体
	 */
	public UseWaterDataOfDay entouryOfDay(String deviceCode,String cardCode,BigDecimal water,Date date,int year,int month,int day,int id){
		UseWaterDataOfDay useWaterDataOfDay = new UseWaterDataOfDay();
		try {
			if(id!=0){
				useWaterDataOfDay.setId(id);
			}
			useWaterDataOfDay.setDeviceCode(deviceCode);
			useWaterDataOfDay.setCardCode(cardCode);
			useWaterDataOfDay.setUseWaterOfDay(water);
			useWaterDataOfDay.setInYear(year);
			useWaterDataOfDay.setInMonth(month);
			useWaterDataOfDay.setInDay(day);
			useWaterDataOfDay.setAssTime(date);
			useWaterDataOfDay.setCreateTime(new Date());

		} catch (Exception e) {
			logger.info("--生成日报表实体出错：--"+e.getMessage());
			e.printStackTrace();
		}
		return useWaterDataOfDay;
	}
	/**
	 * 生成月用水量统计表的实体
	 */
	public UseWaterDataOfMonth entouryOfMonth(String deviceCode,String cardCode,BigDecimal water,Date date,int year,int month,int id){
		UseWaterDataOfMonth useWaterDataOfMonth = new UseWaterDataOfMonth();
		if(id!=0){
			useWaterDataOfMonth.setId(id);
		}
		useWaterDataOfMonth.setDeviceCode(deviceCode);
		useWaterDataOfMonth.setCardCode(cardCode);
		useWaterDataOfMonth.setUseWaterOfMonth(water);
		useWaterDataOfMonth.setInYear(year);
		useWaterDataOfMonth.setInMonth(month);
		useWaterDataOfMonth.setAssTime(date);
		useWaterDataOfMonth.setCreateTime(new Date());
		return useWaterDataOfMonth;
	}
	/**
	 * 生成年用水量统计表的实体
	 */
	public UseWaterDataOfYear entouryOfYear(String deviceCode,String cardCode,BigDecimal water,Date date,int year,int id){
		UseWaterDataOfYear useWaterDataOfYear = new UseWaterDataOfYear();
		if(id!=0){
			useWaterDataOfYear.setId(id);
		}
		useWaterDataOfYear.setDeviceCode(deviceCode);
		useWaterDataOfYear.setCardCode(cardCode);
		useWaterDataOfYear.setUseWaterOfYear(water);
		useWaterDataOfYear.setInYear(year);
		useWaterDataOfYear.setAssTime(date);
		useWaterDataOfYear.setCreateTime(new Date());
		return useWaterDataOfYear;
	}


	public static void main(String[] args) {
		// AT指令信息
		System.out.println("中心地址是：===== "+ConfUtils.getInstance().getREMOTE_URL());
		String strToACSII = "";
		// 远程操作请求体
		String urlBodyStr = "";
		// 操作类型 协议文档获取 5010 为参数设置类型
		String operType = "831001";
		// DI0存储间隔 请求结果
		String re_DI0CCJG = "";
		strToACSII = "llll";
		strToACSII = "llll";
		// 远程请求WebServer请求体
		urlBodyStr = JSONPacketUtils.JSONRemotePacket(new RemoteController().getToken(),"0124080802", operType, strToACSII);
		// 设参请求体 编码处理
		logger.info("远程设参-DI0存储间隔-请求信息:" + urlBodyStr);
		String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
		String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
		try {
			String result_DI0CCJG = httpUtils.get(requestURL);
			re_DI0CCJG = JSONObject.fromObject(result_DI0CCJG).get("message").toString();
			logger.info("远程设参-DI0存储间隔-执行结果:" + re_DI0CCJG);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("远程设参-DI0存储间隔-执行异常:" + e.getMessage());
		}
		if( re_DI0CCJG.equals("ok")){
			System.out.println("远程设置参数成功！！！");
		}else{
			System.out.println("远程设置参数失败！！！");
		}
		System.out.println("升级结束。。。");
	}

}
