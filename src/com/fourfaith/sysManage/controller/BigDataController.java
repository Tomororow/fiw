package com.fourfaith.sysManage.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fourfaith.statisticAnalysis.model.IndustrialDevice;
import com.fourfaith.statisticAnalysis.service.IndustrialdeviceService;
import com.fourfaith.sysManage.dao.WaterindexMapper;
import com.fourfaith.sysManage.model.Waterindex;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fourfaith.alarmManage.model.WarningRecordDetail;
import com.fourfaith.alarmManage.service.WarningRecordDetailService;
import com.fourfaith.statisticAnalysis.model.RptUseWaterDetail;
import com.fourfaith.statisticAnalysis.model.UseWaterDataOfDay;
import com.fourfaith.statisticAnalysis.model.useWaterOfMonthByExcelVO;
import com.fourfaith.statisticAnalysis.service.UseWaterService;
import com.fourfaith.sysManage.model.BaseDeviceExpandInfo;
import com.fourfaith.sysManage.model.BaseDeviceInfo;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.BaseDeviceExpandInfoService;
import com.fourfaith.sysManage.service.BaseDeviceInfoService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.PersonDateUtils;
import com.fourfaith.utils.PropertiesUtils;

@Controller
@RequestMapping(value ="/big_Data")
public class BigDataController {

	@Autowired
	SysWaterAreaService sysWaterAreaService;
	
	@Autowired
	BaseDeviceInfoService baseDeviceInfoService;
	
	@Autowired
	BaseDeviceExpandInfoService baseDeviceExpandInfoService;
	
	@Autowired
	UseWaterService useWaterService;
	
	@Autowired
	private WarningRecordDetailService warningRecordDetailService;

	@Autowired
	private WaterindexMapper waterindexMapper;

	@Autowired
	private IndustrialdeviceService industrialdeviceService;
	
	
	/**
	 * 获取水管区域的集合信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/water_List")
	@ResponseBody
	public String waterAreaList(HttpServletRequest request){
		HttpSession session = request.getSession();
		Map<String,Object> pageInfo = new HashMap<>();
		String waterAreaList = (String)session.getAttribute("waterAreaList");
		try {
			if(!StringUtils.isNotBlank(waterAreaList)){
				pageInfo.put("waterLevel","1");
				List<SysWaterArea> list = sysWaterAreaService.selectWaterAreaList(pageInfo);
				if(list.size()>0){
					waterAreaList = JSONObject.toJSONString(list);
					session.setAttribute("waterAreaList", waterAreaList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return waterAreaList;
	}

	/**
	 * 用水量指标的读取
	 * @return
	 */
	@RequestMapping(value="/proper_Read")
	@ResponseBody
	public String properRead(){
		List<Waterindex> list = waterindexMapper.selectWaterAll(null,"left");
		return JSONObject.toJSONString(list);
	}
	
	/**
	 * 配置文件的写入
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/proper_Write")
	@ResponseBody
	public String properWrite(String addForm) throws IOException{
		if(StringUtils.isNotBlank(addForm)){
			 Map<String,Object> maps = (Map)JSON.parse(addForm);
			 for(Map.Entry<String, Object> entry : maps.entrySet()){
				    String mapKey = entry.getKey();
				    String mapValue = (String) entry.getValue();
				    new PropertiesUtils().setPara(mapKey, mapValue);
				}
		}
		return "success";
	}

	/**
	 * 获取当前年份的取水总量
	 * @param request
	 * @return
	 */
	private BigDecimal SumWater = new BigDecimal("0");
	@RequestMapping(value="/water_red_line")
	@ResponseBody
	public String waterRedLine(HttpServletRequest request,String waterName,Integer sign){
		HttpSession session = request.getSession();
		Map<String,Object> pageInfo = new HashMap<>();
		Map<String,Object> pageData = new HashMap<>();
		Map<String,Object> InfoData = new HashMap<>();
		String [] strList = new String[]{"总水量,Sum","灌溉,Farming","绿化,Virest","生活,Life","工业,Industry"};
		strList = (String[]) (sign==null?strList:new String[]{"总水量,Sum"});
		List<String> baseList = new ArrayList<>();
		SumWater = new BigDecimal("0");
		BigDecimal Indty = new BigDecimal("0");
		IndustrialDevice ind = industrialdeviceService.findWaterTotal(null);
		Indty = ind.getPositry()==null?new BigDecimal("0"):ind.getPositry();
		try {
			if(StringUtils.isNotBlank(waterName)){
				waterList.clear();
				waterArea(waterName);
				pageInfo.put("list", waterList);
				for(String str:strList){
					baseList.clear();
					switch(str.split(",")[0]) {
					case "总水量":
						baseList = baseDeviceInfoService.findSelectWaterString(pageInfo);
						break;
					case "灌溉":
						pageInfo.put("welluse", "灌溉");
						baseList = baseDeviceInfoService.findSelectWaterString(pageInfo);
						break;
					case "绿化":
						pageInfo.put("welluse", "绿化");
						baseList = baseDeviceInfoService.findSelectWaterString(pageInfo);			
						break;
					case "生活":
						pageInfo.put("welluse", "生活");
						baseList = baseDeviceInfoService.findSelectWaterString(pageInfo);
						break;
					case "工业":
						pageInfo.put("welluse", "工业");
						baseList = baseDeviceInfoService.findSelectWaterString(pageInfo);
						break;
					default:
						break;
					}
					InfoData.put("waterData", "UseWaterDataOfYear");InfoData.put("deviceCodeList", baseList);InfoData.put("year",PersonDateUtils.getCurrentYear());
					RptUseWaterDetail ruwd = new RptUseWaterDetail();
					if(baseList.size()>0){
						ruwd = useWaterService.findWaterSumDMYList(InfoData);
						if(ruwd==null){
							ruwd = new RptUseWaterDetail();
							ruwd.setUseWater(new BigDecimal("0"));
						}
					}else{
						ruwd.setUseWater(new BigDecimal("0"));
					}
					SumWater = str.split(",")[0].equals("总水量")?ruwd.getUseWater().setScale(2, BigDecimal.ROUND_HALF_UP):new BigDecimal(0);
					BigDecimal vituy = ruwd.getUseWater()==null?new BigDecimal("0"):ruwd.getUseWater();
					if(str.split(",")[1].equals("Industry")){
						vituy = vituy.add(Indty);
					}
					pageData.put("water"+str.split(",")[1],vituy);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(pageData);
	}
	
	/**
	 * 获取当前年份各个水管所的配水量
	 */
	@RequestMapping(value="/plan_water")
	@ResponseBody
	public String planWaterSum(HttpServletRequest request){
		Map<String,Object> params = new HashMap<>();
		Map<String,Object> pageInfo = new HashMap<>();
		List<Map<String,Object>> lists = new ArrayList<>();
		String waterS = waterAreaList(request);//获取水管区集合
		if(StringUtils.isNotBlank(waterS)){
			List<SysWaterArea> allList = sysWaterAreaService.selectWaterAreaList(null);
			List<SysWaterArea> sysList = JSONObject.parseArray(waterS, SysWaterArea.class);
			if(sysList.size()>0){
				BigDecimal maxWater =  new BigDecimal("0");
				for(SysWaterArea sa:sysList){
					pageInfo = new HashMap<>();
					waterList.clear();
					waterList.add(sa.getId());
					waterAreaList(sa.getId(),allList);
					params.put("waterList",waterList);params.put("year",PersonDateUtils.getCurrentYear());
					BigDecimal sumWater = new BigDecimal("0");
					List<BaseDeviceExpandInfo> aseList = baseDeviceExpandInfoService.findSumWater(params);
					for(BaseDeviceExpandInfo be:aseList){
						sumWater = new BigDecimal("0");
						if(be.getWellUse()!=null){
							switch (be.getWellUse()) {
							case "灌溉":
								sumWater = sumWater.add(be.getsJArea()==null?new BigDecimal("0"):be.getsJArea().multiply(be.getDistWater()));
								break;
							case "绿化":
								sumWater = sumWater.add(be.getsJArea()==null?new BigDecimal("0"):be.getsJArea().multiply(be.getDistWater()));
								break;
							case "生活":
								sumWater = sumWater.add(new BigDecimal(be.getsJSupplyWaterPeople()==null?0:be.getsJSupplyWaterPeople()).multiply(be.getDistWater()));
								break;
							case "工业":
								sumWater = new BigDecimal( be.getIsIndustryProcedure()==null?0:be.getIsIndustryProcedure());
								break;
							default:
								break;
							}
						}
					}
					maxWater = maxWater.add(sumWater);
					pageInfo.put("waterAreaName", sa.getWaterAreaName());
					pageInfo.put("waterSum", sumWater.setScale(2, BigDecimal.ROUND_HALF_UP));
					lists.add(pageInfo);
				}
				params.clear();
				/**
				 * 禁止循环引用
				 */
				
				params.put("maxWater", maxWater);
				params.put("list",stoQuote(lists));
			}
		}
		return JSON.toJSONString(params);
	}
	
	/**
	 * 已用水量统计
	 * @return
	 */
	@RequestMapping(value="/use_water")
	@ResponseBody
	public String useWaterSum(HttpServletRequest request,String waterName){
		Map<String,Object> pageInfo = new HashMap<>();
		Map<String,Object> params = new HashMap<>();
		Map<String,Object> pageData = new HashMap<>();
		List<String> xAisData = new ArrayList<>();
		List<String> planData = new ArrayList<>();
		List<String> useWaterData = new ArrayList<>();
		List<String> baseList = new ArrayList<>();
		BigDecimal dayUseWater = new BigDecimal("0");
		Calendar cal = Calendar.getInstance();
		try {
			if(StringUtils.isNotBlank(waterName)){
				waterList.clear();
				waterArea(waterName);
				if(waterList.size()>0){
					//日用水量统计
					pageInfo.put("waterAreaIdsList", waterList);
					pageInfo.put("year", PersonDateUtils.getCurrentYear());
					pageInfo.put("month",PersonDateUtils.getCurrentMouth());
					pageInfo.put("day", PersonDateUtils.getCurrentDay());
					dayUseWater = useWaterService.getUseWaterStatistics(pageInfo);//得到日用水量
					dayUseWater = dayUseWater==null?new BigDecimal("0"):dayUseWater;
					//月用水量统计
					List<RptUseWaterDetail> uwList =  useWaterService.getListOfMonthBigData(pageInfo);
					params.put("waterList",waterList);params.put("year",PersonDateUtils.getCurrentYear());
					BigDecimal monthUseWater = new BigDecimal("0");
					BigDecimal sumWater = new BigDecimal("0");
					BigDecimal sumYearWater = new BigDecimal("0");
					BigDecimal sumMonthWater = new BigDecimal("0");
					List<BaseDeviceExpandInfo> aseList = baseDeviceExpandInfoService.findSumWater(params);
					for(int i=1;i<13;i++){
						if(uwList.size()>0){
							for(RptUseWaterDetail uw:uwList){
								if(uw!=null){
									BigDecimal useData = uw.getInMonth()==i?uw.getUseWater():new BigDecimal("0");
									monthUseWater = useData;//得到每月总用水量
									cal.setTime(new Date());
									if(cal.get(Calendar.MONTH)+1==i){//得到月总用水量
										sumMonthWater = uw.getUseWater();
									}
									if(i==1){
										sumYearWater = sumYearWater.add(uw.getUseWater());//得到年总用水量
									}
								}
							}
						}
						if(aseList.size()>0){
							for(BaseDeviceExpandInfo be:aseList){
								cal.setTime(be.getMakeTime());
								sumWater = new BigDecimal("0");//得到每月的总配水量
								if(cal.get(Calendar.MONTH)+1==i){
									if(be.getWellUse()!=null){
										switch (be.getWellUse()) {
											case "灌溉":
												sumWater = sumWater.add(be.getsJArea()==null?new BigDecimal("0"):be.getsJArea().multiply(be.getDistWater()));
												break;
											case "绿化":
												sumWater = sumWater.add(be.getsJArea()==null?new BigDecimal("0"):be.getsJArea().multiply(be.getDistWater()));
												break;
											case "生活":
												sumWater = sumWater.add(new BigDecimal(be.getsJSupplyWaterPeople()==null?0:be.getsJSupplyWaterPeople()).multiply(be.getDistWater()));
												break;
											case "工业":
												sumWater = new BigDecimal( be.getIsIndustryProcedure()==null?0:be.getIsIndustryProcedure());
												break;
											default:
												break;
										}
									}
								}
							}
						}
						useWaterData.add(monthUseWater.toString());
						planData.add(""+sumWater);
						xAisData.add(i+"月");
					}
					waterRedLine(request,waterName,0);
					pageData.put("sumWater",SumWater);
					pageData.put("dayUseWater", dayUseWater);
					pageData.put("sumMonthWater", sumMonthWater);
					pageData.put("sumYearWater", sumYearWater);
					pageData.put("xAisData", stoQuote(xAisData));
					pageData.put("planData", stoQuote(planData));
					pageData.put("useWaterData", stoQuote(useWaterData));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(pageData);
	}
	
	/**
	 * 引水量曲线图
	 * @return
	 */
	@RequestMapping(value="/use_water_line")
	@ResponseBody
	public String waterSumLine(HttpServletRequest request,String waterName,int type){
		Map<String,Object> pageInfo = new HashMap<>();
		Map<String,Object> pageData = new HashMap<>();
		try {
			SimpleDateFormat fmty = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat fmtM = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat fmt = new SimpleDateFormat("HH");
			Calendar calendar = Calendar.getInstance();
			int month = calendar.getActualMaximum(Calendar.DATE);
			List<String> dayXsiaData = new ArrayList<>();
			List<String> dayYsiaData = new ArrayList<>();
			List<String> monthXsiaData = new ArrayList<>();
			List<String> monthYsiaCurData = new ArrayList<>();
			List<String> monthYsiaBeforeData = new ArrayList<>();
			List<String> CurXsiaData = new ArrayList<>();
			List<String> CurYsiaData = new ArrayList<>();
			List<String> BeforeYsiaData = new ArrayList<>();
			if(StringUtils.isNotBlank(waterName)){
				waterList.clear();
				waterArea(waterName);
				if(waterList.size()>0){
					pageInfo.put("waterAreaIdsList", waterList);
					pageInfo.put("year", PersonDateUtils.getCurrentYear());
					pageInfo.put("month",PersonDateUtils.getCurrentMouth());
					pageInfo.put("day", PersonDateUtils.getCurrentDay());
					if(type==0){
						List<UseWaterDataOfDay> DayUseList = useWaterService.getWaterDataInfo(pageInfo);//查询当前日的
						for(int d=1;d<25;d++){//日引水曲线图
							BigDecimal dayUseWater = new BigDecimal("0");
							if(DayUseList.size()>0){
								for(UseWaterDataOfDay uw:DayUseList){
									if(uw.getAssTime()!=null){
										int hour = Integer.parseInt(fmt.format(uw.getCreateTime()));
										if((d-1)<hour&&hour<=d){
											dayUseWater = dayUseWater.add(uw.getUseWaterOfDay());
										}
									}
								}
							}
							dayYsiaData.add(dayUseWater.toString());
							dayXsiaData.add(d-1+":00-"+d+":00");
						}
					}else{
						pageInfo.put("day",null);
						List<UseWaterDataOfDay> DayMouthCurUseList = useWaterService.getWaterDayBigData(pageInfo);//查询当前月份的
						pageInfo.put("month",PersonDateUtils.getCurrentMouth()-1);
						List<UseWaterDataOfDay> DayMouthBeforeUseList = useWaterService.getWaterDayBigData(pageInfo);//查询当前月份的
						for(int m=1;m<month+1;m++){//月引水曲线图(每天的水量)
							BigDecimal dayCurMouth = new BigDecimal("0");
							BigDecimal dayBeforeMouth = new BigDecimal("0");
							if(DayMouthCurUseList.size()>0){
								for(int uy=0;uy<DayMouthCurUseList.size();uy++){
									if(Integer.valueOf(DayMouthCurUseList.get(uy).getInDay())==m){
										dayCurMouth = dayCurMouth.add(DayMouthCurUseList.get(uy).getUseWater());
									}
								}
							}
							if(DayMouthBeforeUseList.size()>0){
								for(int uy=0;uy<DayMouthBeforeUseList.size();uy++){
									if(Integer.valueOf(DayMouthBeforeUseList.get(uy).getInDay())==m){
										dayBeforeMouth = dayBeforeMouth.add(DayMouthBeforeUseList.get(uy).getUseWater());
									}
								}
							}
							monthXsiaData.add(PersonDateUtils.getCurrentMouth()+"."+m);
							monthYsiaCurData.add(dayCurMouth.toString());
							monthYsiaBeforeData.add(dayCurMouth.toString());
						}
					}
					pageInfo.put("day",null);
					List<RptUseWaterDetail> CurUseList = useWaterService.getListOfMonthBigData(pageInfo);//查询当前月份的
					pageInfo.put("year", PersonDateUtils.getCurrentYear()-1);
					List<RptUseWaterDetail> BeforeUseList = useWaterService.getListOfMonthBigData(pageInfo);//查询当前月份的
					for(int y=1;y<13;y++){//月引水曲线图
						BigDecimal CurMonthWater = new BigDecimal("0");
						BigDecimal BeforeMonthWater = new BigDecimal("0");
						if(CurUseList.size()>0){
							for(int t =0;t<CurUseList.size();t++){//今年
								if(CurUseList.get(t).getInMonth()==y){
									CurMonthWater = CurMonthWater.add(CurUseList.get(t).getUseWater());
								}
							}
						}
						CurXsiaData.add(y+"月");
						CurYsiaData.add(CurMonthWater.toString());
						if(BeforeUseList.size()>0){
							for(int o =0;o<BeforeUseList.size();o++){//去年
								if(BeforeUseList.get(o).getInMonth()==y){
									BeforeMonthWater = BeforeMonthWater.add(BeforeUseList.get(o).getUseWater());
								}
							}
						}
						BeforeYsiaData.add(BeforeMonthWater.toString());
					}
				}
				pageData.put("nowDay", fmty.format(new Date()));
				pageData.put("beforeMonth", PersonDateUtils.getCurrentYear()-1+"-"+PersonDateUtils.getCurrentMouth());
				pageData.put("curMonth", fmtM.format(new Date()));
				pageData.put("dayXsiaData", dayXsiaData);
				pageData.put("dayYsiaData", dayYsiaData);
				pageData.put("monthXsiaData", monthXsiaData);
				pageData.put("monthYsiaCurData", monthYsiaCurData);
				pageData.put("monthYsiaBeforeData", monthYsiaBeforeData);
				pageData.put("year", PersonDateUtils.getCurrentYear());
				pageData.put("CurXsiaData", CurXsiaData);
				pageData.put("CurYsiaData", CurYsiaData);
				pageData.put("BeforeYsiaData", BeforeYsiaData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(pageData);
	}
	
	/**
	 * 设备基本信息
	 * @return
	 */
	@RequestMapping(value="/device_List")
	@ResponseBody
	public String deviceList(HttpServletRequest request,String waterName){
		HttpSession session = request.getSession();
		Map<String,Object> pageInfo = new HashMap<>();
		Map<String,Object> pageData = new HashMap<>();
		List<BaseDeviceInfo> list = new ArrayList<>();
		List<Map<String,Object>> bigList = new ArrayList<>();
		try {
				if(StringUtils.isNotBlank(waterName)){
					waterList.clear();
					waterArea(waterName);
				}
				list = baseDeviceInfoService.findDevicBigData(waterList);
				List<String> devList = new ArrayList<>();
				if(list.size()>0){
					for(BaseDeviceInfo bs:list){
						pageInfo = new HashMap<>();
						devList = new ArrayList<>();
						if(bs.getLo()!=null && bs.getLt()!=null){
							devList.add(bs.getLo().toString());
							devList.add(bs.getLt().toString());
							devList.add(bs.getNetStatek()==1?"在线":"离线");
						}
						pageInfo.put("name",bs.getDeviceName());
						pageInfo.put("value", devList);
						bigList.add(pageInfo);
					}
				}
				pageData.put("deviceList", list);
				pageData.put("bigList", bigList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(pageData);
	}

	/**
	 * 设备基本信息
	 * @return
	 */
	@RequestMapping(value="/device_List_Indety")
	@ResponseBody
	public String deviceListIndety(HttpServletRequest request){
		Map<String,Object> pageInfo = new HashMap<>();
		List<IndustrialDevice> list = new ArrayList<>();
		try {
			pageInfo.put("table","industrialdevice_current");
			list = industrialdeviceService.selectByPrimaryKey(pageInfo);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return JSONObject.toJSONString(list);
	}
	
	/**
	 * 预警
	 * @param waterName
	 * @return
	 */
	@RequestMapping(value="/warn_list")
	@ResponseBody
	public String selectWarnList(String waterName){
		Map<String,Object> pageInfo = new HashMap<>();
		List<WarningRecordDetail> warnList = new ArrayList<>();
		try {
			if(StringUtils.isNotBlank(waterName)){
				waterList.clear();
				waterArea(waterName);
			}
			pageInfo.put("waterAreaList", waterList);
			warnList = warningRecordDetailService.selectWarnList(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(warnList);
	}
	
	/**
	 * 递归获取当前的水管区域子集Id
	 * @return
	 */
	public void waterArea(String waterName){
		List<String> waterAreaList = new ArrayList<>();
		Map<String,Object> pageInfo = new HashMap<>();
		try {
			pageInfo.put("waterName", waterName);
			List<SysWaterArea> waterLie = sysWaterAreaService.selectWaterAreaList(pageInfo);
			List<SysWaterArea> allList = sysWaterAreaService.selectWaterAreaList(null);
			waterList.clear();
			waterAreaList(waterLie.get(0).getId(),allList);
			waterList.add(waterLie.get(0).getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 递归获取当前的水管区域子集Id
	 * @return
	 */
	private List<String> waterList = new ArrayList<>();//存储水管区集合的方法
	public void waterAreaList(String id,List<SysWaterArea> allList){
		try {
			if(StringUtils.isNotBlank(id)){
				if(allList.size()>0){
					for(SysWaterArea all:allList){
						if(all!=null){
							if(all.getParentWaterAreaId()!=null){
								if(all.getParentWaterAreaId().equals(id)){
									waterList.add(all.getId());
									/*if(!waterList.contains(id)){
										waterList.add(id);
									}*/
									waterAreaList(all.getId(),allList);
								}
							}
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *	禁止引用（避免出现$ref等现象）
	 * @param lists
	 * @return
	 */
	public JSONArray stoQuote(List<?> lists){
		JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(lists, SerializerFeature.DisableCircularReferenceDetect));
		return jsonArray;
	}
}








