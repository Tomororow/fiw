package com.fourfaith.alarmManage.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fourfaith.alarmManage.model.IntelligentDeal;
import com.fourfaith.alarmManage.model.WarningRecordDetail;
import com.fourfaith.alarmManage.model.WarningRecordDetailExcel;
import com.fourfaith.alarmManage.service.IntelligentDealService;
import com.fourfaith.alarmManage.service.WarningRecordDetailService;
import com.fourfaith.paramterManage.model.Warnerrortype;
import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.siteManage.service.SysAreaService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.model.UnusualList;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AreaRecursionUtil;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.EasyPoiUtil;
import com.fourfaith.utils.ExportExcel;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.StringUtils;
import com.fourfaith.utils.WaterAreaRecursionUtil;

/**
 * @ClassName: IntelligentDealController
 * @Description: 异常机井处理查询
 * @Author: zhaojx
 * @Date: 2018年5月9日 上午9:21:14
 */
@Controller
@RequestMapping(value = "/intelligentDeal")
public class IntelligentDealController {

	protected static final String intelligentDealJsp = "/page/history/intelligentDeal/intelligentDeal";
	protected static final String intelligentDealListJsp = "/page/history/intelligentDeal/intelligentDealList";
	protected static final String equipmentJSP = "/page/history/equipment/equiPment";
	protected static final String equipmentListJSP = "/page/history/equipment/equiPmentList";
	
	
	private List<WarningRecordDetail> warnConList = null;
	
	@Autowired
	private IntelligentDealService intelligentDealService;
	@Autowired
	private SysAreaService sysAreaService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private WarningRecordDetailService warningRecordDetailService;

	//定义为导出list
	List<String> areaListexport = null;
	/**
	 * TODO: 进入异常机井处理查询首页  
	 * @param request
	 * 2017年1月12日
	 */
	@RequestMapping(value = "/intelligentDeal", method = RequestMethod.GET)
	private ModelAndView intelligentDeal(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(intelligentDealJsp);
		mav.addObject("defaultTime", new Date());
		//查询异常类型
		List<Warnerrortype> selectAbnormalType = warningRecordDetailService.selectAbnormalType();
		mav.addObject("TypeList", selectAbnormalType);
		return mav;
	}
	//设备异常查询首页
	@RequestMapping(value="/equipment", method = RequestMethod.GET)
	private ModelAndView equipment(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(equipmentJSP);
		mav.addObject("defaultTime", new Date());
		//通过枚举类型获取异常类型
		UnusualList[] values = UnusualList.values();
		mav.addObject("TypeList", values);
		/*List<Warnerrortype> selectAbnormalType = warningRecordDetailService.selectAbnormalType();
		mav.addObject("TypeList", selectAbnormalType);*/
		return mav;
	}
	//设备异常信息的查询
	@RequestMapping(value="/equipmentList")
	private ModelAndView EquipmentList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(equipmentListJSP);
		Map<String, Object> params = new HashMap<String, Object>();
	
		// 获取选中树菜单ID信息
		String nodeIds = request.getParameter("nodeIds");//行政区的树菜单Id集合
			try {
				List<String> areaList = null;
				if(nodeIds.length()>0){//行政区的id集合
				areaList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
				areaListexport=areaList;
					}
		String deviceName = request.getParameter("deviceName");//机井名称
		String deviceCode = request.getParameter("deviceCode");//机井编码
		String startTime = request.getParameter("startTime");//预警开始时间
		String endTime = request.getParameter("endTime");//预警结束时间
		String alarmType = request.getParameter("alarmType");//预警类型
		String group = request.getParameter("group");//是否是页面的初始化
		params.put("deviceName", deviceName);
		params.put("deviceCode", deviceCode);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("areaList", areaList);
		params.put("alarmType", alarmType);
		params.put("group", group);
		//List<WarningRecordDetail> equipmentList1 = warningRecordDetailService.EquipmentList(params);
		//分页
		String pageNo = request.getParameter("pageNo");
		int count = warningRecordDetailService.EquipmentListCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取当前页  用于自增序列号
		int pageCurrent = pagingBean.getPageNo();
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("pageCurrent", pageCurrent);
		// 每页条数
		int pageSize = PagingBean.DEFAULT_PAGE_SIZE;
		UnusualList[] values = UnusualList.values();
		List<WarningRecordDetail> equipmentList = warningRecordDetailService.EquipmentList(params);
		for (WarningRecordDetail warning : equipmentList) {
			for (UnusualList unusualList : values) {
				if(warning.getAbnormaltype().equals(String.valueOf(unusualList.getKey()))){
					warning.setWabnormaltype(unusualList.getValue());
				}
			}
		}
		
		mav.addObject("warnList", equipmentList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	/**
	 * @Title: intelligentDealListByArea
	 * @Description: 异常机井处理列表页   水管区域方式
	 * @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/intelligentDealListByArea", method = RequestMethod.POST)
	private ModelAndView intelligentDealListByArea(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(intelligentDealListJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		// 获取水卡编号信息
		String cardCode = request.getParameter("cardCode");
		// 获取设备编码信息
		String deviceCode = request.getParameter("deviceCode");
		// 获取当前选中的行政区域信息
		String areaId = request.getParameter("id");
		
		// 获取全部水管区域ID并递归查找该ID下的子行政区域
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, areaId);
		List<String> waterAreaIdsList = new ArrayList<String>();
		for(SysWaterArea swa : sysWaterAreaList) {
			waterAreaIdsList.add(swa.getId());
		}
		params.put("waterAreaIdsList", waterAreaIdsList);
		
		if(!StringUtils.isNullOrEmpty(cardCode)) {
			params.put("cardCode", cardCode.trim());
		} else {
			params.put("cardCode", null);
		}
		if(!StringUtils.isNullOrEmpty(deviceCode)) {
			params.put("deviceCode", deviceCode.trim());
		} else {
			params.put("deviceCode", null);
		}
		
		// 分页
		String pageNo = request.getParameter("pageNo");
		// 查询总记录条数
		int count = intelligentDealService.getCount(params);
		// 设置分页对象
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		// 存储开始和结束分页变量
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 查询异常机井信息集合
		List<IntelligentDeal> intelligentDealList = intelligentDealService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("intelligentDealList", intelligentDealList);
		mav.addObject("deviceCode", deviceCode);
		mav.addObject("cardCode", cardCode);
		return mav;
	}
	
	//设备故障信息导出
	@RequestMapping(value="/equipmentexport")
	public void equipmentExport(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> params = new HashMap<String, Object>();
		// 获取选中树菜单ID信息
				List<String> areayList = null;
				areayList = areaListexport;
				String deviceName = request.getParameter("deviceName");//机井名称
				String deviceCode = request.getParameter("deviceCode");//机井编码
				String startTime = request.getParameter("startTime");//预警开始时间
				String endTime = request.getParameter("endTime");//预警结束时间
				String alarmType = request.getParameter("alarmType");//预警类型
				String group = request.getParameter("group");//是否是页面的初始化
				params.put("deviceName", deviceName);
				params.put("deviceCode", deviceCode);
				params.put("startTime", startTime);
				params.put("endTime", endTime);
				params.put("areaList", areayList);
				params.put("alarmType", alarmType);
				params.put("group", group);
				List<WarningRecordDetail> exportlist = warningRecordDetailService.EquipmentList(params);
				UnusualList[] values = UnusualList.values();
				for (WarningRecordDetail warning : exportlist) {
					for (UnusualList unusualList : values) {
						if(warning.getAbnormaltype().equals(String.valueOf(unusualList.getKey()))){
							warning.setWabnormaltype(unusualList.getValue());
						}
					}
				}
		ExportExcel.exportExcel(exportlist, "设备预警信息数据统计","设备预警信息数据统计",WarningRecordDetail.class,"设备预警信息.xls",response );
	}
	
}
