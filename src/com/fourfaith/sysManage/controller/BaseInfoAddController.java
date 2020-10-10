package com.fourfaith.sysManage.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.fourfaith.utils.CommonUtil;

/**   
 * @Title: Controller
 * @Description: 系统管理-->基础信息代码
 * @author Hong
 * @date 2016-10-13 15:13:40
 * @version V1.0
 */
@Controller
@RequestMapping(value ="/baseInfoAdd")
public class BaseInfoAddController {
	
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
	 * 保存新增供应商方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/addOperator", method = RequestMethod.POST)
	@ResponseBody
    public String addOperator(HttpServletRequest request, SysOperator plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			plan.setId(CommonUtil.getRandomUUID());
			plan.setCreateTime(new Date());
			plan.setEditTime(new Date());
			String msg = this.sysOperatorService.add(plan);
			logContent = "添加【"+plan.getOperator()+"】的数据";
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
	 * 保存新增泵管材质方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/addPumpMaterial", method = RequestMethod.POST)
	@ResponseBody
    public String addPumpMaterial(HttpServletRequest request, SysPumpMaterial plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			plan.setId(CommonUtil.getRandomUUID());
			plan.setCreateTime(new Date());
			plan.setEditTime(new Date());
			String msg = this.sysPumpMaterialService.add(plan);
			logContent = "添加【"+plan.getPumpMaterial()+"】的数据";
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
	 * 保存新增取水类型方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/addWaterType", method = RequestMethod.POST)
	@ResponseBody
    public String addWaterType(HttpServletRequest request, SysUseWaterType plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			plan.setId(CommonUtil.getRandomUUID());
			plan.setCreateTime(new Date());
			plan.setEditTime(new Date());
			String msg = this.sysUseWaterTypeService.add(plan);
			logContent = "添加【"+plan.getUseWaterType()+"】的数据";
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
	 * 保存新增应用状况方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/addApplyStatus", method = RequestMethod.POST)
	@ResponseBody
    public String addApplyStatus(HttpServletRequest request, SysApplyStatus plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			plan.setId(CommonUtil.getRandomUUID());
			plan.setCreateTime(new Date());
			plan.setEditTime(new Date());
			String msg = this.sysApplyStatusService.add(plan);
			logContent = "添加【"+plan.getApplyStatus()+"】的数据";
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
	 * 保存新增水井用途方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/addWellUse", method = RequestMethod.POST)
	@ResponseBody
    public String addWellUse(HttpServletRequest request, SysWellUse plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			plan.setId(CommonUtil.getRandomUUID());
			plan.setCreateTime(new Date());
			plan.setEditTime(new Date());
			String msg = this.sysWellUseService.add(plan);
			logContent = "添加【"+plan.getWellUse()+"】的数据";
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
	 * 保存新增灌溉模式方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/addIrrigationMode", method = RequestMethod.POST)
	@ResponseBody
    public String addIrrigationMode(HttpServletRequest request, SysIrrigationMode plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			plan.setId(CommonUtil.getRandomUUID());
			plan.setCreateTime(new Date());
			plan.setEditTime(new Date());
			String msg = this.sysIrrigationModeService.add(plan);
			logContent = "添加【"+plan.getIrrigationMode()+"】的数据";
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
	 * 保存新增灌区类型方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/addIrrigationAreaType", method = RequestMethod.POST)
	@ResponseBody
    public String addIrrigationAreaType(HttpServletRequest request, SysIrrigationAreaType plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			plan.setId(CommonUtil.getRandomUUID());
			plan.setCreateTime(new Date());
			plan.setEditTime(new Date());
			String msg = this.sysIrrigationAreaTypeService.add(plan);
			logContent = "添加【"+plan.getIrrigationAreaType()+"】的数据";
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
	 * 保存新增地貌类型方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/addLandformType", method = RequestMethod.POST)
	@ResponseBody
    public String addLandformType(HttpServletRequest request, SysLandformType plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			plan.setId(CommonUtil.getRandomUUID());
			plan.setCreateTime(new Date());
			plan.setEditTime(new Date());
			String msg = this.sysLandformTypeService.add(plan);
			logContent = "添加【"+plan.getLandformType()+"】的数据";
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
	 * 保存新增计量设施类型方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/addMeasureType", method = RequestMethod.POST)
	@ResponseBody
    public String addMeasureType(HttpServletRequest request, SysMeasureType plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			plan.setId(CommonUtil.getRandomUUID());
			plan.setCreateTime(new Date());
			plan.setEditTime(new Date());
			String msg = this.sysMeasureTypeService.add(plan);
			logContent = "添加【"+plan.getMeasureType()+"】的数据";
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
	 * 保存新增机井设备型号方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/addDeviceModel", method = RequestMethod.POST)
	@ResponseBody
    public String addDeviceModel(HttpServletRequest request, SysDeviceModel plan) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			plan.setId(CommonUtil.getRandomUUID());
			plan.setCreateTime(new Date());
			plan.setEditTime(new Date());
			String msg = this.sysDeviceModelService.add(plan);
			logContent = "添加【"+plan.getDeviceModel()+"】的数据";
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
	 * @Title: addServiceMan
	 * @Description: 增加维修人员信息
	 * @param: @param request
	 * @param: @param sysServiceManInfo
	 * @param: @throws Exception
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value = "/addServiceMan", method = RequestMethod.POST)
	@ResponseBody
    public String addServiceMan(HttpServletRequest request, SysServiceManInfo sysServiceManInfo) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			String [] dtr = sysServiceManInfo.getWaterAreaId().split(",");
			sysServiceManInfo.setWaterAreaId(dtr[dtr.length-1]);
			sysServiceManInfo.setCreateTime(new Date());
			String msg = this.sysServiceManInfoService.add(sysServiceManInfo);
			logContent = "添加维修人员信息数据";
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