package com.fourfaith.sysManage.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.fourfaith.utils.BeanUtils;

/**
 * @ClassName: BaseInfoEditController
 * @Description: 基础信息
 * @Author: zhaojinxin
 * @Date: 2018年3月10日
 */
@Controller
@RequestMapping(value ="/baseInfoEdit")
public class BaseInfoEditController {
	
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
	
	//以下参数为添加日志所需
    public String logContent = "";
    
    /**
	 * 保存编辑供应商方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/editOperator", method = RequestMethod.POST)
	@ResponseBody
    public String editOperator(HttpServletRequest request, SysOperator plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			SysOperator status = sysOperatorService.findById(plan.getId());
			try {
				BeanUtils.copyPropertiesExcludeNull(plan, status);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			status.setEditTime(new Date());
			String msg = this.sysOperatorService.update(status);
			logContent = "编辑【"+plan.getOperator()+"】的数据";
			hm.put("success", true);
			hm.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("success", false);
			hm.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(hm);
	}
	
	/**
	 * 保存编辑泵水材质方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/editPumpMaterial", method = RequestMethod.POST)
	@ResponseBody
    public String editPumpMaterial(HttpServletRequest request, SysPumpMaterial plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			SysPumpMaterial status = sysPumpMaterialService.findById(plan.getId());
			try {
				BeanUtils.copyPropertiesExcludeNull(plan, status);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			status.setEditTime(new Date());
			String msg = this.sysPumpMaterialService.update(status);
			logContent = "编辑【"+plan.getPumpMaterial()+"】的数据";
			hm.put("success", true);
			hm.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("success", false);
			hm.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(hm);
	}
	
	/**
	 * 保存编辑取水类型方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/editWaterType", method = RequestMethod.POST)
	@ResponseBody
    public String editWaterType(HttpServletRequest request, SysUseWaterType plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			SysUseWaterType status = sysUseWaterTypeService.findById(plan.getId());
			try {
				BeanUtils.copyPropertiesExcludeNull(plan, status);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			status.setEditTime(new Date());
			String msg = this.sysUseWaterTypeService.update(status);
			logContent = "编辑【"+plan.getUseWaterType()+"】的数据";
			hm.put("success", true);
			hm.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("success", false);
			hm.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(hm);
	}
	
	/**
	 * 保存编辑应用状况方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/editApplyStatus", method = RequestMethod.POST)
	@ResponseBody
    public String editApplyStatus(HttpServletRequest request, SysApplyStatus plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			SysApplyStatus status = sysApplyStatusService.findById(plan.getId());
			try {
				BeanUtils.copyPropertiesExcludeNull(plan, status);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			status.setEditTime(new Date());
			String msg = this.sysApplyStatusService.update(status);
			logContent = "编辑【"+plan.getApplyStatus()+"】的数据";
			hm.put("success", true);
			hm.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("success", false);
			hm.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(hm);
	}
	
	/**
	 * 保存编辑水井用途方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/editWellUse", method = RequestMethod.POST)
	@ResponseBody
    public String editWellUse(HttpServletRequest request, SysWellUse plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			SysWellUse status = sysWellUseService.findById(plan.getId());
			try {
				BeanUtils.copyPropertiesExcludeNull(plan, status);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			status.setEditTime(new Date());
			String msg = this.sysWellUseService.update(status);
			logContent = "编辑【"+plan.getWellUse()+"】的数据";
			
			hm.put("success", true);
			hm.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("success", false);
			hm.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(hm);
	}
	
	/**
	 * 保存编辑灌溉模式方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/editIrrigationMode", method = RequestMethod.POST)
	@ResponseBody
    public String editIrrigationMode(HttpServletRequest request, SysIrrigationMode plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			SysIrrigationMode status = sysIrrigationModeService.findById(plan.getId());
			try {
				BeanUtils.copyPropertiesExcludeNull(plan, status);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			status.setEditTime(new Date());
			String msg = this.sysIrrigationModeService.update(status);
			logContent = "编辑【"+plan.getIrrigationMode()+"】的数据";
			
			hm.put("success", true);
			hm.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("success", false);
			hm.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(hm);
	}
	
	/**
	 * 保存编辑灌区类型方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/editIrrigationAreaType", method = RequestMethod.POST)
	@ResponseBody
    public String editIrrigationAreaType(HttpServletRequest request, SysIrrigationAreaType plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			SysIrrigationAreaType status = sysIrrigationAreaTypeService.findById(plan.getId());
			try {
				BeanUtils.copyPropertiesExcludeNull(plan, status);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			status.setEditTime(new Date());
			String msg = this.sysIrrigationAreaTypeService.update(status);
			logContent = "编辑【"+plan.getIrrigationAreaType()+"】的数据";
			
			hm.put("success", true);
			hm.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("success", false);
			hm.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(hm);
	}
	
	/**
	 * 保存编辑地貌类型方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/editLandformType", method = RequestMethod.POST)
	@ResponseBody
    public String editLandformType(HttpServletRequest request, SysLandformType plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			SysLandformType status = sysLandformTypeService.findById(plan.getId());
			try {
				BeanUtils.copyPropertiesExcludeNull(plan, status);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			status.setEditTime(new Date());
			String msg = this.sysLandformTypeService.update(status);
			logContent = "编辑【"+plan.getLandformType()+"】的数据";
			
			hm.put("success", true);
			hm.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("success", false);
			hm.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(hm);
	}
	
	/**
	 * 保存编辑计量设施类型方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/editMeasureType", method = RequestMethod.POST)
	@ResponseBody
    public String editMeasureType(HttpServletRequest request, SysMeasureType plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			SysMeasureType status = sysMeasureTypeService.findById(plan.getId());
			try {
				BeanUtils.copyPropertiesExcludeNull(plan, status);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			status.setEditTime(new Date());
			String msg = this.sysMeasureTypeService.update(status);
			logContent = "编辑【"+plan.getMeasureType()+"】的数据";
			
			hm.put("success", true);
			hm.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("success", false);
			hm.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(hm);
	}
	
	/**
	 * 保存编辑机井设备型号方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/editDeviceModel", method = RequestMethod.POST)
	@ResponseBody
    public String editDeviceModel(HttpServletRequest request, SysDeviceModel plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			SysDeviceModel status = sysDeviceModelService.findById(plan.getId());
			try {
				BeanUtils.copyPropertiesExcludeNull(plan, status);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			status.setEditTime(new Date());
			String msg = this.sysDeviceModelService.update(status);
			logContent = "编辑【"+plan.getDeviceModel()+"】的数据";
			
			hm.put("success", true);
			hm.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("success", false);
			hm.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(hm);
	}
	
	/**
	 * @Title: editServiceMan
	 * @Description: 维修人员信息编辑
	 * @param: @param request
	 * @param: @param sysServiceManInfo
	 * @param: @throws Exception
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value = "/editServiceMan", method = RequestMethod.POST)
	@ResponseBody
    public String editServiceMan(HttpServletRequest request, SysServiceManInfo sysServiceManInfo) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			SysServiceManInfo sysInfo = sysServiceManInfoService.findById(sysServiceManInfo.getId());
			String [] dtr = sysServiceManInfo.getWaterAreaId().split(",");
			try {
				BeanUtils.copyPropertiesExcludeNull(sysServiceManInfo, sysInfo);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
//			sysInfo.setCreateTime(new Date());
			sysInfo.setWaterAreaId(dtr[dtr.length-1]);
			String msg = sysServiceManInfoService.update(sysInfo);
			logContent = "编辑维修人员的数据";
			
			hm.put("success", true);
			hm.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("success", false);
			hm.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(hm);
	}
	
}