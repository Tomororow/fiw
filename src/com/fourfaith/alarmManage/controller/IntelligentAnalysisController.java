package com.fourfaith.alarmManage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.alarmManage.model.IntelligentAnalysis;
import com.fourfaith.alarmManage.model.IntelligentDeal;
import com.fourfaith.alarmManage.service.IntelligentAnalysisService;
import com.fourfaith.alarmManage.service.IntelligentDealService;
import com.fourfaith.basicInformation.model.SysServiceManInfo;
import com.fourfaith.basicInformation.service.SysServiceManInfoService;
import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.siteManage.service.SysAreaService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.AreaRecursionUtil;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.Constant;
import com.fourfaith.utils.Huaxin_webjzjs;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.StringUtils;
import com.fourfaith.utils.WaterAreaRecursionUtil;
import com.google.gson.Gson;

/**
 * @ClassName: IntelligentAnalysisController
 * @Description: 异常机井智能预警分析
 * @Author: zhaojinxin
 * @Date: 2019年2月22日
 */
@Controller
@RequestMapping(value="/intelligentAnalysis")
public class IntelligentAnalysisController {

	protected static final String intelligentAnalysisJsp = "/page/history/intelligentAnalysis/intelligentAnalysis";
	protected static final String intelligentAnalysisListJsp = "/page/history/intelligentAnalysis/intelligentAnalysisList";
	protected static final String intelligentAnalysisDealJsp = "/page/history/intelligentAnalysis/intelligentAnalysisDeal";
	protected static final String repairFeedBackJsp = "/page/history/intelligentAnalysis/repairFeedBack";

	//以下参数为添加日志所需
    public String logContent = "";
    
	@Autowired
	private IntelligentAnalysisService intelligentAnalysisService;
	@Autowired
	private SysAreaService sysAreaService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private IntelligentDealService intelligentDealService;
	@Autowired
	private SysServiceManInfoService sysServiceManInfoService;
	
	SimpleDateFormat simFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * @Title: index
	 * @Description: 异常机井智能预警头部
	 * @param: @param request
	 * @param: @param menuId
	 * @param: @param stcd
	 * @param: @param tag
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,String menuId, String stcd,String tag) {
		ModelAndView mav = new ModelAndView(intelligentAnalysisJsp);
		return mav;
	}
	
	/**
	 * @Title: intelligentAnalysisList
	 * @Description: 异常机井智能预警列表页 行政区域方式
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="/intelligentAnalysisList", method = RequestMethod.POST)
	public ModelAndView intelligentAnalysisList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(intelligentAnalysisListJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		// 获取设备查询条件
		String deviceCode = request.getParameter("deviceCode");
		String deviceName = request.getParameter("deviceName");
		
		// 获取当前选中的行政区域信息
		String areaId = request.getParameter("id");
		List<SysArea> sysAllAreaList = sysAreaService.getAllArea();
		List<SysArea> sysAreaList = AreaRecursionUtil.getCurrAndChildAreaList(sysAllAreaList, areaId);
		List<String> areaIdsList = new ArrayList<String>();
		for(SysArea sa : sysAreaList) {
			areaIdsList.add(sa.getId());
		}
		
		// 获取登陆用户信息
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		// 获取登录用户所属的水管区域ID
		String waterAreaId = login_user.getWaterAreaId();
		// 递归查询该水管区域下的所有子区域及当前区域
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
		List<String> waterAreaIdsList = new ArrayList<String>();
		for(SysWaterArea swa : sysWaterAreaList) {
			waterAreaIdsList.add(swa.getId());
		}
		
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
		
		params.put("areaIdsList", areaIdsList);
		params.put("waterAreaIdsList", waterAreaIdsList);
		// 分页
		String pageNo = request.getParameter("pageNo");
		// 查询总记录条数
		int count = intelligentAnalysisService.getCount(params);
		// 设置分页对象
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		// 存储开始和结束分页变量
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 查询异常机井信息集合
		List<IntelligentDeal> intelligentAnalysisList = intelligentAnalysisService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("intelligentAnalysisList", intelligentAnalysisList);
		mav.addObject("deviceCode", deviceCode);
		mav.addObject("deviceName", deviceName);
		return mav;
	}
	
	/**
	 * @Title: intelligentAnalysisListByArea
	 * @Description: 异常机井智能分析列表页 水管区域方式
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/intelligentAnalysisListByArea", method = RequestMethod.POST)
	public ModelAndView intelligentAnalysisListByArea(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(intelligentAnalysisListJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		// 获取设备查询条件
		String deviceCode = request.getParameter("deviceCode");
		String deviceName = request.getParameter("deviceName");
		
		// 获取当前选中的行政区域信息
		String waterAreaId = request.getParameter("id");
		
		// 获取全部水管区域ID并递归查找该ID下的子行政区域
		List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
		List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, waterAreaId);
		List<String> waterAreaIdsList = new ArrayList<String>();
		for(SysWaterArea swa : sysWaterAreaList) {
			waterAreaIdsList.add(swa.getId());
		}
		params.put("waterAreaIdsList", waterAreaIdsList);
		
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
		
		// 分页
		String pageNo = request.getParameter("pageNo");
		// 查询总记录条数
		int count = intelligentAnalysisService.getCount(params);
		// 设置分页对象
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE + "", count, PagingBean.DEFAULT_PAGE_SIZE);
		// 存储开始和结束分页变量
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		// 查询异常机井信息集合
		List<IntelligentDeal> intelligentAnalysisList = intelligentAnalysisService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("intelligentAnalysisList", intelligentAnalysisList);
		mav.addObject("deviceCode", deviceCode);
		mav.addObject("deviceName", deviceName);
		return mav;
	}
	
	/**
	 * @Title: dealExceptionPage
	 * @Description: 跳转异常处理页面
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping("dealExceptionPage")
	private ModelAndView dealExceptionPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(intelligentAnalysisDealJsp);
		String id = request.getParameter("id");
		// 获取维修技术员 填充下拉列表
		List<SysServiceManInfo> serviceManList = sysServiceManInfoService.getAssignInfo();
		// 根据id查询异常机井信息
		IntelligentAnalysis intelligentAnalysis = intelligentAnalysisService.selectById(id);
		mav.addObject("nowDate", simFormat.format(new Date()));
		mav.addObject("intelligentAnalysis", intelligentAnalysis);
		mav.addObject("serviceManList", serviceManList);
		return mav;
	}
	
	/**
	 * @Title: getServiceManPhone
	 * @Description: 根据选择技术员获取电话
	 * @param: @param id
	 * @param: @param request
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value = "/getServiceManPhone")
	@ResponseBody
	public String getServiceManPhone(String id, HttpServletRequest request) {
		String phone = null;
		try {
			phone = new Gson().toJson(sysServiceManInfoService.selectByPrimaryKey(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return phone;
	}
	
	/**
	 * @Title: dealExceptionInfo
	 * @Description: 异常信息处理
	 * @param: @param intelligentDeal
	 * @param: @param request
	 * @return: String
	 * @throws ParseException 
	 */
	@RequestMapping(value="dealExceptionInfo", method = RequestMethod.POST)
	@ResponseBody
	private String dealExceptionInfo(HttpServletRequest request) throws ParseException {
		AjaxJson ajaxJson = new AjaxJson();
		IntelligentDeal intelligentDeal = new IntelligentDeal();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		intelligentDeal.setIntelligentAnalysisId(request.getParameter("intelligentAnalysisId"));
		intelligentDeal.setDealType(Integer.parseInt(request.getParameter("dealType")));
		intelligentDeal.setServiceManId(request.getParameter("serviceManId"));
		intelligentDeal.setPhone(request.getParameter("phone"));
		intelligentDeal.setFixTime(sdFormat.parse(request.getParameter("fixTime")));
		intelligentDeal.setHandleTime(Double.valueOf(request.getParameter("handleTime")));
		intelligentDeal.setDealExceptionRemark(request.getParameter("dealExceptionRemark"));
		intelligentDeal.setDealResult(2);
		
		try {
			String msg = intelligentDealService.update(intelligentDeal);
			if(msg.equals("处理失败")){
				ajaxJson.setSuccess(false);
				logContent = "添加预警信息处理结果失败！";
			}else{
				ajaxJson.setSuccess(true);
				logContent = "添加预警信息处理结果成功！";
				
				IntelligentAnalysis intelligentAnalysis = intelligentAnalysisService.selectById(request.getParameter("intelligentAnalysisId"));
				
				// 调用短信接口并发送短信给技术维修人员
				Huaxin_webjzjs message = new Huaxin_webjzjs();
				
				message.SendMessage("", Constant.ACCOUNT, Constant.ACCOUNT_PWD, "18193686934", "陈学彪你好,"+intelligentAnalysis.getDeviceName()+"出现"+intelligentAnalysis.getIntelligentException()+"故障，请即刻前往维修！" , "").toString();
				message.SendMessage("", Constant.ACCOUNT, Constant.ACCOUNT_PWD, "18298582971", "赵斌武你好,"+intelligentAnalysis.getDeviceName()+"出现"+intelligentAnalysis.getIntelligentException()+"故障，已派遣陈学彪前往维修！" , "").toString();
				
			}
			ajaxJson.setMsg(msg);
			ajaxJson.setObj(logContent);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("添加预警信息结果失败，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("添加预警信息处理结果失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * @Title: repairFeedBackPage
	 * @Description: 机井预警报修反馈处理页面
	 * @param: @param request
	 * @return: ModelAndView
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value = "/repairFeedBackPage")
	public ModelAndView repairFeedBackPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(repairFeedBackJsp);
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		String id = request.getParameter("id");
		
		// 根据ID查询出超时信息表中内容
		IntelligentDeal intelligentDeal = intelligentDealService.selectByIntelligentAnalysisId(id);
		
		// 获取超时时长
		long l = new Date().getTime() - intelligentDeal.getFixTime().getTime();
		long day=l/(24*60*60*1000);
		Long hour = (l/(60*60*1000)-day*24);
		Long overUseTime = (long) (hour - intelligentDeal.getHandleTime());
		if(overUseTime <= 0) {
			overUseTime = 0L;
		}
		mav.addObject("overTimes", Integer.parseInt(hour.toString()));
		mav.addObject("intelligentDeal", intelligentDeal);
		mav.addObject("overUseTime", overUseTime);
		mav.addObject("id", id);
		mav.addObject("login_user", login_user);
		return mav;
	}
	
	/**
	 * @Title: repairFeedBackSave
	 * @Description: 预警反馈信息保存
	 * @param: @param request
	 * @param: @throws ParseException
	 * @return: String
	 * @Author: zhaojinxin
	 */
	@RequestMapping(value="repairFeedBackSave", method = RequestMethod.POST)
	@ResponseBody
	private String repairFeedBackSave(HttpServletRequest request) throws ParseException {
		AjaxJson ajaxJson = new AjaxJson();
		IntelligentDeal intelligentDeal = new IntelligentDeal();
		
		intelligentDeal.setIntelligentAnalysisId(request.getParameter("intelligentAnalysisId"));
		intelligentDeal.setDealResult(Integer.parseInt(request.getParameter("dealResult")));
		intelligentDeal.setDealRemark(request.getParameter("dealRemark"));
		intelligentDeal.setIsOverTime(Integer.parseInt(request.getParameter("isOverTime")));
		intelligentDeal.setDealTime(new Date());
		intelligentDeal.setOverTimeRemark(request.getParameter("overTimeRemark"));
		
		try {
			String msg = intelligentDealService.update(intelligentDeal);
			if(msg.equals("处理失败")){
				ajaxJson.setSuccess(false);
				logContent = "添加预警信息反馈结果失败！";
			}else{
				ajaxJson.setSuccess(true);
				logContent = "添加预警信息反馈结果成功！";
			}
			ajaxJson.setMsg(msg);
			ajaxJson.setObj(logContent);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("添加预警信息反馈结果失败，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("添加预警信息反馈结果失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
}