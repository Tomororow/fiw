package com.fourfaith.paramterManage.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.fourfaith.sysManage.dao.WaterindexMapper;
import com.fourfaith.sysManage.model.Waterindex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fourfaith.basicInformation.model.SysServiceManInfo;
import com.fourfaith.basicInformation.model.SysWellUse;
import com.fourfaith.basicInformation.service.SysServiceManInfoService;
import com.fourfaith.basicInformation.service.SysWellUseService;
import com.fourfaith.paramterManage.model.ParamBaseWaterPrice;
import com.fourfaith.paramterManage.model.ParamMaintainPrice;
import com.fourfaith.paramterManage.model.ParamMeasureTypePrice;
import com.fourfaith.paramterManage.model.ParamPower;
import com.fourfaith.paramterManage.model.ParamPumpingWell;
import com.fourfaith.paramterManage.model.ParamThreeVoltage;
import com.fourfaith.paramterManage.model.ParamWaterSourcePrice;
import com.fourfaith.paramterManage.model.Warnerrortype;
import com.fourfaith.paramterManage.model.Warnmiddleperson;
import com.fourfaith.paramterManage.service.ParamBaseWaterPriceService;
import com.fourfaith.paramterManage.service.ParamMaintainPriceService;
import com.fourfaith.paramterManage.service.ParamMeasureTypePriceService;
import com.fourfaith.paramterManage.service.ParamPowerService;
import com.fourfaith.paramterManage.service.ParamPumpingWellService;
import com.fourfaith.paramterManage.service.ParamThreeVoltageService;
import com.fourfaith.paramterManage.service.ParamWaterSourcePriceService;
import com.fourfaith.paramterManage.service.WarnerrortypeService;
import com.fourfaith.paramterManage.service.WarnmiddlepersonService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PersonDateUtils;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.StringUtils;
import com.fourfaith.utils.WaterAreaRecursionUtil;

/**
 * @ClassName: ParameterManageController
 * @Description: 参数管理
 * @Author: zhaojinxin
 * @Date: 2017年10月21日 上午11:47:39
 */
@Controller
@RequestMapping(value="/parameterManage")
public class ParameterManageController {

	protected static final String threeVoltageJsp = "/page/sysmanage/parameterManage/threeVoltage";
	protected static final String addThreeVoltageJsp = "/page/sysmanage/parameterManage/addThreeVoltage";
	protected static final String editThreeVoltageJsp = "/page/sysmanage/parameterManage/editThreeVoltage";
	protected static final String threeVoltageListJsp = "/page/sysmanage/parameterManage/threeVoltageList";
	protected static final String threeCurrentJsp = "page/sysmanage/parameterManage/threeCurrent";
	protected static final String powerInfoJsp = "page/sysmanage/parameterManage/powerInfo";
	protected static final String addPowerJsp = "/page/sysmanage/parameterManage/addPower";
	protected static final String editPowerJsp = "/page/sysmanage/parameterManage/editPower";
	protected static final String pumpingWellJsp = "page/sysmanage/parameterManage/pumpingWell";
	protected static final String addPumpingWellJsp = "/page/sysmanage/parameterManage/addPumpingWell";
	protected static final String editPumpingWell = "/page/sysmanage/parameterManage/editPumpingWell";
	/* 水资源费率维护页面 */
	protected static final String waterSourcePriceJsp = "/page/sysmanage/parameterManage/waterSourcePrice";
	protected static final String addWaterSourcePriceJsp = "/page/sysmanage/parameterManage/addWaterSourcePrice";
	protected static final String editWaterSourcePriceJsp = "/page/sysmanage/parameterManage/editWaterSourcePrice";
	/* 计量水费率维护页面 */
	protected static final String measureTypePriceJsp = "/page/sysmanage/parameterManage/measureTypePrice";
	protected static final String addMeasureTypePriceJsp = "/page/sysmanage/parameterManage/addMeasureTypePrice";
	protected static final String editMeasureTypePriceJsp = "/page/sysmanage/parameterManage/editMeasureTypePrice";
	/* 基本水费费率维护页面 */
	protected static final String baseWaterPriceJsp = "/page/sysmanage/parameterManage/baseWaterPrice";
	protected static final String addBaseWaterPriceJsp = "/page/sysmanage/parameterManage/addBaseWaterPrice";
	protected static final String editBaseWaterPriceJsp = "/page/sysmanage/parameterManage/editBaseWaterPrice";
	/* 末级渠系费率维护页面 */
	protected static final String maintainPriceJsp = "/page/sysmanage/parameterManage/maintainPrice";
	protected static final String addMaintainPriceJsp = "/page/sysmanage/parameterManage/addMaintainPrice";
	protected static final String editMaintainPriceJsp = "/page/sysmanage/parameterManage/editMaintainPrice";
	/*设备异常类型设置*/
	protected static final String warnErrorPageJsp = "/page/sysmanage/parameterManage/warnErrorPage";
	protected static final String warnAddErrorPageJsp = "/page/sysmanage/parameterManage/warnAddErrorPage";
	protected static final String warnEditErrorPageJsp = "/page/sysmanage/parameterManage/warnEditErrorPage";
	/*设置用水指标*/
	protected static final String waterIndicator = "/page/sysmanage/parameterManage/waterIndicator";
	protected static final String addEditIndicator = "/page/sysmanage/parameterManage/addEditIndicator";
	
	@Autowired
	private ParamThreeVoltageService paramThreeVoltageService;
	@Autowired
	private ParamPowerService paramPowerService;
	@Autowired
	private ParamPumpingWellService paramPumpingWellService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private ParamWaterSourcePriceService paramWaterSourcePriceService;
	@Autowired
	private ParamMeasureTypePriceService paramMeasureTypePriceService;
	@Autowired
	private ParamBaseWaterPriceService paramBaseWaterPriceService;
	@Autowired
	private ParamMaintainPriceService paramMaintainPriceService;
	@Autowired
	private SysWellUseService sysWellUseService;
	@Autowired
	private WarnerrortypeService warnerrortypeService;
	@Autowired
	private WarnmiddlepersonService warnmiddlepersonService;
	@Autowired
	private SysServiceManInfoService sysServiceManInfoService;
	@Autowired
	private WaterindexMapper waterindexMapper;
	
	//以下参数为添加日志所需
    public String logContent = "";
	
	/**
	 * TODO: 三相电压设置
	 * @param request
	 * 2016年11月20日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="/threeVoltage", method = RequestMethod.GET)
	public ModelAndView threeVoltage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(threeVoltageJsp);
		// 获取三相电流记录条数
		int count = paramThreeVoltageService.getCount();
		Map<String, Object> params = new HashMap<String, Object>();
		String s_start = request.getParameter("pageNo");
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取三相电压信息集合
		List<ParamThreeVoltage> paramThreeVoltageList = paramThreeVoltageService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("paramThreeVoltageList", paramThreeVoltageList);
		return mav;
	}
	
	/**
	 * TODO: 跳入三相电压新增界面
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="addThreeVoltagePage")
	public ModelAndView addThreeVoltagePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(addThreeVoltageJsp);
		return mav;
	}
	
	/**
	 * TODO: 保存三相电压新增内容
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="addThreeVoltage")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String addThreeVoltage(ParamThreeVoltage paramThreeVoltage, HttpServletRequest request) {
		String id = CommonUtil.getRandomUUID();
		paramThreeVoltage.setId(id);
		logContent = "";
		AjaxJson ajaxJson = new AjaxJson();
		try{
			// 新增三相电压设置
			String msg = paramThreeVoltageService.add(paramThreeVoltage);
			logContent = "添加【三相电压】的数据";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("添加机井设备信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("添加机井设备信息失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * TODO: 进入三相电压修改页面
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="editThreeVoltagePage")
	public ModelAndView editThreeVoltagePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(editThreeVoltageJsp);
		String id = request.getParameter("id");
		// 根据id查询三相电压信息
		ParamThreeVoltage threeVoltage = paramThreeVoltageService.findThreeVoltageById(id);
		mav.addObject("threeVoltage", threeVoltage);
		return mav;
	}
	
	/**
	 * TODO: 修改三相电压信息
	 * @param paramThreeVoltage
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="editThreeVoltage")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String editThreeVoltage(ParamThreeVoltage paramThreeVoltage, HttpServletRequest request) {
		logContent = "";
		AjaxJson ajaxJson = new AjaxJson();
		try{
			// 修改三相电压设置
			String msg = paramThreeVoltageService.edit(paramThreeVoltage);
			logContent = "修改【三相电压】的数据";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("添加三相电压信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("添加三相电压信息失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * TODO: 根据Id删除三项信息
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="delThreeVoltage")
	@ResponseBody
	public String delThreeVoltage(HttpServletRequest request) {
		logContent = "";
		String ids = request.getParameter("ids");
		AjaxJson ajaxJson = new AjaxJson();
		try{
			ajaxJson = paramThreeVoltageService.delete(ids);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("删除三项信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("删除三项信息失败，请联系管理人员");
		}
		logContent = ajaxJson.getObj().toString();
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * TODO: 功率设置
	 * @param request
	 * 2016年11月20日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="/powerInfo")
	public ModelAndView powerInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(powerInfoJsp);
		// 获取功率记录条数
		int count = paramPowerService.getCount();
		Map<String, Object> params = new HashMap<String, Object>();
		String s_start = request.getParameter("pageNo");
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取功率信息集合
		List<ParamPower> paramPowerList = paramPowerService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("paramPowerList", paramPowerList);
		return mav;
	}
	
	/**
	 * TODO: 跳入功率新增界面
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="addPowerPage")
	public ModelAndView addPowerPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(addPowerJsp);
		return mav;
	}
	
	/**
	 * TODO: 保存功率新增内容
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="addPower")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String addPower(ParamPower paramPower, HttpServletRequest request) {
		String id = CommonUtil.getRandomUUID();
		paramPower.setId(id);
		logContent = "";
		AjaxJson ajaxJson = new AjaxJson();
		try{
			// 新增功率设置
			String msg = paramPowerService.add(paramPower);
			logContent = "添加【功率】的数据";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("添加机井设备信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("添加机井设备信息失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * TODO: 进入功率修改页面
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="editPowerPage")
	public ModelAndView editPowerPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(editPowerJsp);
		String id = request.getParameter("id");
		// 根据id查询功率信息
		ParamPower paramPower = paramPowerService.findPowerById(id);
		mav.addObject("paramPower", paramPower);
		return mav;
	}
	
	/**
	 * TODO: 修改功率信息
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="editPower")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String editPower(ParamPower paramPower, HttpServletRequest request) {
		logContent = "";
		AjaxJson ajaxJson = new AjaxJson();
		try{
			// 修改功率设置
			String msg = paramPowerService.edit(paramPower);
			logContent = "修改【三相电压】的数据";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("添加功率信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("添加功率息失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * TODO: 根据Id删除功率信息
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="delPower")
	@ResponseBody
	public String delPower(HttpServletRequest request) {
		logContent = "";
		String ids = request.getParameter("ids");
		AjaxJson ajaxJson = new AjaxJson();
		try{
			ajaxJson = paramPowerService.delete(ids);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("删除功率信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("删除功率信息失败，请联系管理人员");
		}
		logContent = ajaxJson.getObj().toString();
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * TODO: 异常参数设置设置
	 * @param request
	 * 2016年11月20日
	 * Administrator: xiaogaoxiang
	 * @throws ParseException 
	 */
	@RequestMapping(value="/pumpingWell", method = RequestMethod.GET)
	public ModelAndView peakPumping(HttpServletRequest request) throws ParseException {
		ModelAndView mav = new ModelAndView(pumpingWellJsp);
		// 获取记录条数
		Map<String, Object> params = new HashMap<String, Object>();
		String deviceCode = request.getParameter("deviceCode");
		String deviceName = request.getParameter("deviceName");
		String deviceWellUse = request.getParameter("deviceWellUse");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String waterAreaId = request.getParameter("waterAreaId");
		
		params.put("endTime", endTime);
		params.put("deviceCode",deviceCode);
		params.put("deviceName",deviceName);
		params.put("deviceWellUse", deviceWellUse);
		params.put("startTime", beginTime);
		List<String> waterAreaIdsList = new ArrayList<String>();
		List<SysWaterArea> AllList = new ArrayList<SysWaterArea>();
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		if(StringUtils.isNullOrEmpty(waterAreaId)){
			//获取选择的水管区域树菜单id 得到所属的子区域并放入查询集合
			if(login_user!=null){
				waterAreaId = login_user.getWaterAreaId();
			}
		}
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getCurrAndChildWaterArea(waterAreaId);
		for(SysWaterArea sysWaterArea : sysWaterAreaList){
			waterAreaIdsList.add(sysWaterArea.getId());
			AllList.add(sysWaterArea);
		}
		waterAreaIdsList.add(waterAreaId);
		params.put("list", waterAreaIdsList);
		int count =0;
		count = paramPumpingWellService.getCount(params);
		String s_start = request.getParameter("pageNo");
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		Calendar cal = Calendar.getInstance();  
		String sDateTime = cal.get(Calendar.YEAR)+"-01-01 00:00:00";
		String sEndTime = cal.get(Calendar.YEAR)+"-12-31 23:59:59";
		Date sTime = null;
		Date eTime = null;
		if(!StringUtils.isNullOrEmpty(beginTime)){
			sTime = PersonDateUtils.StringToDate(beginTime,"yyyy-MM-dd HH:mm:ss");
			eTime = PersonDateUtils.StringToDate(endTime,"yyyy-MM-dd HH:mm:ss");
		}else{
			sTime = PersonDateUtils.StringToDate(sDateTime,"yyyy-MM-dd HH:mm:ss");
			eTime = PersonDateUtils.StringToDate(sEndTime,"yyyy-MM-dd HH:mm:ss");
		}
		List<ParamPumpingWell> selectAllList = paramPumpingWellService.selectAll(params);
		List<SysWellUse> list = sysWellUseService.getList(params);
		mav.addObject("sTime", sTime);
		mav.addObject("eTime", eTime);
		mav.addObject("wellList", list);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("sysWaterAreaList", AllList);
		mav.addObject("paramPowerList", selectAllList);
		return mav;
	}
/*	//预警参数设置信息的查询
	@RequestMapping(value="selectPumpingWellPage")
	public ModelAndView SelectPumpingWellPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(addPumpingWellJsp);
		
		return mav;
	}*/
		
	/**
	 * TODO: 跳入新增界面
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="addPumpingWellPage")
	public ModelAndView addPumpingWellPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(addPumpingWellJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		List<SysWellUse> list = sysWellUseService.getList(params);
		Calendar cal = Calendar.getInstance();  
		mav.addObject("wellList", list);
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getLoginerWaterAreaList(login_user);
		mav.addObject("sysWaterAreaList", sysWaterAreaList);
		return mav;
	}
	
	/**
	 * TODO: 保存异常机井参数新增内容
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="/addPumpingWell")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String addPumpingWell(String deviceWellUse,BigDecimal backone,int onnetstate,int waterlow,String waterAreaId,String baseDeviceId, String startTimes, String endTimes, HttpServletRequest request) {
		String id = CommonUtil.getRandomUUID();
		logContent = "";
		AjaxJson ajaxJson = new AjaxJson();
		try{
			// 新增异常机井参数设置
			String msg = paramPumpingWellService.add(deviceWellUse,backone,onnetstate,waterlow,waterAreaId,baseDeviceId,startTimes,endTimes);
			logContent = "添加【异常机井参数信息】的数据";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("添加异常机井参数信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("添加异常机井参数信息失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	/**
	 * 跳转修改异常数据
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/editPumpingWell")
	public ModelAndView editPumpingWell(String id) throws ParseException{
		ModelAndView mav = new ModelAndView(editPumpingWell);
		ParamPumpingWell ppw = paramPumpingWellService.findPumpingWellById(id);
		mav.addObject("ppw", ppw);
		mav.addObject("startTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ppw.getStartTime()));
		mav.addObject("endTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ppw.getEndTime()));
		return mav;
	}
	/**
	 * 修改异常数据
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/editPumpingData")
	@ResponseBody
	public String editPumpingWell(int id,int onlineDay,int useWater,String startTimes, String endTimes,String backone) throws ParseException{
 		ParamPumpingWell paramPumpingWell = new ParamPumpingWell();
		paramPumpingWell.setOnnetstate(onlineDay);
		paramPumpingWell.setWaterlow(useWater);
		paramPumpingWell.setBackone(backone);
		if(!StringUtils.isNullOrEmpty(startTimes)){
			Date date = PersonDateUtils.StringToDate(startTimes, "yyyy-MM-dd HH:mm:ss");
			paramPumpingWell.setStartTime(date);
		}
		if(!StringUtils.isNullOrEmpty(endTimes)){
			Date date = PersonDateUtils.StringToDate(endTimes, "yyyy-MM-dd HH:mm:ss");
			paramPumpingWell.setEndTime(date);
		}
		paramPumpingWell.setId(id);
		paramPumpingWell.setEdittime(new Date());
		int i = paramPumpingWellService.edit(paramPumpingWell);
		return JSONObject.toJSONString(i);
	}
	/**
	 * TODO: 根据Id删除异常机井智能参数设置信息
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="/delPumpingWell")
	@ResponseBody
	public String delPumpingWell(HttpServletRequest request) {
		logContent = "";
		String ids = request.getParameter("ids");
		AjaxJson ajaxJson = new AjaxJson();
		try{
			ajaxJson = paramPumpingWellService.delete(ids);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("删除功率信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("删除功率信息失败，请联系管理人员");
		}
		logContent = ajaxJson.getObj().toString();
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * 设备异常类型设置
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/warnErrorPage")
	public ModelAndView errorPage(HttpServletRequest request,int sign){
		ModelAndView mav = null;
		List<Warnerrortype> warnList = new ArrayList<>();
		
		List<SysServiceManInfo> sysInfoList = new ArrayList<>();
		SysUser login_user = new SysUser();
		String regex = "^,*|,*$";
		try {
			new ModelAndView(warnErrorPageJsp);
			switch (sign) {
			case 0:
				String pageNo = request.getParameter("pageNo");
				pageNo = StringUtils.isNullOrEmpty(pageNo)?"1":pageNo;
				mav = new ModelAndView(warnErrorPageJsp);
				int counr = 0;
				counr = warnerrortypeService.selectByCount();
				PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", counr, PagingBean.DEFAULT_PAGE_SIZE);
				warnList = warnerrortypeService.selectByPrimaryKey(null,""+pagingBean.getStart(), ""+pagingBean.getLimit());
				for(Warnerrortype wt:warnList){
					List<SysServiceManInfo> abList = sysServiceManInfoService.findMiddlePersonList(""+wt.getAbnormalcode());
					String tryui = "";
					if(abList.size()>0){
						for(SysServiceManInfo sy:abList){
							if(sy!=null){
								tryui+= sy.getServiceMan()+",";
							}
						}
						wt.setBackone(tryui.replaceAll(regex, ""));
					}
					
				}
				mav.addObject("pagingBean", pagingBean);
				mav.addObject("warnList",warnList);
				break;
			case 1:
				mav = new ModelAndView(warnAddErrorPageJsp);
				mav.addObject("sysInfoList", sysInfoList(request));
				break;
			default:
				mav = new ModelAndView(warnEditErrorPageJsp);
				String id = request.getParameter("id");
				String code = request.getParameter("code");
				if(!StringUtils.isNullOrEmpty(id)){
					warnList = warnerrortypeService.selectByPrimaryKey(Integer.parseInt(id),null,null);
					mav.addObject("abnormaltype", warnList.get(0).getAbnormaltype());
					mav.addObject("abnormalcode", warnList.get(0).getAbnormalcode());
					mav.addObject("abnormaldetail", warnList.get(0).getAbnormaldetail());
					mav.addObject("ismess", warnList.get(0).getIsmess());
				}
				int count = 0;
				String personList = "";
				List<SysServiceManInfo> sysList = sysInfoList(request);
				List<SysServiceManInfo> abList = sysServiceManInfoService.findMiddlePersonList(code);
				if(sysList.size()>0){
					for(SysServiceManInfo sm:sysList){
						if(abList.size()>0){
							for(SysServiceManInfo si:abList){
								if(si!=null){
									if(sm.getId().equals(si.getId())){
										sm.setPorary(sm.getId());
										count+=1;
										personList+=sm.getId()+",";
									}
								}
							}
						}
					}
				}
				
				mav.addObject("personList",personList);
				mav.addObject("seCount",count);
				mav.addObject("sysInfoList",sysList);
				break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * 根据waterAreaId查询相关联系人
	 * @return
	 */
	public List<SysServiceManInfo> sysInfoList(HttpServletRequest request){
		List<SysServiceManInfo> sysInfoList = new ArrayList<>();
		Map<String,Object> pageInfo = new HashMap<>();
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		try {
			List<SysWaterArea> sysWaterAreaLists = sysWaterAreaService.getCurrAndChildWaterArea(login_user.getWaterAreaId());
			List<String> waterAreaIdsLists = new ArrayList<String>();
			for(SysWaterArea sysWaterArea : sysWaterAreaLists){
				waterAreaIdsLists.add(sysWaterArea.getId());
			}
			waterAreaIdsLists.add(login_user.getWaterAreaId());
			pageInfo.put("waterIdList", waterAreaIdsLists);
			sysInfoList = sysServiceManInfoService.findPersonList(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysInfoList;
	}
	
	/**
	 * 添加或修改异常信息
	 * @return
	 */
	@RequestMapping(value="/addEditWarnType")
	@ResponseBody
	public String addEditWarnType(int isMess,String abnormaltype,String abnormaldetail,String abnormalcode,String personList){
		String message = "";
		try {
			Warnerrortype wy = new Warnerrortype();
			wy.setAbnormaltype(abnormaltype);
			wy.setAbnormaldetail(abnormaldetail);
			wy.setAbnormalcode(Integer.valueOf(abnormalcode));
			wy.setCreatetime(new Date());
			wy.setIsmess(isMess);
			int i = warnerrortypeService.insertSelective(wy);
			if(i==1){
				String [] strUy = personList.split(",");
				for(int u=0;u<strUy.length;u++) {
					if(!StringUtils.isNullOrEmpty(strUy[u])){
						Warnmiddleperson wp = new Warnmiddleperson();
						wp.setAbnormalcode(Integer.valueOf(abnormalcode));
						wp.setAbnormalperson(Integer.valueOf(strUy[u]));
						warnmiddlepersonService.insertSelective(wp);
					}
				}
				message="添加成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message="添加失败";
		}
		return message;
	}
	
	@RequestMapping(value="/EditWarnType")
	@ResponseBody
	public String addEditWarnType(int isMess,String id,String abnormaltype,String abnormaldetail,String abnormalcode,String personList){
		String message = "";
		try {
			List<Warnerrortype> warnList = warnerrortypeService.selectByPrimaryKey(Integer.parseInt(id),null,null);
			Warnerrortype wy = warnList.get(0);
			wy.setAbnormaltype(abnormaltype);
			wy.setAbnormaldetail(abnormaldetail);
			wy.setAbnormalcode(Integer.valueOf(abnormalcode));
			wy.setIsmess(isMess);
			wy.setEdittime(new Date());
			int i = warnerrortypeService.updateByPrimaryKeySelective(wy);
			if(i==1){
				int y = warnmiddlepersonService.deleteByPrimaryKey(Integer.valueOf(abnormalcode));
				String [] strUy = personList.split(",");
				for(int u=0;u<strUy.length;u++) {
					if(!StringUtils.isNullOrEmpty(strUy[u])){
						Warnmiddleperson wp = new Warnmiddleperson();
						wp.setAbnormalcode(Integer.valueOf(abnormalcode));
						wp.setAbnormalperson(Integer.valueOf(strUy[u]));
						warnmiddlepersonService.insertSelective(wp);
					}
				}
				message="修改成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message="修改失败";
		}
		return message;
	}
	
	@RequestMapping(value="/delWarnType")
	@ResponseBody
	public String delWarnType(int id,int code){
		String message = "";
		try {
			if(!StringUtils.isNullOrEmpty(""+id)){
				int i = warnerrortypeService.deleteByPrimaryKey(id);
				if(i==1){
					int y = warnmiddlepersonService.deleteByPrimaryKey(code);
					message = "成功";
				}
			}
			
		} catch (Exception e) {
			message = "失败";
		}
		return message;
	}
	
	/**
	 * @Title: baseWaterPrice
	 * @Description: 基本水费费率参数列表
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/baseWaterPrice")
	public ModelAndView baseWaterPrice(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(baseWaterPriceJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		
		// 获取记录条数
		int count = paramBaseWaterPriceService.getBaseWaterCount(params);
		String s_start = request.getParameter("pageNo");
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		
		// 获取信息集合
		List<ParamBaseWaterPrice> baseWaterPriceList = paramBaseWaterPriceService.getBaseWaterList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("baseWaterPriceList", baseWaterPriceList);
		
		return mav;
	}
	
	/**
	 * @Title: addBaseWaterPricePage
	 * @Description: 新增基本水费费率页面
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 * @throws
	 */
	@RequestMapping(value="addBaseWaterPricePage")
	public ModelAndView addBaseWaterPricePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(addBaseWaterPriceJsp);
		// 获取当前年份 放入参数map
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		mav.addObject("currentYear", currentYear);
		return mav;
	}
	
	/**
	 * @Title: saveBaseWaterPrice
	 * @Description: 基本水费费率增加保存
	 * @param: @param request
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="saveBaseWaterPrice")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String saveBaseWaterPrice(HttpServletRequest request, ParamBaseWaterPrice paramBaseWaterPrice){
		String id = CommonUtil.getRandomUUID();
		// 信息提示实体
		AjaxJson ajaxJson = new AjaxJson();
		if(paramBaseWaterPrice != null && id != null){
			try {
				paramBaseWaterPrice.setId(id);
				String msg = paramBaseWaterPriceService.insertBaseWater(paramBaseWaterPrice);
				ajaxJson.setMsg(msg);
				ajaxJson.setSuccess(true);
				ajaxJson.setObj("添加基本水费费率成功！");
			} catch (Exception e) {
				e.printStackTrace();
				ajaxJson.setSuccess(false);
				ajaxJson.setObj("添加基本水费费率信息，抛出异常！具体异常信息："+ e.getMessage());
				ajaxJson.setMsg("添加基本水费费率失败，请联系管理人员");
			}
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * @Title: deleteBaseWater
	 * @Description: 根据ID删除基本水费费率信息
	 * @param: @param request
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="deleteBaseWater")
	@ResponseBody
	public String deleteBaseWater(HttpServletRequest request) {
		// 删除的id数组
		String ids = request.getParameter("ids");
		AjaxJson ajaxJson = new AjaxJson();
		try{
			ajaxJson = paramBaseWaterPriceService.delBaseWater(ids);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("删除基本水费费率抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("删除基本水费费率失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * @Title: editBaseWaterPricePage
	 * @Description: 修改基本费率信息页面
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="editBaseWaterPricePage")
	public ModelAndView editBaseWaterPricePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(editBaseWaterPriceJsp);
		String id = request.getParameter("id");
		// 根据id查询基本费率信息
		ParamBaseWaterPrice baseWaterPrice = paramBaseWaterPriceService.findBaseWaterPriceById(id);
		mav.addObject("baseWaterPrice", baseWaterPrice);
		return mav;
	}
	
	/**
	 * @Title: editBaseWaterPrice
	 * @Description: 保存修改基本费率信息
	 * @param: @param request
	 * @param: @param paramBaseWaterPrice
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="editBaseWaterPrice")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String editBaseWaterPrice(HttpServletRequest request, ParamBaseWaterPrice paramBaseWaterPrice) {
		AjaxJson ajaxJson = new AjaxJson();
		try{
			String msg = paramBaseWaterPriceService.editBaseWaterPrice(paramBaseWaterPrice);
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj("修改基本费率信息成功");
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("修改基本费率信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("修改基本费率信息失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * TODO: 进入水资源收费参数页面
	 * @param request
	 * 2016年12月5日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="/waterSourcePrice", method = RequestMethod.GET)
	public ModelAndView waterSource(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(waterSourcePriceJsp);
		String distType = request.getParameter("distType");
		Map<String, Object> params = new HashMap<String, Object>();
		if(null!=distType)
			params.put("distType", distType);
		else
			params.put("distType", 1);
		// 获取记录条数
		int count = paramWaterSourcePriceService.getCount(params);
		String s_start = request.getParameter("pageNo");
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取信息集合
		List<ParamWaterSourcePrice> paramWaterSourcePriceList = paramWaterSourcePriceService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("paramWaterSourcePriceList", paramWaterSourcePriceList);
		mav.addObject("distType", distType);
		return mav;
	}
	
	/**
	 * TODO: 跳入新增界面
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="addWaterSourcePricePage")
	public ModelAndView addWaterSourcePricePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(addWaterSourcePriceJsp);
		// 获取当前年份 放入参数map
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		mav.addObject("currentYear", currentYear);
		return mav;
	}
	
	/**
	 * TODO: 保存水资源费新增内容
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="addWaterSourcePrice")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String addWaterSourcePrice(ParamWaterSourcePrice paramWaterSourcePrice, HttpServletRequest request) {
		String id = CommonUtil.getRandomUUID();
		paramWaterSourcePrice.setId(id);
		paramWaterSourcePrice.setDistType(1);
		logContent = "";
		AjaxJson ajaxJson = new AjaxJson();
		try{
			// 新增水资源费信息
			String msg = paramWaterSourcePriceService.add(paramWaterSourcePrice);
			logContent = "添加水资源费数据成功";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("添加水资源费信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("添加水资源费信息失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * TODO: 根据Id删除水资源费信息
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="delWaterSourcePrice")
	@ResponseBody
	public String delWaterSourcePrice(HttpServletRequest request) {
		logContent = "";
		String ids = request.getParameter("ids");
		AjaxJson ajaxJson = new AjaxJson();
		try{
			ajaxJson = paramWaterSourcePriceService.delete(ids);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("删除水资源费信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("删除水资源费信息失败，请联系管理人员");
		}
		logContent = ajaxJson.getObj().toString();
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * @Title: editWaterSourcePricePage
	 * @Description: 修改水资源费信息页面
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="editWaterSourcePricePage")
	public ModelAndView editWaterSourcePricePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(editWaterSourcePriceJsp);
		String id = request.getParameter("id");
		// 根据id查询水资源费信息页面
		ParamWaterSourcePrice paramWaterSourcePrice = paramWaterSourcePriceService.findWaterSourcePriceById(id);
		mav.addObject("paramWaterSourcePrice", paramWaterSourcePrice);
		return mav;
	}
	
	/**
	 * @Title: editWaterSourcePrice
	 * @Description: 保存修改水资源费信息
	 * @param: @param request
	 * @param: @param paramWaterSourcePrice
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="editWaterSourcePrice")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String editWaterSourcePrice(HttpServletRequest request, ParamWaterSourcePrice paramWaterSourcePrice) {
		AjaxJson ajaxJson = new AjaxJson();
		try{
			String msg = paramWaterSourcePriceService.editWaterSourcePrice(paramWaterSourcePrice);
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj("修改水资源费信息成功");
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("修改水资源费信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("修改水资源费信息失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * TODO: 进入计量水费参数页面
	 * @param request
	 * 2016年12月5日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="/measureTypePrice", method = RequestMethod.GET)
	public ModelAndView measureTypePrice(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(measureTypePriceJsp);
		String distType = request.getParameter("distType");
		Map<String, Object> params = new HashMap<String, Object>();
		if(null!=distType)
			params.put("distType", distType);
		else
			params.put("distType", 1);
		// 获取记录条数
		int count = paramMeasureTypePriceService.getCount(params);
		String s_start = request.getParameter("pageNo");
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 获取信息集合
		List<ParamMeasureTypePrice> paramMeasureTypePriceList = paramMeasureTypePriceService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("paramMeasureTypePriceList", paramMeasureTypePriceList);
		mav.addObject("distType", distType);
		return mav;
	}
	
	/**
	 * TODO: 跳入新增界面
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="addMeasureTypePricePage")
	public ModelAndView addMeasureTypePricePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(addMeasureTypePriceJsp);
		// 获取当前年份 放入参数map
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		mav.addObject("currentYear", currentYear);
		return mav;
	}
	
	/**
	 * TODO: 保存计量水费新增内容
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="addMeasureTypePrice")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String addMeasureTypePrice(ParamMeasureTypePrice paramMeasureTypePrice, HttpServletRequest request) {
		String id = CommonUtil.getRandomUUID();
		paramMeasureTypePrice.setId(id);
		paramMeasureTypePrice.setDistType(1);
		logContent = "";
		AjaxJson ajaxJson = new AjaxJson();
		try{
			// 新增计量水费信息
			String msg = paramMeasureTypePriceService.add(paramMeasureTypePrice);
			logContent = "添加【功率】的数据";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("添加计量水费信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("添加计量水费信息失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * TODO: 根据Id删除计量水费信息
	 * @param request
	 * 2016年11月26日
	 * Administrator: xiaogaoxiang
	 */
	@RequestMapping(value="delMeasureTypePrice")
	@ResponseBody
	public String delMeasureTypePrice(HttpServletRequest request) {
		logContent = "";
		String ids = request.getParameter("ids");
		AjaxJson ajaxJson = new AjaxJson();
		try{
			ajaxJson = paramMeasureTypePriceService.delete(ids);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("删除计量水费信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("删除计量水费信息失败，请联系管理人员");
		}
		logContent = ajaxJson.getObj().toString();
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * @Title: editMeasureTypePricePage
	 * @Description: 修改计量水费费率信息页面
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="editMeasureTypePricePage")
	public ModelAndView editMeasureTypePricePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(editMeasureTypePriceJsp);
		String id = request.getParameter("id");
		// 根据id查询计量水费费率信息
		ParamMeasureTypePrice measureTypePrice = paramMeasureTypePriceService.findMeasureTypePriceById(id);
		mav.addObject("measureTypePrice", measureTypePrice);
		return mav;
	}
	
	/**
	 * @Title: editMeasureTypePrice
	 * @Description: 保存修改计量水费费率信息
	 * @param: @param request
	 * @param: @param paramMeasureTypePrice
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="editMeasureTypePrice")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String editMeasureTypePrice(HttpServletRequest request, ParamMeasureTypePrice paramMeasureTypePrice) {
		AjaxJson ajaxJson = new AjaxJson();
		try{
			String msg = paramMeasureTypePriceService.editMeasureTypePrice(paramMeasureTypePrice);
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj("修改计量水费费率信息成功");
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("修改计量水费费率信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("修改计量水费费率信息失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * @Title: baseWaterPrice
	 * @Description: 末级渠系费率参数列表
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/maintainPrice")
	public ModelAndView maintainPrice(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(maintainPriceJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		
		// 获取记录条数
		int count = paramMaintainPriceService.getMaintainPriceCount(params);
		String s_start = request.getParameter("pageNo");
		PagingBean pagingBean = PageUtil.page(s_start, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		
		// 获取信息集合
		List<ParamMaintainPrice> maintainPriceList = paramMaintainPriceService.getMaintainPriceList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("maintainPriceList", maintainPriceList);
		
		return mav;
	}
	
	/**
	 * @Title: addMaintainPricePage
	 * @Description: 新增末级渠系费率页面
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="addMaintainPricePage")
	public ModelAndView addMaintainPricePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(addMaintainPriceJsp);
		// 获取当前年份 放入参数map
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		mav.addObject("currentYear", currentYear);
		return mav;
	}
	
	/**
	 * @Title: saveMaintainPrice
	 * @Description: 末级渠系费率增加保存
	 * @param: @param request
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="saveMaintainPrice")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String saveMaintainPrice(HttpServletRequest request, ParamMaintainPrice paramMaintainPrice){
		String id = CommonUtil.getRandomUUID();
		// 信息提示实体
		AjaxJson ajaxJson = new AjaxJson();
		if(paramMaintainPrice != null && id != null){
			try {
				paramMaintainPrice.setId(id);
				String msg = paramMaintainPriceService.insertMaintainPrice(paramMaintainPrice);
				ajaxJson.setMsg(msg);
				ajaxJson.setSuccess(true);
				ajaxJson.setObj("添加末级渠系费率成功！");
			} catch (Exception e) {
				e.printStackTrace();
				ajaxJson.setSuccess(false);
				ajaxJson.setObj("添加末级渠系费率信息，抛出异常！具体异常信息："+ e.getMessage());
				ajaxJson.setMsg("添加末级渠系费率失败，请联系管理人员");
			}
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * @Title: deleteMaintainPrice
	 * @Description: 根据ID删除末级渠系费率信息
	 * @param: @param request
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="deleteMaintainPrice")
	@ResponseBody
	public String deleteMaintainPrice(HttpServletRequest request) {
		// 删除的id数组
		String ids = request.getParameter("ids");
		AjaxJson ajaxJson = new AjaxJson();
		try{
			ajaxJson = paramMaintainPriceService.delMaintainPrice(ids);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("删除末级渠系费率抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("删除末级渠系费率失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * @Title: editMaintainPricePage
	 * @Description: 修改末级渠系费率信息页面
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="editMaintainPricePage")
	public ModelAndView editMaintainPricePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(editMaintainPriceJsp);
		String id = request.getParameter("id");
		// 根据id查询末级渠系费率信息
		ParamMaintainPrice maintainPrice = paramMaintainPriceService.findMaintainPriceById(id);
		mav.addObject("maintainPrice", maintainPrice);
		return mav;
	}
	
	/**
	 * @Title: editMaintainPrice
	 * @Description: 保存修改末级渠系费率信息
	 * @param: @param request
	 * @param: @param paramMaintainPrice
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="editMaintainPrice")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public String editMaintainPrice(HttpServletRequest request, ParamMaintainPrice paramMaintainPrice) {
		AjaxJson ajaxJson = new AjaxJson();
		try{
			String msg = paramMaintainPriceService.editMaintainPrice(paramMaintainPrice);
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj("修改末级渠系费率信息成功");
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("修改末级渠系费率信息，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("修改末级渠系费率信息失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}

	/**
	 * 返回用水指标的页面
	 * @return
	 */
	@RequestMapping(value="waterIndicator")
	public String waterIndicatorIndex(){return waterIndicator;}
	/**
	 * 返回新增修改的页面
	 * @param sign
	 * @param
	 * @return
	 */
	@RequestMapping(value="waterIndicatorData")
	@ResponseBody
	public String waterIndicatorData(int sign,HttpServletRequest request) {
		List<Waterindex> list = new ArrayList<>();
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		String waterAreaId = "";
		if(login_user!=null){
			waterAreaId = login_user.getWaterAreaId();
		}
		try {
			switch (sign) {
				case 0://新增
					list = waterindexMapper.selectWaterAll(waterAreaId,"left");
					break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return JSON.toJSONString(list);
	}

		/**
		 * 新增修改的数据处理方法
		 */
		@RequestMapping(value="indicatorData")
		@ResponseBody
		public String indicatorData(int sign, String addForm, HttpServletRequest request){
			String y = "";
			List<Waterindex> list = new ArrayList<>();
			try {
				switch (sign){
					case 0://新增
						if(!StringUtils.isNullOrEmpty(addForm)){
							List<Waterindex> lists = (List<Waterindex>) JSONArray.parseArray(addForm,Waterindex.class);
							if(lists.size()>0){
								for(Waterindex w:lists){
									Waterindex waterindex = waterindexMapper.selectByPrimaryKey(w.getWaterareaid());
										int u = 0;
										if(waterindex==null){
											u = waterindexMapper.insertSelective(w);
										}else{
											w.setId(waterindex.getId());
											u = waterindexMapper.updateByPrimaryKeySelective(w);
										}
										y = ""+u;
								}
							}
						}
						break;
					case 2://页面查询数据
						SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
						list = waterindexMapper.selectWaterAll(login_user.getWaterAreaId(),"right");
						y = JSONObject.toJSONString(list);
						break;
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			return y;
	}

}
