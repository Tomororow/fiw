package com.fourfaith.baseManager.controller;

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
import com.fourfaith.siteManage.service.SysAreaService;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.waterRightManage.model.BaseDistWaterPlan;
import com.fourfaith.waterRightManage.service.BaseDistWaterPlanService;

/**
 * @ClassName: WaterRightManageController
 * @Description: 水权管理Controller
 * @Author: zhaojx
 */
@Controller
@RequestMapping(value ="/waterRightManage")
public class WaterRightManageController {
	
	protected static final String indexJsp = "/page/waterRightManage/index";
	protected static final String farmingJsp = "/page/waterRightManage/farming/farmingIndex";
	protected static final String addFarmingJsp = "/page/waterRightManage/farming/addFarming";
	protected static final String industryJsp = "/page/waterRightManage/industry/industryIndex";
	protected static final String addIndustryJsp = "/page/waterRightManage/industry/addIndustry";
	protected static final String lifeJsp = "/page/waterRightManage/life/lifeIndex";
	protected static final String addLifeJsp = "/page/waterRightManage/life/addLife";
	protected static final String addVirestPage = "/page/waterRightManage/virest/addVirestPage";
	protected static final String VirestPage = "/page/waterRightManage/virest/virestIndex";
	
	
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private BaseDistWaterPlanService baseDistWaterPlanService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private SysAreaService sysAreaService;
	
	String logContent = "";
	
	/**
	 * 水权管理首页
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,String menuId){
		ModelAndView mav = new ModelAndView(indexJsp);
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		List<SysMenu> sysMenuList = new ArrayList<SysMenu>();
		//根据权限动态获取菜单列表
		sysMenuList = this.sysMenuService.getListByRoleidAndMenuid(menuId, login_user.getSysrole().getId());
		mav.addObject("sysMenuList", sysMenuList);
		return mav;
	}
	
	/**
	 * 新增农业用水
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addFarmingPage")
    public ModelAndView addFarmingPage(BaseDistWaterPlan plan,HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(addFarmingJsp);
    	SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
    	// 获取登陆人员所在的区域做级联下拉框操作
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getLoginerWaterAreaList(login_user);
		mav.addObject("sysWaterAreaList",sysWaterAreaList);
		request.setAttribute("plan", plan);
    	return mav;
    }
	
	/**
	 * @Title: farming
	 * @Description: 农业用水配水计划-列表显示
	 * @param: @param request
	 * @return: ModelAndView
	 * @author: zhaojinxin 
	 */
	@RequestMapping(value="farming")
	public ModelAndView farming(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(farmingJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		
		/**
		 * 获取到所属水管区域，来做权限管理
		 * 	1、只有水务局/水管处/水管所/水资办有权限来配水
		 * 	2、只有水管所这一级有权限来追加水量
		 * 	3、其他水管级别无权限操作水权管理
		 */
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		SysWaterArea sysWaterArea = sysWaterAreaService.findById(login_user.getWaterAreaId());
		if(null==sysWaterArea) {
			sysWaterArea = sysWaterAreaService.findFirstWaterAreaLevelById(login_user.getWaterAreaId());
		}
		
		// 农业配水信息
		params.put("distType", 1);
		params.put("waterAreaId", login_user.getWaterAreaId());
		//
//		 List<BaseDistWaterPlan> baseDistWaterPlanList1 = baseDistWaterPlanService.getList(params);
		// 分页
		String pageNo = request.getParameter("pageNo");
		int count = baseDistWaterPlanService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		
		// 获取农业配水计划列表信息
		List<BaseDistWaterPlan> baseDistWaterPlanList = baseDistWaterPlanService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("farmingList", baseDistWaterPlanList);
		mav.addObject("sysWaterArea", sysWaterArea);
		return mav;
	}
	
	/**
	 * 新增工业用水
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addIndustryPage")
    public ModelAndView addIndustryPage(BaseDistWaterPlan plan,HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(addIndustryJsp);
    	SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
    	// 获取登陆人员所在的区域做级联下拉框操作
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getLoginerWaterAreaList(login_user);
		mav.addObject("sysWaterAreaList",sysWaterAreaList);
		request.setAttribute("plan", plan);
    	return mav;
    }
	
	/**
	 * @Title: industry
	 * @Description: 工业用水配水计划-列表显示
	 * @param request
	 * @return: ModelAndView
	 * @Author: xiaogaoxiang
	 */
	@RequestMapping(value="industry")
	public ModelAndView industry(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(industryJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		
		/**
		 * 获取到所属水管区域，来做权限管理
		 * 	1、只有水务局/水管处/水管所/水资办有权限来配水
		 * 	2、只有水管所这一级有权限来追加水量
		 * 	3、其他水管级别无权限操作水权管理
		 */
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		SysWaterArea sysWaterArea = sysWaterAreaService.findById(login_user.getWaterAreaId());
		if(null==sysWaterArea) {
			sysWaterArea = sysWaterAreaService.findFirstWaterAreaLevelById(login_user.getWaterAreaId());
		}
		
		// 工业配水信息
		params.put("distType", 2);
		params.put("waterAreaId", login_user.getWaterAreaId());
		
		// 分页
		String pageNo = request.getParameter("pageNo");
		int count = baseDistWaterPlanService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		
		// 获取工业配水列表	
		List<BaseDistWaterPlan> baseDistWaterPlanList = baseDistWaterPlanService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("industryList",baseDistWaterPlanList);
		mav.addObject("sysWaterArea",sysWaterArea);
		return mav;
	}
	
	/**
	 * 新增生活用水
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addLifePage")
    public ModelAndView addLifePage(BaseDistWaterPlan plan,HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(addLifeJsp);
    	SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
    	// 获取登陆人员所在的区域做级联下拉框操作
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getLoginerWaterAreaList(login_user);
		mav.addObject("sysWaterAreaList",sysWaterAreaList);
		request.setAttribute("plan", plan);
    	return mav;
    }
	
	/**
	 * @Title: life
	 * @Description: 生活用水配水计划-列表显示
	 * @param request
	 * @return: ModelAndView
	 * @Author: xioagaoxiang
	 */
	@RequestMapping(value="life")
	public ModelAndView life(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(lifeJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		
		/**
		 * 获取到所属水管区域，来做权限管理
		 * 	1、只有水务局/水管处/水管所/水资办有权限来配水
		 * 	2、只有水管所这一级有权限来追加水量
		 * 	3、其他水管级别无权限操作水权管理
		 */
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		SysWaterArea sysWaterArea = sysWaterAreaService.findById(login_user.getWaterAreaId());
		if(null==sysWaterArea) {
			sysWaterArea = sysWaterAreaService.findFirstWaterAreaLevelById(login_user.getWaterAreaId());
		}
		
		// 生活配水信息
		params.put("distType", 3);
		params.put("waterAreaId", login_user.getWaterAreaId());
		
		// 分页
		String pageNo = request.getParameter("pageNo");
		int count = baseDistWaterPlanService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		
		// 获取生活配水列表信息
		List<BaseDistWaterPlan> baseDistWaterPlanList = baseDistWaterPlanService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("industryList",baseDistWaterPlanList);
		mav.addObject("sysWaterArea",sysWaterArea);
		return mav;
	}
	/**
	 * 新增绿化用水 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addVirestPage")
    public ModelAndView addVirestPage(BaseDistWaterPlan plan,HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(addVirestPage);
    	SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
    	// 获取登陆人员所在的区域做级联下拉框操作
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getLoginerWaterAreaList(login_user);
		mav.addObject("sysWaterAreaList",sysWaterAreaList);
		request.setAttribute("plan", plan);
    	return mav;
    }
	/**
	 * @Title: life
	 * @Description: 绿化用水配水计划-列表显示
	 * @param request
	 * @return: ModelAndView
	 * @Author: xioagaoxiang
	 */
	@RequestMapping(value="/virestPage")
	public ModelAndView virestPage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(VirestPage);
		Map<String, Object> params = new HashMap<String, Object>();
		
		/**
		 * 获取到所属水管区域，来做权限管理
		 * 	1、只有水务局/水管处/水管所/水资办有权限来配水
		 * 	2、只有水管所这一级有权限来追加水量
		 * 	3、其他水管级别无权限操作水权管理
		 */
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		SysWaterArea sysWaterArea = sysWaterAreaService.findById(login_user.getWaterAreaId());
		if(null==sysWaterArea) {
			sysWaterArea = sysWaterAreaService.findFirstWaterAreaLevelById(login_user.getWaterAreaId());
		}
		
		// 绿化配水信息
		params.put("distType", 4);
		params.put("waterAreaId", login_user.getWaterAreaId());
		
		// 分页
		String pageNo = request.getParameter("pageNo");
		int count = baseDistWaterPlanService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		
		// 获取绿化配水列表信息
		List<BaseDistWaterPlan> baseDistWaterPlanList = baseDistWaterPlanService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("virestList",baseDistWaterPlanList);
		mav.addObject("sysWaterArea",sysWaterArea);
		return mav;
	}
	
	/**
	 * 保存新增公用方法
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/addFarming", method = RequestMethod.POST)
	@ResponseBody
    public String addFarming(HttpServletRequest request, BaseDistWaterPlan plan) throws Exception{
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			plan.setId(CommonUtil.getRandomUUID());
			plan.setOperator(login_user.getId());
			plan.setCreateTime(new Date());
			plan.setEditTime(new Date());
			String msg = this.baseDistWaterPlanService.add(plan);
			logContent = "添加【"+plan.getDistMode()+"】的配水数据";
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
	 * @Title: delBaseDistWaterPlan
	 * @Description: 删除配水计划
	 * @param: @param request
	 * @param: @throws Exception
	 * @return: String
	 */
    @RequestMapping(value = "/delPlan")
    @ResponseBody
    public String delBaseDistWaterPlan(HttpServletRequest request) throws Exception{
    	String items = request.getParameter("items");
    	logContent = "";
    	AjaxJson ajaxJson = baseDistWaterPlanService.deleteDis(items);
    	logContent = ajaxJson.getObj().toString();
    	return JSONObject.toJSONString(ajaxJson);
    }
    
    /**
     *  TODO: 配水年份选择完后，筛选系统数据库
	 * 	1、如果当前配水方式，年份不存在（第一次添加），则轮次默认选择1，不能为其他轮次
	 * 	2、如果当前配水方式，年份存在（已经配过一次或多次），则轮次将读取数据库轮次+1
     * @param plan
     * @param request
     * 2016年10月26日
     * Administrator: xiaogaoxiang
     */
    @RequestMapping(value = "/getDistRoundInfo")
    @ResponseBody
    public String getDistRoundInfo(BaseDistWaterPlan plan, String dist, HttpServletRequest request) {
    	Map<String, Object> hm = new HashMap<String, Object>();
    	SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
    	hm = baseDistWaterPlanService.getDistRoundInfo(plan,login_user.getUserName());
		return JSONObject.toJSONString(hm);
    }
    
    /**
     * TODO: 根据配水Id获取配水机井设备信息
     * @param plan
     * @param request
     * 2016年10月27日
     * Administrator: xiaogaoxiang
     */
    @RequestMapping(value = "/showDistWaterDeviceInfo")
    @ResponseBody
    public String showDistWaterDeviceInfo(BaseDistWaterPlan plan, HttpServletRequest request) {
    	String BaseDistWaterPlanDevice = null;
    	try {
			BaseDistWaterPlanDevice = baseDistWaterPlanService.showDistWaterDeviceInfo(plan);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return BaseDistWaterPlanDevice;
    }
    
}