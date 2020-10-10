package com.fourfaith.statisticAnalysis.controller;

import java.util.ArrayList;
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

import com.fourfaith.basicInformation.model.SysApplyStatus;
import com.fourfaith.basicInformation.model.SysDeviceModel;
import com.fourfaith.basicInformation.model.SysIrrigationAreaType;
import com.fourfaith.basicInformation.model.SysIrrigationMode;
import com.fourfaith.basicInformation.model.SysLandformType;
import com.fourfaith.basicInformation.model.SysMeasureType;
import com.fourfaith.basicInformation.model.SysOperator;
import com.fourfaith.basicInformation.model.SysPumpMaterial;
import com.fourfaith.basicInformation.model.SysUseWaterType;
import com.fourfaith.basicInformation.model.SysWellUse;
import com.fourfaith.basicInformation.service.SysApplyStatusService;
import com.fourfaith.basicInformation.service.SysDeviceModelService;
import com.fourfaith.basicInformation.service.SysIrrigationAreaTypeService;
import com.fourfaith.basicInformation.service.SysIrrigationModeService;
import com.fourfaith.basicInformation.service.SysLandformTypeService;
import com.fourfaith.basicInformation.service.SysMeasureTypeService;
import com.fourfaith.basicInformation.service.SysOperatorService;
import com.fourfaith.basicInformation.service.SysPumpMaterialService;
import com.fourfaith.basicInformation.service.SysUseWaterTypeService;
import com.fourfaith.basicInformation.service.SysWellUseService;
import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.siteManage.service.SysAreaService;
import com.fourfaith.statisticAnalysis.model.RptBaseDeviceDetail;
import com.fourfaith.statisticAnalysis.service.DeviceInfoService;
import com.fourfaith.sysManage.model.BaseDeviceExpandInfo;
import com.fourfaith.sysManage.model.BaseDeviceInfo;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.BaseDeviceDynamicInfoService;
import com.fourfaith.sysManage.service.BaseDeviceExpandInfoService;
import com.fourfaith.sysManage.service.BaseDeviceInfoService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AreaRecursionUtil;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.ExportExcel;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.WaterAreaRecursionUtil;

/**
 * @ClassName: DeviceInfoController
 * @Description: 机井基本信息查询控制器
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午3:20:15
 */
@Controller
@RequestMapping(value = "/deviceInfo")
public class DeviceInfoController {

	protected static final String deviceInfoJsp = "/page/statistic/deviceInfo/device";
	protected static final String deviceInfoListJsp = "/page/statistic/deviceInfo/deviceList";
	protected static final String deviceDetailInfoJsp = "/page/statistic/deviceInfo/detail";
	protected static final String deviceInfoWaterAreaJsp = "/page/statistic/deviceInfo/deviceInfoWaterArea";
	
	@Autowired
	private SysAreaService sysAreaService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private DeviceInfoService deviceInfoService;
	@Autowired
	private BaseDeviceInfoService baseDeviceInfoService;
	@Autowired
	private BaseDeviceExpandInfoService baseDeviceExpandInfoService;
	@Autowired
	private BaseDeviceDynamicInfoService baseDeviceDynamicInfoService;
	@Autowired
	private SysDeviceModelService sysDeviceModelService;
	@Autowired
	private SysOperatorService sysOperatorService;
	@Autowired
	private SysPumpMaterialService sysPumpMaterialService;
	@Autowired
	private SysUseWaterTypeService sysUseWaterTypeService;
	@Autowired
	private SysApplyStatusService sysApplyStatusService;
	@Autowired
	private SysWellUseService sysWellUseService;
	@Autowired
	private SysIrrigationModeService sysIrrigationModeService;
	@Autowired
	private SysMeasureTypeService sysMeasureTypeService;
	@Autowired
	private SysLandformTypeService sysLandformTypeService;
	@Autowired
	private SysIrrigationAreaTypeService sysIrrigationAreaTypeService;
	//定义机井导出的list
		List<RptBaseDeviceDetail> exportlist = new ArrayList();
	/**
	 * 设备机井查询初始条件加载
	 * @param request
	 * 2016年11月6日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value = "/deviceInfo", method = RequestMethod.GET)
	public ModelAndView deviceInfo(HttpServletRequest request) {
		ModelAndView mav = null;
		Map<String,Object> params = new HashMap<String,Object>();
		List<SysWellUse> list = sysWellUseService.getList(params);
		// 根据登录用户  获取其水管区域或者行政区域信息
    	SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
    	int areaWay = login_user.getAreaWay();
    	
    	// 水管区域方式查看
    	if(areaWay == 0){
    		mav = new ModelAndView(deviceInfoWaterAreaJsp);
		// 行政区域方式查看
    	}else if(areaWay == 1){
    		mav = new ModelAndView(deviceInfoJsp);
    	}
		
		// 获取所有的水管区域信息集合
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		// 获取当前登陆用户所属水管区域及所有子区域
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, login_user.getWaterAreaId());
		mav.addObject("sysWaterAreaList", sysWaterAreaList);
		mav.addObject("wellList", list);
		return mav;
	}
	
	/**
	 * TODO: 查询设备机井基本信息   行政区域方式
	 * @param request
	 * 2016年11月6日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value = "/deviceInfoList", method = RequestMethod.POST)
	public ModelAndView deviceInfoList(HttpServletRequest request) {
		// 定位到设备机井基础信息列表页
		ModelAndView mav = new ModelAndView(deviceInfoListJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> paramsAll = new HashMap<String, Object>();
		/**
		 * 	查询条件
		 * 	areaId : 所属行政区域Id
		 * 	waterAreaId : 所属水管区域Id
		 * 	deviceName : 机井名称
		 * 	deviceCode : 机井编码
		 */
		String areaId = request.getParameter("id");
		String waterAreaId = request.getParameter("waterAreaId");
		String deviceName = request.getParameter("deviceName");
		String deviceCode = request.getParameter("deviceCode");
		String deviceWellUse = request.getParameter("deviceWellUse");
		params.put("deviceWellUse", deviceWellUse);
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
		
		// 机井编码
		try {
			if(StringUtils.isNotBlank(deviceCode)) {
				params.put("deviceCode", deviceCode.trim());
			} else {
				params.put("deviceCode", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 获取所有水管区域信息集合
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		if(null==waterAreaId || "".equals(waterAreaId)) {
			SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
			waterAreaId = login_user.getWaterAreaId();
		}
		// 获取当前区域及所有子区域信息
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
		List<String> waterAreaIdsList = new ArrayList<String>();
		for(SysWaterArea swa : sysWaterAreaList) {
			waterAreaIdsList.add(swa.getId());
		}
		params.put("waterAreaIdsList", waterAreaIdsList);
		// 机井名称
		if(null!=deviceName && !"".equals(deviceName)) {
			params.put("deviceName", deviceName.trim());
		} else {
			params.put("deviceName", null);
		}
		//查询所有
			paramsAll = params;
			exportlist = deviceInfoService.getList(paramsAll);
		// 分页操作
		String pageNo = request.getParameter("pageNo");
		// 获取分页条数
		int count = deviceInfoService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取设备机井基础信息历史数据
		List<RptBaseDeviceDetail> deviceInfoList = deviceInfoService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("deviceInfoList", deviceInfoList);
		mav.addObject("areaId", areaId);
		mav.addObject("waterAreaId", waterAreaId);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceCode", deviceCode);
		return mav;
	}
	
	/**
	 * @Title: deviceInfoListByWaterArea
	 * @Description: 查询设备机井基本信息   水管区域方式
	 * @param request
	 * @return: ModelAndView
	 * @Author: zhaojx
	 * @Date: 2017年11月20日 上午11:39:27
	 */
	@RequestMapping(value = "/deviceInfoListByWaterArea", method = RequestMethod.POST)
	public ModelAndView deviceInfoListByWaterArea(HttpServletRequest request) {
		// 定位到设备机井基础信息列表页
		ModelAndView mav = new ModelAndView(deviceInfoListJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		/**
		 * 	查询条件
		 * 	id : ztree树菜单选择节点所属水管Id
		 * 	waterAreaId : 所属水管区域Id
		 * 	deviceName : 机井名称
		 * 	deviceCode : 机井编码
		 */
		String id = request.getParameter("id");
		String deviceName = request.getParameter("deviceName");
		String deviceCode = request.getParameter("deviceCode");
		
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
		
		// 机井编码
		try {
			if(StringUtils.isNotBlank(deviceCode)) {
				params.put("deviceCode", deviceCode.trim());
			} else {
				params.put("deviceCode", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 机井名称
		if(null!=deviceName && !"".equals(deviceName)) {
			params.put("deviceName", deviceName.trim());
		} else {
			params.put("deviceName", null);
		}
		// 分页操作
		String pageNo = request.getParameter("pageNo");
		// 获取分页条数
		int count = deviceInfoService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取设备机井基础信息历史数据
		List<RptBaseDeviceDetail> deviceInfoList = deviceInfoService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("deviceInfoList", deviceInfoList);
		mav.addObject("areaId", id);
		mav.addObject("deviceName", deviceName);
		mav.addObject("deviceCode", deviceCode);
		return mav;
	}
	
	/**
	 * 查看设备机井详情
	 * @param request
	 * 2016年11月21日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="detailPage")
	public ModelAndView detailPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(deviceDetailInfoJsp);
		String id = request.getParameter("id");
		BaseDeviceInfo baseDeviceInfo = baseDeviceInfoService.findById(id);
		BaseDeviceExpandInfo baseDeviceExpandInfo = baseDeviceExpandInfoService.findById(id);
		mav.addObject("baseDeviceInfo", baseDeviceInfo);
		mav.addObject("baseDeviceExpandInfo", baseDeviceExpandInfo);
		mav = getRelatedInfo(mav);
		return mav;
	}
	
	public ModelAndView getRelatedInfo(ModelAndView mav){
		List<SysDeviceModel> sysDeviceModelList = sysDeviceModelService.getList(null);
		List<SysOperator> sysOperatorList = sysOperatorService.getList(null);
		List<SysPumpMaterial> sysPumpMaterialList = sysPumpMaterialService.getList(null);
		List<SysUseWaterType> sysUseWaterTypeList = sysUseWaterTypeService.getList(null);
		List<SysApplyStatus> sysApplyStatusList = sysApplyStatusService.getList(null);
		List<SysWellUse> sysWellUseList = sysWellUseService.getList(null);
		List<SysIrrigationMode> sysIrrigationModeList = sysIrrigationModeService.getList(null);
		List<SysMeasureType> sysMeasureTypeList = sysMeasureTypeService.getList(null);
		List<SysLandformType> sysLandformTypeList = sysLandformTypeService.getList(null);
		List<SysIrrigationAreaType> sysIrrigationAreaTypeList = sysIrrigationAreaTypeService.getList(null);
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		List<SysArea> sysAreaList = sysAreaService.getAllArea();
		mav.addObject("sysDeviceModelList", sysDeviceModelList);
		mav.addObject("sysOperatorList", sysOperatorList);
		mav.addObject("sysPumpMaterialList", sysPumpMaterialList);
		mav.addObject("sysUseWaterTypeList", sysUseWaterTypeList);
		mav.addObject("sysApplyStatusList", sysApplyStatusList);
		mav.addObject("sysWellUseList", sysWellUseList);
		mav.addObject("sysIrrigationModeList", sysIrrigationModeList);
		mav.addObject("sysMeasureTypeList", sysMeasureTypeList);
		mav.addObject("sysLandformTypeList", sysLandformTypeList);
		mav.addObject("sysIrrigationAreaTypeList", sysIrrigationAreaTypeList);
		mav.addObject("sysWaterAreaList", sysWaterAreaList);
		mav.addObject("sysAreaList", sysAreaList);
		return mav;
	}
	
	/*机井信息导出的方法
	 * 
	 */
	@RequestMapping(value="/exportDeviceInfo")
	public void exportSaleWater(HttpServletResponse response){
		try{
		ExportExcel.exportExcel(exportlist, "机井基本信息表", "机井基本信息表", RptBaseDeviceDetail.class, "机井基本信息表.xls", response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}