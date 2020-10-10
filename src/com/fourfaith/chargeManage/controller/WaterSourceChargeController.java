package com.fourfaith.chargeManage.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fourfaith.basicInformation.model.SysWellUse;
import com.fourfaith.basicInformation.service.SysWellUseService;
import com.fourfaith.chargeManage.model.WaterPriceAmountVO;
import com.fourfaith.chargeManage.model.WaterSourceCharge;
import com.fourfaith.chargeManage.service.WaterSourceChargeService;
import com.fourfaith.paramterManage.model.ParamMaintainPrice;
import com.fourfaith.paramterManage.model.ParamMeasureTypePrice;
import com.fourfaith.paramterManage.model.ParamWaterSourcePrice;
import com.fourfaith.paramterManage.service.ParamMaintainPriceService;
import com.fourfaith.paramterManage.service.ParamMeasureTypePriceService;
import com.fourfaith.paramterManage.service.ParamWaterSourcePriceService;
import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.siteManage.service.SysAreaService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AreaRecursionUtil;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.ExportExcel;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.WaterAreaRecursionUtil;

/**
 * @ClassName: WaterSourceChargeController
 * @Description: 其他费用统计(计量水费-水资源费-末级渠系水费)Controller
 * @Author: zhaojinxin
 * @Date: 2018年9月18日 下午4:12:48
 */
@Controller
@RequestMapping(value="/waterSourceCharge")
public class WaterSourceChargeController {
	
	protected static final String indexJsp = "/page/chargeManage/waterSourceCharge/waterSourceChargeIndex";
	protected static final String waterSourceChargeJsp = "/page/chargeManage/waterSourceCharge/waterSourceCharge";
	
	@Autowired
	private ParamWaterSourcePriceService paramWaterSourcePriceService;
	@Autowired
	private ParamMeasureTypePriceService paramMeasureTypePriceService;
	@Autowired
	private ParamMaintainPriceService paramMaintainPriceService;
	@Autowired
	private SysAreaService sysAreaService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private WaterSourceChargeService waterSourceChargeService;
	@Autowired
	private SysWellUseService sysWellUseService;
	
	// 获取当前年份 
	Calendar cal = Calendar.getInstance();
	int currentYear = cal.get(Calendar.YEAR);
	
	/**
	 * @Title: index
	 * @Description: 其他费用统计(基本水费-计量水费-水资源费-末级渠系水费)初始页面
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(indexJsp);
		Map<String,Object> params = new HashMap<String,Object>();
		List<SysWellUse> list = sysWellUseService.getList(params);
		// 费用统计日期默认当年第一天
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, currentYear);
		Date currYearFirst = calendar.getTime();
		
		mav.addObject("startTime", currYearFirst);
		mav.addObject("endTime", new Date());
		mav.addObject("wellList", list);
		return mav;
	}
	
	/**
	 * @Title: waterSourceChargeList
	 * @Description: 其他费用统计(基本水费-计量水费-水资源费-末级渠系水费) 行政区域方式
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="/waterSourceChargeList", method = RequestMethod.GET)
	public ModelAndView waterSourceChargeList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(waterSourceChargeJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("inYear", currentYear);
		
		// 购水总金额 
		double chargeAmountSum = 0;
		// 计量水费总金额
		double measureTypeAmountSum = 0;
		// 末级渠系费总金额
		double maintainPriceAmountSum = 0;
		// 水资源费总金额
		double waterSourcePriceAmountSum = 0;
		
		// 计量水费
		ParamMeasureTypePrice paramMeasureTypePrice = paramMeasureTypePriceService.findMeasurePriceByYear(currentYear);
		// 末级渠系费
		ParamMaintainPrice paramMaintainPrice = paramMaintainPriceService.findMaintainPriceByYear(currentYear);
		// 水资源费
		ParamWaterSourcePrice paramWaterSourcePrice = paramWaterSourcePriceService.getAllList(params);
		
		// 获取页面参数
		String id = request.getParameter("id");
		// 获取全部行政区域ID并递归查找该ID下的子行政区域
		List<SysArea> sysAllAreaList = sysAreaService.getAllArea();
		List<SysArea> sysAreaList = AreaRecursionUtil.getCurrAndChildAreaList(sysAllAreaList, id);
		List<String> areaIdList = new ArrayList<String>();
		for(SysArea sa : sysAreaList) {
			areaIdList.add(sa.getId());
		}
		params.put("areaIdList", areaIdList);
		
		// 查询条件
		String deviceName = request.getParameter("deviceName");
		String deviceCode = request.getParameter("deviceCode");
		if(!deviceName.equals("") && deviceName != null) {
			params.put("deviceName", deviceName.trim());
		} else {
			params.put("deviceName", null);
		}
		if(!deviceCode.equals("") && deviceCode != null) {
			params.put("deviceCode", deviceCode.trim());
		} else {
			params.put("deviceCode", null);
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
		String deviceWellUse = request.getParameter("deviceWellUse");
		if(StringUtils.isEmpty(deviceWellUse)){
			params.put("deviceWellUse", null);
		}else{
			params.put("deviceWellUse", deviceWellUse);
		}
		
		// 获取各种费用 不分页 统计全部
		List<WaterSourceCharge> sourceSumList = waterSourceChargeService.getPriceAmount(params);
		// 统计费率信息集合
		List<WaterSourceCharge> waterSourceSumList = new ArrayList<WaterSourceCharge>();
		if(sourceSumList != null && sourceSumList.size() > 0){
			for(WaterSourceCharge waterSource : sourceSumList){
				// 计量水费收费金额=购水量*计量水费费参数
				waterSource.setMeasureTypeAmount(waterSource.getWaterAmount().multiply(paramMeasureTypePrice.getMeasureTypePrice()));
				// 末级渠系费收费金额=购水量*末级渠系费参数
				waterSource.setMaintainAmount(waterSource.getWaterAmount().multiply(paramMaintainPrice.getMaintainPrice()));
				// 水资源费收费金额=购水量*水资源费参数
				waterSource.setWaterSourceAmount(waterSource.getWaterAmount().multiply(paramWaterSourcePrice.getWaterSourcePrice()));
				waterSourceSumList.add(waterSource);
			}
		}
		// 计算各种费率收费总金额
		if(null != waterSourceSumList && waterSourceSumList.size() > 0){
			for (int i = 0; i < waterSourceSumList.size(); i++) {
				// 购水总金额
				chargeAmountSum += waterSourceSumList.get(i).getChargeAmount().doubleValue();
				// 计量水费总金额
				measureTypeAmountSum += waterSourceSumList.get(i).getMeasureTypeAmount().doubleValue();
				// 末级渠系费总金额
				maintainPriceAmountSum += waterSourceSumList.get(i).getMaintainAmount().doubleValue();
				// 水资源费总金额
				waterSourcePriceAmountSum += waterSourceSumList.get(i).getWaterSourceAmount().doubleValue();
			}
		}
		
		// 分页参数
		String s_start = request.getParameter("pageNo");
		int count = waterSourceChargeService.getCount(params);
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		
		// 获取其他费率综合信息  分页  用于每页展示
		List<WaterSourceCharge> waterSourceChargeList = waterSourceChargeService.getWaterSourceList(params);
		// 计算各种费率信息  并放入新集合
		List<WaterSourceCharge> wscList = new ArrayList<WaterSourceCharge>();
		if(null != waterSourceChargeList && waterSourceChargeList.size() > 0){
			for(WaterSourceCharge wsc : waterSourceChargeList) {
				// 计量水费收费金额=购水量*计量水费费参数
				wsc.setMeasureTypeAmount(wsc.getWaterAmount().multiply(paramMeasureTypePrice.getMeasureTypePrice()));
				// 末级渠系费收费金额=购水量*末级渠系费参数
				wsc.setMaintainAmount(wsc.getWaterAmount().multiply(paramMaintainPrice.getMaintainPrice()));
				// 水资源费收费金额=购水量*水资源费参数
				wsc.setWaterSourceAmount(wsc.getWaterAmount().multiply(paramWaterSourcePrice.getWaterSourcePrice()));
				wscList.add(wsc);
			}
		}
		// 小数位转换
		DecimalFormat dfFormat = new DecimalFormat("#0.00");
		
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("currentYear", currentYear);
		// 将各种费率放入页面
		if(paramMeasureTypePrice != null){
			mav.addObject("measureTypePrice", paramMeasureTypePrice.getMeasureTypePrice());
		}
		if(paramMaintainPrice != null){
			mav.addObject("maintainPrice", paramMaintainPrice.getMaintainPrice());
		}
		if(paramWaterSourcePrice != null){
			mav.addObject("waterSourcePrice", paramWaterSourcePrice.getWaterSourcePrice());
		}
		mav.addObject("wscList", wscList);
		mav.addObject("chargeAmountSum", dfFormat.format(chargeAmountSum));
		mav.addObject("measureTypeAmountSum", dfFormat.format(measureTypeAmountSum));
		mav.addObject("maintainPriceAmountSum", dfFormat.format(maintainPriceAmountSum));
		mav.addObject("waterSourcePriceAmountSum", dfFormat.format(waterSourcePriceAmountSum));
		mav.addObject("startTime", startTime);
		mav.addObject("endTime", endTime);
		return mav;
	}
	
	/**
	 * @Title: waterSourceChargeListByWaterArea
	 * @Description: 水资源费统计列表  水管区域列表列表
	 * @param request
	 * @return: ModelAndView
	 * @Author: zhaojx
	 * @Date: 2017年11月17日 上午11:12:23
	 */
	@RequestMapping(value="/waterSourceChargeListByWaterArea", method = RequestMethod.GET)
	public ModelAndView waterSourceChargeListByWaterArea(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(waterSourceChargeJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("inYear", currentYear);
		
		// 购水总金额 
		double chargeAmountSum = 0;
		// 计量水费总金额
		double measureTypeAmountSum = 0;
		// 末级渠系费总金额
		double maintainPriceAmountSum = 0;
		// 水资源费总金额
		double waterSourcePriceAmountSum = 0;
		
		// 计量水费
		ParamMeasureTypePrice paramMeasureTypePrice = paramMeasureTypePriceService.findMeasurePriceByYear(currentYear);
		// 末级渠系费
		ParamMaintainPrice paramMaintainPrice = paramMaintainPriceService.findMaintainPriceByYear(currentYear);
		// 水资源费
		ParamWaterSourcePrice paramWaterSourcePrice = paramWaterSourcePriceService.getAllList(params);
		
		// 获取页面参数
		String id = request.getParameter("id");
		// 获取全部行政区域ID并递归查找该ID下的子行政区域
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, id);
		List<String> waterAreaIdsList = new ArrayList<String>();
		for(SysWaterArea swa : sysWaterAreaList) {
			waterAreaIdsList.add(swa.getId());
		}
		params.put("waterAreaIdsList", waterAreaIdsList);
		
		// 查询条件
		String deviceName = request.getParameter("deviceName");
		String deviceCode = request.getParameter("deviceCode");
		if(!deviceName.equals("") && deviceName != null) {
			params.put("deviceName", deviceName.trim());
		} else {
			params.put("deviceName", null);
		}
		if(!deviceCode.equals("") && deviceCode != null) {
			params.put("deviceCode", deviceCode.trim());
		} else {
			params.put("deviceCode", null);
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
		
		// 获取各种费用 不分页 统计全部
		List<WaterSourceCharge> sourceSumList = waterSourceChargeService.getPriceAmount(params);
		// 统计费率信息集合
		List<WaterSourceCharge> waterSourceSumList = new ArrayList<WaterSourceCharge>();
		if(sourceSumList != null && sourceSumList.size() > 0){
			for(WaterSourceCharge waterSource : sourceSumList){
				// 计量水费收费金额=购水量*计量水费费参数
				waterSource.setMeasureTypeAmount(waterSource.getWaterAmount().multiply(paramMeasureTypePrice.getMeasureTypePrice()));
				// 末级渠系费收费金额=购水量*末级渠系费参数
				waterSource.setMaintainAmount(waterSource.getWaterAmount().multiply(paramMaintainPrice.getMaintainPrice()));
				// 水资源费收费金额=购水量*水资源费参数
				waterSource.setWaterSourceAmount(waterSource.getWaterAmount().multiply(paramWaterSourcePrice.getWaterSourcePrice()));
				waterSourceSumList.add(waterSource);
			}
		}
		// 计算各种费率收费总金额
		if(null != waterSourceSumList && waterSourceSumList.size() > 0){
			for (int i = 0; i < waterSourceSumList.size(); i++) {
				// 购水总金额
				chargeAmountSum += waterSourceSumList.get(i).getChargeAmount().doubleValue();
				// 计量水费总金额
				measureTypeAmountSum += waterSourceSumList.get(i).getMeasureTypeAmount().doubleValue();
				// 末级渠系费总金额
				maintainPriceAmountSum += waterSourceSumList.get(i).getMaintainAmount().doubleValue();
				// 水资源费总金额
				waterSourcePriceAmountSum += waterSourceSumList.get(i).getWaterSourceAmount().doubleValue();
			}
		}
		
		// 分页参数
		String s_start = request.getParameter("pageNo");
		int count = waterSourceChargeService.getCount(params);
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		
		// 获取其他费率综合信息  分页  用于每页展示
		List<WaterSourceCharge> waterSourceChargeList = waterSourceChargeService.getWaterSourceList(params);
		// 计算各种费率信息  并放入新集合
		List<WaterSourceCharge> wscList = new ArrayList<WaterSourceCharge>();
		if(null != waterSourceChargeList && waterSourceChargeList.size() > 0){
			for(WaterSourceCharge wsc : waterSourceChargeList) {
				// 计量水费收费金额=购水量*计量水费费参数
				wsc.setMeasureTypeAmount(wsc.getWaterAmount().multiply(paramMeasureTypePrice.getMeasureTypePrice()));
				// 末级渠系费收费金额=购水量*末级渠系费参数
				wsc.setMaintainAmount(wsc.getWaterAmount().multiply(paramMaintainPrice.getMaintainPrice()));
				// 水资源费收费金额=购水量*水资源费参数
				wsc.setWaterSourceAmount(wsc.getWaterAmount().multiply(paramWaterSourcePrice.getWaterSourcePrice()));
				wscList.add(wsc);
			}
		}
		
		// 小数位转换
		DecimalFormat dfFormat = new DecimalFormat("#0.00");
		
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("currentYear", currentYear);
		// 将各种费率放入页面
		if(paramMeasureTypePrice != null){
			mav.addObject("measureTypePrice", paramMeasureTypePrice.getMeasureTypePrice());
		}
		if(paramMaintainPrice != null){
			mav.addObject("maintainPrice", paramMaintainPrice.getMaintainPrice());
		}
		if(paramWaterSourcePrice != null){
			mav.addObject("waterSourcePrice", paramWaterSourcePrice.getWaterSourcePrice());
		}
		mav.addObject("wscList", wscList);
		mav.addObject("chargeAmountSum", dfFormat.format(chargeAmountSum));
		mav.addObject("measureTypeAmountSum", dfFormat.format(measureTypeAmountSum));
		mav.addObject("maintainPriceAmountSum", dfFormat.format(maintainPriceAmountSum));
		mav.addObject("waterSourcePriceAmountSum", dfFormat.format(waterSourcePriceAmountSum));
		mav.addObject("startTime", startTime);
		mav.addObject("endTime", endTime);
		return mav;
	}
	
	/**
	 * @Title: exportWaterAmountInfo
	 * @Description: 其他费用统计(计量水费-水资源费-末级渠系水费)统计报表
	 * @param: @param deviceName
	 * @param: @param deviceCode
	 * @param: @param startTime
	 * @param: @param endTime
	 * @param: @param request
	 * @param: @param response
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="/exportWaterAmountInfo")
	public void exportWaterAmountInfo(String deviceName, String deviceCode, String startTime, String endTime, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("inYear", currentYear);
		
		// 计量水费
		ParamMeasureTypePrice paramMeasureTypePrice = paramMeasureTypePriceService.findMeasurePriceByYear(currentYear);
		// 末级渠系费
		ParamMaintainPrice paramMaintainPrice = paramMaintainPriceService.findMaintainPriceByYear(currentYear);
		// 水资源费
		ParamWaterSourcePrice paramWaterSourcePrice = paramWaterSourcePriceService.getAllList(params);
		
		// 行政编码
		String nodeIds = request.getParameter("areaIds");
		if(nodeIds != null && !nodeIds.equals("")){
			List<String> areaIdList = null;
			if(StringUtils.isNotBlank(nodeIds)){
				areaIdList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
			}else{
				areaIdList = null;
			}
			params.put("areaIdList", areaIdList);
		}
		
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
		
		// 设备编码  名称
		if(!deviceName.equals("") && deviceName != null) {
			params.put("deviceName", deviceName.trim());
		} else {
			params.put("deviceName", null);
		}
		if(!deviceCode.equals("") && deviceCode != null) {
			params.put("deviceCode", deviceCode.trim());
		} else {
			params.put("deviceCode", null);
		}
		// 开始时间  结束时间
		if(startTime != null && endTime != null){
			params.put("startTime", startTime);
	   		params.put("endTime", endTime);
		}else{
			params.put("startTime", null);
	   		params.put("endTime", null);
		}
		
		// 获取其他费率 总金额信息
		List<WaterPriceAmountVO> waterPriceAmountList = waterSourceChargeService.getWaterAmountExcelList(params);
		// 计算各种费率信息  并放入新集合
		List<WaterPriceAmountVO> waterPriceAmounts = new ArrayList<WaterPriceAmountVO>();
		if(null != waterPriceAmountList && waterPriceAmountList.size() > 0){
			for(WaterPriceAmountVO waterPriceAmount : waterPriceAmountList) {
				// 计量水费收费金额=购水量*计量水费费参数
				waterPriceAmount.setMeasureTypePrice(paramMeasureTypePrice.getMeasureTypePrice());
				waterPriceAmount.setMeasureTypeAmount(waterPriceAmount.getWaterAmount().multiply(paramMeasureTypePrice.getMeasureTypePrice()));
				// 末级渠系费收费金额=购水量*末级渠系费参数
				waterPriceAmount.setMaintainPrice(paramMaintainPrice.getMaintainPrice());
				waterPriceAmount.setMaintainAmount(waterPriceAmount.getWaterAmount().multiply(paramMaintainPrice.getMaintainPrice()));
				// 水资源费收费金额=购水量*水资源费参数
				waterPriceAmount.setWaterSourcePrice(paramWaterSourcePrice.getWaterSourcePrice());
				waterPriceAmount.setWaterSourceAmount(waterPriceAmount.getWaterAmount().multiply(paramWaterSourcePrice.getWaterSourcePrice()));
				waterPriceAmounts.add(waterPriceAmount);
			}
		}
		
		// excel导出
        try {
        	ExportExcel.exportExcel(waterPriceAmounts, "水费信息报表", "水费信息报表", WaterPriceAmountVO.class, "水费信息报表.xls", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}