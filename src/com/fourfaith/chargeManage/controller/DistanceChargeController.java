package com.fourfaith.chargeManage.controller;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Object;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import jdk.nashorn.internal.ir.annotations.Reference;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.record.chart.BeginRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fourfaith.basicInformation.model.SysWellUse;
import com.fourfaith.basicInformation.service.SysWellUseService;
import com.fourfaith.chargeManage.model.BaseWaterCharge;
import com.fourfaith.chargeManage.service.BaseWaterChargeService;
import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.siteManage.service.SysAreaService;
import com.fourfaith.statisticAnalysis.model.RptBaseDistWaterDetail;
import com.fourfaith.statisticAnalysis.model.RptChargeDetail;
import com.fourfaith.statisticAnalysis.service.DistWaterPlanInfoService;
import com.fourfaith.statisticAnalysis.service.RptChargeDetailService;
import com.fourfaith.sysManage.model.BaseDeviceExpandInfo;
import com.fourfaith.statisticAnalysis.model.BaseCardInfo;
import com.fourfaith.statisticAnalysis.service.BaseCardInfoService;
import com.fourfaith.sysManage.model.BaseDeviceInfo;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.model.WaterTree;
import com.fourfaith.sysManage.service.BaseDeviceExpandInfoService;
import com.fourfaith.sysManage.service.BaseDeviceInfoService;
import com.fourfaith.sysManage.service.BjextendinfoService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AreaRecursionUtil;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.WaterAreaRecursionUtil;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlan;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlanDevice;
import com.fourfaith.waterRightManage.service.BaseDistWaterPlanDeviceService;
import com.fourfaith.waterRightManage.service.BaseDistWaterPlanService;

/**
 * ClassName: RemoteTopUpController
 * @类描述: 远程充值管理
 * @author 刘海年
 * @date 2018年8月14日-上午11:21:37
 */

@Controller
@RequestMapping(value="/distance_charge")
public class DistanceChargeController {
	
	private static final  Logger  logger= LoggerFactory.getLogger(BaseDeviceInfo.class);
	
	private static final String INDEX = "/page/chargeManage/distanceChargeIndex/distanceChargeIndex";
	
	private static final String INDEXLIST = "/page/chargeManage/distanceChargeIndex/distanceChargeList";
	
	private static final String DISTANPAY = "/page/chargeManage/distanceChargeIndex/distanPany";
	
	@Autowired
	private BjextendinfoService  bjextendinfoService;
	
	@Autowired
	private BaseDeviceInfoService baseDeviceInfoService;

	@Autowired
	private SysWellUseService sysWellUseService;
	
	@Autowired
	private BaseDistWaterPlanDeviceService baseDistWaterPlanDeviceService;

	@Autowired
	private SysWaterAreaService  sysWaterAreaService;
	
	
	@Autowired
	private BaseCardInfoService baseCardInfoService;
	
	@Autowired
	private SysAreaService sysAreaService;
	
	@Autowired
	private BaseDistWaterPlanService baseDistWaterPlanService;
	
	@Autowired
	private BaseWaterChargeService baseWaterChargeService;
	
	
	@Autowired
	private DistWaterPlanInfoService distWaterPlanInfoService;
	
	@Autowired
	private BaseDeviceExpandInfoService baseDeviceExpandInfoService;
	
	@Autowired
	private RptChargeDetailService rptChargeDetailService;
	
	private SysUser login_user;
	
	private List<String> waterAreaId = new ArrayList<>();
	/*@RequestMapping("/index")
	public String index(){
		
		return INDEX;
	}*/
	//充值页面查询方法
	@RequestMapping("/test_all")
	@ResponseBody
	public String testAll(String num,String price,int type){
		String messsge = null;
		try {
			if (type == 0) {
				Pattern p = Pattern.compile("\\d+");
				Matcher m = p.matcher(num);
				while (m.find()) {
					num = m.group(0);
				}
				
			} else {

			}
			logger.info("");
			messsge = "success";
		} catch (Exception e) {
			messsge = "error";
			logger.error("",e);
		}
		
		return messsge;
	}
/*	树结构
	@RequestMapping("/test_tree")
	@ResponseBody
	public String testTree(HttpServletRequest request){
		login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		sysAreaService.waterTree(2);
		List<WaterTree> list = sysAreaService.treeList();
		List<WaterTree> listS =treeList(login_user.getWaterAreaId());
		for(WaterTree tcs:listS){
			SysWaterArea sysWaterArea = sysWaterAreaService.selectWaterLevel(tcs.getId());
			if(sysWaterArea.getWaterAreaLevel()==2){
				tcs.setChildren(sysAreaService.recursionArea(tcs.getId(),sysAreaService.waterTree(1)));
			}else{
				tcs.setChildren(sysAreaService.recursion(tcs.getId(),list));
			}
			
		}
		return JSONObject.toJSONString(listS);
		
	}
	public List<WaterTree> treeList(String id) {
		SysWaterArea swa = sysWaterAreaService.findFirstWaterAreaLevelById(id);
		List<WaterTree> menuList = new ArrayList<>();
			WaterTree tmc = new WaterTree();
				tmc.setName(swa.getWaterAreaName());
				tmc.setId(swa.getId());
				tmc.setDeviceCode(swa.getWaterAreaCode());
				tmc.setAreaLevel(swa.getWaterAreaLevel());
				tmc.setParentId(swa.getParentWaterAreaId());
				menuList.add(tmc);
		return menuList;
	}
	//Element水管区树结构查询=====================================================================================
	
	@RequestMapping("element_waterTree")
	@ResponseBody
	public String elementWaterTree(HttpServletRequest request){
		login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		List<WaterTree> list = sysAreaService.treeList();
		List<WaterTree> listS =treeList(login_user.getWaterAreaId());
		for(WaterTree tcs:listS){
			tcs.setChildren(sysAreaService.recursionFour(tcs.getId(),list));
		}
		return JSONObject.toJSONString(listS);
	}*/
	
	/**
	 * 充值页面基础查询参数
	 * @param request
	 * @return
	 */
	@RequestMapping("/distanceIndex")
	public ModelAndView distanceIndex(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(INDEX);
		Map<String,Object> params = new HashMap<String,Object>();
		List<SysWellUse> list = sysWellUseService.getList(params);
		mav.addObject("wellList", list);
		return mav;
	}
	
	/**
	 * 充值页面列表数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/distanceDataList", method = RequestMethod.POST)
	public ModelAndView distanceDataList(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(INDEXLIST);
		Map<String,Object> params = new HashMap<String,Object>();
		String deviceName = request.getParameter("deviceName");
		String deviceCode = request.getParameter("deviceCode");
		String pageNo = request.getParameter("pageNo");
		pageNo = pageNo==null?"1":pageNo;
		String areaId = request.getParameter("nodeId");
		String deviceWellUse = request.getParameter("deviceWellUse");
		
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		int areaType = 1;//登录用户操作方式;0:水管区域,1:行政区域
		if(login_user!=null){
			areaType = login_user.getAreaWay();
		}
		List<String> waterAreaIdsList = new ArrayList<String>();
		List<String> areaIdsList = new ArrayList<String>();
		if(!StringUtils.isNotBlank(areaId)&&login_user!=null){
			areaId = areaType == 1?login_user.getAreaId():login_user.getWaterAreaId();
			if(areaType==0){
				//水管区域
				List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
				if(StringUtils.isNotBlank(areaId)){
					List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, areaId);
					for(SysWaterArea swa : sysWaterAreaList) {
						waterAreaIdsList.add(swa.getId());
					}
				}
			}
			if(areaType==1){
				//行政区域
				List<SysArea> areaList = sysAreaService.seelectWater();
				if(StringUtils.isNotBlank(areaId)){
					List<SysArea> sysAreaList = AreaRecursionUtil.getCurrAndChildAreaList(areaList, areaId);
					for(SysArea swa : sysAreaList) {
						areaIdsList.add(swa.getId());
					}
				}
			}
		}else{
			if(areaType==0){
				waterAreaIdsList = Arrays.asList(areaId.split(","));
			}else{
				areaIdsList = Arrays.asList(areaId.split(","));
			}
		}
		params.put("areaIdList",areaIdsList);
		params.put("waterAreaIdsList",waterAreaIdsList);
		params.put("deviceName",deviceName);
		params.put("deviceCode",deviceCode);
		params.put("deviceWellUse",deviceWellUse);
	    int count = 0;
		List<BaseDeviceInfo> baseDeviceInfoList = new ArrayList<BaseDeviceInfo>();
	    count = baseDeviceInfoService.getCountOfBase(params);
	    PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
	    params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		baseDeviceInfoList = baseDeviceInfoService.getListOfBase(params);
		mav.addObject("deviceCode", deviceCode);
	    mav.addObject("deviceName", deviceName);
		mav.addObject("distanceList", baseDeviceInfoList);
		mav.addObject("count",count);
		mav.addObject("pagingBean", pagingBean);
		return mav;
	}
	
	/**
	 * 单个井充值方法
	 * @param request
	 * @param id
	 * @param deviceCode
	 * @param netState
	 * @param pumpState
	 * @return
	 */
	@RequestMapping("/distanPay")
	public ModelAndView distanPay(HttpServletRequest request,String id,String deviceCode,int netState,int pumpState,String wellUse,BigDecimal sJArea,Integer sJSupplyWaterPeople,BigDecimal industryApprovedWater){
		ModelAndView mav = new ModelAndView(DISTANPAY);
		List<BaseCardInfo> list = baseCardInfoService.selectByDeviceIds(id);
		List<RptBaseDistWaterDetail> allList = new ArrayList<>();//存储年份List
		List<Integer> yearList = new ArrayList<>();//存储年份List
		List<RptBaseDistWaterDetail> rptList = distWaterPlanInfoService.selectBasePlayId(id);
		for(RptBaseDistWaterDetail rdw:rptList){
			if(!yearList.contains(rdw.getDistYear())){
				yearList.add(rdw.getDistYear());
				rdw.setChildren(resource(rdw.getId(),rdw.getDistYear(),deviceCode,rptList,wellUse,sJArea,sJSupplyWaterPeople,industryApprovedWater));
				allList.add(rdw);
			}
		}
		mav.addObject("cardList", list);
		mav.addObject("deviceCode", deviceCode);
		mav.addObject("netState", netState);
		mav.addObject("pumpState", pumpState);
		mav.addObject("yearList", yearList);
		mav.addObject("allList", JSONObject.toJSONString(allList));
		return mav;
	}
	
	/**
	 * 配水计划转换方法
	 * @return
	 */
	public List<RptBaseDistWaterDetail> resource(String id,int year,String deviceCode,List<RptBaseDistWaterDetail> meuList,String wellUse,BigDecimal sJArea,Integer sJSupplyWaterPeople,BigDecimal industryApprovedWater){
		List<RptBaseDistWaterDetail> menuList = new ArrayList<>();
		if(meuList.size()>0){
			for(RptBaseDistWaterDetail rdw:meuList){
				RptBaseDistWaterDetail rbd = new RptBaseDistWaterDetail();
				if(rdw.getDistYear() == year){
					rbd.setId(id);
					rbd.setDistRound(rdw.getDistRound());
					rbd.setDistPrice(rdw.getDistPrice());
					rbd.setDistWater(rdw.getDistWater());
					DecimalFormat df = new DecimalFormat("0.00");
					BigDecimal sumWaterAmout = new BigDecimal("0");
					switch (wellUse) {
					case "灌溉":
						sumWaterAmout = sJArea.multiply(rdw.getDistWater()==null?new BigDecimal('0'):rdw.getDistWater());
						break;
					case "绿化":
						sumWaterAmout = sJArea.multiply(rdw.getDistWater()==null?new BigDecimal('0'):rdw.getDistWater());
						break;
					case "生活":
						sumWaterAmout = new BigDecimal(sJSupplyWaterPeople==null?0:sJSupplyWaterPeople).multiply(rdw.getDistWater()==null?new BigDecimal('0'):rdw.getDistWater());
						break;
					case "工业":
						sumWaterAmout = industryApprovedWater;
						break;
					}
					rbd.setSumWaterAmout(df.format(sumWaterAmout));//总配水量
					String waterXl = rptChargeDetailService.getCommonWater(deviceCode, ""+year, ""+rdw.getDistRound());//该井已充值水量
					waterXl = !StringUtils.isNotBlank(waterXl)?"0":waterXl;
					rbd.setWaterAmout(waterXl);
					rbd.setMayAmout(df.format(sumWaterAmout.subtract(new BigDecimal(waterXl))));
					menuList.add(rbd);
				}
			}
		}
		return menuList;
	}
	
	/**
	 * 远程充值数据入库
	 * @param vityAret
	 * @param id
	 * @param request
	 */
	@RequestMapping("/addRptCharge")
	@ResponseBody
	public void addRptCharge(String vityAret,String id,HttpServletRequest request){
		String userIpAddr = request.getRemoteAddr();
		try {
			if(StringUtils.isNotBlank(vityAret)){
				SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
				RptChargeDetail rcd = JSONObject.parseObject(vityAret, RptChargeDetail.class);
				rcd.setId(UUID.randomUUID().toString().replace("-", ""));
				rcd.setCreateTime(new Date());
				rcd.setOperator(login_user.getUserName());
				rcd.setChargeType(2);//平台远程充值
				rcd.setIpToken(userIpAddr);
				rptChargeDetailService.insertSelective(rcd);
				BaseCardInfo bc = baseCardInfoService.selectCardCode(rcd.getCardCode());
				if(bc!=null){
					bc.setTotalWater(new BigDecimal(String.format("%.2f", bc.getTotalWater().add(rcd.getWaterAmount()))));
					baseCardInfoService.updateCredWater(bc);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	//============机井查询=====================================================================================
		List<String> coIdList = new ArrayList<>();
		List<String> WaterCodeList = new ArrayList<>();
		@RequestMapping("/test_begin_baseinfo")
		@ResponseBody
		public String testBeginTree(String testData,String code){
			Map<String, Object> pamas = new HashMap<>();
			Map<String, Object> pageInfo = new HashMap<>();
			List<BaseDeviceInfo> list = null;
			//点击的是水管区
			if(code.length()<10){
				swaList(testData);
				pageInfo.put("waterAreaIdsList", WaterCodeList);
				list = baseDeviceInfoService.selectPageList(pageInfo);
				int count = baseDeviceInfoService.selectPageCount(pageInfo);
				pamas.put("list", list);
				pamas.put("count", count);
				WaterCodeList.clear();
			}else{
				areaList(testData);
				pageInfo.put("areaIdList", coIdList);
				list = baseDeviceInfoService.selectPageList(pageInfo);
				int count = baseDeviceInfoService.selectPageCount(pageInfo);
				pamas.put("list", list);
				pamas.put("count", count);
				coIdList.clear();
			}
				return JSONObject.toJSONString(pamas);
		}
		//模糊查询机井信息
		@RequestMapping("/like_device")
		@ResponseBody
		public String likeDevice(String deviceName,String deviceCode){
			Map<String, Object> pamas = new HashMap<>();
			Map<String, Object> pageInfo = new HashMap<>();
			pageInfo.put("deviceName", deviceName);
			pageInfo.put("deviceCode", deviceCode);
			List<BaseDeviceInfo> list = baseDeviceInfoService.selectPageList(pageInfo);
			int count = baseDeviceInfoService.selectPageCount(pageInfo);
			pamas.put("list", list);
			pamas.put("count", count);
			return JSONObject.toJSONString(pamas);
		}
		
		public List<String> swaList(String id){
			List<SysWaterArea> list = sysWaterAreaService.selectChildArea(id);
			WaterCodeList.add(id);
			for(SysWaterArea sa:list){
				WaterCodeList.add(sa.getId());
				swaList(sa.getId());
			}
			return WaterCodeList;
		}
		//机井查询递归
		public List<String> areaList(String id){
			List<SysArea> list = sysAreaService.selectParentId(id);
			coIdList.add(id);
			for(SysArea sa:list){
				coIdList.add(sa.getId());
				areaList(sa.getId());
			}
			return coIdList;
		}
	
	/**
	 * @Title: getDeviceCodeByMessage
	 * @Description: 根据机井编号查询：村号、村名、户号、户名、充值次数、总购金额、用户剩余金额 
	 * @param request
	 * @param code
	 * @return
	 * @return BaseWaterCharge
	 * @author 刘海年
	 * @date 2018年9月4日下午3:13:47
	 */
	@RequestMapping("/getDeviceCodeByMessage")
	@ResponseBody
	public String getDeviceCodeByMessage(HttpServletRequest request,String code){
		BaseWaterCharge baseWaterCharge=baseWaterChargeService.getDeviceCodeByMessage(code);
		if(baseWaterCharge!=null){
			request.setAttribute("baseWaterCharge", baseWaterCharge);
		}
		return  JSONObject.toJSONString(baseWaterCharge);
	}
	//配水计划查询
	@RequestMapping("/dynamicPlan")
	@ResponseBody
	public String dynamicPlan(String deviceId){
		List<BaseDistWaterPlan> BaseDistWaterList = null;
		try{
			List<BaseDistWaterPlanDevice> baseDataList = baseDistWaterPlanDeviceService.selectBaseDistList(login_user.getId(), deviceId);
			if(baseDataList!=null && baseDataList.size()!=0){
				for(BaseDistWaterPlanDevice bwp :baseDataList){
					waterAreaId.add(bwp.getBaseDistWaterId());
				}
				if(waterAreaId!=null){
					BaseDistWaterList = baseDistWaterPlanService.selectiDList(waterAreaId);
				}
				waterAreaId.clear();
			}else{
				
			}
			logger.info("配水计划查询成功");
		}catch(Exception e){
			logger.error("配水计划查询失败",e);
		}
		return JSON.toJSONString(BaseDistWaterList);
	}
	/**
	 * @Title: determineWater
	 * @Description:判断本次购买的水方量是否超出额定水方量
	 * @param deviceCode
	 * @param chargeXl 年份
	 * @param chargeSl 轮次
	 * @return
	 * @return String
	 * @author 刘海年
	 * @date 2018年9月16日下午4:19:11
	 */
	@RequestMapping("/determineWater")
	@ResponseBody
	public String determineWater(String deviceCode,String chargeXl,String chargeSl,String chargeTy){
		System.out.println("------>"+chargeTy);
		RptBaseDistWaterDetail rptBaseDistWaterDetail =null;
		int type=0;
		if(chargeTy!=null && !chargeTy.equals("")){
			if(chargeTy.equals("灌溉")){
				type=1;
			}else if(chargeTy.equals("工业")){
				type=2;
			}else if(chargeTy.equals("生活")){
				type=3;
			}
			//查出本轮次该机井每亩地配水量
			rptBaseDistWaterDetail =distWaterPlanInfoService.determineWater(deviceCode,chargeXl,chargeSl,type);
		}
		
		BaseDeviceExpandInfo baseDeviceExpandInfo=baseDeviceExpandInfoService.getSJArea(deviceCode);
		List<BigDecimal> rptBaseDistWaterDetailList=new ArrayList<>();
		if(rptBaseDistWaterDetail!=null&&baseDeviceExpandInfo!=null){
			BigDecimal distWater = null;
			BigDecimal sJArea;
			BigDecimal  distPrice = null;
			BigDecimal  TBW = null;
			String commonWater = null;
			int sJSupplyWaterPeople=0;
			distPrice =rptBaseDistWaterDetail.getDistPrice();//
			commonWater=rptChargeDetailService.getCommonWater(deviceCode,chargeXl,chargeSl);//查询出已经配过的水量
			if(commonWater==null||commonWater.equals("")){
				commonWater="0.00";
			}
			distWater=rptBaseDistWaterDetail.getDistWater();//分配水量，单位吨
			if(type==1){
				sJArea=baseDeviceExpandInfo.getsJArea();//机井实时灌溉面积
				TBW=distWater.multiply(sJArea);//该机井最多能配的水方量
			}else if(type==2){
				TBW=baseDeviceExpandInfo.getIndustryApprovedWater();//年核定水量
			}else if(type==3){
				sJSupplyWaterPeople=baseDeviceExpandInfo.getsJSupplyWaterPeople();//实际供水人口
				BigDecimal sJSupplyWaterPer = new BigDecimal(sJSupplyWaterPeople);
				TBW=distWater.multiply(sJSupplyWaterPer);//该机井最多能配的水方量
			}
			
	        BigDecimal bigCommonWater=new BigDecimal(commonWater);
			rptBaseDistWaterDetailList.add(TBW.subtract(bigCommonWater));//剩余可配水量
			rptBaseDistWaterDetailList.add(distPrice);
			return JSON.toJSONString(rptBaseDistWaterDetailList);
			
		}else {
			return "NO";
		}
		
	}
	//根据机井Id查询水卡信息
	@RequestMapping("/select_ByDeviceId")
	@ResponseBody
	public String selectByDeviceId(String deviceId){
		List<BaseCardInfo> bciList = null;
		if(deviceId!=""||deviceId!=null){
			 bciList =  baseCardInfoService.selectByDeviceIds(deviceId);
		}else{
			bciList = null;
		}
		return JSON.toJSONString(bciList);
	}
	@RequestMapping("/select_code")
	@ResponseBody
	public String selectCode(String cardCode){
		BaseCardInfo baseCardInfo = null;
		if(cardCode!=null||cardCode!=""){
			baseCardInfo  = baseCardInfoService.selectCardCode(cardCode);
		}
		return JSON.toJSONString(baseCardInfo);
	}
	
}