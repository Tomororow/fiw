package com.fourfaith.sysManage.controller;

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
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysPermission;
import com.fourfaith.sysManage.model.SysRole;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserRole;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.sysManage.service.SysPermissionService;
import com.fourfaith.sysManage.service.SysRoleService;
import com.fourfaith.sysManage.service.SysUserRoleService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.BeanUtils;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.StringUtils;

/**   
 * @Title: Controller
 * @Description: 系统角色表
 * @author administrator
 * @date 2016-05-23 10:08:43
 * @version V1.0   
 */
@Controller
@RequestMapping(value ="/sysRole")
public class SysRoleController {

	protected static final String indexJsp="/page/sysmanage/role/roleIndex";
	protected static final String addJsp="/page/sysmanage/role/add";
	protected static final String editJsp="/page/sysmanage/role/edit";
	protected static final String permissionJsp="/page/sysmanage/role/permissionInfo";
	
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysPermissionService sysPermissionService;
	
	//以下参数为添加日志所需
    public String logContent = "";
	
    /**
	 * 角色列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(indexJsp);
		Map<String, Object> params = new HashMap<String, Object>();
		String searchRoleName = request.getParameter("searchRoleName");
		try {
			if(!StringUtils.isNullOrEmpty(searchRoleName)){
				params.put("searchRoleName", searchRoleName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//分页
		String pageNo = request.getParameter("pageNo");
		int count = sysRoleService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("pageStart", pagingBean.getStart());
		params.put("pageEnd", pagingBean.getLimit());
		List<SysRole> sysRoleList = sysRoleService.getList(params);
 		mav.addObject("pagingBean", pagingBean);
		mav.addObject("sysRoleList",sysRoleList);
		return mav;
	}
	
	/**
	 * 新增角色
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addPage")
    public ModelAndView addPage(HttpServletRequest request){
    	ModelAndView mav=new ModelAndView(addJsp);
    	return mav;
    }
	
	/**
	 * 保存新增角色
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSysRole", method = RequestMethod.POST)
	@ResponseBody
    public String addSysRole(HttpServletRequest request, SysRole role) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			role.setId(CommonUtil.getRandomUUID());
			role.setCreateTime(new Date());
			role.setEditTime(new Date());
			String msg = this.sysRoleService.add(role);
			logContent = "添加【"+role.getRoleName()+"】的角色数据";
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
	 * 修改角色
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/editPage")
    public ModelAndView edit(HttpServletRequest request) throws Exception{
    	ModelAndView mav=new ModelAndView(editJsp);
		String id=request.getParameter("id");
		SysRole sysRole=this.sysRoleService.findById(id);
		mav.addObject("sysRole",sysRole);
    	return mav;
    }
	
	/**
	 * 保存修改角色
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/editSysRole", method = RequestMethod.POST)
	@ResponseBody
    public String editSysRole(HttpServletRequest request,SysRole role) throws Exception{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		logContent = "";
		try {
			SysRole newSysRole = this.sysRoleService.findById(role.getId());
			role.setEditTime(new Date());
			BeanUtils.copyPropertiesExcludeNull(role, newSysRole);
			String msg = this.sysRoleService.update(newSysRole);
			logContent = "编辑【"+role.getRoleName()+"】的角色数据";
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
	 * 配置角色权限信息
	 * @param request
	 */
	@RequestMapping(value = "/setPermissionInfo")
	public ModelAndView setPermissionInfo(HttpServletRequest request){
		ModelAndView mav=new ModelAndView(permissionJsp);
		mav.addObject("roleId", request.getParameter("roleId"));
		return mav;
	}
	
	/**
	 * 检测角色是否存在关联用户
	 * @param request
	 * @throws Exception
	 */
    @RequestMapping(value = "/checkUserRoleBy")
    @ResponseBody
    public String checkUserRoleBy(HttpServletRequest request) throws Exception{
    	Map<String, Object> hm = new HashMap<String, Object>();
    	String items = request.getParameter("items");
    	hm.put("exist", false);
    	if(items!=null)
    	{
    		String [] itemArray=items.split(",");
	    	for(String item:itemArray)
    		{
				String id = item;
				//根据角色id获取角色用户关联信息
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("roleId", id);
				List<SysUserRole> sysUserRole = this.sysUserRoleService.getList(params);
				if(sysUserRole != null && sysUserRole.size()>0){
					hm.put("exist", true);
					break;
				}
	    	}
	    }
	    return JSONObject.toJSONString(hm);
    }
	
   /**
     * 删除角色
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/delSysRole")
    @ResponseBody
    public String delSysRole(HttpServletRequest request) throws Exception{
    	String items = request.getParameter("items");
    	logContent = "";
    	AjaxJson ajaxJson = sysRoleService.deleteRole(items);
    	logContent = ajaxJson.getObj().toString();
    	return JSONObject.toJSONString(ajaxJson);
    }
    
    /**
	 * 检查角色编码是否存在
	 */
	@RequestMapping(value="/checkRoleCodeExist")
	@ResponseBody
    public String checkRoleCodeExist(String roleCode){
		String msg = null;
		try {
			msg = sysRoleService.checkRoleCodeExist(roleCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(msg);
    }
	
	/**
     * 获取可见菜单树
     * @param request
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getMenuTree")
    @ResponseBody
    public String getMenuTree(HttpServletRequest request) throws Exception{
    	Map<String, Object> sysMenuParams = new HashMap<String, Object>();
    	Map<String, Object> sysPermissionParams = new HashMap<String, Object>();
    	List list = new ArrayList<>();
    	List<String> menuIdList = new ArrayList<String>();
		String roleId = request.getParameter("roleId");
		SysUser loginUser = (SysUser) CommonUtil.getLoginUserInfo(request);
		sysPermissionParams.put("roleId", loginUser.getSysrole().getId());
		List<SysPermission> sysPermissionList = this.sysPermissionService.getList(sysPermissionParams);
		//获取菜单
		for(SysPermission sysPermission: sysPermissionList){
			menuIdList.add(sysPermission.getMenuId());
		}
		sysMenuParams.put("idList", menuIdList);
		List<SysMenu> menuList = this.sysMenuService.getList(sysMenuParams);
		if(menuList!=null && menuList.size()>0){
			for (int i = 0; i < menuList.size(); i++) {
				SysMenu menu = menuList.get(i);
				HashMap<String, Object> menuHm = new HashMap<String, Object>();
				if(menu!=null){
					//判断是否有应用权限
					menuHm.put("name", menu.getMenuName());
					menuHm.put("id", menu.getId());
					menuHm.put("pId", menu.getParentMenuId());
					menuHm.put("open", true);
					SysPermission sysPermission = this.sysPermissionService.findByMenuIdAndRoleId(menu.getId(), roleId);
					if(sysPermission!=null && sysPermission.getId()!=null){
						menuHm.put("checked", true);
					}
					list.add(menuHm);
				}
			}
		}
		return JSONObject.toJSONString(list);
    }
    
    /**
   	 * 保存用户角色关联信息 new 
   	 * @param request
   	 */
   	@SuppressWarnings({ "unchecked" })
   	@RequestMapping(value = "/saveRoleMenuPermission")
   	@ResponseBody
   	public String saveRoleMenuPermission(HttpServletRequest request){
   		
   		Map<String, Object> hm = new HashMap<String, Object>();
   		String roleId = request.getParameter("roleId");
   		//最子集id
   		String menuIds = request.getParameter("menuIds");
   		
   		logContent = "";
   		Map<String, List<String>> menuMap = new HashMap<String, List<String>>();
   		try{
   			//先删除角色关联的权限数据
   			this.sysPermissionService.deleteByRoleId(roleId);
   			
   			//构造关联信息
   			menuMap = (Map<String, List<String>>) JSONObject.parse(menuIds);
   			
   			for(String key : menuMap.keySet()){
   				//菜单列表
   				List<String> menuList = menuMap.get(key);
   				if(menuList!=null && menuList.size()>0){
   					for (int i = 0; i < menuList.size(); i++) {
   						//角色应用菜单表
   						SysPermission sysPermission = new SysPermission();
   						sysPermission.setId(CommonUtil.getRandomUUID());
   						sysPermission.setRoleId(roleId);
   						sysPermission.setMenuId(menuList.get(i));
   						this.sysPermissionService.add(sysPermission);
   					}
   				}
   				
   			}
   			SysRole role = this.sysRoleService.findById(roleId);
   			logContent = "配置【"+role.getRoleName()+"】角色的权限数据";
   			hm.put("success", true);
   			hm.put("msg", "权限配置成功");
   			
   		}catch(Exception e){
   			e.printStackTrace();
   			hm.put("success", false);
   			hm.put("msg", "操作失败");
   		}
   		return JSONObject.toJSONString(hm);
   	}
   	
}