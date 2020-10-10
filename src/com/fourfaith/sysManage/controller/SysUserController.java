package com.fourfaith.sysManage.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fourfaith.basicInformation.service.SysIrrigationAreaTypeService;
import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.siteManage.service.SysAreaService;
import com.fourfaith.sysManage.model.SysOrganization;
import com.fourfaith.sysManage.model.SysRole;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserRole;
import com.fourfaith.sysManage.model.SysUserWater;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.SysOrganizationService;
import com.fourfaith.sysManage.service.SysRoleService;
import com.fourfaith.sysManage.service.SysUserRoleService;
import com.fourfaith.sysManage.service.SysUserService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.AreaRecursionUtil;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.Constant;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.StringUtils;
import com.fourfaith.utils.WaterAreaRecursionUtil;
import com.mysql.fabric.xmlrpc.base.Params;

/**   
 * @Title: Controller
 * @Description: 系统用户表
 * @author administrator
 * @date 2016-05-23 10:07:52
 * @version V1.0   
 */
@Controller
@RequestMapping(value="/sysUser")
@SuppressWarnings("all")
public class SysUserController {
	
	protected static final String indexJsp="/page/sysmanage/user/userIndex";
	protected static final String addJsp="page/sysmanage/user/add";
	protected static final String editJsp="page/sysmanage/user/edit";
	protected static final String listJsp="page/sysmanage/user/list";
	protected static final String moveJsp="page/sysmanage/user/moveUser";
	protected static final String changePwdJsp="page/sysmanage/user/changePwd";
	protected static final String roleInfoJsp="page/sysmanage/user/roleInfo";
	protected static final String BUY_WATER_JSP="page/sysmanage/user/addWaterPre";
	protected static final String EDIT_BUY_WATER_JSP="page/sysmanage/user/editWaterPre";
	protected static final String APP_AUDIT_JSP="page/sysmanage/user/appAudit";
	protected static final String APP_USER_LIST="page/sysmanage/user/appUserList";
	protected static final String APP_USER_INDEX="page/sysmanage/user/appUserIndex";

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysOrganizationService sysOrganizationService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysAreaService sysAreaService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private SysIrrigationAreaTypeService sysIrrigationAreaTypeService;
	
	//以下参数为添加日志所需
    public String logContent = "";
	
	/**
	 * @Title: getValidateCode
	 * @Description: 生成验证码 并发送
	 * @param: @param phone
	 * @return: String
	 */
	@RequestMapping(value="/getValidateCode", method = RequestMethod.GET)
	public String getValidateCode(HttpServletRequest request, String phone){
		String mobile = request.getParameter("phone");
		String code = sysUserService.getValidateCode(phone);
		
		if (null == code || code.equals("")){
			return "failed";
		}
		return JSONObject.toJSONString(code);
	}
	
	/**
	 * @Title: index
	 * @Description: 用户列表首页
	 * @param request
	 * @return: ModelAndView
	 * @Author: zhaojx
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(indexJsp);
		return mav;
	}

	/**
	 * @Title: list
	 * @Description: 用户信息分页列表
	 * @param request
	 * @return: ModelAndView
	 * @Author: zhaojx
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(listJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		String query_username = request.getParameter("query_username");
		try {
			if(!StringUtils.isNullOrEmpty(query_username)){
				mav.addObject("query_username", query_username);
			}else{
				mav.addObject("query_username", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		if(!StringUtils.isNullOrEmpty(query_username)){
			params.put("userName", query_username);
		}
		
		//分页
		PagingBean pagingBean = null;
		// 获取当前登陆人员的子节点权限信息
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);

		/**
		 * 根据行政、水管区域查询用户的子权限信息
		 * 	1、如果用户只选择了行政区域，没有水管区域，则查询出所属行政区域的Id集合
		 * 	2、如果用户只选择了水管区域，没有行政区域，则查询出所属水管区域的Id集合
		 * 	3、如果用户既选择了行政区域，又有水管区域，则查询出所属两者区域的Id集合，分别做保存
		 */
		List<SysArea> sysAreaList = null;
		List<SysWaterArea> sysWaterAreaList = null;
		if(null==login_user.getAreaId() && null!=login_user.getWaterAreaId()) {
			sysWaterAreaList = sysWaterAreaService.getCurrAndChildWaterArea(login_user.getWaterAreaId());
		} else if(null!=login_user.getAreaId() && null==login_user.getWaterAreaId()) {
			sysAreaList = sysAreaService.getCurrAndChildArea(login_user.getAreaId());
		} else {
			sysAreaList = sysAreaService.getCurrAndChildArea(login_user.getAreaId());
			sysWaterAreaList = sysWaterAreaService.getCurrAndChildWaterArea(login_user.getWaterAreaId());
		}
		params.put("sysAreaList", sysAreaList);
		params.put("sysWaterAreaList", sysWaterAreaList);
		
		String pageNo = request.getParameter("pageNo");
		int count = sysUserService.getCount(params);
		pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());

		List<SysUser> userList = sysUserService.getList(params);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("userList",userList);
		return mav;
	}
	
	/**
	 * 新增用户页面
	 * @param request
	 */
	@RequestMapping(value = "/addPage")
    public ModelAndView addPage(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(addJsp);
    	SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
    	// 获取登陆用户所属行政区域集合
    	List<SysArea> sysAreaList = sysAreaService.getLoginerAreaList(login_user);
    	// 获取登陆用户所属水管区域集合
    	List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getLoginerWaterAreaList(login_user);
    	// 获取所有角色信息
    	List<SysRole> sysRoleList = sysRoleService.getAllRoleInfo();
    	mav.addObject("sysAreaList", sysAreaList);
    	mav.addObject("sysWaterAreaList", sysWaterAreaList);
    	mav.addObject("sysRoleList", sysRoleList);
    	return mav;
    }
	
	/**
	 * 保存新增用户
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/logAddUser", method = RequestMethod.POST)
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
    public String logAddUser(HttpServletRequest request,@ModelAttribute("user") SysUser user) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		try {
			String msg = this.sysUserService.add(user);
			// 保存用户角色关联信息
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(user.getId());
			sysUserRole.setRoleId(user.getRoleId());
			this.sysUserRoleService.add(sysUserRole);
			logContent = "添加【"+user.getUserName()+"】的用户数据";
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
	 * 修改用户页面
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/editPage")
    public ModelAndView editPage(HttpServletRequest request) throws Exception{
    	ModelAndView mav = new ModelAndView(editJsp);
		String id = request.getParameter("id");
		SysUser user = this.sysUserService.findById(id);
		//创建list
		List<SysUser> rolelist = new ArrayList<SysUser>();
		Method method = rolelist.getClass().getMethod("add" , Object.class);
		method.invoke(rolelist,user.getRole());
		method.invoke(rolelist,user.getIsCardUpdate());
		// 根据用户id查询用户角色关联表信息
		SysUserRole sysUserRole = sysUserRoleService.findByUserId(user);
		user.setRoleId(sysUserRole.getRoleId());
		// 初始化行政区域信息
		SysArea sysArea = new SysArea();
		sysArea.setId(user.getAreaId());
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
		sysWaterArea.setId(user.getWaterAreaId());
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
		// 获取所有角色信息
    	List<SysRole> sysRoleList = sysRoleService.getAllRoleInfo();
		mav.addObject("model",user);
		mav.addObject("rolelist",rolelist);
		mav.addObject("sysAreaList",sysAreaList);
		mav.addObject("sysWaterAreaList",sysWaterAreaList);
		mav.addObject("sysRoleList",sysRoleList);
    	return mav;
    }
	
	/**
	 * 保存修改用户
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/logEditUser", method = RequestMethod.POST)
	@ResponseBody
    public String logEditUser(HttpServletRequest request,@ModelAttribute("user") SysUser user) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		try {
			// 修改用户角色
			SysUserRole sysUserRole = sysUserRoleService.findByUserId(user);
			sysUserRole.setRoleId(user.getRoleId());
			sysUserRoleService.update(sysUserRole);
			String msg = this.sysUserService.update(user);
			
			logContent = "编辑【"+user.getUserName()+"】的用户数据";
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
	 * TODO: 单个删除用户
	 * @param request
	 * 2016年11月4日
	 * Administrator : xiaogaoxiang
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUser(HttpServletRequest request) {
		String id = request.getParameter("id");
		AjaxJson ajaxJson = sysUserService.deleteUser(id);
		logContent = ajaxJson.getObj().toString();
		return JSONObject.toJSONString(ajaxJson);
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @throws Exception
	 */
    @RequestMapping(value = "/logDelUser")
    @ResponseBody
    public String logDelUser(HttpServletRequest request) throws Exception{
    	String items=request.getParameter("items");
    	AjaxJson ajaxJson = sysUserService.deleteUser(items);
    	logContent = ajaxJson.getObj().toString();
    	return JSONObject.toJSONString(ajaxJson);
    }
    
    /**
     * 移动用户的页面
     * @param request
     * @param dids
     * @param appCode
     */
    @RequestMapping(value="/movePage")
	public ModelAndView movePage(HttpServletRequest request,String dids,String enterId){
		ModelAndView mav = new ModelAndView(moveJsp);
	    mav.addObject("dids", dids);
	    mav.addObject("enterId", enterId);
		return mav;
	}
    
	/**
	 * 移动用户到其他机构
	 */
	@RequestMapping(value="/moveUserfo")
	@ResponseBody
	public String moveUserfo(HttpServletRequest request,String enterId){
		JSONObject enterObject = new JSONObject();
		enterObject.put("id", enterId);
		enterObject.put("pId", 0);
		enterObject.put("open", true);//展开
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("enterpriseid", enterId);
		map.put("organizationflag", Constant.organFlag);
		//查找所有设备分组
		List<SysOrganization> list = sysOrganizationService.getList(map);
	    JSONArray jsonarray = new JSONArray();
	    jsonarray.add(enterObject);
		for(SysOrganization organ:list){
			JSONObject jsonObject = new JSONObject();
			if(organ.getPid().equals("0")){
				//第一层
				jsonObject.put("id", organ.getId());
				jsonObject.put("pId",enterId);
				jsonObject.put("name", organ.getOrganname());
				jsonObject.put("open", true);//展开
				jsonarray.add(jsonObject);
			}else{
				jsonObject.put("id", organ.getId());
				jsonObject.put("pId", organ.getPid());
				jsonObject.put("name", organ.getOrganname());
				jsonarray.add(jsonObject);
			}
		}
		return JSONObject.toJSONString(jsonarray);
	}
	
	/**
	 * 保存移动的用户信息
	 */
	@RequestMapping(value="/logSaveMoveUser")
	@ResponseBody
	public String logSaveMoveUser(HttpServletRequest request){
		AjaxJson ajaxJson  = new AjaxJson();
		String ids = request.getParameter("ids");
		logContent = "";
		try{
			if(ids!=null){
				String[] idArray = ids.split(",");
				for (int i = 0; i < idArray.length; i++) {
					SysUser user = sysUserService.findById(idArray[i]);
					sysUserService.moveUser(user);
				}
				ajaxJson.setMsg("移动成功");
				ajaxJson.setSuccess(true);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			ajaxJson.setMsg("操作失败，异常信息："+ex.getMessage());
			ajaxJson.setSuccess(false);
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
    /**
     * 用户初始化密码
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/logInitPwdUser")
    @ResponseBody
    public String logInitPwdUser(HttpServletRequest request) throws Exception{
    	String items=request.getParameter("items");
    	HashMap<String, Object> hm = new HashMap<String, Object>();
    	logContent = "";
    	if(items!=null)
    	{
    		String [] itemArray=items.split(",");
    		for(String item:itemArray)
    		{
    			String id = item;
    			SysUser user=this.sysUserService.findById(id);
    			user.setUserPassword(StringUtils.encryptMd5("123456"));
    			user.setUserPasswordming("123456");
    			sysUserService.initPwd(user);
    			logContent = logContent+"初始化【"+user.getUserName()+"】用户的密码"+",";
    		}
    	}
    	hm.put("success", true);
    	hm.put("msg", "初始化成功");
    	return JSONObject.toJSONString(hm);
    }

	/**
	 * 用户密码修改
	 * @param request
	 */
	@RequestMapping(value = "/changePwd")
	public ModelAndView changePwd(HttpServletRequest request){
		ModelAndView mav=new ModelAndView(changePwdJsp);
		String userId = request.getParameter("userId");
		mav.addObject("userId", userId);
		return mav;
	}
	
	/**
	 * 保存密码修改
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/changePwdSave", method = RequestMethod.POST)
	@ResponseBody
    public String changePwdSave(HttpServletRequest request) throws Exception{
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String oldUserpwd = request.getParameter("oldUserpwd");
		HashMap<String, Object> hm = new HashMap<String, Object>();
		try {
			SysUser user = this.sysUserService.findById(userId);
			user.setUserPassword(StringUtils.encryptMd5(userPwd));
			user.setUserPasswordming(userPwd);
			this.sysUserService.updateByPrimaryKeySelective(user);
			hm.put("success", true);
			hm.put("msg", "密码修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			hm.put("success", false);
			hm.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(hm);
    }
	
	/**
	 * 检查用户密码是否正确
	 */
	@RequestMapping(value="/checkUserPwd")
	@ResponseBody
	public String checkUserPwd(String userId,String userPwd){
		AjaxJson ajaxJson = new AjaxJson();
		SysUser user = this.sysUserService.findById(userId);
		if(user!=null){
			String userpwd = StringUtils.encryptMd5(userPwd);
			if(user.getUserPassword().equals(userpwd)){
				ajaxJson.setSuccess(false);
			}
		}else{
			ajaxJson.setSuccess(true);
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
    /**
	 * 获取组织机构
	 * @param request
	 * @throws Exception
	 */
    @RequestMapping(value = "/isHasChildOrg")
    @ResponseBody
    public String isHasChildOrg(HttpServletRequest request) throws Exception{
    	String orgId=request.getParameter("orgId");
    	HashMap<String, Object> hm = new HashMap<String, Object>();
    	if(!StringUtils.isNullOrEmpty(orgId))
    	{
    		hm.put("result", true);
    		HashMap<String, Object> orgParams = new HashMap<String, Object>();
    		orgParams.put("enabledstate", 1);
    		orgParams.put("pid", orgId);
    		
    		List<SysOrganization> orgList = sysOrganizationService.getList(orgParams);;
    		if(orgList != null && orgList.size()>0){
    			hm.put("result", false);
    		}
    	}
    	return JSONObject.toJSONString(hm);
    }
    
	/**
	 * 配置角色信息
	 * @param request
	 */
	@RequestMapping(value = "/setRoleInfo")
	public ModelAndView setRoleInfo(HttpServletRequest request){
		ModelAndView mav=new ModelAndView(roleInfoJsp);
		mav.addObject("userId", request.getParameter("userId"));
		mav.addObject("userName", request.getParameter("userName"));
		mav.addObject("enterId", request.getParameter("enterId"));
		return mav;
	}
	
	/**
     * 获取用户角色树
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/getEnterpriseRole")
    @ResponseBody
    public String getEnterpriseRole(HttpServletRequest request) throws Exception{
    	Map<String, Object> params = new HashMap<String, Object>();
    	List<Object> list = new ArrayList<>();
    	
		String enterId = request.getParameter("enterId");
		String userId = request.getParameter("userId");
		
		params.put("enterpriseid", enterId);
		params.put("roleflag", 1);
    	
		HashMap<String, Object> enterHm = new HashMap<String, Object>();
		enterHm.put("pId", 0);
		enterHm.put("nocheck", true); 
		list.add(enterHm);
		//获取所有角色列表
		List<SysRole> allRoleList = this.sysRoleService.getList(params);
		List<String> roleIdList = this.sysUserRoleService.getRoleIdByUserId(userId);
		if(allRoleList!=null && allRoleList.size()>0){
			for (SysRole sysRole : allRoleList) {
				HashMap<String, Object> roleHm = new HashMap<String, Object>();
				roleHm.put("name", sysRole.getRoleName());
				roleHm.put("id", sysRole.getId());
				if(roleIdList.contains(sysRole.getId())){
					roleHm.put("checked", true);
				}
				list.add(roleHm);
			}
		}
    	return JSONObject.toJSONString(list);
    }
    
	/**
	 * 保存用户角色关联信息
	 * @param request
	 */
	@RequestMapping(value = "/logSaveUserRole")
	@ResponseBody
	public String logSaveUserRole(HttpServletRequest request){
		Map<String, Object> hm = new HashMap<String, Object>();
		
		String userId = request.getParameter("userId");
		String roleIds = request.getParameter("roleIds");
		String[] roleArray = roleIds.split(",");
		
		//先删除用户角色关联信息
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", userId);
		List<SysUserRole> oldSysUserRole = this.sysUserRoleService.getList(params);
		for (SysUserRole sysUserRole : oldSysUserRole) {
			sysUserRoleService.delete(sysUserRole.getId());
		}
		SysUser sysUser = null;
		
		if(org.apache.commons.lang.StringUtils.isNotEmpty(userId)){
			 sysUser = sysUserService.findById(userId);
		}
		if(sysUser != null){
			// userName = sysUser.getUsername();
		}
		for (int i = 0; i < roleArray.length; i++) {
			if(!StringUtils.isNullOrEmpty(roleArray[i])){
				SysUserRole model = new SysUserRole();
				model.setId(CommonUtil.getRandomUUID());
				this.sysUserRoleService.add(model);
			}
		}
		hm.put("success", true);
		hm.put("msg", "操作成功");
		hm.put("userId", userId);
		return JSONObject.toJSONString(hm);
	}
	
	/**
	 * @Title: findUserWaterByCode
	 * @Description: 根据用户ID 获取用户售水对象
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping(value="/findUserWaterByID")
	@ResponseBody
	public String findUserWaterByID(HttpServletRequest request){
		String msg = null;
		// 获取需要增加售水权限的用户ID
		String id = request.getParameter("id");
		// 根据ID获取用户编码
		String userCode = sysUserService.findUserCodeByID(id);
		// 根据用户编码  获取用户售水对象
		try {
			msg = sysUserService.findUserWaterByCode(userCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(msg);
	}

	/**
	 * @Title: findUserWaterByUserCode
	 * @Description: 根据用户编码  获取用户售水对象
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping(value="/findUserWaterByUserCode")
	@ResponseBody
	public String findUserWaterByUserCode(HttpServletRequest request){
		String msg = null;
		String userCode = request.getParameter("userCode");
		
		try {
			msg = sysUserService.findUserWaterByCode(userCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(msg);
	}
	
	/**
	 * @Title: addUserWaterPrePage
	 * @Description: 跳转开通售水权限页面
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/addUserWaterPrePage")
	public ModelAndView addUserWaterPrePage(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView(BUY_WATER_JSP);
		// 获取需要增加售水权限的ID
		String id = request.getParameter("id");
		// 根据ID获取用户编码
		String userCode = sysUserService.findUserCodeByID(id);
		
		modelAndView.addObject("userCode", userCode);
		return modelAndView;
	}
	
	/**
	 * @Title: saveUserWater
	 * @Description: 保存用户售水对象
	 * @param: @param sysUserWater
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping(value="/saveUserWater", method = RequestMethod.POST)
	@ResponseBody
	public String saveUserWater(SysUserWater sysUserWater){
		logContent = "";
		Map<String, Object> param = new HashMap<String, Object>();
		SysUser sysUser = null;
		
		try {
			sysUserWater.setId(CommonUtil.getRandomUUID());
			// 保存用户售水权限  至售水权限表
			String msg = sysUserService.saveUserWater(sysUserWater);
			// 同时修改该用户  用户表中的Authority(售水权限)字段
			sysUser = new SysUser();
			sysUser.setUserCode(sysUserWater.getUserCode());
			sysUser.setAuthority(sysUserWater.getIsBuyWater());
			sysUserService.updateUserAuthority(sysUser);
			
			logContent = "添加【用户售水权限】的数据";
			param.put("success", true);
			param.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			param.put("success", false);
			param.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(param);
	}
	
	/**
	 * @Title: editUserWaterPage
	 * @Description: 跳转修改用户售水权限页面
	 * @param: @param request
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/editUserWaterPage")
	public ModelAndView editUserWaterPage(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView(EDIT_BUY_WATER_JSP);
		// 获取页面ID
		String id = request.getParameter("id");
		// 根据ID获得用户编码
		String userCode = sysUserService.findUserCodeByID(id);
		// 根据用户编码 获取用户售水对象
		SysUserWater sysUserWater = sysUserService.findUserWaterByUserCode(userCode);
		modelAndView.addObject("sysUserWater", sysUserWater);
		modelAndView.addObject("userCode", userCode);
		return modelAndView;
	}
	
	/**
	 * @Title: editUserWater
	 * @Description: 保存修改的用户售水权限信息
	 * @param: @param request
	 * @return: String
	 */
	@RequestMapping(value="/editUserWater", method=RequestMethod.POST)
	@ResponseBody
	public String editUserWater(SysUserWater sysUserWater){
		logContent = "";
		Map<String, Object> param = new HashMap<String, Object>();
		SysUser sysUser = null;
		
		try {
			// 用户售水权限  至售水权限表
			String msg = sysUserService.editUserWater(sysUserWater);
			// 同时修改该用户  用户表中的Authority(售水权限)字段
			sysUser = new SysUser();
			sysUser.setUserCode(sysUserWater.getUserCode());
			sysUser.setAuthority(sysUserWater.getIsBuyWater());
			sysUserService.updateUserAuthority(sysUser);
			
			logContent = "编辑【用户售水权限】的数据";
			param.put("success", true);
			param.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			param.put("success", false);
			param.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(param);
	}
	
	/**
	 * @Title: uniqueUserCode
	 * @Description: 用户编码 唯一检查
	 * @param request
	 * @param userCode
	 * @return: String
	 * @Author: zhaojx
	 */
	@RequestMapping(value="/uniqueUserCode")
	@ResponseBody
	public String uniqueUserCode(HttpServletRequest request, String userCode){
		String msg = null;
		try {
			msg = sysUserService.uniqueUserCode(userCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(msg);
	}
	
	/**
	 * @Title: checkIsAudit
	 * @Description: 检查app用户是否审核过
	 * @param request
	 * @return: String
	 * @Author: zhaojx
	 */
	@RequestMapping(value="checkIsAudit")
	@ResponseBody
	public String checkIsAudit(HttpServletRequest request){
		String msg = "";
		// 获取页面操作用户ID
		String id = request.getParameter("id");
		if(id != null && !id.equals("")){
			SysUser userInfo = sysUserService.selectByPrimaryKey(id);
			if(userInfo.getAuditFlag() == 0){
				msg = "success";
			}else{
				msg = "failed";
			}
		}
		return JSONObject.toJSONString(msg);
	}
	
	/**
	 * @Title: appAuditPage
	 * @Description: 跳转app审核页面
	 * @param request
	 * @return: ModelAndView
	 * @Author: zhaojx
	 */
	@RequestMapping(value="/appAuditPage")
	public ModelAndView appAuditPage(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView(APP_AUDIT_JSP);
		// 获取页面操作用户ID
		String id = request.getParameter("id");
		
		List<SysWaterArea> sysWaterAreaList = sysWaterAreaService.getFirstLevelList(null);
		List<SysArea> sysAreaList = sysAreaService.getFirstAreaLevelList(null);
		// 根据ID查询用户信息
		if(null != id && !id.equals("")){
			SysUser userInfo = sysUserService.selectByPrimaryKey(id);
			modelAndView.addObject("userInfo", userInfo);
			modelAndView.addObject("id", id);
			modelAndView.addObject("sysWaterAreaList", sysWaterAreaList);
			modelAndView.addObject("sysAreaList", sysAreaList);
		}
		return modelAndView;
	}
	
	/**
	 * @Title: appAudit
	 * @Description: app用户审核
	 * @param userModel
	 * @param request
	 * @return: String
	 * @Author: zhaojx
	 */
	@RequestMapping(value="/appAudit", method=RequestMethod.POST)
	@ResponseBody
	public String appAudit(SysUser userModel, HttpServletRequest request){
		logContent = "";
		String msg = "";
		// 参数Map集合
		Map<String, Object> param = new HashMap<String, Object>();
		
		// 获取页面水管区域和行政区域Id
		String waterAreaId = userModel.getWaterAreaId();
		String areaId = userModel.getAreaId();
		// 截取最后一级子水管区域Id和子行政区域Id
		String newWaterAreaId = waterAreaId.substring(waterAreaId.lastIndexOf(",")+1, waterAreaId.length());
		String newAreaId = areaId.substring(areaId.lastIndexOf(",")+1, areaId.length());
		
		try {
			// 将截取后的Id放入用户对象
			userModel.setAreaId(newAreaId);
			userModel.setWaterAreaId(newWaterAreaId);
			// app审核（用户信息修改）
			msg = sysUserService.appAudit(userModel);
			param.put("success", true);
			param.put("msg", msg);
			logContent = "审核app用户"+userModel.getUserName()+"";
		} catch (Exception e) {
			e.printStackTrace();
			param.put("success", false);
			param.put("msg", "操作失败！具体异常信息：" + e.getMessage());
		}
		return JSONObject.toJSONString(msg);
	}
	
	/**
	 * @Title: appIndex
	 * @Description: 跳转app用户列表首页
	 * @param request
	 * @return: ModelAndView
	 * @Author: zhaojx
	 */
	@RequestMapping(value = "/appIndex")
	public ModelAndView appIndex(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView(APP_USER_INDEX);
		return modelAndView;
	}
	
	/**
	 * @Title: appUserList
	 * @Description: app用户分页列表
	 * @param request
	 * @return: ModelAndView
	 * @Author: zhaojx
	 */
	@RequestMapping(value="/appUserList")
	public ModelAndView appUserList(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView(APP_USER_LIST);
		Map<String, Object> params = new HashMap<String, Object>();
		// 分页工具类
		PagingBean pagingBean = null;
		// 获取当前页
		String pageNo = request.getParameter("pageNo");
		// 获取用户总条数
		int count = sysUserService.getAppUserCount(params);
		// 分页工具类
		pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		// 将分页参数放入Map集合
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		
		// 获取app用户列表
		List<SysUser> appUserList = sysUserService.getAppUserList(params);
		// 将用户分页信息放入ModelAndView
		modelAndView.addObject("pagingBean", pagingBean);
		modelAndView.addObject("appUserList", appUserList);
		// 返回模型视图对象
		return modelAndView;
	}
	
}