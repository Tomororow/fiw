package com.fourfaith.statisticAnalysis.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.fourfaith.basicInformation.model.SysWellUse;
import com.fourfaith.basicInformation.service.SysWellUseService;
import com.fourfaith.sysManage.model.BaseDeviceInfo;
import com.fourfaith.sysManage.service.BaseDeviceInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fourfaith.statisticAnalysis.model.RptUseWaterDetail;
import com.fourfaith.statisticAnalysis.model.UseWaterDataOfDay;
import com.fourfaith.statisticAnalysis.model.useWaterOfDayByExcelVO;
import com.fourfaith.statisticAnalysis.model.useWaterOfMonthByExcelVO;
import com.fourfaith.statisticAnalysis.model.useWaterOfYearByExcelVO;
import com.fourfaith.statisticAnalysis.service.UseWaterService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.ExportExcel;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.WaterAreaRecursionUtil;

/**
 * @ClassName: UseWaterController
 * @Description: 用水统计控制器
 * @Author: zhaojx
 * @Date: 2018年3月15日 下午3:36:24
 */
@Controller
@RequestMapping(value="/useWater")
public class UseWaterController {

	/*用水日统计页面*/
	protected static final String useWaterOfDayJsp="/page/statistic/water/useWaterOfDay";
	protected static final String useWaterListOfDayJsp="/page/statistic/water/useWaterListOfDay";
	protected static final String useWaterByAreaOfDayJsp="/page/statistic/water/useWaterByAreaOfDay";
	/*用水月统计页面*/
	protected static final String useWaterOfMonthJsp="/page/statistic/water/useWaterOfMonth";
	protected static final String useWaterListOfMonthJsp="/page/statistic/water/useWaterListOfMonth";
	protected static final String useWaterByAreaOfMonthJsp="/page/statistic/water/useWaterByAreaOfMonth";
	/*用水年统计页面*/
	protected static final String useWaterOfYearJsp="/page/statistic/water/useWaterOfYear";
	protected static final String useWaterListOfYearJsp="/page/statistic/water/useWaterListOfYear";
	protected static final String useWaterByAreaOfYearJsp="/page/statistic/water/useWaterByAreaOfYear";
	
	@Autowired
	private UseWaterService useWaterService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private BaseDeviceInfoService basedeviceinfoService;
	@Autowired
	private SysWellUseService sysWellUseService;
	/**
	 * @Title: useWaterOfDay
	 * @Description: 日用水统计头部页面
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value = "/useWaterOfDay", method = RequestMethod.GET)
	public ModelAndView useWaterOfDay(HttpServletRequest request){
		ModelAndView mav = null;
		// 根据登录用户  获取其水管区域或者行政区域信息
    	SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
    	int areaWay = login_user.getAreaWay();
    	
    	// 水管区域方式查看
    	if(areaWay == 0){
    		mav = new ModelAndView(useWaterByAreaOfDayJsp);
		// 行政区域方式查看
    	}else if(areaWay == 1){
    		mav = new ModelAndView(useWaterOfDayJsp);
    	}
		
    	// 日统计默认为当前时间和前一天
		Date date = new Date();
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		//c.add(Calendar.DATE, -1);
		date=c.getTime();
		
		// 获取所有的水管区域信息集合
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		// 获取当前登陆用户所属水管区域及所有子区域
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, login_user.getWaterAreaId());
		List<SysWellUse> list = sysWellUseService.getList(null);
		mav.addObject("wellList", list);
		mav.addObject("sysWaterAreaList", sysWaterAreaList);
		mav.addObject("sTime", date);
		mav.addObject("eTime", new Date());
		return mav;
	}
	
	/**
	 * @Title: useWaterList
	 * @Description: 日用水信息列表 行政水管区域
	 * @param: @param request
	 * @param: @param query_startTime
	 * @param: @param query_endTime
	 * @param: @param areaType
	 * @param: @throws ParseException
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value = "/useWaterList", method = RequestMethod.POST)
	public ModelAndView useWaterList(HttpServletRequest request, String query_startTime,String areaType) throws ParseException{
		ModelAndView mav = new ModelAndView(useWaterListOfDayJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		// 区域编码
		String nodeIds = request.getParameter("nodeIds");
		/*//定义行政区code集合
		List<BaseDeviceInfo> CodeList = new ArrayList<>();*/


		// 根据区域类型显示数据
		if(areaType.equals("area")){
			List<String> areaIdList = null;
			if(StringUtils.isNotBlank(nodeIds)){
				areaIdList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
			}else{
				//无id，则置空
				areaIdList = null;
			}
			params.put("areaIdList", areaIdList);
			String wellUse = request.getParameter("wellUse");
			params.put("wellUse", wellUse);
			params.put("deviceType", request.getParameter("deviceType"));
			// 水管编码
			String waterAreaId = request.getParameter("waterAreaId");
			if(null==waterAreaId || "".equals(waterAreaId)) {
				SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
				waterAreaId = login_user.getWaterAreaId();
			}
			// 查询所有的水管区域信息集合
			List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
			// 递归获取当前所选的水管区域的子区域信息
			List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
			// 将递归获取到的水管区域信息Id 存放到集合中
			List<String> waterAreaIdsList = new ArrayList<String>();
			for(SysWaterArea swa : sysWaterAreaList) {
				waterAreaIdsList.add(swa.getId());
			}
			/*//拿到所选行政区域下所有的devicecode
			CodeList = basedeviceinfoService.selectwaterAreaAllCode(waterAreaIdsList);*/
			params.put("waterAreaIdsList", waterAreaIdsList);
		}else if(areaType.equals("waterArea")){
			List<String> waterAreaIdsList = null;
			if(StringUtils.isNotBlank(nodeIds)){
				waterAreaIdsList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
			}else{
				//无id，则置空
				waterAreaIdsList = null;
			}
			/*//拿到所选行政区域下所有的devicecode
			if(waterAreaIdsList.size()>0){
				CodeList = basedeviceinfoService.selectAreaAllCode(waterAreaIdsList);
			}*/

			params.put("waterAreaIdsList", waterAreaIdsList);
		}

		// 设备编码
		String dCode_query = request.getParameter("dCode_query");
		try {
			if(StringUtils.isNotBlank(dCode_query)){
				params.put("dCode_query", dCode_query.trim());
			}else{
				params.put("dCode_query", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 设备名称
		String dName_query = request.getParameter("dName_query");
		try {
			if(StringUtils.isNotBlank(dName_query)){
				params.put("dName_query", dName_query.trim());
			}else{
				params.put("dName_query", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 开始时间，结束时间
		if(query_startTime != null){
			String[] strUy = query_startTime.split("-");
			//params.put("startTime", query_startTime);
			params.put("year", strUy[0]);
			params.put("month", strUy[1]);
			params.put("day", strUy[2]);
	   		//params.put("endTime", query_endTime+" 23:59:59");
			params.put("queryTime",query_startTime);
		}else{
			params.put("year", null);
			params.put("month", null);
			params.put("day", null);
			//params.put("startTime", null);
	   		//params.put("endTime", null);
			params.put("queryTime",null);
		}
		params.put("tableName","UseWaterDataOfDay");
   		// 分页
		String pageNo = request.getParameter("pageNo");
		int count = 0;
		count = useWaterService.getCount(params);
		//定义两个list接受时间与用水量
		List<Object> hourList = new ArrayList<>();
		List<Object> sumwaterList = new ArrayList<>();
		int i=0;
		// 按条件统计计算所有用水量
		if(count != 0){
			// 计算总用水量
			BigDecimal useWaterSum = useWaterService.getUseWaterStatistics(params);
			//按照每个小时求和用数量（柱状统计图）
			List<RptUseWaterDetail> Hourlist = useWaterService.selectdayHour(params);
			//遍历拿到每个小时的  时间   总用水量   （若某一时段的总用水量为空则补为0）
			
			for (RptUseWaterDetail rptUseWaterDetail : Hourlist) {
				if(i != rptUseWaterDetail.getInHour()){
					while(i != rptUseWaterDetail.getInHour()){
						sumwaterList.add(0);
						hourList.add(i);
						i++;
					}
					if(i == rptUseWaterDetail.getInHour()){
						sumwaterList.add(rptUseWaterDetail.getSumusewater());
						hourList.add(rptUseWaterDetail.getInHour());
						i++;
					}
				}else{
					sumwaterList.add(rptUseWaterDetail.getSumusewater());
					hourList.add(rptUseWaterDetail.getInHour());
					i++;
				}
			}
			//获取当前小时数
			Calendar instance = Calendar.getInstance();
			int j = instance.get(instance.HOUR_OF_DAY);
			while(i<=j){
				sumwaterList.add(0);
				hourList.add(i);
				i++;
			}
			mav.addObject("hourList", hourList);
			mav.addObject("sumwaterList", sumwaterList);
			if(useWaterSum != null){
				mav.addObject("useWaterSum", useWaterSum);
			}else{
				mav.addObject("useWaterSum", 0);
			}
		}else{
			mav.addObject("useWaterSum", 0);
		}
		
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取历史数据  用于显示
		List<UseWaterDataOfDay> useWaterDetails = useWaterService.getWaterDataInfo(params);
		
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("uwdList", useWaterDetails);
		mav.addObject("startTime", query_startTime);
		//mav.addObject("endTime", query_endTime);
		mav.addObject("dCode", dCode_query);
		return mav;
	}
	
	/**
	 * @Title: useWaterOfMonth
	 * @Description: 月用水统计头部页面
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="/useWaterOfMonth", method = RequestMethod.GET)
	public ModelAndView useWaterOfMonth(HttpServletRequest request){
		ModelAndView mav = null;
		// 根据登录用户  获取其水管区域或者行政区域信息
    	SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
    	int areaWay = login_user.getAreaWay();
    	
    	// 水管区域方式查看
    	if(areaWay == 0){
    		mav = new ModelAndView(useWaterByAreaOfMonthJsp);
		// 行政区域方式查看
    	}else if(areaWay == 1){
    		mav = new ModelAndView(useWaterOfMonthJsp);
    	}
		List<SysWellUse> list = sysWellUseService.getList(null);
		// 获取所有的水管区域信息集合
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		// 获取当前登陆用户所属水管区域及所有子区域
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, login_user.getWaterAreaId());
		mav.addObject("sysWaterAreaList", sysWaterAreaList);
		mav.addObject("sTime", new Date());
		mav.addObject("wellList", list);
		return mav;
	}
	
	/**
	 * @Title: useWaterListOfMonth
	 * @Description: 月用水信息列表 行政水管区域
	 * @param: @param request
	 * @param: @param query_startTime
	 * @param: @param query_endTime
	 * @param: @param areaType
	 * @param: @throws ParseException
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="/useWaterListOfMonth", method=RequestMethod.POST)
	public ModelAndView useWaterListOfMonth(HttpServletRequest request, String query_startTime, String areaType) throws ParseException{
		ModelAndView mav = new ModelAndView(useWaterListOfMonthJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		// 区域编码
		String nodeIds = request.getParameter("nodeIds");
		
		// 根据区域类型显示数据
		if(areaType.equals("area")){
			List<String> areaIdList = new ArrayList<>();
			if(StringUtils.isNotBlank(nodeIds)){
				//areaIdList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
				String [] str = nodeIds.split(",");
				for(String s:str){
					if(StringUtils.isNotBlank(s)){
						areaIdList.add(s.trim());
					}
				}
			}else{
				//无id，则置空
				areaIdList = null;
			}
			params.put("areaIdList", areaIdList);
			String wellUse = request.getParameter("wellUse");
			params.put("wellUse", wellUse);
			params.put("deviceType", request.getParameter("deviceType"));
			// 水管编码
			String waterAreaId = request.getParameter("waterAreaId");
			if(null==waterAreaId || "".equals(waterAreaId)) {
				SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
				waterAreaId = login_user.getWaterAreaId();
			}
			// 查询所有的水管区域信息集合
			List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
			// 递归获取当前所选的水管区域的子区域信息
			List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
			// 将递归获取到的水管区域信息Id 存放到集合中
			List<String> waterAreaIdsList = new ArrayList<String>();
			for(SysWaterArea swa : sysWaterAreaList) {
				waterAreaIdsList.add(swa.getId());
			}
			params.put("waterAreaIdsList", waterAreaIdsList);
		}else if(areaType.equals("waterArea")){
			List<String> waterAreaIdsList = null;
			if(StringUtils.isNotBlank(nodeIds)){
				waterAreaIdsList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
			}else{
				//无id，则置空
				waterAreaIdsList = null;
			}
			params.put("waterAreaIdsList", waterAreaIdsList);
		}
		
		// 设备编码
		String dCode_query = request.getParameter("dCode_query");
		try {
			if(StringUtils.isNotBlank(dCode_query)){
				params.put("dCode_query", dCode_query.trim());
			}else{
				params.put("dCode_query", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 设备名称
		String dName_query = request.getParameter("dName_query");
		try {
			if(StringUtils.isNotBlank(dName_query)){
				params.put("dName_query", dName_query.trim());
			}else{
				params.put("dName_query", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		params.put("tableName","UseWaterDataOfMonth");
		// 开始时间，结束时间
		if(query_startTime != null){
			String[] strUy = query_startTime.split("-");
			//params.put("startTime", query_startTime);
			params.put("year", strUy[0]);
			params.put("month", strUy[1]);
	   		params.put("queryTime", query_startTime+"-01");
		}else{
			params.put("year", null);
			params.put("month", null);
			params.put("queryTime",null);
			//params.put("startTime", null);
	   		//params.put("endTime", null);
		}
		//定义两个list接受时间与用水量
				List<Object> dayList = new ArrayList<>();
				List<Object> sumwaterList = new ArrayList<>();
				int i=1;
   		// 分页
		String pageNo = request.getParameter("pageNo");
		int count = useWaterService.getCount(params);
		
		// 按条件统计计算所有用水量
		if(count != 0){
			// 计算总用水量
			BigDecimal useWaterSum = useWaterService.getUseWaterStatisticsOfMonth(params);
			//月统计（按天显示柱状图）
			//List<RptUseWaterDetail> monthdayList = useWaterService.selectmonthday(query_startTime+"-01");
			List<RptUseWaterDetail> monthdayList = useWaterService.selectmonthday(params);
			//遍历拿到每个小时的  时间   总用水量   （若某一时段的总用水量为空则补为0）
			for (RptUseWaterDetail rptUseWaterDetail : monthdayList) {
				if(i != rptUseWaterDetail.getInDay()){
					while(i != rptUseWaterDetail.getInDay()){
						sumwaterList.add(0);
						dayList.add(i);
						i++;
					}
					if(i == rptUseWaterDetail.getInDay()){
						sumwaterList.add(rptUseWaterDetail.getSumusewater());
						dayList.add(rptUseWaterDetail.getInDay());
						i++;
					}
				}else{
					sumwaterList.add(rptUseWaterDetail.getSumusewater());
					dayList.add(rptUseWaterDetail.getInDay());
					i++;
				}
			}
			//获取当前小时数
			Calendar instance = Calendar.getInstance();
			int j = instance.get(instance.DATE);
			while(i<=j){
				sumwaterList.add(0);
				dayList.add(i);
				i++;
			}
			mav.addObject("dayList", dayList);
			mav.addObject("sumdaywaterList", sumwaterList);
			
			if(useWaterSum != null){
				mav.addObject("useWaterSum", useWaterSum);
			}else{
				mav.addObject("useWaterSum", 0);
			}
		}else{
			mav.addObject("useWaterSum", 0);
		}
		
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取历史数据  用于显示
		List<UseWaterDataOfDay> useWaterDetails = useWaterService.getWaterDataOfMonthInfo(params);
		
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("uwdList", useWaterDetails);
		mav.addObject("startTime", query_startTime);
		mav.addObject("dCode", dCode_query);
		return mav;
	}
	
	/**
	 * @Title: useWaterOfYear
	 * @Description: 年用水统计头部页面
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value = "/useWaterOfYear", method = RequestMethod.GET)
	public ModelAndView useWaterOfYear(HttpServletRequest request){
		ModelAndView mav = null;
		// 根据登录用户  获取其水管区域或者行政区域信息
    	SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
    	int areaWay = login_user.getAreaWay();
    	
    	// 水管区域方式查看
    	if(areaWay == 0){
    		mav = new ModelAndView(useWaterByAreaOfYearJsp);
		// 行政区域方式查看
    	}else if(areaWay == 1){
    		mav = new ModelAndView(useWaterOfYearJsp);
    	}
		
		// 获取所有的水管区域信息集合
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		// 获取当前登陆用户所属水管区域及所有子区域
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, login_user.getWaterAreaId());
		List<SysWellUse> list = sysWellUseService.getList(null);
		mav.addObject("wellList", list);
		mav.addObject("sysWaterAreaList", sysWaterAreaList);
		mav.addObject("sTime", new Date());
		return mav;
	}
	
	/**
	 * @Title: useWaterListOfYear
	 * @Description: 年用水信息列表 行政水管区域
	 * @param: @param request
	 * @param: @param query_startTime
	 * @param: @param query_endTime
	 * @param: @param areaType
	 * @param: @throws ParseException
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value = "/useWaterListOfYear", method = RequestMethod.POST)
	public ModelAndView useWaterListOfYear(HttpServletRequest request, String query_startTime, String areaType) throws ParseException{
		ModelAndView mav = new ModelAndView(useWaterListOfYearJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		// 区域编码
		String nodeIds = request.getParameter("nodeIds");
		
		// 根据区域类型显示数据
		if(areaType.equals("area")){
			List<String> areaIdList = null;
			if(StringUtils.isNotBlank(nodeIds)){
				areaIdList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
			}else{
				//无id，则置空
				areaIdList = null;
			}
			params.put("areaIdList", areaIdList);
			String wellUse = request.getParameter("wellUse");
			params.put("wellUse", wellUse);
			params.put("deviceType", request.getParameter("deviceType"));
			// 水管编码
			String waterAreaId = request.getParameter("waterAreaId");
			if(null==waterAreaId || "".equals(waterAreaId)) {
				SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
				waterAreaId = login_user.getWaterAreaId();
			}
			// 查询所有的水管区域信息集合
			List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
			// 递归获取当前所选的水管区域的子区域信息
			List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
			// 将递归获取到的水管区域信息Id 存放到集合中
			List<String> waterAreaIdsList = new ArrayList<String>();
			for(SysWaterArea swa : sysWaterAreaList) {
				waterAreaIdsList.add(swa.getId());
			}
			params.put("waterAreaIdsList", waterAreaIdsList);
		}else if(areaType.equals("waterArea")){
			List<String> waterAreaIdsList = null;
			if(StringUtils.isNotBlank(nodeIds)){
				waterAreaIdsList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
			}else{
				//无id，则置空
				waterAreaIdsList = null;
			}
			params.put("waterAreaIdsList", waterAreaIdsList);
		}
		
		// 设备编码
		String dCode_query = request.getParameter("dCode_query");
		try {
			if(StringUtils.isNotBlank(dCode_query)){
				params.put("dCode_query", dCode_query.trim());
			}else{
				params.put("dCode_query", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 设备名称
		String dName_query = request.getParameter("dName_query");
		try {
			if(StringUtils.isNotBlank(dName_query)){
				params.put("dName_query", dName_query.trim());
			}else{
				params.put("dName_query", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		params.put("tableName","UseWaterDataOfYear");
		// 开始时间，结束时间
		if(query_startTime != null){
			String[] strUy = query_startTime.split("-");
			//params.put("startTime", query_startTime);
			params.put("year", strUy[0]);
			params.put("queryTime", query_startTime+"-01-01");

	   		//params.put("endTime", query_endTime+" 23:59:59");
		}else{
			params.put("year", null);
			params.put("queryTime", null);
			//params.put("startTime", null);
	   		//params.put("endTime", null);
		}
		//定义两个list接受时间与用水量
		List<Object> monthList = new ArrayList<>();
		List<Object> sumyearwaterList = new ArrayList<>();
		int i=1;
   		// 分页
		String pageNo = request.getParameter("pageNo");
		int count = useWaterService.getCount(params);
		// 按条件统计计算所有用水量
		if(count != 0){
			// 计算总用水量
			BigDecimal useWaterSum = useWaterService.getUseWaterStatisticsOfYear(params);
			
			//年统计（按月显示柱状图）
			//List<RptUseWaterDetail> yearmonthList = useWaterService.selectyearmonth(query_startTime+"-01-01");
			List<RptUseWaterDetail> yearmonthList = useWaterService.selectyearmonth(params);
			//遍历拿到每个小时的  时间   总用水量   （若某一时段的总用水量为空则补为0）
			for (RptUseWaterDetail rptUseWaterDetail : yearmonthList) {
				if(i != rptUseWaterDetail.getInMonth()){
					while(i != rptUseWaterDetail.getInMonth()){
						sumyearwaterList.add(0);
						monthList.add(i);
						i++;
					}
					if(i == rptUseWaterDetail.getInMonth()){
						sumyearwaterList.add(rptUseWaterDetail.getSumusewater());
						monthList.add(rptUseWaterDetail.getInMonth());
						i++;
					}
				}else{
					sumyearwaterList.add(rptUseWaterDetail.getSumusewater());
					monthList.add(rptUseWaterDetail.getInMonth());
					i++;
				}
			}
			//获取当前月份数
			Calendar instance = Calendar.getInstance();
			int j = instance.get(instance.MONTH)+1;
			while(i<=j){
				sumyearwaterList.add(0);
				monthList.add(i);
				i++;
			}
			mav.addObject("monthList", monthList);
			mav.addObject("sumyearwaterList", sumyearwaterList);
			
			if(useWaterSum != null){
				mav.addObject("useWaterSum", useWaterSum);
			}else{
				mav.addObject("useWaterSum", 0);
			}
		}else{
			mav.addObject("useWaterSum", 0);
			int k = 1;
			if(query_startTime.compareTo(new SimpleDateFormat("YYYY").format(new Date()).toString()) < 0){
				while(k < 13){
					sumyearwaterList.add(0);
					monthList.add(k);
					k++;
				}
			}else{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				int monNum = calendar.get(Calendar.MONTH)+1;
				while(k < monNum){
					sumyearwaterList.add(0);
					monthList.add(k);
					k++;
				}
				
			}
			mav.addObject("monthList", monthList);
			mav.addObject("sumyearwaterList", sumyearwaterList);
		}
		
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取历史数据  用于显示
		List<UseWaterDataOfDay> useWaterDetails = useWaterService.getWaterDataOfYearInfo(params);
		
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("uwdList", useWaterDetails);
		mav.addObject("startTime", query_startTime);
		mav.addObject("dCode", dCode_query);
		return mav;
	}
	
	/**
	 * @Title: exportUseWaterInfo
	 * @Description: 日、月、年报表导出
	 * @param: @param request
	 * @param: @param response
	 * @param: @param type
	 * @return: void
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="/exportUseWaterInfo")
	@ResponseBody
	public void exportUseWaterInfo(HttpServletRequest request, HttpServletResponse response, String type,String wellUse) {
		Map<String, Object> params = new HashMap<String, Object>();
		// 行政编码  水管区域方式没有此信息
		String nodeIds = request.getParameter("areaIds");
		if(nodeIds != null && !nodeIds.equals("")){
			List<String> areaIdList = null;
			if(StringUtils.isNotBlank(nodeIds)){
				areaIdList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
			}else{
				//无id，则置空
				areaIdList = null;
			}
			params.put("areaIdList", areaIdList);
		}
		
		// 水管编码
		String waterAreaId = request.getParameter("waterAreaId");
		if(waterAreaId != null && !waterAreaId.equals("")){
			List<String> waterAreaIdList = null;
			if(StringUtils.isNotBlank(waterAreaId)){
				waterAreaIdList = new ArrayList<>(Arrays.asList(waterAreaId.split(",")));
			}else{
				//无id，则置空
				waterAreaIdList = null;
			}
			params.put("waterAreaIdsList", waterAreaIdList);
		}
		
		// 设备名称
		String dName = request.getParameter("dName");
		try {
			if(StringUtils.isNotBlank(dName)){
				params.put("dName_query", dName.trim());
			}else{
				params.put("dName_query", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 设备编码
		String dCode = request.getParameter("dCode");
		try {
			if(StringUtils.isNotBlank(dCode)){
				params.put("dCode_query", dCode.trim());
			}else{
				params.put("dCode_query", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		// 开始时间，结束时间query_startTime
		String query_startTime = request.getParameter("query_startTime");
		String[] strUy = query_startTime.split("-");
		params.put("wellUse", wellUse);
		// 根据报表类型 选择时间参数 只有日报表有结束时间
		if(type.equals("day")){
			params.put("year", strUy[0]);
			params.put("month", strUy[1]);
			params.put("day", strUy[2]);
			// 日用水数据集合
			List<useWaterOfDayByExcelVO> getUseWaterOfDayList = useWaterService.getListOfDayByExcel(params);
			// excel导出
	        try {
	        	ExportExcel.exportExcel(getUseWaterOfDayList, "日用水统计报表", "日用水统计报表", useWaterOfDayByExcelVO.class, "日用水统计报表.xls", response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(type.equals("month")){
			params.put("year", strUy[0]);
			params.put("month", strUy[1]);
			// 月用水数据集合
			List<useWaterOfMonthByExcelVO> getUseWaterOfMonthList = useWaterService.getListOfMonthByExcel(params);
			// excel导出
	        try {
	        	ExportExcel.exportExcel(getUseWaterOfMonthList, "月用水统计报表", "月用水统计报表", useWaterOfMonthByExcelVO.class, "月用水统计报表.xls", response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(type.equals("year")){
			params.put("year", strUy[0]);
			// 年用水数据集合
			List<useWaterOfYearByExcelVO> getUseWaterOfYearList = useWaterService.getListOfYearByExcel(params);
			// excel导出
	        try {
	        	ExportExcel.exportExcel(getUseWaterOfYearList, "年用水统计报表", "年用水统计报表", useWaterOfYearByExcelVO.class, "年用水统计报表.xls", response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}