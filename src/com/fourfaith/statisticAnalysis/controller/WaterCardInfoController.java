package com.fourfaith.statisticAnalysis.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.siteManage.service.SysAreaService;
import com.fourfaith.statisticAnalysis.model.BaseCardInfo;
import com.fourfaith.statisticAnalysis.model.BaseCardInfoVO;
import com.fourfaith.statisticAnalysis.service.BaseCardInfoService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.AreaRecursionUtil;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.ExportExcel;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.StringUtils;
import com.fourfaith.utils.WaterAreaRecursionUtil;

/**
 * @ClassName: WaterCardInfoController
 * @Description: 水卡信息控制器
 * @Author: zhaojx
 * @Date: 2018年4月12日 下午6:09:57
 */
@Controller
@RequestMapping(value="/waterCardInfo")
public class WaterCardInfoController {
	
	protected Logger logger = Logger.getLogger(WaterCardInfoController.class);

	protected static final String waterCardInfoJsp = "/page/waterCard/waterCard";
	protected static final String waterCardInfoListJsp = "/page/waterCard/waterCardList";
	protected static final String manageCardJsp = "/page/waterCard/manageCard";
	protected static final String manageCardListJsp = "/page/waterCard/manageCardList";
	protected static final String manageCardWaterAreaJsp = "/page/waterCard/waterAreaManageCard";
	
	@Autowired
	private BaseCardInfoService baseCardInfoService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private SysAreaService sysAreaService;
	
	/**
	 * TODO:用户水卡信息头部
	 * @param request
	 * 2016年11月20日
	 */
	@RequestMapping(value="/waterCardInfo", method = RequestMethod.GET)
	public ModelAndView waterCardInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(waterCardInfoJsp);
		return mav;
	}
	
	/**
	 * @Title: manageCardInfo
	 * @Description: 管理卡首页
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/manageCardInfo", method = RequestMethod.GET)
	public ModelAndView manageCardInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(manageCardJsp);
		
		// 根据登录用户  获取其水管区域或者行政区域信息
    	SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
    	int areaWay = login_user.getAreaWay();
    	
    	// 水管区域方式查看  页面无水管区域下拉框
    	if(areaWay == 0){
    		mav = new ModelAndView(manageCardWaterAreaJsp);
		// 行政区域方式查看  页面有水管区域下拉框
    	}else if(areaWay == 1){
    		mav = new ModelAndView(manageCardJsp);
    	}
		
		// 获取所有的水管区域信息集合
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		// 获取当前登陆用户所属水管区域及所有子区域
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, login_user.getWaterAreaId());
		mav.addObject("sysWaterAreaList", sysWaterAreaList);
		return mav;
	}
	
	/**
	 * TODO: 水卡信息统计列表页   行政区域方式
	 * @param request
	 * 2016年11月20日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="/waterCardInfoList", method = RequestMethod.POST)
	public ModelAndView waterCardInfoList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(waterCardInfoListJsp);
		String ownerName = request.getParameter("ownerName");
		String deviceName = request.getParameter("deviceName");
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		String waterAreaId = login_user.getWaterAreaId();
		String areaId = request.getParameter("id");
		
		Map<String, Object> params = new HashMap<String, Object>();
		// 判断输入的用户名是否为空
		if(!StringUtils.isNullOrEmpty(ownerName)) {
			params.put("ownerName", ownerName.trim());
		} else {
			params.put("ownerName", null);
		}
		// 判断机井名称
		if(!StringUtils.isNullOrEmpty(deviceName)) {
			params.put("deviceName", deviceName);
		} else {
			params.put("deviceName", null);
		}
		
		List<SysArea> sysAllAreaList = sysAreaService.getAllArea();
		List<SysArea> sysAreaList = AreaRecursionUtil.getCurrAndChildAreaList(sysAllAreaList, areaId);
		List<String> areaIdsList = new ArrayList<String>();
		for(SysArea sa : sysAreaList) {
			areaIdsList.add(sa.getId());
		}
		params.put("areaIdsList", areaIdsList);
		// 根据用户所在水管区域查询
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
		List<String> waterAreaIdsList = new ArrayList<String>();
		for(SysWaterArea swa : sysWaterAreaList) {
			waterAreaIdsList.add(swa.getId());
		}
		params.put("waterAreaIdsList", waterAreaIdsList);
		String s_start = request.getParameter("pageNo");
		int count = baseCardInfoService.getCount(params);
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<BaseCardInfo> cardInfoList = baseCardInfoService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("cardInfoList", cardInfoList);
		mav.addObject("ownerName", ownerName);
		mav.addObject("deviceName", deviceName);
		return mav;
	}
	
	/**
	 * @Title: waterCardListByArea
	 * @Description: 水卡信息统计列表页   水管区域方式
	 * @return: ModelAndView
	 * @Author: zhaojx
	 * @Date: 2017年11月20日 下午7:53:46
	 */
	@RequestMapping(value="/waterCardListByArea", method = RequestMethod.POST)
	public ModelAndView waterCardListByArea(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(waterCardInfoListJsp);
		String ownerName = request.getParameter("ownerName");
		String deviceName = request.getParameter("deviceName");
		String id = request.getParameter("id");
		
		Map<String, Object> params = new HashMap<String, Object>();
		// 判断输入的用户名是否为空
		if(!StringUtils.isNullOrEmpty(ownerName)) {
			params.put("ownerName", ownerName.trim());
		} else {
			params.put("ownerName", null);
		}
		// 判断机井名称
		if(!StringUtils.isNullOrEmpty(deviceName)) {
			params.put("deviceName", deviceName);
		} else {
			params.put("deviceName", null);
		}
		
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
		
		String s_start = request.getParameter("pageNo");
		int count = baseCardInfoService.getCount(params);
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<BaseCardInfo> cardInfoList = baseCardInfoService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("cardInfoList", cardInfoList);
		mav.addObject("ownerName", ownerName);
		mav.addObject("deviceName", deviceName);
		return mav;
	}
	
	/**
	 * @Title: delCardInfo
	 * @Description: 删除用户卡信息,包括管理卡 (水卡已在控制器上卡 需要删除信息后补同一张卡)
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping(value="/delCardInfo")
	@ResponseBody
	public String delCardInfo(HttpServletRequest request){
		String id = request.getParameter("id");
		// 返回前台ajax实体信息
		AjaxJson ajaxJson = new AjaxJson();
		
		try{
			ajaxJson = this.baseCardInfoService.delCardInfo(id);
			logger.info("删除水卡信息成功!");
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("删除水卡信息失败!");
			logger.error("异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * @Title: manageCardList
	 * @Description: 管理卡信息列表页面
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/manageCardList", method = RequestMethod.POST)
	public ModelAndView manageCardList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(manageCardListJsp);
		
		String ownerName = request.getParameter("ownerName");
		String areaId = request.getParameter("id");
		
		Map<String, Object> params = new HashMap<String, Object>();
		// 迭代获取选中右侧行政区域树及下级区域
		List<SysArea> sysAllAreaList = sysAreaService.getAllArea();
		List<SysArea> sysAreaList = AreaRecursionUtil.getCurrAndChildAreaList(sysAllAreaList, areaId);
		List<String> areaIdsList = new ArrayList<String>();
		for(SysArea sa : sysAreaList) {
			areaIdsList.add(sa.getId());
		}
		params.put("areaIdsList", areaIdsList);
		
		// 判断输入的用户名是否为空
		if(!StringUtils.isNullOrEmpty(ownerName)) {
			params.put("ownerName", ownerName.trim());
		} else {
			params.put("ownerName", null);
		}
		
		String s_start = request.getParameter("pageNo");
		int count = baseCardInfoService.getManageCardCount(params);
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<BaseCardInfo> manageCardList = baseCardInfoService.getManageCardList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("manageCardList", manageCardList);
		return mav;
	}
	
	
	/**
	 * @Title: waterCardInfoExport
	 * @Description: 水卡信息导出
	 * @param: @param ownerName
	 * @param: @param cardCode
	 * @param: @param ownerTelphone
	 * @param: @param request
	 * @param: @param response
	 * @return: void
	 */
	@RequestMapping(value="/waterCardInfoExport")
	public void waterCardInfoExport(String ownerName, String deviceName, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		// 行政编码  行政区域方式具有
		String areaId = request.getParameter("areaIds");
		if(areaId != null && !areaId.equals("")){
			List<SysArea> sysAllAreaList = sysAreaService.getAllArea();
			List<SysArea> sysAreaList = AreaRecursionUtil.getCurrAndChildAreaList(sysAllAreaList, areaId);
			List<String> areaIdsList = new ArrayList<String>();
			for(SysArea sa : sysAreaList) {
				areaIdsList.add(sa.getId());
			}
			params.put("areaIdsList", areaIdsList);
		}else{
			params.put("areaIdsList", null);
		}
		
		// 水管编码  水管区域方式具有
		String waterAreaId = request.getParameter("waterAreaId");
		if(waterAreaId != null && !waterAreaId.equals("")) {
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
		}else{
			params.put("waterAreaIdsList", null);
		}
		
		// 查询条件放入map
		if(ownerName !=null && !ownerName.equals("请输入持卡人姓名")){
			params.put("ownerName", ownerName);
		}else{
			params.put("ownerName", null);
		}
		if(deviceName !=null && !deviceName.equals("请输入机井名称")){
			params.put("deviceName", deviceName);
		}else{
			params.put("deviceName", null);
		}
		
		// 水卡信息集合
		List<BaseCardInfoVO> waterCardList = baseCardInfoService.getListExcel(params);
		// 分转元的比率 1元=100分
		BigDecimal rate = new BigDecimal(100);
		// 处理剩余金额单位为分的问题
		if(waterCardList != null && waterCardList.size() > 0){
			for (int i = 0; i < waterCardList.size(); i++) {
				// 如果剩余金额不为零  则除以100  换算为元
				if(waterCardList.get(i).getRemainMoney().doubleValue() != 0.0){
					waterCardList.get(i).setRemainMoney(waterCardList.get(i).getRemainMoney().divide(rate, 2));
				}
			}
		}
		// excel导出
        try {
        	ExportExcel.exportExcel(waterCardList, "水卡信息报表", "水卡信息报表", BaseCardInfoVO.class, "水卡信息报表.xls", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}