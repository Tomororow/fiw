package com.fourfaith.alarmManage.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.fourfaith.alarmManage.model.ThreeVoltageAlarmExportVO;
import com.fourfaith.alarmManage.model.ThreeVoltageAlarmVO;
import com.fourfaith.alarmManage.service.DeviceFaultService;
import com.fourfaith.alarmManage.service.WarningRecordDetailService;
import com.fourfaith.paramterManage.model.ParamThreeVoltage;
import com.fourfaith.paramterManage.model.Warnerrortype;
import com.fourfaith.paramterManage.service.ParamThreeVoltageService;
import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.siteManage.service.SysAreaService;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AreaRecursionUtil;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.ExportExcel;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.WaterAreaRecursionUtil;

/**
 * @ClassName: DeviceFaultController
 * @Description: 设备故障告警控制器
 * @Author: zhaojinxin
 * @Date: 2019年1月27日 下午5:20:30
 */
@Controller
@RequestMapping(value ="/device")
public class DeviceFaultController {

	protected static final String deviceJsp="/page/history/device/device";
	protected static final String deviceListJsp="/page/history/device/deviceList";
	
	@Autowired
	private DeviceFaultService deviceFaultService;
	@Autowired
	private ParamThreeVoltageService paramThreeVoltageService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private SysAreaService sysAreaService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private WarningRecordDetailService warningRecordDetailService;
	/**
	 * @Title: alarm 
	 * @Description: 设备告警查询
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/device", method = RequestMethod.GET)
	public ModelAndView alarm(HttpServletRequest request,String menuId, String stcd,String tag){
		ModelAndView mav = new ModelAndView(deviceJsp);
		mav.addObject("defaultTime", new Date());
		mav.addObject("tag", tag);
		//查询异常类型
		List<Warnerrortype> selectAbnormalType = warningRecordDetailService.selectAbnormalType();
		mav.addObject("TypeList", selectAbnormalType);
		return mav;
	}
	
	/**
	 * @Title: deviceList
	 * @Description: 设备告警查询列表  行政区域方式
	 * @param: @param request
	 * @param: @param query_startTime
	 * @param: @param query_endTime
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/deviceList", method = RequestMethod.POST)
	public ModelAndView deviceList(HttpServletRequest request, String query_startTime, String query_endTime){
		ModelAndView mav = new ModelAndView(deviceListJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		
		// 获取选中树菜单ID信息
		String nodeIds = request.getParameter("nodeIds");
		List<String> areaList = null;
		if(StringUtils.isNotBlank(nodeIds)){
			areaList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}else{
			//无id，则置空
			areaList = null;
		}
		params.put("areaList", areaList);
		
		// 模糊查询井名称信息
		String deviceName = request.getParameter("deviceName");
		try {
			if(StringUtils.isNotBlank(deviceName)){
				params.put("deviceName", deviceName);
			}else{
				params.put("deviceName", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 告警类型查询条件
		String alarmType = request.getParameter("alarmType");
		if(alarmType != null){
			// 获取所有告警上下限电压
			List<ParamThreeVoltage> threeVoltageServices = paramThreeVoltageService.getVoltageList();
			if(alarmType.equals("0")){
				params.put("aPhaseVoltageDown", threeVoltageServices.get(0).getaPhaseVoltageDown());
				params.put("bPhaseVoltageDown", threeVoltageServices.get(0).getbPhaseVoltageDown());
				params.put("cPhaseVoltageDown", threeVoltageServices.get(0).getcPhaseVoltageDown());
			}else if(alarmType.equals("1")){
				params.put("aPhaseVoltageUp", threeVoltageServices.get(0).getaPhaseVoltageUp());
				params.put("bPhaseVoltageUp", threeVoltageServices.get(0).getbPhaseVoltageUp());
				params.put("cPhaseVoltageUp", threeVoltageServices.get(0).getcPhaseVoltageUp());
			}
			params.put("alarmType", alarmType);
		}
		
		// 告警时间查询条件
		if(query_startTime != null && query_endTime != null){
			params.put("startTime", query_startTime);
	   		params.put("endTime", query_endTime);
		}else{
			params.put("startTime", null);
	   		params.put("endTime", null);
		}
		
   		//分页
		String pageNo = request.getParameter("pageNo");
		int count = deviceFaultService.getVoltageAlarmCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		
		// 获取当前页  用于自增序列号
		int pageCurrent = pagingBean.getPageNo();
		// 每页条数
		int pageSize = PagingBean.DEFAULT_PAGE_SIZE;
		//获取历史数据
		List<ThreeVoltageAlarmVO> voltageAlarmList = deviceFaultService.getThreeVoltageAlarmList(params);
		
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("voltageAlarmList", voltageAlarmList);
		mav.addObject("deviceName", deviceName);
		mav.addObject("alarmType", alarmType);
		mav.addObject("pageCurrent", pageCurrent);
		mav.addObject("pageSize", pageSize);
		return mav;
	}
	
	/**
	 * @Title: deviceListByArea
	 * @Description: 设备告警查询列表  水管区域方式
	 * @param request
	 * @param query_startTime
	 * @param query_endTime
	 * @return: ModelAndView
	 * @Author: zhaojx
	 * @Date: 2017年11月22日 下午5:54:09
	 */
	@RequestMapping(value = "/deviceListByArea", method = RequestMethod.POST)
	public ModelAndView deviceListByArea(HttpServletRequest request, String query_startTime, String query_endTime){
		ModelAndView mav = new ModelAndView(deviceListJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		
		// 获取选中树菜单ID信息
		String nodeIds = request.getParameter("nodeIds");
		List<String> waterAreaIdsList = null;
		if(StringUtils.isNotBlank(nodeIds)){
			waterAreaIdsList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}else{
			//无id，则置空
			waterAreaIdsList = null;
		}
		params.put("waterAreaIdsList", waterAreaIdsList);
		
		// 模糊查询井名称信息
		String deviceName = request.getParameter("deviceName");
		try {
			if(StringUtils.isNotBlank(deviceName)){
				params.put("deviceName", deviceName);
			}else{
				params.put("deviceName", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		// 告警类型查询条件
		String alarmType = request.getParameter("alarmType");
		if(alarmType != null){
			// 获取所有告警上下限电压
			List<ParamThreeVoltage> threeVoltageServices = paramThreeVoltageService.getVoltageList();
			if(alarmType.equals("0")){
				params.put("aPhaseVoltageDown", threeVoltageServices.get(0).getaPhaseVoltageDown());
				params.put("bPhaseVoltageDown", threeVoltageServices.get(0).getbPhaseVoltageDown());
				params.put("cPhaseVoltageDown", threeVoltageServices.get(0).getcPhaseVoltageDown());
			}else if(alarmType.equals("1")){
				params.put("aPhaseVoltageUp", threeVoltageServices.get(0).getaPhaseVoltageUp());
				params.put("bPhaseVoltageUp", threeVoltageServices.get(0).getbPhaseVoltageUp());
				params.put("cPhaseVoltageUp", threeVoltageServices.get(0).getcPhaseVoltageUp());
			}
			params.put("alarmType", alarmType);
		}
		
		// 告警时间查询条件
		if(query_startTime != null && query_endTime != null){
			params.put("startTime", query_startTime);
	   		params.put("endTime", query_endTime);
		}else{
			params.put("startTime", null);
	   		params.put("endTime", null);
		}
		
   		//分页
		String pageNo = request.getParameter("pageNo");
		int count = deviceFaultService.getVoltageAlarmCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		
		// 获取当前页  用于自增序列号
		int pageCurrent = pagingBean.getPageNo();
		// 每页条数
		int pageSize = PagingBean.DEFAULT_PAGE_SIZE;
		//获取历史数据
		List<ThreeVoltageAlarmVO> voltageAlarmList = deviceFaultService.getThreeVoltageAlarmList(params);
		
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("voltageAlarmList", voltageAlarmList);
		mav.addObject("deviceName", deviceName);
		mav.addObject("alarmType", alarmType);
		mav.addObject("pageCurrent", pageCurrent);
		mav.addObject("pageSize", pageSize);
		return mav;
	}
	
	/**
	 * @Title: exportAlarmThreeVoltage
	 * @Description: 三相电压告警信息导出
	 * @param: @param request
	 * @param: @param response
	 * @return: void
	 */
	@RequestMapping(value="/exportAlarmThreeVoltage")
	public void exportAlarmThreeVoltage(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> params = new HashMap<String, Object>();
		
		// 行政区域方式 行政树菜单
		String areaId = request.getParameter("areaIds");
		if(areaId != null && !areaId.equals("")){
			// 获取所有行政区域信息集合
			List<SysArea> sysAllAreaList = sysAreaService.getAllArea();
			// 获取当前区域及所有子区域信息
			List<SysArea> sysAreaList = AreaRecursionUtil.getCurrAndChildAreaList(sysAllAreaList, areaId);
			List<String> areaIdList = new ArrayList<String>();
			for(SysArea sa : sysAreaList) {
				areaIdList.add(sa.getId());
			}
			// 将行政区域加入查询条件中
			params.put("areaIdList", areaIdList);
		}else{
			params.put("areaIdList", null);
		}
		
		// 水管区域方式 水管树菜单
		String waterAreaId = request.getParameter("waterAreaIds");
		if(waterAreaId != null && !waterAreaId.equals("")){
			// 获取所有水管区域信息集合
			List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
			// 获取当前水管区域及所有子区域信息
			List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
			List<String> waterAreaIdList = new ArrayList<String>();
			for(SysWaterArea swa : sysWaterAreaList) {
				waterAreaIdList.add(swa.getId());
			}
			// 将水管区域加入查询条件中
			params.put("waterAreaIdList", waterAreaIdList);
		}else{
			params.put("waterAreaIdList", null);
		}
		
		// 模糊查询井名称信息
		String deviceName = request.getParameter("deviceName");
		try {
			if(StringUtils.isNotBlank(deviceName)){
				params.put("deviceName", deviceName);
			}else{
				params.put("deviceName", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		// 告警时间查询条件
		String query_startTime = request.getParameter("query_startTime");
		String query_endTime = request.getParameter("query_endTime");
		if(query_startTime != null && query_endTime != null){
			params.put("startTime", query_startTime);
	   		params.put("endTime", query_endTime);
		}else{
			params.put("startTime", null);
	   		params.put("endTime", null);
		}
		
		// 告警类型查询条件
		String alarmType = request.getParameter("alarmType");
		if(alarmType != null){
			// 获取所有告警上下限电压
			List<ParamThreeVoltage> threeVoltageServices = paramThreeVoltageService.getVoltageList();
			// 放入告警类型 查询区分
			params.put("alarmType", alarmType);
			// 低压告警
			if(alarmType.equals("0")){
				params.put("aPhaseVoltageDown", threeVoltageServices.get(0).getaPhaseVoltageDown());
				params.put("bPhaseVoltageDown", threeVoltageServices.get(0).getbPhaseVoltageDown());
				params.put("cPhaseVoltageDown", threeVoltageServices.get(0).getcPhaseVoltageDown());
				
				// 导出信息查询
				List<ThreeVoltageAlarmExportVO> threeVoltageAlarmList = deviceFaultService.getThreeVoltageAlarmExportList(params);
				// excel导出
		        try {
		        	ExportExcel.exportExcel(threeVoltageAlarmList, "三相电压低压告警信息", "低压告警信息", ThreeVoltageAlarmExportVO.class, "三相电压低压告警信息.xls", response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			// 高压告警
			}else if(alarmType.equals("1")){
				params.put("aPhaseVoltageUp", threeVoltageServices.get(0).getaPhaseVoltageUp());
				params.put("bPhaseVoltageUp", threeVoltageServices.get(0).getbPhaseVoltageUp());
				params.put("cPhaseVoltageUp", threeVoltageServices.get(0).getcPhaseVoltageUp());
				
				// 导出信息查询
				List<ThreeVoltageAlarmExportVO> threeVoltageAlarmList = deviceFaultService.getThreeVoltageAlarmExportList(params);
				// excel导出
		        try {
		        	ExportExcel.exportExcel(threeVoltageAlarmList, "三相电压高压告警信息", "高压告警信息", ThreeVoltageAlarmExportVO.class, "三相电压高压告警信息.xls", response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
    
}