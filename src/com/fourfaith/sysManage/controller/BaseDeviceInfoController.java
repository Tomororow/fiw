package com.fourfaith.sysManage.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import com.fourfaith.sysManage.model.Abnormalstate;
import com.fourfaith.sysManage.model.BaseDeviceDynamicInfo;
import com.fourfaith.sysManage.model.BaseDeviceExpandInfo;
import com.fourfaith.sysManage.model.BaseDeviceInfo;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.model.WellInfo;
import com.fourfaith.sysManage.model.WellRtData;
import com.fourfaith.sysManage.service.AbnormalstateService;
import com.fourfaith.sysManage.service.BaseDeviceDynamicInfoService;
import com.fourfaith.sysManage.service.BaseDeviceExpandInfoService;
import com.fourfaith.sysManage.service.BaseDeviceInfoService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.sysManage.service.WellInfoService;
import com.fourfaith.sysManage.service.WellRtDataService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.AreaRecursionUtil;
import com.fourfaith.utils.BeanUtils;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PersonDateUtils;
import com.fourfaith.utils.FileUtils;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.StringUtils;
import com.fourfaith.utils.WaterAreaRecursionUtil;

/**   
 * @Title: Controller
 * @Description: 机井设备信息代码
 * @author Dan
 * @date 2016-08-14 22:52:40
 * @version V1.0
 */
@Controller
@RequestMapping(value ="/baseDeviceInfo")
public class BaseDeviceInfoController {
	
	protected static final String indexJsp="/page/sysmanage/basedeviceinfo/baseDeviceInfoIndex";
	protected static final String listJsp = "/page/sysmanage/basedeviceinfo/list";
	protected static final String addJsp = "/page/sysmanage/basedeviceinfo/add";
	protected static final String editJsp = "/page/sysmanage/basedeviceinfo/edit";
	protected static final String updateImg = "/page/sysmanage/basedeviceinfo/updateImg";
	protected static final String distanceUpdate = "/page/sysmanage/distanceUpdate/index";

	@Autowired
	private BaseDeviceInfoService baseDeviceInfoService;
	@Autowired
	private BaseDeviceExpandInfoService baseDeviceExpandInfoService;
	@Autowired
	private BaseDeviceDynamicInfoService baseDeviceDynamicInfoService;
	@Autowired
	private WellInfoService wellInfoService;
	@Autowired
	private WellRtDataService wellRtDataService;
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
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private SysAreaService sysAreaService;
	@Autowired
	private AbnormalstateService abnormalstateService;
	
	//以下参数为添加日志所需
    public String logContent = "";
	
    /**
     * @Title: index
     * @Description: 首页
     * @param: @param request
     * @return: ModelAndView
     */
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(indexJsp);
		return mav;
	}
	
	/**
	 * 新增机井设备页面
	 * @param request
	 */
	@RequestMapping(value="addPage")
	public ModelAndView addPage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(addJsp);
		String areaId = request.getParameter("areaId");
		mav.addObject("areaId", areaId);
		mav = getRelatedInfo(mav);
		String defaultTime = PersonDateUtils.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
		mav.addObject("defaultTime", defaultTime);
		return mav;
	}
	
	/**
	 * @Title: list
	 * @Description: 机井设备列表  行政区域方式
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="list")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(listJsp);
		String areaId = request.getParameter("id");
		String deviceCode = request.getParameter("deviceCode");
		String deviceName = request.getParameter("deviceName");
		Map<String,Object> params = new HashMap<String,Object>();
		
		List<String> areaIdList = new ArrayList<String>();
		List<SysArea> sysAreaList = sysAreaService.getChildArea(areaId);
		for(SysArea sysArea : sysAreaList){
			areaIdList.add(sysArea.getId());
		}
		areaIdList.add(areaId);
		params.put("areaIdList", areaIdList);
		
		try {
			// 条件查询参数
			if(!StringUtils.isNullOrEmpty(deviceCode)) {
				params.put("deviceCode", deviceCode);
			}
			if(!StringUtils.isNullOrEmpty(deviceName)) {
				params.put("deviceName", deviceName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int count = baseDeviceInfoService.getCount(params);
		String s_start = request.getParameter("pageNo");
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		
		List<BaseDeviceInfo> baseDeviceInfoList = baseDeviceInfoService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("deviceCode", deviceCode);
		mav.addObject("deviceName", deviceName);
		mav.addObject("baseDeviceInfoList", baseDeviceInfoList);
		return mav;
	}
	
	/**
	 * @Title: listByWaterArea
	 * @Description: 机井设备列表  水管区域方式
	 * @param request
	 * @return: ModelAndView
	 * @Author: zhaojx
	 * @Date: 2017年11月22日 下午6:40:04
	 */
	@RequestMapping(value="listByWaterArea")
	public ModelAndView listByWaterArea(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(listJsp);
		String waterAreaId = request.getParameter("id");
		String deviceCode = request.getParameter("deviceCode");
		String deviceName = request.getParameter("deviceName");
		Map<String,Object> params = new HashMap<String,Object>();
		
		// 获取选择的水管区域树菜单id 得到所属的子区域并放入查询集合
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getCurrAndChildWaterArea(waterAreaId);
		List<String> waterAreaIdsList = new ArrayList<String>();
		for(SysWaterArea sysWaterArea : sysWaterAreaList){
			waterAreaIdsList.add(sysWaterArea.getId());
		}
		waterAreaIdsList.add(waterAreaId);
		params.put("waterAreaIdsList", waterAreaIdsList);
		
		try {
			// 条件查询参数
			if(!StringUtils.isNullOrEmpty(deviceCode)) {
				params.put("deviceCode", deviceCode);
			}
			if(!StringUtils.isNullOrEmpty(deviceName)) {
				params.put("deviceName", deviceName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int count = baseDeviceInfoService.getCount(params);
		String s_start = request.getParameter("pageNo");
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		
		List<BaseDeviceInfo> baseDeviceInfoList = baseDeviceInfoService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("deviceCode", deviceCode);
		mav.addObject("deviceName", deviceName);
		mav.addObject("baseDeviceInfoList", baseDeviceInfoList);
		return mav;
	}
	
	/**
	 * 新增机井设备
	 */
	@RequestMapping(value="addBaseDeviceInfo")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String addBaseDeviceInfo(@RequestParam(value = "photoBefores",required = false) MultipartFile photoBefore, @RequestParam(value = "photoAfters",required = false) MultipartFile photoAfter, 
			HttpServletRequest request, BaseDeviceInfo baseDeviceInfo, BaseDeviceExpandInfo baseDeviceExpandInfo, String installTimeStr, String makeTimeStr){
		//String filePath = request.getSession().getServletContext().getRealPath("/image/");
		String filePath = "D:\\PirSource\\";
		File file = new File(filePath);
		if(!file.exists()&&!file.isDirectory()){
			file.mkdir();
		}
		AjaxJson ajaxJson = new AjaxJson();
		
		if(baseDeviceInfo != null){
			// 获取新增机井的行政区域编码信息
			String deviceAreaCode = baseDeviceInfo.getDeviceAreaCode();
			
			// 设备安装前图片上传
			FileUtils.fileUpload(filePath, photoBefore, deviceAreaCode+"B");
			String oriPhotoBefore = photoBefore.getOriginalFilename();
			// 安装前照片命名规则:行政编码+B+图片后缀
			baseDeviceInfo.setPhotoBefore(deviceAreaCode+"B"+oriPhotoBefore.substring(oriPhotoBefore.lastIndexOf(".")));
			
			// 设备安装后图片上传
			if(photoAfter!=null){
				FileUtils.fileUpload(filePath, photoAfter, deviceAreaCode+"A");
				String oriPhotoAfter = photoAfter.getOriginalFilename();
				// 安装前照片命名规则:行政编码+A+图片后缀
				baseDeviceInfo.setPhotoAfter(deviceAreaCode+"A"+oriPhotoAfter.substring(oriPhotoAfter.lastIndexOf(".")));
			}
			// 程序生成唯一UUID
			String id = CommonUtil.getRandomUUID();
			baseDeviceInfo.setId(id);
			// 安装时间
			if(!StringUtils.isNullOrEmpty(installTimeStr)){
				Date date = PersonDateUtils.StringToDate(installTimeStr, "yyyy-MM-dd HH:mm:ss");
				baseDeviceInfo.setInstallTime(date);
			}
			if(!StringUtils.isNullOrEmpty(makeTimeStr)){
				Date date = PersonDateUtils.StringToDate(makeTimeStr, "yyyy-MM-dd HH:mm:ss");
				baseDeviceExpandInfo.setMakeTime(date);
			}
			// 设备类型
			if(baseDeviceInfo.getDeviceModel().equals("ZYJZ-IC")) {
				baseDeviceInfo.setDeviceType(1);
			} else {
				baseDeviceInfo.setDeviceType(2);
			}
			baseDeviceInfo.setSiteType(baseDeviceInfo.getSiteType());
			logContent = "";
			
			try{
				String msg = baseDeviceInfoService.add(baseDeviceInfo);
				// 设备类型
				if(baseDeviceInfo.getDeviceModel().equals("ZYJZ-IC")) {
					// 金讯润泽机井实时用水数据
					BaseDeviceDynamicInfo baseDeviceDynamicInfo = new BaseDeviceDynamicInfo();
					baseDeviceDynamicInfo.setDeviceId(id);
					baseDeviceDynamicInfo.setDeviceCode(baseDeviceInfo.getDeviceCode());
					// 保存相关信息
					baseDeviceDynamicInfoService.add(baseDeviceDynamicInfo);
					
					// 金讯润泽机井设备参数信息
					baseDeviceExpandInfo.setDeviceId(id);
					baseDeviceExpandInfo.setDeviceCode(baseDeviceInfo.getDeviceCode());
					// 保存相关信息
					baseDeviceExpandInfoService.add(baseDeviceExpandInfo);
					//在新增的机井信息的同时在abnormalstate中增加一条数据
					Abnormalstate abnormalstate = new Abnormalstate();
					abnormalstate.setDevicecode(baseDeviceInfo.getDeviceCode());
					abnormalstateService.insertSelective(abnormalstate);
				} else {
					// 青岛恒泽机井设备参数信息
					WellInfo wellInfo = new WellInfo();
					wellInfo.setWellId(id);
					wellInfo.setWellName(baseDeviceInfo.getDeviceName());
					wellInfo.setDevID(Integer.parseInt(baseDeviceInfo.getDeviceNo()));
					wellInfo.setCezhanID(baseDeviceInfo.getSimCard());
					
					// 青岛恒泽机井实时用水数据
					WellRtData wellRtData = new WellRtData();
					wellRtData.setWellId(id);
					
					// 保存相关信息
					wellInfoService.add(wellInfo);
					wellRtDataService.add(wellRtData);
					
				}
				logContent = "添加【"+baseDeviceInfo.getDeviceName()+"】的机井设备数据";
				ajaxJson.setMsg(msg);
				ajaxJson.setSuccess(true);
				ajaxJson.setObj(logContent);
			}catch(Exception e){
				e.printStackTrace();
				ajaxJson.setSuccess(false);
				ajaxJson.setObj("添加机井设备信息，抛出异常！具体异常信息："+ e.getMessage());
				ajaxJson.setMsg("添加机井设备信息失败，请联系管理人员");
			}
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * 检查机井设备编码是否存在
	 * @return
	 */
	@RequestMapping(value="/checkDeviceCodeExist")
	@ResponseBody
    public String checkDeviceCodeExist(HttpServletRequest request,String deviceCode){
    	//机井设备编码，不允许重复
		AjaxJson ajaxJson = new AjaxJson();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("deviceCode", deviceCode);
		int count = baseDeviceInfoService.getCount(params);
		if(count > 0){
			ajaxJson.setSuccess(true);
		}else{
			ajaxJson.setSuccess(false);
		}
		return JSONObject.toJSONString(ajaxJson);
    }
	
	/**
	 * 删除机井设备信息
	 */
	@RequestMapping(value="delBaseDeviceInfo")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String delBaseDeviceInfo(HttpServletRequest request){
		logContent = "";
		String ids = request.getParameter("ids");
		AjaxJson ajaxJson = new AjaxJson();
		try{
			ajaxJson = baseDeviceInfoService.delBaseDeviceInfo(ids);
			if(ajaxJson.isSuccess()==true) {
				/*wellInfoService.delWellInfo(ids);
				wellRtDataService.delWellRtData(ids);*/
				baseDeviceExpandInfoService.delBaseDeviceExpandInfo(ids);
				baseDeviceDynamicInfoService.delBaseDeviceDynamicInfo(ids);
			}
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("删除机井设备信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("删除机井设备信息失败，请联系管理人员");
		}
		logContent = ajaxJson.getObj().toString();
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * 跳转编辑机井设备信息页面
	 * @param request
	 */
	@RequestMapping(value="editPage")
	public ModelAndView editPage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(editJsp);
		String id = request.getParameter("id");
		BaseDeviceInfo baseDeviceInfo = baseDeviceInfoService.findById(id);
		BaseDeviceExpandInfo baseDeviceExpandInfo = baseDeviceExpandInfoService.findById(id);
		
		// 初始化行政区域信息
		SysArea sysArea = new SysArea();
		sysArea.setId(baseDeviceInfo.getAreaId());
		sysArea = sysAreaService.getAreaById(sysArea);
		// 获取到所有地区信息
		List<SysArea> sysAllAreaList = sysAreaService.getAllArea();
		// 获取所在行政区域的名称
		// 根据用户所属最低一级行政区域查询本区域及所有父区域
		List<SysArea> sysAreaList = null;
		if(null==sysArea.getParentAreaId() || "".equals(sysArea.getParentAreaId())) {
			sysAreaList = new ArrayList<SysArea>();
			sysAreaList.add(sysArea);
		} else {
			sysAreaList = AreaRecursionUtil.getCurrAndParentAreaList(sysAllAreaList, sysArea.getParentAreaId());
			sysAreaList.add(0, sysArea);
		}
		// 初始化水管区域信息
		SysWaterArea sysWaterArea = new SysWaterArea();
		sysWaterArea.setId(baseDeviceInfo.getWaterAreaId());
		sysWaterArea = sysWaterAreaService.getWaterAreaById(sysWaterArea); 
		// 获取到所有地区信息
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		// 获取所在行政区域的名称
		// 根据用户所属最低一级水管区域查询本区域及所有父区域
		List<SysWaterArea> sysWaterAreaList = null;
    	if(null==sysWaterArea.getParentWaterAreaId() || "".equals(sysWaterArea.getParentWaterAreaId())) {
    		sysWaterAreaList = new ArrayList<SysWaterArea>();
    		sysWaterAreaList.add(sysWaterArea);
    	} else {
    		sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndParentWaterAreaList(sysAllWaterAreaList, sysWaterArea.getParentWaterAreaId());
    		sysWaterAreaList.add(0, sysWaterArea);
    	}
		
		mav.addObject("baseDeviceInfo", baseDeviceInfo);
		mav.addObject("baseDeviceExpandInfo", baseDeviceExpandInfo);
		mav.addObject("sysAreaLists",sysAreaList);
		mav.addObject("sysWaterAreaLists",sysWaterAreaList);
		mav = getRelatedInfo(mav);
		return mav;
	}
	
	/**
	 * @Title: editBaseDeviceInfo
	 * @Description: 编辑机井设备信息
	 * @param: @param request
	 * @param: @param baseDeviceInfo
	 * @param: @param baseDeviceDynamicInfo
	 * @param: @param baseDeviceExpandInfo
	 * @param: @param installTimeStr
	 * @param: @param makeTimeStr
	 * @return: String
	 */
	@RequestMapping(value="editBaseDeviceInfo")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String editBaseDeviceInfo(HttpServletRequest request,BaseDeviceInfo baseDeviceInfo, BaseDeviceDynamicInfo baseDeviceDynamicInfo, BaseDeviceExpandInfo baseDeviceExpandInfo, String installTimeStr, String makeTimeStr){
		if(!StringUtils.isNullOrEmpty(installTimeStr)){
			Date date = PersonDateUtils.StringToDate(installTimeStr, "yyyy-MM-dd HH:mm:ss");
			baseDeviceInfo.setInstallTime(date);
		}
		if(!StringUtils.isNullOrEmpty(makeTimeStr)){
			Date date = PersonDateUtils.StringToDate(makeTimeStr, "yyyy-MM-dd HH:mm:ss");
			baseDeviceExpandInfo.setMakeTime(date);
		}
		AjaxJson ajaxJson = new AjaxJson();
		logContent = "";
		String msg = "";
		try{
			BaseDeviceInfo newBaseDeviceInfo = baseDeviceInfoService.findById(baseDeviceInfo.getId());
			try {
				BeanUtils.copyPropertiesExcludeNull(baseDeviceInfo, newBaseDeviceInfo);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			// 根据ID查询动态用水信息有无实体  只用判断
			BaseDeviceDynamicInfo deviceDynamicInfo = baseDeviceDynamicInfoService.findById(newBaseDeviceInfo.getId());
			// 根据ID查询机井参数信息有无实体  只用判断
			BaseDeviceExpandInfo deviceExpandInfo = baseDeviceExpandInfoService.findById(newBaseDeviceInfo.getId());
			
			if(baseDeviceInfo.getDeviceModel().equals("ZYJZ-IC")) {
				// 修改机井基础信息 更换设备后改DeviceType
				newBaseDeviceInfo.setDeviceType(1);
				baseDeviceInfoService.update(newBaseDeviceInfo);
				
				// 根据ID查询机井动态用水信息有无  有则修改  无则新增
				if(deviceDynamicInfo != null){
					baseDeviceDynamicInfo.setDeviceId(newBaseDeviceInfo.getId());
					baseDeviceDynamicInfoService.updateDynamicInfo(baseDeviceDynamicInfo);
				}else{
					// 恒泽更换金志控制器后 新增实时用水数据信息
					BaseDeviceDynamicInfo baseDynamicInfo = new BaseDeviceDynamicInfo();
					baseDynamicInfo.setDeviceId(newBaseDeviceInfo.getId());
					baseDynamicInfo.setDeviceCode(newBaseDeviceInfo.getDeviceCode());
					// 保存相关信息
					baseDeviceDynamicInfoService.add(baseDynamicInfo);
				}
				
				// 根据ID查询机井参数信息有无  有则修改  无则新增
				if(deviceExpandInfo != null){
					// 有信息 修改金讯机井参数信息
					baseDeviceExpandInfo.setDeviceId(newBaseDeviceInfo.getId());
					baseDeviceExpandInfoService.update(baseDeviceExpandInfo);
				}else{
					// 没有信息 新增机井设备参数信息
					BaseDeviceExpandInfo baseExpandInfo = new BaseDeviceExpandInfo();
					baseExpandInfo.setDeviceId(newBaseDeviceInfo.getId());
					baseExpandInfo.setDeviceCode(newBaseDeviceInfo.getDeviceCode());
					// 保存相关信息
					baseDeviceExpandInfoService.add(baseExpandInfo);
					// 新增后再修改信息 所需要的信息
					baseDeviceExpandInfo.setDeviceId(newBaseDeviceInfo.getId());
					baseDeviceExpandInfoService.update(baseDeviceExpandInfo);
				}
			} else {
				// 修改机井基础信息 更换设备后改DeviceType
				newBaseDeviceInfo.setDeviceType(2);
				baseDeviceInfoService.update(newBaseDeviceInfo);
				
				// 修改恒泽机井设备参数信息
				WellInfo wellInfo = new WellInfo();
				wellInfo.setWellId(newBaseDeviceInfo.getId());
				wellInfo.setWellName(newBaseDeviceInfo.getDeviceName());
				if(newBaseDeviceInfo.getDeviceNo() != ""){
					wellInfo.setDevID(Integer.parseInt(newBaseDeviceInfo.getDeviceNo()));
				}
				wellInfo.setCezhanID(newBaseDeviceInfo.getSimCard());
				wellInfoService.update(wellInfo);
			}
			logContent = "编辑【"+baseDeviceInfo.getDeviceName()+"】的机井设备数据";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);

		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("更新机井设备信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("编辑机井设备信息失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
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
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getFirstLevelList(null);
		List<SysArea> sysAreaList = sysAreaService.getFirstAreaLevelList(null);
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
	
	/**
	 * TODO: 获取水管区域子区域级联操作
	 * @param sysWaterArea
	 * 2016年10月16日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value = "getChildAreaInfo")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String getChildAreaInfo(SysWaterArea sysWaterArea, HttpServletRequest request) {
		String childAreaInfo = null;
		try {
			childAreaInfo = sysWaterAreaService.getChildAreaInfo(sysWaterArea);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return childAreaInfo;
	}
	
	/**
	 * 根据已选择的水管区域，获取到所有的水管区域的waterAreaCode值
	 * @param sysWaterArea
	 * @param request
	 * 2016年10月16日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value = "getWaterAreaCode")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String getWaterAreaCode(SysWaterArea sysWaterArea, HttpServletRequest request) {
		String waterAreaCodes = null;
		try {
			waterAreaCodes = sysWaterAreaService.getWaterAreaCode(sysWaterArea);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return waterAreaCodes;
	}
	
	/**
	 * 生成机井管理中同地区最大的机井水管码
	 * @param baseDeviceInfo
	 * @param request
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value = "getMaxWaterAreaCode")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String getMaxWaterAreaCode(BaseDeviceInfo baseDeviceInfo, HttpServletRequest request) {
		String deviceWaterAreaCode = null;
		try {
			deviceWaterAreaCode = baseDeviceInfoService.getMaxWaterAreaCode(baseDeviceInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deviceWaterAreaCode;
	}
	
	/**
	 * 获取行政区域子区域级联操作
	 * @param sysArea
	 * @param request
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value = "getChildDeviceAreaInfo")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String getChildDeviceAreaInfo(SysArea sysArea, HttpServletRequest request) {
		String childDeviceAreaInfo = null;
		try {
			childDeviceAreaInfo = sysAreaService.getChildDeviceAreaInfo(sysArea);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return childDeviceAreaInfo;
	}
	
	/**
	 * 根据已选择的行政区域，获取到所有的行政区域的areaCode值
	 * @param sysArea
	 * @param request
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value = "getAreaCode")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String getAreaCode(SysArea sysArea, HttpServletRequest request) {
		String areaCodes = null;
		try {
			areaCodes = sysAreaService.getAreaCode(sysArea);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return areaCodes;
	}
	
	/**
	 * 生成机井管理中同地区最大的机井行政码
	 * @param baseDeviceInfo
	 * @param request
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value = "getMaxAreaCode")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String getMaxAreaCode(BaseDeviceInfo baseDeviceInfo, HttpServletRequest request) {
		String deviceAreaCode = null;
		try {
			deviceAreaCode = baseDeviceInfoService.getMaxAreaCode(baseDeviceInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deviceAreaCode;
	}
	
	/**
	 * 根据最下一级的Id来获取机井基本表中机井信息
	 * @param baseDeviceInfo
	 * @param request
	 * 2016年10月25日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value = "getLastDeviceWaterAreaInfo")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String getLastDeviceWaterAreaInfo(BaseDeviceInfo baseDeviceInfo, HttpServletRequest request) {
		String deviceId = null;
		try {
			// 获取最后一级行政区域Id，根据areaId查询出baseDeviceInfo表中机井设备信息，封装成json字符串返回
			deviceId = baseDeviceInfoService.getLastDeviceWaterAreaInfo(baseDeviceInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deviceId;
	}
	
	/**
	 * @Title: uniqueDevCode
	 * @Description: 设备编码  唯一检验
	 * @param: @param deviceCode
	 * @return: String
	 */
	@RequestMapping(value="/uniqueDevCode")
	@ResponseBody
	public String uniqueDevCode(String deviceCode){
		String msg = null;
		try {
			msg = baseDeviceInfoService.uniqueDevCode(deviceCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(msg);
	}
	
	/**
	 * @Title: uniquePlateCode
	 * @Description: 机井编号  唯一检验
	 * @param: @param devicePlate
	 * @return: String
	 */
	@RequestMapping(value="/uniquePlateCode")
	@ResponseBody
	public String uniquePlateCode(String devicePlate){
		String msg = null;
		try {
			msg = baseDeviceExpandInfoService.uniquePlateCode(devicePlate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(msg);
	}
	
	/**
	 * @Title: uniquePlateCode
	 * @Description: 机井编号  唯一检验
	 * @param: @param devicePlate
	 * @return: String
	 */
	@RequestMapping(value="/uniqueDevno")
	@ResponseBody
	public String uniqueDevno(String deviceNo){
		String msg = null;
		try {
			msg = baseDeviceInfoService.uniqueDevno(deviceNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(msg);
	}
	
	/**
	 * @Title: uniqueDTUNo
	 * @Description: DTUNo唯一校验
	 * @param: @param dtuNo
	 * @return: String
	 */
	@RequestMapping(value="/uniqueDTUNo")
	@ResponseBody
	public String uniqueDTUNo(String dtuNo){
		String msg = null;
		try {
			msg = baseDeviceInfoService.uniqueDTUNo(dtuNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(msg);
	}
	/**
	 * @Title: getRealTimeByDeviceCode
	 * @Description:根据机井编码查询机井实时数据
	 * @param request
	 * @param deviceCode
	 * @return
	 * @return String
	 * @author 刘海年
	 * @date 2018年9月9日下午3:44:52
	 */
	@RequestMapping("/getRealTimeByDeviceCode")
	@ResponseBody
	public String getRealTimeByDeviceCode(HttpServletRequest request,String deviceCode){
		BaseDeviceDynamicInfo baseDeviceDynamicInfo = baseDeviceDynamicInfoService.getRealTimeByDeviceCode(deviceCode);
		request.setAttribute("baseDeviceDynamicInfo", baseDeviceDynamicInfo);
		return JSONObject.toJSONString(baseDeviceDynamicInfo);
	}
	/**
	 * 远程升级的页面
	 * @return
	 */
	@RequestMapping("/distanceUpdate")
	public ModelAndView distanceUpdate(){
		ModelAndView mav = new ModelAndView(distanceUpdate);
		List<String> versionList = baseDeviceInfoService.findDeviceVersion();//返回控制器的所有版本（不重复）
		mav.addObject("versionList", versionList);
		
		return mav;
	}
	
	/**
	 * 修改或查看图片信息页面
	 * @return
	 */
	@RequestMapping("/updateImgPage")
	public ModelAndView updateImgInfo(String id){
		BaseDeviceInfo baseInfo = baseDeviceInfoService.findSelectWaterId(id);
		ModelAndView mav = new ModelAndView(updateImg);
		mav.addObject("baseInfo", baseInfo);
		return mav;
	}
	
	@RequestMapping("/updateImgInfo")
	@ResponseBody
	public String updateImgInfo(HttpServletRequest request){
		String i = "";
		String filePath = "D:\\PirSource\\";
		String sign = request.getParameter("sign");
		String id = request.getParameter("id");
		BaseDeviceInfo baseInfo = baseDeviceInfoService.findSelectWaterId(id);
		String deviceAreaCode = request.getParameter("deviceAreaCode");
		MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest)request;
		MultipartFile file = mulRequest.getFile("uploadFile");
		String oriPhotoBefore = file.getOriginalFilename();
		File filer = new File(filePath);
		if(!filer.exists()&&!filer.isDirectory()){
			filer.mkdir();
		}
		FileUtils.fileUpload(filePath, file, deviceAreaCode+sign);
		try {
			if("A".equals(sign)){
				baseInfo.setPhotoAfter(deviceAreaCode+sign+oriPhotoBefore.substring(oriPhotoBefore.lastIndexOf(".")));
			}else{
				baseInfo.setPhotoBefore(deviceAreaCode+sign+oriPhotoBefore.substring(oriPhotoBefore.lastIndexOf(".")));
			}
			i = baseDeviceInfoService.update(baseInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(i);
	}
}










