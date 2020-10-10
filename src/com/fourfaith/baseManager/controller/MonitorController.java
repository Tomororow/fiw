package com.fourfaith.baseManager.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import com.alibaba.fastjson.JSONArray;
import com.fourfaith.statisticAnalysis.model.IndustrialDevice;
import com.fourfaith.statisticAnalysis.service.IndustrialdeviceService;
import com.fourfaith.utils.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.basicInformation.model.SysWellUse;
import com.fourfaith.basicInformation.service.SysWellUseService;
import com.fourfaith.statisticAnalysis.model.BaseCardInfo;
import com.fourfaith.statisticAnalysis.model.Cardhistory;
import com.fourfaith.statisticAnalysis.service.BaseCardInfoService;
import com.fourfaith.statisticAnalysis.service.CardhistoryService;
import com.fourfaith.statisticAnalysis.service.StoppumbhistoryService;
import com.fourfaith.sysManage.model.Abnormalstate;
import com.fourfaith.sysManage.model.Abonmalhistory;
import com.fourfaith.sysManage.model.BaseDeviceInfo;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.model.Tripshistory;
import com.fourfaith.sysManage.model.TriplList;
import com.fourfaith.sysManage.model.UnusualList;
import com.fourfaith.sysManage.service.AbnormalstateService;
import com.fourfaith.sysManage.service.AbonmalhistoryService;
import com.fourfaith.sysManage.service.BaseDeviceDynamicInfoService;
import com.fourfaith.sysManage.service.BaseDeviceInfoService;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.sysManage.service.SysUserService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.sysManage.service.TripshistoryService;

/**
 * 实时监测控制器
 * @author administrator
 */
@Controller
@RequestMapping(value ="/monitor")
public class MonitorController {
	
	protected Logger logger = Logger.getLogger(MonitorController.class);
	
	protected static final String indexJsp="/page/monitor/index";
	protected static final String dataJsp="/page/monitor/data";
	protected static final String dataListJsp="/page/monitor/dataList";
	protected static final String mapListJsp="/page/monitor/mapList";
	protected static final String detailInfoJsp="/page/monitor/detailInfo";
	protected static final String mapJsp="/page/monitor/map";
	protected static final String statistListJsp="/page/monitor/statistList";
	protected static final String detailJsp = "/page/monitor/detail";
	protected static final String mapDataJsp = "/page/monitor/mapDataList";
	protected static final String remoteJsp = "/page/monitor/remote";
	protected static final String remoteListJsp = "/page/monitor/remoteList";
	protected static final String dataHistoryJsp = "/page/monitor/dataHistory";
	protected static final String requestHistoryDataJsp = "/page/monitor/requestHistoryData";
	protected static final String dataHistoryContentJsp = "/page/monitor/dataHistoryContent";
	protected static final String requestHistoryDataContentJsp = "/page/monitor/requestHistoryDataContent";
	protected static final String unusualHstoryDataJSP = "/page/monitor/unusualHstoryData";
	protected static final String unusualHstoryDataContentJSP = "/page/monitor/unusualHstoryDataContent";
	protected static final String remoteParamSetJsp = "/page/monitor/remoteParamSet";
	protected static final String industrialModel = "/page/monitor/industrialModel";
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private BaseDeviceInfoService baseDeviceInfoService;
	
	@Autowired
	private BaseDeviceDynamicInfoService baseDeviceDynamicInfoService;
	
	@Autowired
	private BaseCardInfoService baseCardInfoService;
	
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysWellUseService sysWellUseService;
	
	@Autowired
	private StoppumbhistoryService stoppumbhistoryService;
	
	@Autowired
	private CardhistoryService cardhistoryService;
	
	@Autowired
	private AbnormalstateService abnormalstateService;
	
	@Autowired
	private AbonmalhistoryService abonmalhistoryService;
	
	@Autowired
	private TripshistoryService tripshistoryService;

	@Autowired
	private IndustrialdeviceService industrialdeviceService;

	/**
     * @Title: areaChoose
     * @Description: 区域选择，用于决定需要水管区域还是行政区域
     * @return: String
	 * @throws IOException 
     * @Author: zhaojx
     * @Date: 2017年11月12日 下午4:00:07
     */
    @RequestMapping(value="/areaChoose", method = RequestMethod.GET)
    public void areaChoose(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	// 根据登录用户  获取其水管区域或者行政区域信息
    	SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
    	int areaWay = 0;
    	if(login_user!=null){
    		areaWay = login_user.getAreaWay();
    	}
    	String flag = "";
    	
    	// 水管区域方式查看
    	if(areaWay == 0){
    		flag = "success";
		// 行政区域方式查看
    	}else if(areaWay == 1){
    		flag = "failed";
    	}
    	response.getWriter().write(flag);
    	response.getWriter().close();
    }
	
	/**
	 * 实时监测首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,String menuId){
		ModelAndView mav = new ModelAndView(indexJsp);
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		List<SysMenu> sysMenuList = new ArrayList<SysMenu>();
		//根据权限动态获取菜单列表
		if(login_user!=null){
			sysMenuList = this.sysMenuService.getListByRoleidAndMenuid(menuId, login_user.getSysrole().getId());
		}
		mav.addObject("sysMenuList", sysMenuList);
		return mav;
	}
	
	  @RequestMapping(value="/login_user", method = RequestMethod.POST)
	    @ResponseBody
		public String sysUserLogin(HttpServletRequest request,String userId){
	    	SysUser login_user = null;
	    	try{
	    		if("".equals(userId)){
	    			login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
	    			//login_user = sysUserService.selectByPrimaryKey(login_user.getId());
	    		}
	    		if(!"".equals(userId)){
	    			login_user = sysUserService.selectByPrimaryKey(userId);
	    		}
	    	}catch(Exception ex){
	    		ex.printStackTrace();
	    	}
	    	
			return login_user.getUserTypeJuris();
		}
	/**
	 * 实时数据页面
	 */
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public ModelAndView data(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(dataJsp);
		Map<String,Object> params = new HashMap<String,Object>();
		List<SysWellUse> list = sysWellUseService.getList(params);
		mav.addObject("defaultTime", new Date());
		mav.addObject("wellList", list);
		return mav;
	}
	
	/**
	 * 电子地图页面
	 */
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public ModelAndView map(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(mapJsp);
		return mav;
	}
	
	/**
	 * 实时数据列表页面  根据行政区域
	 */
	@RequestMapping(value = "/dataList", method = RequestMethod.POST)
	public ModelAndView dataList(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(dataListJsp);
		Map<String,Object> params = new HashMap<String,Object>();
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		String waterAreaId = "";
		if(login_user!=null){
			waterAreaId = login_user.getWaterAreaId();
		}
		String nodeIds = request.getParameter("nodeIds");
		String deviceCode = request.getParameter("deviceCode");
		String deviceName = request.getParameter("deviceName");
		String deviceType = request.getParameter("deviceType");
		String deviceWellUse = request.getParameter("deviceWellUse");
		String sitrype = request.getParameter("siteType");
		int siteType = "1".equals(sitrype)?1:0;
		try {
			if(StringUtils.isNotBlank(deviceCode)){
				params.put("deviceCode", deviceCode.trim());
			}else{
				params.put("deviceCode", null);
			}
			if(StringUtils.isNotBlank(deviceName)){
				params.put("deviceName", deviceName.trim());
			}else{
				params.put("deviceName", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> areaIdList = null;
		if(StringUtils.isNotBlank(nodeIds)){
			areaIdList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}else{
			//无id，则置空
			areaIdList = new ArrayList<>(Arrays.asList(""));
		}
		
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		List<String> waterAreaIdsList = new ArrayList<String>();
		if(StringUtils.isNotBlank(waterAreaId)){
			List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
			for(SysWaterArea swa : sysWaterAreaList) {
				waterAreaIdsList.add(swa.getId());
			}
		}
		params.put("waterAreaIdsList", waterAreaIdsList);
		params.put("areaIdList", areaIdList);
		params.put("deviceType", deviceType);
		params.put("deviceWellUse", deviceWellUse);
		params.put("siteType", siteType);
		// 分页
	    String pageNo = request.getParameter("pageNo");
	    int count = 0;
		List<BaseDeviceInfo> baseDeviceInfoList = new ArrayList<BaseDeviceInfo>();
    	count = baseDeviceInfoService.getCountOfBase(params);
	    PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		mav.addObject("pagingBean", pagingBean);
    	baseDeviceInfoList = baseDeviceInfoService.getListOfBase(params);
		//获取在线设备数量
	    params.put("netState", 1);
	    int onlineCount = 0;
	    onlineCount = baseDeviceInfoService.getCountOfBase(params);
	    mav.addObject("deviceCode", deviceCode);
	    mav.addObject("deviceName", deviceName);
		mav.addObject("baseDeviceInfoList", baseDeviceInfoList);
		mav.addObject("count",count);
		mav.addObject("onlineCount",onlineCount);
		return mav;
	}
	
	/**
	 * @Title: dataListByWaterArea
	 * @Description: 实时数据列表页面  根据水管区域
	 * @param request
	 * @return: ModelAndView
	 * @Author: zhaojx
	 * @Date: 2017年11月17日 上午9:55:56
	 */
	@RequestMapping(value = "/dataListByWaterArea", method = RequestMethod.POST)
	public ModelAndView dataListByWaterArea(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(dataListJsp);
		
		// map参数集合
		Map<String,Object> params = new HashMap<String,Object>();
		String nodeIds = request.getParameter("nodeIds");
		String deviceCode = request.getParameter("deviceCode");
		String deviceName = request.getParameter("deviceName");
		String deviceType = request.getParameter("deviceType");
		String sitrype = request.getParameter("siteType");
		int siteType = "1".equals(sitrype)?1:0;
		try {
			if(StringUtils.isNotBlank(deviceCode)){
				params.put("deviceCode", deviceCode.trim());
			}else{
				params.put("deviceCode", null);
			}
			if(StringUtils.isNotBlank(deviceName)){
				params.put("deviceName", deviceName.trim());
			}else{
				params.put("deviceName", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 水管区域节点  用于查询机井信息所归属
		List<String> waterAreaIdsList = null;
		if(StringUtils.isNotBlank(nodeIds)){
			waterAreaIdsList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}else{
			//无id，则置空
			waterAreaIdsList = new ArrayList<>(Arrays.asList(""));
		}
		
		params.put("waterAreaIdsList", waterAreaIdsList);
		params.put("deviceType", deviceType);
		params.put("siteType", siteType);
		// 分页
	    String pageNo = request.getParameter("pageNo");
	    int count = 0;
		List<BaseDeviceInfo> baseDeviceInfoList = new ArrayList<BaseDeviceInfo>();
    	count = baseDeviceInfoService.getCountOfBase(params);
	    PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		mav.addObject("pagingBean", pagingBean);
    	baseDeviceInfoList = baseDeviceInfoService.getListOfBase(params);
		//获取在线设备数量
	    params.put("netState", 1);
	    int onlineCount = 0;
	    onlineCount = baseDeviceInfoService.getCountOfBase(params);
	    mav.addObject("deviceCode", deviceCode);
	    mav.addObject("deviceName", deviceName);
		mav.addObject("baseDeviceInfoList", baseDeviceInfoList);
		mav.addObject("count",count);
		mav.addObject("onlineCount",onlineCount);
		return mav;
	}
	
	/**
	 * @Title: dataHistoryHead
	 * @Description: 用水历史头部页面加载(时间查询条)
	 * @param request
	 * @return: ModelAndView
	 * @Author: zhaojx
	 * @Date: 2017年7月28日 下午3:09:33
	 */
	@RequestMapping(value="/dataHistoryHead")
	public ModelAndView dataHistoryHead(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(dataHistoryJsp);
		String id = request.getParameter("id");
		String sign = request.getParameter("sign");
		// 历史数据默认加载一个月
		Date date = new Date();
		Calendar c = new GregorianCalendar();
		c.setTime(date); 
		c.add(Calendar.DATE,-30);
		date=c.getTime();
		List<BaseCardInfo> list = baseCardInfoService.selectByDeviceIds(id);
		mav.addObject("sTime", date);
		mav.addObject("eTime", new Date());
		mav.addObject("id", id);
		mav.addObject("sign", sign);
		mav.addObject("cardList", list);
		return mav;
	}
	
	/**
	 * @Title: historyPage
	 * @Description: 实时监测-历史数据查询(时段报历史数据查询)
	 * @param: @param request
	 * @param: @param beginTime
	 * @param: @param endTime
	 * @param: @param id
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/historyPage")
	public ModelAndView historyPage(HttpServletRequest request, String beginTime,String cardCode, String endTime, String id,String sign) {
		ModelAndView mav = new ModelAndView(dataHistoryContentJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		// 时间非空判断
		if((beginTime != null && !beginTime.equals("")) && (endTime != null && !endTime.equals(""))){
			params.put("beginTime", beginTime);
			params.put("endTime", endTime);
		}else{
			params.put("beginTime", null);
			params.put("endTime", null);
		}
		if(cardCode!=null && !cardCode.equals("")){
			params.put("cardCode", cardCode);
		}else{
			params.put("cardCode", null);
		}
		List<?> dataList = new ArrayList<>();
		dataList.clear();
		//0:时段报历史记录;1:开关泵历史记录
		if(StringUtils.isNotBlank(sign)){
			dataList = sign.equals("0")?baseDeviceInfoService.getHistoryList(params):stoppumbhistoryService.selectByTimeList(params);
		}
		// 查询结果重组  计算本次用水量并放入集合
		//List<BaseDeviceInfo> dataHistoryList = UseWaterTotal.historyThisUseWater(dataList);
		mav.addObject("dataHistoryList", dataList);
		mav.addObject("id", id);
		mav.addObject("sign", sign);
		return mav;
	}
	
	/**
	 * @Title: requestHistoryDataHead
	 * @Description: 控制器遥测下发查询历史数据头部
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/requestHistoryDataHead")
	public ModelAndView requestHistoryDataHead(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(requestHistoryDataJsp);
		String deviceCode = request.getParameter("deviceCode");
		String id = request.getParameter("id");
		String eign = request.getParameter("eign");
		List<BaseCardInfo> list = baseCardInfoService.selectByDeviceIds(id);
		// 历史数据默认加载一个月
		Date date = new Date();
		Calendar c = new GregorianCalendar();
		c.setTime(date); 
		c.add(Calendar.DATE,-30);
		date=c.getTime();
		
		mav.addObject("sTime", date);
		mav.addObject("eTime", new Date());
		mav.addObject("deviceCode", deviceCode);
		mav.addObject("eign", eign);
		mav.addObject("cardList", list);
		return mav;
	}
	
	 /**
	  * 上下卡历史记录查询（数据库非遥测）
	  * @param request
	  * @return
	  */
	@RequestMapping(value = "/requestHistoryQuery")
	public ModelAndView requestHistoryQuery(HttpServletRequest request,String deviceCode,String beginTime,String endTime,String cardCode,String eign){
		ModelAndView mav = new ModelAndView(requestHistoryDataContentJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		// 时间非空判断
		if((beginTime != null && !beginTime.equals("")) && (endTime != null && !endTime.equals(""))){
			params.put("beginTime", beginTime);
			params.put("endTime", endTime);
		}else{
			params.put("beginTime", null);
			params.put("endTime", null);
		}
		if(cardCode!=null && !cardCode.equals("")){
			params.put("cardCode", cardCode);
		}else{
			params.put("cardCode", null);
		}
		if(deviceCode!=null && !deviceCode.equals("")){
			params.put("deviceCode", deviceCode);
		}else{
			params.put("deviceCode", null);
		}
		List<Cardhistory> list = cardhistoryService.requestHistoryQuery(params);
		mav.addObject("dataHistoryList", list);
		mav.addObject("eign", eign);
		return mav;
	}
 	
	/**
	 * @Title: requestHistoryPage
	 * @Description: 控制器遥测查询历史数据
	 * @param: @param request
	 * @param: @param beginTime
	 * @param: @param endTime
	 * @param: @param id
	 * @return: String
	 */
	@RequestMapping(value = "/requestHistoryPage")
	@ResponseBody
	public String requestHistoryPage(HttpServletRequest request, String beginTime, String endTime, String deviceCode) {
		String mav = "";
		try {
			baseDeviceInfoService.delete_8400(deviceCode, beginTime, endTime);
		} catch (Exception e) {
			logger.error("清除8400查询信息失败" + e);
		}
		try {
			// 登录验证请求体
			String urlBodyStr = "{\"type\":1001,\"seq\":1,\"token\":\"null\",\"payload\":{\"Identity\":\"WebServer\"}}";

			// 将请求体进行编码处理
			String urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
			String requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;
			// 将真实请求的url装入get请求类 得到响应结果
			String result = httpUtils.get(requestURL);

			// json解析
			JSONObject jsonObject = JSONObject.parseObject(result);
			JSONObject payload = (JSONObject) jsonObject.get("payload");
			String token = payload.getString("Token");

			// 远程操作请求体
			urlBodyStr = "";
			// 请求结果
			result = "";
			// 操作类型 协议文档获取 5010 为参数设置类型
			String operType = "8400";
			// AT指令信息
			String strToACSII = beginTime + "A" + endTime;

			// 获取ajax请求参数 编码和操作状态
			urlBodyStr = "{\"type\":2001,\"seq\":1,\"token\":\"" + token
					+ "\",\"payload\":{\"DeviceCode\":\"" + deviceCode
					+ "\",\"OperType\":\"" + operType + "\",\"OperContent\":\""
					+ strToACSII + "\"}}";
			// 将请求体进行编码处理

			urlBodyEncoded = URLEncodeUtils.toURLEncoded(urlBodyStr);
			requestURL = ConfUtils.getInstance().getREMOTE_URL() + urlBodyEncoded;

			result = httpUtils.get(requestURL);
			jsonObject = JSONObject.parseObject(result);
			payload = (JSONObject) jsonObject.get("payload");
			String Result = payload.getString("Result");
			if (Result.contains("1")) {
				mav="1";
			} else {
				return mav;
			}
		} catch (Exception e) {
			logger.error("查询历史数据失败！" + e);
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * @Title: breakConditionList
	 * @Description: 刷新控制器遥测查询历史数据
	 * @param: @param deviceCode
	 * @param: @param beginTime
	 * @param: @param endTime
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/breakConditionList")
	public ModelAndView breakConditionList(String deviceCode,String beginTime,String endTime,String eign) {
		ModelAndView mav = new ModelAndView(requestHistoryDataContentJsp);
		// 根据Id获取历史数据集合
		List<BaseDeviceInfo> dataList = baseDeviceInfoService.getHistoryList_8400(deviceCode,beginTime,endTime);
		mav.addObject("dataHistoryList", dataList);
		mav.addObject("eign", eign);
		return mav;
	}
	
	/**
	 * TODO: 实时数据详细数据
	 * @param request
	 * 2016年12月6日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="detailInfoPage")
	public ModelAndView detailInfoPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(detailInfoJsp);
		String id = request.getParameter("id");
		String deviceCode = request.getParameter("deviceCode");
		if(StringUtils.isNotBlank(deviceCode)){
			Abnormalstate abnormalstate = abnormalstateService.selectByPrimarytuData(deviceCode);
			mav.addObject("abnormalstate", abnormalstate);
		}
		
		//BaseDeviceDynamicInfo baseDeviceDynamicInfo = baseDeviceDynamicInfoService.findById(id);
		//mav.addObject("baseDeviceDynamicInfo", baseDeviceDynamicInfo);
		return mav;
	}
	
	/**
	 * 
	 * 设备异常历史记录
	 * @param request
	 * @param deviceCode
	 * @param disn
	 * @return
	 */
	@RequestMapping(value="/unusualHstoryDataPage")
	public ModelAndView unusualHstoryData(HttpServletRequest request,String deviceCode,String disn){
		ModelAndView mav = new ModelAndView(unusualHstoryDataJSP);
		// 历史数据默认加载一个月
		Date date = new Date();
		Calendar c = new GregorianCalendar();
		c.setTime(date); 
		c.add(Calendar.DATE,-30);
		date=c.getTime();
		mav.addObject("sTime", date);
		mav.addObject("eTime", new Date());
		mav.addObject("deviceCode", deviceCode);
		mav.addObject("disn", disn);
		return mav;
	}
	/**
	 * 设备异常历史记录中间数据页面
	 * @param request
	 * @param deviceCode
	 * @param disn:0:设备异常记录表;1:设备跳闸记录表
	 * @return
	 */
	@RequestMapping(value="/unusualHstoryList")
	public ModelAndView unusualHstoryData(HttpServletRequest request,String beginTime, String endTime,String deviceCode,String disn){
		ModelAndView mav = new ModelAndView(unusualHstoryDataContentJSP);
		Map<String, Object> params = new HashMap<String, Object>();
		List<Abonmalhistory> abList = new ArrayList<>();
		List<Tripshistory> triList = new ArrayList<>();
		if((beginTime != null && !beginTime.equals("")) && (endTime != null && !endTime.equals(""))){
			params.put("beginTime", beginTime);
			params.put("endTime", endTime);
		}else{
			params.put("beginTime", null);
			params.put("endTime", null);
		}
		if(deviceCode!=null && !deviceCode.equals("")){
			params.put("deviceCode", deviceCode);
		}else{
			params.put("deviceCode", null);
		}
		if(disn.equals("0")){
			abList = abonmalhistoryService.findUnableHistory(params);
			AbnormaType(abList,null,0);
			mav.addObject("unusualList", abList);
		}else{
			triList = tripshistoryService.findTripHistory(params);
			AbnormaType(null,triList,1);
			mav.addObject("unusualList", triList);
		}
		mav.addObject("deviceCode", deviceCode);
		mav.addObject("disn", disn);
		return mav;
	}
	
	/**
	 * 遍历设备异常类型
	 */
	public List<?> AbnormaType (List<Abonmalhistory> abList,List<Tripshistory> triList,int sign){
		List<?> list = new ArrayList<>();
		if(sign==0){
			for(Abonmalhistory ab :abList){
				int vity = ab.getAbnormaltype();
				if(vity==24){
					if(ab.getAbnormalstate()==0){
						ab.setAbnormalname("正常");
					}else if(ab.getAbnormalstate()==1){
						ab.setAbnormalname("水表表底被清零");
					}else if(ab.getAbnormalstate()==2){
						ab.setAbnormalname("水表表底流量增量过大");
					}else if(ab.getAbnormalstate()==3){
						ab.setAbnormalname("水表底数小");
					}
				}else if(vity==29){
					if(ab.getAbnormalstate()==0){
						ab.setAbnormalname("正常");
					}else if(ab.getAbnormalstate()==1){
						ab.setAbnormalname("电表表底被清零");
					}else if(ab.getAbnormalstate()==2){
						ab.setAbnormalname("表底电量增量过大");
					}else if(ab.getAbnormalstate()==3){
						ab.setAbnormalname("电表底数小");
					}
				}else{
					ab.setAbnormalname(UnusualList.toList(ab.getAbnormaltype()));
				}
			}
			list = abList;
		}
		if(sign==1){
			for(Tripshistory tr :triList){
				tr.setTripsname(TriplList.toList(tr.getTripstype()));
			}
			list = triList;
		}
		return list;
	}
	
	/**
	 * @Title: mapData
	 * @Description: 地图实时数据列表
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/mapData", method = RequestMethod.POST)
	public ModelAndView mapData(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(mapDataJsp);
		Map<String,Object> params = new HashMap<String,Object>();
		
		String nodeIds = request.getParameter("nodeIds");
		String deviceCode = request.getParameter("deviceCode");
		String deviceName = request.getParameter("deviceName");
		
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		String waterAreaId = login_user.getWaterAreaId();
		
		List<String> areaIdList = null;
		if(StringUtils.isNotBlank(nodeIds)){
			areaIdList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}else{
			//无id，则置空
			areaIdList = new ArrayList<>(Arrays.asList(""));
		}
		params.put("areaIdList", areaIdList);
		
		if(null==deviceCode || "".equals(deviceCode)) {
			params.put("deviceCode", null);
		} else {
			params.put("deviceCode", deviceCode.trim());
		}
		if(null==deviceName || "".equals(deviceName)) {
			params.put("deviceName", null);
		} else {
			params.put("deviceName", deviceName.trim());
		}
		
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
		List<String> waterAreaIdsList = new ArrayList<String>();
		for(SysWaterArea swa : sysWaterAreaList) {
			waterAreaIdsList.add(swa.getId());
		}
		params.put("waterAreaIdsList", waterAreaIdsList);
		
		// 分页
	    String pageNo = request.getParameter("pageNo");
	    int count = baseDeviceInfoService.getCountOfBase(params);
	    PagingBean pagingBean = PageUtil.page(pageNo, 4+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		mav.addObject("pagingBean", pagingBean);
		
		List<BaseDeviceInfo> baseDeviceInfoList = baseDeviceInfoService.getListOfBase(params);
		mav.addObject("baseDeviceInfoList", baseDeviceInfoList);
		return mav;
	}
	
	/**
	 *  电子地图信息 以行政区域划分
	 * @param request
	 */
	@RequestMapping(value = "/mapInfo", method = RequestMethod.POST)
	public ModelAndView mapInfo(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(mapListJsp);
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		
		String waterAreaId = login_user.getWaterAreaId();
		String nodeIds = request.getParameter("nodeIds");
		List<String> areaIdList = null;
		if(StringUtils.isNotBlank(nodeIds)){
			areaIdList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}else{
			//无id，则置空
			areaIdList = new ArrayList<>(Arrays.asList(""));
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("areaIdList", areaIdList);
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
		List<String> waterAreaIdsList = new ArrayList<String>();
		for(SysWaterArea swa : sysWaterAreaList) {
			waterAreaIdsList.add(swa.getId());
		}
		params.put("waterAreaIdsList", waterAreaIdsList);
		
		// 地图显示信息列表
		List<BaseDeviceInfo> baseDeviceInfoList = baseDeviceInfoService.getDeviceInfoOnMap(params);
		mav.addObject("devList", JSONObject.toJSONString(baseDeviceInfoList));
		
		return mav;
	}
	
	/**
	 * @Title: mapInfoByWaterArea
	 * @Description: 电子地图信息  以水管区域划分
	 * @param request
	 * @return: ModelAndView
	 * @Author: zhaojx
	 * @Date: 2017年11月16日 下午7:57:43
	 */
	@RequestMapping(value = "/mapInfoByWaterArea", method = RequestMethod.POST)
	public ModelAndView mapInfoByWaterArea(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(mapListJsp);
		
		// 获取点击节点id
		String nodeIds = request.getParameter("nodeIds");
		List<String> waterAreaIdsList = null;
		if(StringUtils.isNotBlank(nodeIds)){
			waterAreaIdsList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}else{
			//无id，则置空
			waterAreaIdsList = new ArrayList<>(Arrays.asList(""));
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("waterAreaIdsList", waterAreaIdsList);
		
		// 地图显示信息列表
		List<BaseDeviceInfo> baseDeviceInfoList = baseDeviceInfoService.getDeviceInfoOnMap(params);
		mav.addObject("devList", JSONObject.toJSONString(baseDeviceInfoList));
		return mav;
	}
	
	/**
	 * 点击层，查询最新的数据
	 */
	@RequestMapping(value="mapDetailInfo")
	@ResponseBody
	public String mapDetailInfo(HttpServletRequest request){
		AjaxJson ajaxJson = new AjaxJson();
		String id = request.getParameter("id");
		BaseDeviceInfo baseDeviceInfo = baseDeviceInfoService.findById(id);
		ajaxJson.setObj(baseDeviceInfo);
		ajaxJson.setSuccess(true);
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * @Title: remote
	 * @Description: 远程监控 列表头部页面
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value = "/remote", method = RequestMethod.GET)
	public ModelAndView remote(HttpServletRequest request){
		Map<String,Object> params = new HashMap<String,Object>();
		ModelAndView mav = new ModelAndView(remoteJsp);
		// 获取全部控制器程序版本
		List<String> versionList = baseDeviceInfoService.findDeviceVersion();
		List<SysWellUse> list = sysWellUseService.getList(params);
		mav.addObject("wellList", list);
		mav.addObject("versionList", versionList);
		return mav;
	}
	
	/**
	 * @Title: remoteList
	 * @Description: 根据地图首页菜单树  远程监控列表  以行政区域划分
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value = "/remoteList", method = RequestMethod.POST)
	public ModelAndView remoteList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(remoteListJsp);
		Map<String,Object> params = new HashMap<String,Object>();
		
		// 查询条件
		String nodeIds = request.getParameter("nodeIds");
		String deviceCode = request.getParameter("deviceCode");
		String deviceName = request.getParameter("deviceName");
		String deviceVersion = request.getParameter("deviceVersion");
		String netState = request.getParameter("netState");
		String pumpState = request.getParameter("pumpState");
		String deviceWellUse = request.getParameter("deviceWellUse");
		
		List<String> areaIdList = null;
		if(StringUtils.isNotBlank(nodeIds)){
			areaIdList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}else{
			//无id，则置空
			areaIdList = new ArrayList<>(Arrays.asList(""));
		}
		params.put("areaIdList", areaIdList);
		
		// 查询条件
		try {
			if(StringUtils.isNotBlank(deviceCode)){
				params.put("deviceCode", deviceCode.trim());
			}else{
				params.put("deviceCode", null);
			}
			if(StringUtils.isNotBlank(deviceName)){
				params.put("deviceName", deviceName.trim());
			}else{
				params.put("deviceName", null);
			}
			if(StringUtils.isNotBlank(deviceVersion)){
				params.put("deviceVersion", deviceVersion.trim());
			}else{
				params.put("deviceVersion", null);
			}
			if(StringUtils.isNotBlank(netState)){
				params.put("netState", netState.trim());
			}else{
				params.put("netState", null);
			}
			if(StringUtils.isNotBlank(pumpState)){
				params.put("pumpState", pumpState.trim());
			}else{
				params.put("pumpState", null);
			}
			if(StringUtils.isNotBlank(deviceWellUse)){
				params.put("deviceWellUse", deviceWellUse.trim());
			}else{
				params.put("deviceWellUse", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 分页
	    String pageNo = request.getParameter("pageNo");
	    // 查询分页信息总条数
	    int count = baseDeviceInfoService.getBaseCount(params);
	    PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		mav.addObject("pagingBean", pagingBean);
		
		// 查询分页信息总信息
		List<BaseDeviceInfo> baseDeviceInfoList = baseDeviceInfoService.getBaseList(params);
	    mav.addObject("deviceCode", deviceCode);
	    mav.addObject("deviceName", deviceName);
		mav.addObject("baseDeviceInfoList", baseDeviceInfoList);
		mav.addObject("count",count);
		return mav;
	}
	
	/**
	 * @Title: remoteListByWaterArea
	 * @Description: 根据地图首页菜单树  获取远程监控列表  以水管区域划分
	 * @param request
	 * @return: ModelAndView
	 * @Author: zhaojx
	 * @Date: 2017年11月17日 上午10:16:30
	 */
	@RequestMapping(value = "/remoteListByWaterArea", method = RequestMethod.POST)
	public ModelAndView remoteListByWaterArea(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(remoteListJsp);
		
		Map<String,Object> params = new HashMap<String,Object>();
		String nodeIds = request.getParameter("nodeIds");
		String deviceCode = request.getParameter("deviceCode");
		String deviceName = request.getParameter("deviceName");
		String deviceVersion = request.getParameter("deviceVersion");
		String netState = request.getParameter("netState");
		String pumpState = request.getParameter("pumpState");
		
		List<String> waterAreaIdsList = null;
		if(StringUtils.isNotBlank(nodeIds)){
			waterAreaIdsList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}else{
			//无id，则置空
			waterAreaIdsList = new ArrayList<>(Arrays.asList(""));
		}
		params.put("waterAreaIdsList", waterAreaIdsList);
		
		// 查询条件
		try {
			if(StringUtils.isNotBlank(deviceCode)){
				params.put("deviceCode", deviceCode.trim());
			}else{
				params.put("deviceCode", null);
			}
			if(StringUtils.isNotBlank(deviceName)){
				params.put("deviceName", deviceName.trim());
			}else{
				params.put("deviceName", null);
			}
			if(StringUtils.isNotBlank(deviceVersion)){
				params.put("deviceVersion", deviceVersion.trim());
			}else{
				params.put("deviceVersion", null);
			}
			if(StringUtils.isNotBlank(netState)){
				params.put("netState", netState.trim());
			}else{
				params.put("netState", null);
			}
			if(StringUtils.isNotBlank(pumpState)){
				params.put("pumpState", pumpState.trim());
			}else{
				params.put("pumpState", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 分页
	    String pageNo = request.getParameter("pageNo");
	    // 查询分页信息总条数
	    int count = baseDeviceInfoService.getBaseCount(params);
	    PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		mav.addObject("pagingBean", pagingBean);
		// 查询分页信息总信息
		List<BaseDeviceInfo> baseDeviceInfoList = baseDeviceInfoService.getBaseList(params);
	    mav.addObject("deviceCode", deviceCode);
	    mav.addObject("deviceName", deviceName);
		mav.addObject("baseDeviceInfoList", baseDeviceInfoList);
		mav.addObject("count",count);
		return mav;
	}
	
	/**
	 * @Title: setRemoteInfoPage
	 * @Description: 控制器参数远程设置页面
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="/setRemoteInfoPage")
	public ModelAndView setRemoteInfoPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(remoteParamSetJsp);
		String deviceCode = request.getParameter("deviceCode");
		if(deviceCode != null && !deviceCode.equals("")){
			mav.addObject("deviceCode", deviceCode);
		}
		return mav;
	}

	/**
	 * 工业机井数据导出
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loadIndustrial")
	public ModelAndView loadIndustrial(HttpServletRequest request){
		ModelAndView mav = null;
		String type = request.getParameter("type")==null?"0":request.getParameter("type");
		if(type.equals("0")){//动态数据
			mav = new ModelAndView(dataListJsp);
		}else if(type.equals("1")){//历史数据
			mav = new ModelAndView(industrialModel);
		}
		// 历史数据默认加载一个月
		Date date = new Date();
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.DATE,-30);
		date=c.getTime();
		List<IndustrialDevice> list = new ArrayList<>();
		Map<String,Object> pageInfo = new HashedMap();
		Page pageHelper = new Page();
		String deviceName = request.getParameter("deviceName");
		String deviceport = request.getParameter("deviceport");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String backtwo = request.getParameter("backtwo");

		pageInfo.put("deviceName",StringUtils.isNotBlank(deviceName)?deviceName:null);
		pageInfo.put("startTime",StringUtils.isNotBlank(startTime)?startTime:null);
		pageInfo.put("endTime",StringUtils.isNotBlank(endTime)?endTime:null);
        pageInfo.put("deviceport",StringUtils.isNotBlank(deviceport)?deviceport:null);
		pageInfo.put("table",type.equals("0")?"industrialdevice_current":"industrialdevice");
		pageInfo.put("backtwo",type.equals("0")?backtwo:null);
		// 分页
		String pageNo = request.getParameter("pageNo");
		if(type.equals("0")){
			pageHelper = PageHelper.startPage(Integer.parseInt(StringUtils.isNotBlank(pageNo)?pageNo:"1"), 10);
		}
		try {
			list = industrialdeviceService.selectByPrimaryKey(pageInfo);
			long count = 0l;
			if(type.equals("1")){
				BigDecimal  compData=list.get(0).getPositivetotal().subtract(list.get(list.size()-1).getPositivetotal());
				mav.addObject("compData", compData);
			}
			if(type.equals("0")){
				count = pageHelper.getTotal();
				PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", (int) count, PagingBean.DEFAULT_PAGE_SIZE);
				mav.addObject("pagingBean", pagingBean);
			}
			mav.addObject("deviceNamg", deviceName);
            mav.addObject("deviceport", deviceport);

			mav.addObject("count", count);
			mav.addObject("type", type);
			mav.addObject("IndustrialDeviceList", list);
			mav.addObject("sTime", date);
			mav.addObject("eTime", new Date());
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return mav;
	}

	/**
	 * 修改设备名称
	 * @param deviceName
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/updateByKeyName")
	@ResponseBody
	public String updateByKeyName(String deviceName,String ids){
		int y = 0;
		try {
			if(StringUtils.isNotBlank(deviceName)&&StringUtils.isNotBlank(ids)){
				y = industrialdeviceService.updateByKeyName(deviceName,ids);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return ""+y;
	}

	/**
	 * 工业机井报表导出
	 * @return
	 */
	@RequestMapping(value="/IndustReport")
	public void IndustReport(HttpServletResponse response,String deviceName,String deviceport,String startTime,String endTime,String backtwo,String type){
		List<IndustrialDevice> list = new ArrayList<>();
		Map<String,Object> pageInfo = new HashedMap();
		try {
				// 历史数据默认加载一个月
				Date date = new Date();
				Calendar c = new GregorianCalendar();
				c.setTime(date);
				c.add(Calendar.DATE,-30);
				date = c.getTime();
				pageInfo.put("deviceName",StringUtils.isNotBlank(deviceName)?deviceName:null);
				pageInfo.put("startTime",StringUtils.isNotBlank(startTime)?startTime:null);
				pageInfo.put("endTime",StringUtils.isNotBlank(endTime)?endTime:null);
				pageInfo.put("deviceport",StringUtils.isNotBlank(deviceport)?deviceport:null);
				pageInfo.put("table",type.equals("0")?"industrialdevice_current":"industrialdevice");
				pageInfo.put("backtwo",type.equals("0")?backtwo:null);
				list = industrialdeviceService.selectByPrimaryKey(pageInfo);
				ExportExcel.exportExcel(list,"工业机井监测数据导出","工业机井监测数据导出",IndustrialDevice.class,"工业机井监测数据导出.xls",response);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}