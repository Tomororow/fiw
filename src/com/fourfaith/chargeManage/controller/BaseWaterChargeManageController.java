package com.fourfaith.chargeManage.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.chargeManage.model.BaseWaterCharge;
import com.fourfaith.chargeManage.model.BaseWaterChargeVO;
import com.fourfaith.chargeManage.service.BaseWaterChargeService;
import com.fourfaith.paramterManage.model.ParamBaseWaterPrice;
import com.fourfaith.paramterManage.service.ParamBaseWaterPriceService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.BaseDeviceExpandInfoService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.ExportExcel;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.StringUtils;
import com.fourfaith.utils.WaterAreaRecursionUtil;

/**
 * TODO: 基本水费收缴管理
 * @author Administrator
 * 2016年11月20日
 */
@Controller
@RequestMapping(value="/baseWaterCharge")
public class BaseWaterChargeManageController {

	protected static final String indexJsp = "/page/chargeManage/baseWaterCharge/baseWaterChargeInfoIndex";
	protected static final String baseWaterChargeJsp = "/page/chargeManage/baseWaterCharge/baseWaterCharge";
	protected static final String addBaseWaterChargeJsp = "/page/chargeManage/baseWaterCharge/addBaseWaterCharge";
	protected static final String baseWaterChargeOperatorJsp = "/page/chargeManage/baseWaterCharge/baseWaterChargeOperator";
	
	@Autowired
	private BaseWaterChargeService baseWaterChargeService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private BaseDeviceExpandInfoService baseDeviceExpandInfoService;
	@Autowired
	private ParamBaseWaterPriceService paramBaseWaterPriceService;

	//以下参数为添加日志所需
    public String logContent = "";
    // 根据当前年份 获取费率信息 
	Calendar cal = Calendar.getInstance();
	int currentYear = cal.get(Calendar.YEAR);
    
	/**
	 * TODO: 基本收费初始页面
	 * @param request
	 * 2016年11月21日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(indexJsp);
		mav.addObject("currentYear", currentYear);
		return mav;
	}
	
	/**
	 * @Title: baseWaterCharge
	 * @Description: 基本水费收费列表信息
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="/baseWaterCharge", method = RequestMethod.GET)
	public ModelAndView baseWaterCharge(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(baseWaterChargeJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		BigDecimal waterPrice = new BigDecimal(0); 
		ParamBaseWaterPrice paramBaseWaterPrice = paramBaseWaterPriceService.findBaseWaterPriceByYear(currentYear);
		if(paramBaseWaterPrice!=null){
			if(paramBaseWaterPrice.getStandardPrice()!=null){
				waterPrice = paramBaseWaterPrice.getStandardPrice();
			}
		}
		
		String waterAreaId = request.getParameter("waterAreaId");
		String deviceName = request.getParameter("deviceName");
		String deviceCode = request.getParameter("deviceCode");
		
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
		List<String> waterAreaIdList = new ArrayList<String>();
		for(SysWaterArea swa : sysWaterAreaList) {
			waterAreaIdList.add(swa.getId());
		}
		params.put("waterAreaIdList", waterAreaIdList);
		
		if(!StringUtils.isNullOrEmpty(deviceName)) {
			params.put("deviceName", deviceName.trim());
		} else {
			params.put("deviceName", null);
		}
		if(!StringUtils.isNullOrEmpty(deviceCode)) {
			params.put("deviceCode", deviceCode.trim());
		} else {
			params.put("deviceCode", null);
		}
		
		// 获取基本水费统计
		BigDecimal baseWaterPriceSUM = baseWaterChargeService.getBaseWaterPriceSum(params);
		
		String s_start = request.getParameter("pageNo");
		int count = baseWaterChargeService.getCount(params);
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<BaseWaterCharge> baseWaterChargeList = baseWaterChargeService.getList(params);
		
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("baseWaterChargeList", baseWaterChargeList);
		mav.addObject("standardPrice", waterPrice);
		mav.addObject("currentYear", currentYear);
		mav.addObject("baseWaterPriceSUM", baseWaterPriceSUM != null ? baseWaterPriceSUM:0.00);
		return mav;
	}
	
	/**
	 * TODO: 基本水费新增页面
	 * @param request
	 * 2016年11月28日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="addBaseWaterChargePage")
	public ModelAndView addBaseWaterChargePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(addBaseWaterChargeJsp);
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getLoginerWaterAreaList(login_user);
		mav.addObject("sysWaterAreaList", sysWaterAreaList);
		return mav;
	}
	
	/**
	 * TODO: 获取设备井实际灌溉地亩数
	 * @param request
	 * 2016年11月28日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="/getSjAreaInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getSjAreaInfo(String baseDeviceId, HttpServletRequest request) {
		String sjArea = null;
		try {
			sjArea = baseDeviceExpandInfoService.getSjAreaInfo(baseDeviceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sjArea;
	}
	
	/**
	 * @Title: addBaseWaterCharge
	 * @Description: 根据费率保存基本水费信息
	 * @param: @param baseWaterCharge
	 * @param: @param request
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="addBaseWaterCharge")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String addBaseWaterCharge(BaseWaterCharge baseWaterCharge,HttpServletRequest request) {
		logContent = "";
		AjaxJson ajaxJson = new AjaxJson();
		try{
			// 新增三相电压设置
			String msg = baseWaterChargeService.add(baseWaterCharge);
			logContent = "添加【基本水费】的数据";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("添加基本水费信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("添加基本水费信息失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * TODO: 进入收费弹出页面
	 * @param request
	 * 2016年11月28日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="baseWaterChargeOperatorPage")
	public ModelAndView baseWaterChargeOperatorPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(baseWaterChargeOperatorJsp);
		String id = request.getParameter("id");
		// 根据id查询基本收费信息
		BaseWaterCharge baseWaterCharge = baseWaterChargeService.findById(id);
		mav.addObject("baseWaterCharge", baseWaterCharge);
		return mav;
	}
	
	/**
	 * TODO: 新增收费方式设置
	 * @param baseWaterCharge
	 * @param request
	 * 2016年11月28日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="baseWaterChargeOperator")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String baseWaterChargeOperator(BaseWaterCharge baseWaterCharge, HttpServletRequest request) {
		logContent = "";
		AjaxJson ajaxJson = new AjaxJson();
		try{
			// 新增收费方式设置
			String msg = baseWaterChargeService.addChargeMode(baseWaterCharge);
			logContent = "添加【收费方式】的数据";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("添加收费方式信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("添加收费方式信息失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * @Title: delBaseWaterCharge
	 * @Description: 删除机井基本水费收费信息
	 * @param: @param request
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="delBaseWaterCharge")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String delBaseWaterCharge(HttpServletRequest request) {
		logContent = "";
		String ids = request.getParameter("ids");
		AjaxJson ajaxJson = new AjaxJson();
		try{
			ajaxJson = baseWaterChargeService.delBaseWaterCharge(ids);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("删除机井基本水费收费信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("删除机井基本水费收费信息失败，请联系管理人员");
		}
		logContent = ajaxJson.getObj().toString();
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * @Title: exportBasePriceInfo
	 * @Description: 基础水费信息导出
	 * @param: @param deviceName
	 * @param: @param deviceCode
	 * @param: @param request
	 * @param: @param response
	 * @return: void
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="/exportBasePriceInfo")
	public void exportBasePriceInfo(String deviceName, String deviceCode, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("inYear", currentYear);
		
		// 获取基本水费费率
		ParamBaseWaterPrice paramBaseWaterPrice = paramBaseWaterPriceService.findBaseWaterPriceByYear(currentYear);
		
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
		List<String> waterAreaIdList = new ArrayList<String>();
		for(SysWaterArea swa : sysWaterAreaList) {
			waterAreaIdList.add(swa.getId());
		}
		params.put("waterAreaIdList", waterAreaIdList);
		
		if(!StringUtils.isNullOrEmpty(deviceName)) {
			params.put("deviceName", deviceName.trim());
		} else {
			params.put("deviceName", null);
		}
		if(!StringUtils.isNullOrEmpty(deviceCode)) {
			params.put("deviceCode", deviceCode.trim());
		} else {
			params.put("deviceCode", null);
		}
		
		// 获取需要导出的报表信息
		List<BaseWaterChargeVO> baseWaterChargeList = baseWaterChargeService.getBaseWaterExcelList(params);
		List<BaseWaterChargeVO> baseWaterChargeVos = new ArrayList<BaseWaterChargeVO>();
		if(null != baseWaterChargeList && baseWaterChargeList.size() > 0){
			for(BaseWaterChargeVO baseWaterCharge : baseWaterChargeList) {
				baseWaterCharge.setStandardPrice(paramBaseWaterPrice.getStandardPrice());
				// 收费方式
				if(Integer.parseInt(baseWaterCharge.getIsCharge()) == 0){
					baseWaterCharge.setChargeMode("--");
				}else{
					if(Integer.parseInt(baseWaterCharge.getChargeMode()) == 1){
						baseWaterCharge.setChargeMode("现金");
					}else{
						baseWaterCharge.setChargeMode("银行卡");
					}
				}
				// 缴费状态
				if(Integer.parseInt(baseWaterCharge.getIsCharge()) == 0){
					baseWaterCharge.setIsCharge("未缴费");
				}else if(Integer.parseInt(baseWaterCharge.getIsCharge()) == 1){
					baseWaterCharge.setIsCharge("已缴费");
				}
				baseWaterChargeVos.add(baseWaterCharge);
			}
		}
		
		// excel导出
        try {
        	ExportExcel.exportExcel(baseWaterChargeVos, "基本水费信息报表", "基本水费信息报表", BaseWaterChargeVO.class, "基本水费信息报表.xls", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}