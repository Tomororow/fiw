package com.fourfaith.sysManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.basicInformation.model.SysApplyStatus;
import com.fourfaith.basicInformation.model.SysDeviceModel;
import com.fourfaith.basicInformation.model.SysIrrigationAreaType;
import com.fourfaith.basicInformation.model.SysIrrigationMode;
import com.fourfaith.basicInformation.model.SysLandformType;
import com.fourfaith.basicInformation.model.SysMeasureType;
import com.fourfaith.basicInformation.model.SysOperator;
import com.fourfaith.basicInformation.model.SysPumpMaterial;
import com.fourfaith.basicInformation.model.SysServiceManInfo;
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
import com.fourfaith.basicInformation.service.SysServiceManInfoService;
import com.fourfaith.basicInformation.service.SysUseWaterTypeService;
import com.fourfaith.basicInformation.service.SysWellUseService;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;

/**   
 * @Title: Controller
 * @Description: 参数控制器
 * @author Hong
 * @date 2016-10-08 09:48:40
 * @version V1.0
 */
@Controller
@RequestMapping(value ="/baseInfo")
public class BaseInfoController {
	
	protected static final String indexJsp="/page/sysmanage/baseInfo/infoIndex";
	//运营商
	protected static final String operatorJsp = "/page/sysmanage/baseInfo/operator";
	protected static final String operatorAddJsp = "/page/sysmanage/baseInfo/operatorAdd";
	protected static final String operatorEditJsp = "/page/sysmanage/baseInfo/operatorEdit";
	//泵管材质
	protected static final String pumpMaterialJsp = "/page/sysmanage/baseInfo/pumpMaterial";
	protected static final String pumpMaterialAddJsp = "/page/sysmanage/baseInfo/pumpMaterialAdd";
	protected static final String pumpMaterialEditJsp = "/page/sysmanage/baseInfo/pumpMaterialEdit";
	//取水类型
	protected static final String waterTypeJsp = "/page/sysmanage/baseInfo/waterType";
	protected static final String waterTypeAddJsp = "/page/sysmanage/baseInfo/waterTypeAdd";
	protected static final String waterTypeEditJsp = "/page/sysmanage/baseInfo/waterTypeEdit";
	//应用状况
	protected static final String applyStatusJsp = "/page/sysmanage/baseInfo/applyStatus";
	protected static final String applyStatusAddJsp = "/page/sysmanage/baseInfo/applyStatusAdd";
	protected static final String applyStatusEditJsp = "/page/sysmanage/baseInfo/applyStatusEdit";
	//水井用途
	protected static final String wellUseJsp = "/page/sysmanage/baseInfo/wellUse";
	protected static final String wellUseAddJsp = "/page/sysmanage/baseInfo/wellUseAdd";
	protected static final String wellUseEditJsp = "/page/sysmanage/baseInfo/wellUseEdit";
	//灌溉模式
	protected static final String irrigationModeJsp = "/page/sysmanage/baseInfo/irrigationMode";
	protected static final String irrigationModeAddJsp = "/page/sysmanage/baseInfo/irrigationModeAdd";
	protected static final String irrigationModeEditJsp = "/page/sysmanage/baseInfo/irrigationModeEdit";
	//灌区类型
	protected static final String irrigationAreaTypeJsp = "/page/sysmanage/baseInfo/irrigationAreaType";
	protected static final String irrigationAreaTypeAddJsp = "/page/sysmanage/baseInfo/irrigationAreaTypeAdd";
	protected static final String irrigationAreaTypeEditJsp = "/page/sysmanage/baseInfo/irrigationAreaTypeEdit";
	//地貌类型
	protected static final String landformTypeJsp = "/page/sysmanage/baseInfo/landformType";
	protected static final String landformTypeAddJsp = "/page/sysmanage/baseInfo/landformTypeAdd";
	protected static final String landformTypeEditJsp = "/page/sysmanage/baseInfo/landformTypeEdit";
	//计量设施类型
	protected static final String measureTypeJsp = "/page/sysmanage/baseInfo/measureType";
	protected static final String measureTypeAddJsp = "/page/sysmanage/baseInfo/measureTypeAdd";
	protected static final String measureTypeEditJsp = "/page/sysmanage/baseInfo/measureTypeEdit";
	//机井设备型号
	protected static final String deviceModelJsp = "/page/sysmanage/baseInfo/deviceModel";
	protected static final String deviceModelAddJsp = "/page/sysmanage/baseInfo/deviceModelAdd";
	protected static final String deviceModelEditJsp = "/page/sysmanage/baseInfo/deviceModelEdit";
	//维修人员
	protected static final String serviceManJsp = "/page/sysmanage/baseInfo/serviceMan";
	protected static final String serviceManAddJsp = "/page/sysmanage/baseInfo/serviceManAdd";
	protected static final String serviceManEditJsp = "/page/sysmanage/baseInfo/serviceManEdit";
	
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
	private SysIrrigationAreaTypeService sysIrrigationAreaTypeService;
	@Autowired
	private SysLandformTypeService sysLandformTypeService;
	@Autowired
	private SysMeasureTypeService sysMeasureTypeService;
	@Autowired
	private SysDeviceModelService sysDeviceModelService;
	@Autowired
	private SysServiceManInfoService sysServiceManInfoService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	
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
	 * @Title: operator
	 * @Description: 运营商
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="operator")
	public ModelAndView operator(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(operatorJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		//分页
		String pageNo = request.getParameter("pageNo");
		int count = sysOperatorService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<SysOperator> sysOperatorList = sysOperatorService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("sysOperatorList",sysOperatorList);
		return mav;
	}
	
	/**
	 * @Title: operatorAdd
	 * @Description: 添加信息
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/operatorAdd")
    public ModelAndView operatorAdd(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(operatorAddJsp);
    	return mav;
    }
	
	/**
	 * @Title: operatorEdit
	 * @Description: 编辑信息
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/operatorEdit")
    public ModelAndView operatorEdit(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(operatorEditJsp);
    	String id = request.getParameter("id");
		SysOperator sysOperator = sysOperatorService.findById(id);
		mav.addObject("sysOperator", sysOperator);
    	return mav;
    }
	
	/**
	 * @Title: pumpMaterial
	 * @Description: 泵管材质
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="pumpMaterial")
	public ModelAndView pumpMaterial(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(pumpMaterialJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		//分页
		String pageNo = request.getParameter("pageNo");
		int count = sysPumpMaterialService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<SysPumpMaterial> sysPumpMaterialList = sysPumpMaterialService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("sysPumpMaterialList",sysPumpMaterialList);
		return mav;
	}
	
	/**
	 * @Title: pumpMaterialAdd
	 * @Description: 泵管材质信息添加
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/pumpMaterialAdd")
    public ModelAndView pumpMaterialAdd(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(pumpMaterialAddJsp);
    	return mav;
    }
	
	/**
	 * @Title: pumpMaterialEdit
	 * @Description: 泵管材质信息编辑
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/pumpMaterialEdit")
    public ModelAndView pumpMaterialEdit(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(pumpMaterialEditJsp);
    	String id = request.getParameter("id");
		SysPumpMaterial sysPumpMaterial = sysPumpMaterialService.findById(id);
		mav.addObject("sysPumpMaterial", sysPumpMaterial);
    	return mav;
    }
	
	/**
	 * @Title: waterType
	 * @Description: 取水类型
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="waterType")
	public ModelAndView waterType(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(waterTypeJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		//分页
		String pageNo = request.getParameter("pageNo");
		int count = sysUseWaterTypeService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<SysUseWaterType> sysUseWaterTypeList = sysUseWaterTypeService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("sysUseWaterTypeList",sysUseWaterTypeList);
		return mav;
	}
	
	/**
	 * @Title: waterTypeAdd
	 * @Description: 取水类型信息添加
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/waterTypeAdd")
    public ModelAndView waterTypeAdd(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(waterTypeAddJsp);
    	return mav;
    }
	
	/**
	 * @Title: waterTypeEdit
	 * @Description: 取水类型信息修改
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/waterTypeEdit")
    public ModelAndView waterTypeEdit(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(waterTypeEditJsp);
    	String id = request.getParameter("id");
    	SysUseWaterType sysUseWaterType = sysUseWaterTypeService.findById(id);
		mav.addObject("sysUseWaterType", sysUseWaterType);
    	return mav;
    }
	
	/**
	 * @Title: applyStatus
	 * @Description: 应用状况
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="applyStatus")
	public ModelAndView applyStatus(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(applyStatusJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		//分页
		String pageNo = request.getParameter("pageNo");
		int count = sysApplyStatusService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<SysApplyStatus> sysApplyStatusList = sysApplyStatusService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("sysApplyStatusList",sysApplyStatusList);
		return mav;
	}
	
	/**
	 * @Title: applyStatusAdd
	 * @Description: 应用状况信息添加
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/applyStatusAdd")
    public ModelAndView applyStatusAdd(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(applyStatusAddJsp);
    	return mav;
    }
	
	/**
	 * @Title: applyStatusEdit
	 * @Description: 应用状况信息修改
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/applyStatusEdit")
    public ModelAndView applyStatusEdit(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(applyStatusEditJsp);
    	String id = request.getParameter("id");
		SysApplyStatus sysApplyStatus = sysApplyStatusService.findById(id);
		mav.addObject("sysApplyStatus", sysApplyStatus);
    	return mav;
    }

	/**
	 * @Title: wellUse
	 * @Description: 水井用途
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="wellUse")
	public ModelAndView wellUse(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(wellUseJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		//分页
		String pageNo = request.getParameter("pageNo");
		int count = sysWellUseService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<SysWellUse> sysWellUseList = sysWellUseService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("sysWellUseList",sysWellUseList);
		return mav;
	}
	
	/**
	 * @Title: wellUseAdd
	 * @Description: 水井用途信息添加
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/wellUseAdd")
    public ModelAndView wellUseAdd(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(wellUseAddJsp);
    	return mav;
    }
	
	/**
	 * @Title: wellUseEdit
	 * @Description: 水井用途信息修改
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/wellUseEdit")
    public ModelAndView wellUseEdit(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(wellUseEditJsp);
    	String id = request.getParameter("id");
    	SysWellUse sysWellUse = sysWellUseService.findById(id);
		mav.addObject("sysWellUse", sysWellUse);
    	return mav;
    }
	
	/**
	 * @Title: irrigationMode
	 * @Description: 灌溉模式
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="irrigationMode")
	public ModelAndView irrigationMode(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(irrigationModeJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		//分页
		String pageNo = request.getParameter("pageNo");
		int count = sysIrrigationModeService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<SysIrrigationMode> sysIrrigationModeList = sysIrrigationModeService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("sysIrrigationModeList",sysIrrigationModeList);
		return mav;
	}
	
	/**
	 * @Title: irrigationModeAdd
	 * @Description: 灌溉模式信息添加
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/irrigationModeAdd")
    public ModelAndView irrigationModeAdd(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(irrigationModeAddJsp);
    	return mav;
    }
	
	/**
	 * @Title: irrigationModeEdit
	 * @Description: 灌溉模式信息修改
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/irrigationModeEdit")
    public ModelAndView irrigationModeEdit(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(irrigationModeEditJsp);
    	String id = request.getParameter("id");
    	SysIrrigationMode sysIrrigationMode = sysIrrigationModeService.findById(id);
		mav.addObject("sysIrrigationMode", sysIrrigationMode);
    	return mav;
    }
	
	/**
	 * @Title: irrigationAreaType
	 * @Description: 灌区类型
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="irrigationAreaType")
	public ModelAndView irrigationAreaType(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(irrigationAreaTypeJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		//分页
		String pageNo = request.getParameter("pageNo");
		int count = sysIrrigationAreaTypeService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<SysIrrigationAreaType> sysIrrigationAreaTypeList = sysIrrigationAreaTypeService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("sysIrrigationAreaTypeList",sysIrrigationAreaTypeList);
		return mav;
	}
	
	/***
	 * @Title: irrigationAreaTypeAdd
	 * @Description: 灌区类型信息添加
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/irrigationAreaTypeAdd")
    public ModelAndView irrigationAreaTypeAdd(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(irrigationAreaTypeAddJsp);
    	return mav;
    }
	
	/**
	 * @Title: irrigationAreaTypeEdit
	 * @Description: 灌区类型信息修改
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/irrigationAreaTypeEdit")
    public ModelAndView irrigationAreaTypeEdit(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(irrigationAreaTypeEditJsp);
    	String id = request.getParameter("id");
    	SysIrrigationAreaType sysIrrigationAreaType = sysIrrigationAreaTypeService.findById(id);
		mav.addObject("sysIrrigationAreaType", sysIrrigationAreaType);
    	return mav;
    }
	
	/**
	 * @Title: landformType
	 * @Description: 地貌类型
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="landformType")
	public ModelAndView landformType(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(landformTypeJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		//分页
		String pageNo = request.getParameter("pageNo");
		int count = sysLandformTypeService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<SysLandformType> sysLandformTypeList = sysLandformTypeService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("sysLandformTypeList",sysLandformTypeList);
		return mav;
	}
	
	/**
	 * @Title: landformTypeAdd
	 * @Description: 地貌类型信息添加
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/landformTypeAdd")
    public ModelAndView landformTypeAdd(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(landformTypeAddJsp);
    	return mav;
    }

	/**
	 * @Title: landformTypeTypeEdit
	 * @Description: 地貌类型信息修改
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/landformTypeEdit")
    public ModelAndView landformTypeTypeEdit(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(landformTypeEditJsp);
    	String id = request.getParameter("id");
    	SysLandformType sysLandformType = sysLandformTypeService.findById(id);
		mav.addObject("sysLandformType", sysLandformType);
    	return mav;
    }
	
	/**
	 * @Title: measureType
	 * @Description: 计量设施类型
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="measureType")
	public ModelAndView measureType(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(measureTypeJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		//分页
		String pageNo = request.getParameter("pageNo");
		int count = sysMeasureTypeService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<SysMeasureType> sysMeasureTypeList = sysMeasureTypeService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("sysMeasureTypeList",sysMeasureTypeList);
		return mav;
	}
	
	/**
	 * @Title: measureTypeAdd
	 * @Description: 计量设施类型信息添加
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/measureTypeAdd")
    public ModelAndView measureTypeAdd(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(measureTypeAddJsp);
    	return mav;
    }
	
	/**
	 * @Title: measureTypeEdit
	 * @Description: 计量设施类型信息修改
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/measureTypeEdit")
    public ModelAndView measureTypeEdit(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(measureTypeEditJsp);
    	String id = request.getParameter("id");
    	SysMeasureType sysMeasureType = sysMeasureTypeService.findById(id);
		mav.addObject("sysMeasureType", sysMeasureType);
    	return mav;
    }
	
	/**
	 * @Title: deviceModel
	 * @Description: 机井设备型号
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="deviceModel")
	public ModelAndView deviceModel(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(deviceModelJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		//分页
		String pageNo = request.getParameter("pageNo");
		int count = sysDeviceModelService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<SysDeviceModel> sysDeviceModelList = sysDeviceModelService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("sysDeviceModelList",sysDeviceModelList);
		return mav;
	}
	
	/**
	 * @Title: deviceModelAdd
	 * @Description: 机井设备型号信息添加
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/deviceModelAdd")
    public ModelAndView deviceModelAdd(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(deviceModelAddJsp);
    	return mav;
    }
	
	/**
	 * @Title: deviceModelEdit
	 * @Description: 机井设备型号信息编辑
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/deviceModelEdit")
    public ModelAndView deviceModelEdit(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(deviceModelEditJsp);
    	String id = request.getParameter("id");
    	SysDeviceModel sysDeviceModel = sysDeviceModelService.findById(id);
		mav.addObject("sysDeviceModel", sysDeviceModel);
    	return mav;
    }
	
	/**
	 * @Title: serviceMan
	 * @Description: 维修技术员
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="serviceMan")
	public ModelAndView serviceMan(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(serviceManJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		//分页
		String pageNo = request.getParameter("pageNo");
		int count = sysServiceManInfoService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<SysServiceManInfo> sysServiceManList = sysServiceManInfoService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("sysServiceManList",sysServiceManList);
		return mav;
	}
	
	/**
	 * @Title: serviceMan
	 * @Description: 维修技术员添加
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value = "/serviceManAdd")
    public ModelAndView serviceManAdd(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(serviceManAddJsp);
    	List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getFirstLevelList(null);
    	mav.addObject("sysWaterAreaList", sysWaterAreaList);
    	return mav;
    }
	
	/**
	 * @Title: serviceManEdit
	 * @Description: 维修技术员编辑
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value = "/serviceManEdit")
    public ModelAndView serviceManEdit(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(serviceManEditJsp);
    	String id = request.getParameter("id");
    	SysServiceManInfo sysServiceManInfo = sysServiceManInfoService.selectByPrimaryKey(id);
    	List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getFirstLevelList(null);
    	mav.addObject("sysWaterAreaList", sysWaterAreaList);
		mav.addObject("sysServiceMan", sysServiceManInfo);
    	return mav;
    }
	
	
	/**
	 * @Title: delBaseInfo
	 * @Description: 删除技术信息公用方法
	 * @param: @param request
	 * @param: @param distType
	 * @param: @throws Exception
	 * @return: String
	 * @Author: zhaojinxin
	 */
    @RequestMapping(value = "/delBaseInfo")
    @ResponseBody
    public String delBaseInfo(HttpServletRequest request,String distType) throws Exception{
    	String items = request.getParameter("items");
    	logContent = "";
    	
    	AjaxJson ajaxJson = null;
    	if("operator".equals(distType)){
    		ajaxJson = sysOperatorService.deleteDis(items);
    	}else if("pumpMaterial".equals(distType)){
    		ajaxJson = sysPumpMaterialService.deleteDis(items);
    	}else if("useWaterType".equals(distType)){
    		ajaxJson = sysUseWaterTypeService.deleteDis(items);
    	}else if("applyStatus".equals(distType)){
    		ajaxJson = sysApplyStatusService.deleteDis(items);
    	}else if("wellUse".equals(distType)){
    		ajaxJson = sysWellUseService.deleteDis(items);
    	}else if("irrigationMode".equals(distType)){
    		ajaxJson = sysIrrigationModeService.deleteDis(items);
    	}else if("irrigationAreaType".equals(distType)){
    		ajaxJson = sysIrrigationAreaTypeService.deleteDis(items);
    	}else if("landformType".equals(distType)){
    		ajaxJson = sysLandformTypeService.deleteDis(items);
    	}else if("measureType".equals(distType)){
    		ajaxJson = sysMeasureTypeService.deleteDis(items);
    	}else if("deviceModel".equals(distType)){
    		ajaxJson = sysDeviceModelService.deleteDis(items);
    	}else if("serviceMan".equals(distType)){
    		ajaxJson = sysServiceManInfoService.deleteDis(items);
    	}
    	logContent = ajaxJson.getObj().toString();
    	return JSONObject.toJSONString(ajaxJson);
    }
    
}