package com.fourfaith.statisticAnalysis.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.siteManage.service.SysAreaService;
import com.fourfaith.statisticAnalysis.model.RptChargeDetail;
import com.fourfaith.statisticAnalysis.model.RptChargeDetailVO;
import com.fourfaith.statisticAnalysis.service.RptChargeDetailService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AreaRecursionUtil;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.ExportExcel;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.PersonDateUtils;
import com.fourfaith.utils.StringUtils;
import com.fourfaith.utils.WaterAreaRecursionUtil;

/**
 * @ClassName: RptChargeDetailController
 * @Description: 售水信息控制器
 * @Author: zhaojx
 * @Date: 2018年4月13日 下午2:24:39
 */
@Controller
@RequestMapping(value="/saleWaterInfo")
public class RptChargeDetailController {

	protected static final String saleWaterJsp = "/page/statistic/saleWater/saleWater";
	protected static final String saleWaterListJsp = "/page/statistic/saleWater/saleWaterList";
	protected static final String saleWaterByAreaJsp = "/page/statistic/saleWater/saleWaterByArea";
	
	@Autowired
	private RptChargeDetailService rptChargeDetailService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private SysAreaService sysAreaService;
	
	// 获取当前年份 
	Calendar cal = Calendar.getInstance();
	int currentYear = cal.get(Calendar.YEAR);
	// 售水统计图表 y轴单位 万元转换
	BigDecimal zhDecimal = new BigDecimal(10000);
	
	/**
	 * @Title: saleWaterInfo
	 * @Description: 售水记录初始页
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/saleWaterInfo", method = RequestMethod.GET)
	public ModelAndView saleWaterInfo(HttpServletRequest request) {
		ModelAndView mav = null;
		// 根据登录用户  获取其水管区域或者行政区域信息
    	SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
    	int areaWay = login_user.getAreaWay();
    	
    	// 水管区域方式查看
    	if(areaWay == 0){
    		mav = new ModelAndView(saleWaterByAreaJsp);
		// 行政区域方式查看
    	}else if(areaWay == 1){
    		mav = new ModelAndView(saleWaterJsp);
    	}
		
		// 获取所有的水管区域信息集合
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		// 获取当前登陆用户所属水管区域及所有子区域
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, login_user.getWaterAreaId());
		mav.addObject("sysWaterAreaList", sysWaterAreaList);
		
		// 售水记录统计日期默认当年第一天
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear);
		Date currYearFirst = calendar.getTime();
		
		mav.addObject("startTime", currYearFirst);
		mav.addObject("endTime", new Date());
		return mav;
	}
	
	/**
	 * @Title: saleWaterInfoList
	 * @Description: 水卡充值记录查询  行政区域方式
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/saleWaterInfoList", method = RequestMethod.POST)
	public ModelAndView saleWaterInfoList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(saleWaterListJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		// 查询条件
		String id = request.getParameter("id");
		String waterAreaId = request.getParameter("waterAreaId");
		String deviceName = request.getParameter("deviceName");
		String cardCode = request.getParameter("cardCode");
		
		// 获取所有行政区域信息集合
		List<SysArea> sysAllAreaList = sysAreaService.getAllArea();
		// 获取当前区域及所有子区域信息
		List<SysArea> sysAreaList = AreaRecursionUtil.getCurrAndChildAreaList(sysAllAreaList, id);
		List<String> areaIdsList = new ArrayList<String>();
		for(SysArea sa : sysAreaList) {
			areaIdsList.add(sa.getId());
		}
		// 将行政区域加入查询条件中
		params.put("areaIdsList", areaIdsList);
		
		if(null==waterAreaId || "".equals(waterAreaId)) {
			SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
			waterAreaId = login_user.getWaterAreaId();
		}
		// 获取所有水管区域信息集合
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		// 获取当前区域及所有子区域信息
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
		List<String> waterAreaIdsList = new ArrayList<String>();
		for(SysWaterArea swa : sysWaterAreaList) {
			waterAreaIdsList.add(swa.getId());
		}
		params.put("waterAreaIdsList", waterAreaIdsList);
		
		// 机井名称
		if(!StringUtils.isNullOrEmpty(deviceName)) {
			params.put("deviceName", deviceName.trim());
		} else {
			params.put("deviceName", null);
		}
		// 水卡卡号
		if(!StringUtils.isNullOrEmpty(cardCode)) {
			params.put("cardCode", cardCode.trim());
		} else {
			params.put("cardCode", null);
		}
		// 开始时间  结束时间
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if(startTime != null && endTime != null){
			params.put("startTime", startTime);
	   		params.put("endTime", endTime);
		}else{
			params.put("startTime", null);
	   		params.put("endTime", null);
		}
		
		// 获取水量、金额统计
		RptChargeDetail rptDetail = rptChargeDetailService.getChargeAndWaterAmount(params);
		
		// 一月
		params.put("startDate", PersonDateUtils.start_1);
		params.put("endDate", PersonDateUtils.end_1);
		RptChargeDetail amount_1 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 二月
		if ((currentYear%4 == 0 && currentYear%100 != 0) || currentYear%400 == 0) {
			params.put("startDate", currentYear + "-02-01 00:00:00");
			params.put("endDate", currentYear + "-02-29 23:59:59");
		} else {
			params.put("startDate", currentYear + "-02-01 00:00:00");
			params.put("endDate", currentYear + "-02-28 23:59:59");
		}
		RptChargeDetail amount_2 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 三月
		params.put("startDate", PersonDateUtils.start_3);
		params.put("endDate", PersonDateUtils.end_3);
		RptChargeDetail amount_3 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 四月
		params.put("startDate", PersonDateUtils.start_4);
		params.put("endDate", PersonDateUtils.end_4);
		RptChargeDetail amount_4 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 五月
		params.put("startDate", PersonDateUtils.start_5);
		params.put("endDate", PersonDateUtils.end_5);
		RptChargeDetail amount_5 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 六月
		params.put("startDate", PersonDateUtils.start_6);
		params.put("endDate", PersonDateUtils.end_6);
		RptChargeDetail amount_6 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 七月
		params.put("startDate", PersonDateUtils.start_7);
		params.put("endDate", PersonDateUtils.end_7);
		RptChargeDetail amount_7 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 八月
		params.put("startDate", PersonDateUtils.start_8);
		params.put("endDate", PersonDateUtils.end_8);
		RptChargeDetail amount_8 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 九月
		params.put("startDate", PersonDateUtils.start_9);
		params.put("endDate", PersonDateUtils.end_9);
		RptChargeDetail amount_9 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 十月
		params.put("startDate", PersonDateUtils.start_10);
		params.put("endDate", PersonDateUtils.end_10);
		RptChargeDetail amount_10 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 十一月
		params.put("startDate", PersonDateUtils.start_11);
		params.put("endDate", PersonDateUtils.end_11);
		RptChargeDetail amount_11 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 十二月
		params.put("startDate", PersonDateUtils.start_12);
		params.put("endDate", PersonDateUtils.end_12);
		RptChargeDetail amount_12 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		
		// 分页操作
		String pageNo = request.getParameter("pageNo");
		// 获取分页条数
		int count = rptChargeDetailService.getChargeCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取设备机井基础信息历史数据
		List<RptChargeDetail> saleWaterList = rptChargeDetailService.getChargeList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("saleWaterList", saleWaterList);
		mav.addObject("waterAreaId", waterAreaId);
		mav.addObject("cardCode", cardCode);
		mav.addObject("deviceName", deviceName);
		mav.addObject("startTime", startTime);
		mav.addObject("endTime", endTime);
		if(rptDetail != null){
			mav.addObject("chargeAmount", rptDetail.getChargeAmount());
			mav.addObject("waterAmount", rptDetail.getWaterAmount());
		}else{
			mav.addObject("chargeAmount", 0.0);
			mav.addObject("waterAmount", 0.0);
		}
		
		// 按月份统计返回数据 一月
		if(amount_1 == null){
			mav.addObject("charge_1", 0);
			mav.addObject("water_1", 0);
		}else{
			mav.addObject("charge_1", amount_1.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_1", amount_1.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 二月
		if(amount_2 == null){
			mav.addObject("charge_2", 0);
			mav.addObject("water_2", 0);
		}else{
			mav.addObject("charge_2", amount_2.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_2", amount_2.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 三月
		if(amount_3 == null){
			mav.addObject("charge_3", 0);
			mav.addObject("water_3", 0);
		}else{
			mav.addObject("charge_3", amount_3.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_3", amount_3.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 四月
		if(amount_4 == null){
			mav.addObject("charge_4", 0);
			mav.addObject("water_4", 0);
		}else{
			mav.addObject("charge_4", amount_4.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_4", amount_4.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 五月
		if(amount_5 == null){
			mav.addObject("charge_5", 0);
			mav.addObject("water_5", 0);
		}else{
			mav.addObject("charge_5", amount_5.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_5", amount_5.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 六月
		if(amount_6 == null){
			mav.addObject("charge_6", 0);
			mav.addObject("water_6", 0);
		}else{
			mav.addObject("charge_6", amount_6.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_6", amount_6.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 七月
		if(amount_7 == null){
			mav.addObject("charge_7", 0);
			mav.addObject("water_7", 0);
		}else{
			mav.addObject("charge_7", amount_7.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_7", amount_7.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 八月
		if(amount_8 == null){
			mav.addObject("charge_8", 0);
			mav.addObject("water_8", 0);
		}else{
			mav.addObject("charge_8", amount_8.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_8", amount_8.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 九月
		if(amount_9 == null){
			mav.addObject("charge_9", 0);
			mav.addObject("water_9", 0);
		}else{
			mav.addObject("charge_9", amount_9.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_9", amount_9.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 十月
		if(amount_10 == null){
			mav.addObject("charge_10", 0);
			mav.addObject("water_10", 0);
		}else{
			mav.addObject("charge_10", amount_10.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_10", amount_10.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 十一月
		if(amount_11 == null){
			mav.addObject("charge_11", 0);
			mav.addObject("water_11", 0);
		}else{
			mav.addObject("charge_11", amount_11.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_11", amount_11.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 十二月
		if(amount_12 == null){
			mav.addObject("charge_12", 0);
			mav.addObject("water_12", 0);
		}else{
			mav.addObject("charge_12", amount_12.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_12", amount_12.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		return mav;
	}
	
	/**
	 * @Title: saleWaterInfoListByArea
	 * @Description: 水卡充值记录查询  水管区域方式
	 * @return: ModelAndView
	 * @Author: zhaojx
	 * @Date: 2017年11月20日 下午5:52:14
	 */
	@RequestMapping(value = "/saleWaterInfoListByArea", method = RequestMethod.POST)
	public ModelAndView saleWaterInfoListByArea(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(saleWaterListJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		
		// 查询条件
		String id = request.getParameter("id");
		String deviceName = request.getParameter("deviceName");
		String cardCode = request.getParameter("cardCode");
		
		// 获取所有水管区域信息集合
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		// 获取当前水管区域及所有子区域信息
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, id);
		List<String> waterAreaIdsList = new ArrayList<String>();
		for(SysWaterArea swa : sysWaterAreaList) {
			waterAreaIdsList.add(swa.getId());
		}
		// 将水管区域加入查询条件中
		params.put("waterAreaIdsList", waterAreaIdsList);
		
		// 机井名称和卡号条件
		if(!StringUtils.isNullOrEmpty(deviceName)) {
			params.put("deviceName", deviceName.trim());
		} else {
			params.put("deviceName", null);
		}
		// 水卡卡号
		if(!StringUtils.isNullOrEmpty(cardCode)) {
			params.put("cardCode", cardCode);
		} else {
			params.put("cardCode", null);
		}
		// 开始时间  结束时间
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if(startTime != null && endTime != null){
			params.put("startTime", startTime);
	   		params.put("endTime", endTime);
		}else{
			params.put("startTime", null);
	   		params.put("endTime", null);
		}
		
		// 获取水量、金额统计
		RptChargeDetail rptDetail = rptChargeDetailService.getChargeAndWaterAmount(params);
		
		// 一月
		params.put("startDate", PersonDateUtils.start_1);
		params.put("endDate", PersonDateUtils.end_1);
		RptChargeDetail amount_1 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 二月
		if ((currentYear%4 == 0 && currentYear%100 != 0) || currentYear%400 == 0) {
			params.put("startDate", currentYear + "-02-01 00:00:00");
			params.put("endDate", currentYear + "-02-29 23:59:59");
		} else {
			params.put("startDate", currentYear + "-02-01 00:00:00");
			params.put("endDate", currentYear + "-02-28 23:59:59");
		}
		RptChargeDetail amount_2 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 三月
		params.put("startDate", PersonDateUtils.start_3);
		params.put("endDate", PersonDateUtils.end_3);
		RptChargeDetail amount_3 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 四月
		params.put("startDate", PersonDateUtils.start_4);
		params.put("endDate", PersonDateUtils.end_4);
		RptChargeDetail amount_4 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 五月
		params.put("startDate", PersonDateUtils.start_5);
		params.put("endDate", PersonDateUtils.end_5);
		RptChargeDetail amount_5 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 六月
		params.put("startDate", PersonDateUtils.start_6);
		params.put("endDate", PersonDateUtils.end_6);
		RptChargeDetail amount_6 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 七月
		params.put("startDate", PersonDateUtils.start_7);
		params.put("endDate", PersonDateUtils.end_7);
		RptChargeDetail amount_7 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 八月
		params.put("startDate", PersonDateUtils.start_8);
		params.put("endDate", PersonDateUtils.end_8);
		RptChargeDetail amount_8 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 九月
		params.put("startDate", PersonDateUtils.start_9);
		params.put("endDate", PersonDateUtils.end_9);
		RptChargeDetail amount_9 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 十月
		params.put("startDate", PersonDateUtils.start_10);
		params.put("endDate", PersonDateUtils.end_10);
		RptChargeDetail amount_10 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 十一月
		params.put("startDate", PersonDateUtils.start_11);
		params.put("endDate", PersonDateUtils.end_11);
		RptChargeDetail amount_11 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		// 十二月
		params.put("startDate", PersonDateUtils.start_12);
		params.put("endDate", PersonDateUtils.end_12);
		RptChargeDetail amount_12 = rptChargeDetailService.getChargeAndWaterSumByMonth(params);
		
		// 分页操作
		String pageNo = request.getParameter("pageNo");
		// 获取分页条数
		int count = rptChargeDetailService.getChargeCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取设备机井基础信息历史数据
		List<RptChargeDetail> saleWaterList = rptChargeDetailService.getChargeList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("saleWaterList", saleWaterList);
		mav.addObject("cardCode", cardCode);
		mav.addObject("deviceName", deviceName);
		mav.addObject("startTime", startTime);
		mav.addObject("endTime", endTime);
		if(rptDetail != null){
			mav.addObject("chargeAmount", rptDetail.getChargeAmount());
			mav.addObject("waterAmount", rptDetail.getWaterAmount());
		}else{
			mav.addObject("chargeAmount", 0.0);
			mav.addObject("waterAmount", 0.0);
		}
		
		// 按月份统计返回数据 一月
		if(amount_1 == null){
			mav.addObject("charge_1", 0);
			mav.addObject("water_1", 0);
		}else{
			mav.addObject("charge_1", amount_1.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_1", amount_1.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 二月
		if(amount_2 == null){
			mav.addObject("charge_2", 0);
			mav.addObject("water_2", 0);
		}else{
			mav.addObject("charge_2", amount_2.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_2", amount_2.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 三月
		if(amount_3 == null){
			mav.addObject("charge_3", 0);
			mav.addObject("water_3", 0);
		}else{
			mav.addObject("charge_3", amount_3.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_3", amount_3.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 四月
		if(amount_4 == null){
			mav.addObject("charge_4", 0);
			mav.addObject("water_4", 0);
		}else{
			mav.addObject("charge_4", amount_4.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_4", amount_4.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 五月
		if(amount_5 == null){
			mav.addObject("charge_5", 0);
			mav.addObject("water_5", 0);
		}else{
			mav.addObject("charge_5", amount_5.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_5", amount_5.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 六月
		if(amount_6 == null){
			mav.addObject("charge_6", 0);
			mav.addObject("water_6", 0);
		}else{
			mav.addObject("charge_6", amount_6.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_6", amount_6.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 七月
		if(amount_7 == null){
			mav.addObject("charge_7", 0);
			mav.addObject("water_7", 0);
		}else{
			mav.addObject("charge_7", amount_7.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_7", amount_7.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 八月
		if(amount_8 == null){
			mav.addObject("charge_8", 0);
			mav.addObject("water_8", 0);
		}else{
			mav.addObject("charge_8", amount_8.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_8", amount_8.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 九月
		if(amount_9 == null){
			mav.addObject("charge_9", 0);
			mav.addObject("water_9", 0);
		}else{
			mav.addObject("charge_9", amount_9.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_9", amount_9.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 十月
		if(amount_10 == null){
			mav.addObject("charge_10", 0);
			mav.addObject("water_10", 0);
		}else{
			mav.addObject("charge_10", amount_10.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_10", amount_10.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 十一月
		if(amount_11 == null){
			mav.addObject("charge_11", 0);
			mav.addObject("water_11", 0);
		}else{
			mav.addObject("charge_11", amount_11.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_11", amount_11.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		// 十二月
		if(amount_12 == null){
			mav.addObject("charge_12", 0);
			mav.addObject("water_12", 0);
		}else{
			mav.addObject("charge_12", amount_12.getChargeAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
			mav.addObject("water_12", amount_12.getWaterAmount().divide(zhDecimal, 2, BigDecimal.ROUND_HALF_UP));
		}
		return mav;
	}
	
	/**
	 * @Title: exportSaleWater
	 * @Description: 水卡充值记录导出(售水记录)
	 * @param: @param request
	 * @param: @param response
	 * @return: void
	 */
	@RequestMapping(value="/exportSaleWater")
	public void exportSaleWater(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> params = new HashMap<String, Object>();
		// 行政区域方式 行政树菜单
		String areaId = request.getParameter("areaId");
		if(areaId != null && !areaId.equals("")){
			// 获取所有行政区域信息集合
			List<SysArea> sysAllAreaList = sysAreaService.getAllArea();
			// 获取当前区域及所有子区域信息
			List<SysArea> sysAreaList = AreaRecursionUtil.getCurrAndChildAreaList(sysAllAreaList, areaId);
			List<String> areaIdsList = new ArrayList<String>();
			for(SysArea sa : sysAreaList) {
				areaIdsList.add(sa.getId());
			}
			// 将行政区域加入查询条件中
			params.put("areaIdsList", areaIdsList);
		}else{
			params.put("areaIdsList", null);
		}
		
		// 行政区域方式 水管区域下拉框
		String selWaterAreaId = request.getParameter("selWaterAreaId");
		if(selWaterAreaId != null && !selWaterAreaId.equals("")) {
			params.put("selWaterAreaId", selWaterAreaId);
		} else {
			params.put("selWaterAreaId", null);
		}
		
		// 水管区域方式 水管树菜单
		String waterAreaId = request.getParameter("waterAreaId");
		if(waterAreaId != null && !waterAreaId.equals("")){
			// 获取所有水管区域信息集合
			List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
			// 获取当前水管区域及所有子区域信息
			List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
			List<String> waterAreaIdsList = new ArrayList<String>();
			for(SysWaterArea swa : sysWaterAreaList) {
				waterAreaIdsList.add(swa.getId());
			}
			// 将水管区域加入查询条件中
			params.put("waterAreaIdsList", waterAreaIdsList);
		}else{
			params.put("waterAreaIdsList", null);
		}

		// 查询条件放入map
		String deviceName = request.getParameter("deviceName");
		String cardCode = request.getParameter("cardCode");
		// 机井名称和卡号条件
		if(!StringUtils.isNullOrEmpty(deviceName)) {
			params.put("deviceName", deviceName.trim());
		} else {
			params.put("deviceName", null);
		}
		// 水卡卡号
		if(!StringUtils.isNullOrEmpty(cardCode)) {
			params.put("cardCode", cardCode);
		} else {
			params.put("cardCode", null);
		}
		
		// 开始时间  结束时间
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if(startTime != null && endTime != null){
			params.put("startTime", startTime);
	   		params.put("endTime", endTime);
		}else{
			params.put("startTime", null);
	   		params.put("endTime", null);
		}
			
		// 导出信息查询
		List<RptChargeDetailVO> saleWaterList = rptChargeDetailService.getExcelList(params);
		// excel导出
        try {
        	ExportExcel.exportExcel(saleWaterList, "售水记录报表", "售水记录报表", RptChargeDetailVO.class, "售水记录报表.xls", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}