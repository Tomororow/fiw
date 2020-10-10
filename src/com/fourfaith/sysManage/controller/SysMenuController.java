package com.fourfaith.sysManage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.utils.AjaxJson;

/**   
 * @Title: Controller
 * @Description: 系统菜单信息表
 * @author administrator
 * @date 2016-05-23 10:08:18
 * @version V1.0   
 */
@Controller
@RequestMapping(value ="/sysMenu")
public class SysMenuController {

	@Autowired
	private SysMenuService sysMenuService;
	
	protected static final String indexJsp="/page/sysmanage/menu/menuIndex";
	protected static final String childMenuJsp = "/page/sysmanage/menu/childMenuPage";
	protected static final String addJsp = "/page/sysmanage/menu/add";
	protected static final String editJsp = "/page/sysmanage/menu/edit";
	
	/**
	 * 查找所有父级菜单
	 */
//	@RequestMapping(value="/index")
//	public ModelAndView index(HttpServletRequest request,String menuName){
//		ModelAndView mav  = new ModelAndView(indexJsp);
//		Map<String,Object> params = new HashMap<String,Object>();
//		try {
//			if(StringUtils.isNotEmpty(menuName)){
//				menuName = URLDecoder.decode(menuName, "UTF-8");
//				mav.addObject("menuname", menuName);
//			}else{
//				mav.addObject("menuname", null);
//			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} 
//		params.put("pid",0);
//	    params.put("menuname_fuzzy", menuName);
//	    params.put("enabledstate_notEqual", Constant.delStatus);
//	    
//	    // 分页
//	    String pageNo = request.getParameter("pageNo");
//	    int count = sysMenuService.getCount(params);
//	    PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
//		params.put("pageStart", pagingBean.getStart());
//		params.put("pageEnd", pagingBean.getLimit());
//		
//	    //父级标识为0--代表父菜单
//	    List<SysMenu> list = sysMenuService.getList(params);
//		mav.addObject("menuList", list);
//		mav.addObject("pagingBean", pagingBean);
//		return mav;
//	}
//	
//	/**
//	 * 菜单下的子菜单管理
//	 */
//	@RequestMapping(value="/menuPage")
//	public ModelAndView menuPage(HttpServletRequest request){
//		ModelAndView mav = new ModelAndView(childMenuJsp);
//		String id = request.getParameter("id");
//		mav.addObject("menuId", id);
//		return mav;
//	}
//	
//	/**
//	 * 根据父菜单，查找所有子菜单
//	 */
//	@RequestMapping(value="/getChildMenuList")
//	@ResponseBody
//	public String getByAppId(HttpServletRequest request,String menuId){
//		//通过父菜单id,查找父菜单的信息
//		SysMenu parentMenu = sysMenuService.findById(menuId);
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("pid", menuId);
//		params.put("enabledstate_notEqual", Constant.delStatus);
//		List<SysMenu> list = sysMenuService.getList(params);
//		JSONArray jsonarray = new JSONArray();
//		JSONObject menuObject = new JSONObject();
//		menuObject.put("id", menuId);
//		menuObject.put("name", parentMenu.getMenuname()+"【父菜单】");
//		menuObject.put("pId", 0);
//		menuObject.put("open", true);//展开
//		jsonarray.add(menuObject);
//		for(SysMenu menu:list){
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.put("id", menu.getId());
//				jsonObject.put("pId", menuId);
//				jsonObject.put("name", menu.getMenuname());
//				jsonObject.put("open", true);//展开
//				jsonarray.add(jsonObject);
//		}
//		return JSONObject.toJSONString(jsonarray);
//	}
//	
//	/**
//	 * 新增菜单页面
//	 */
//	@RequestMapping(value="/addPage")
//	public ModelAndView addPage(HttpServletRequest request,String id,String level,String menuName){
//		ModelAndView mav  = new ModelAndView(addJsp);
//		mav.addObject("parentMenuId",id);
//		mav.addObject("level", level+2);//菜单级别
//		try {
//			if(StringUtils.isNotEmpty(menuName)){
//				menuName = URLDecoder.decode(menuName, "UTF-8");
//				mav.addObject("menuName", menuName);
//			}else{
//				mav.addObject("menuName", null);
//			}
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		Integer order = sysMenuService.getMaxMenuOrder();
//		if(order==null){
//			order=0;
//		}
//		mav.addObject("order", order+1);
//		return mav;
//	}
//	
//	/**
//	 * 新增菜单
//	 */
//	@RequestMapping(value="/addMenu")
//	@ResponseBody
//	public String addMenu(HttpServletRequest request,SysMenu menu){
//		AjaxJson ajax = new AjaxJson();
//		menu.setId(CommonUtil.getRandomUUID());
//		menu.setMenucreatetime(new Date());
//		menu.setMenuupdatetime(new Date());
//		if(menu.getEnabledstate()==null){
//			menu.setEnabledstate(0);
//		}
//		try{
//			String msg = sysMenuService.add(menu);
//			ajax.setMsg(msg);
//			ajax.setSuccess(true);
//		}catch(Exception ex){
//			ex.printStackTrace();
//			ajax.setMsg("操作失败，异常信息："+ex.getMessage());
//			ajax.setSuccess(false);
//		}
//		return JSONObject.toJSONString(ajax);
//	}
//	
//	/**
//	 * 编辑菜单页面
//	 */
//	@RequestMapping(value="/editPage")
//	public ModelAndView editPage(HttpServletRequest request,String id,String parentName){
//		ModelAndView mav  = new ModelAndView(editJsp);
//		SysMenu menu = sysMenuService.findById(id);
//		mav.addObject("sysMenu", menu);
//		mav.addObject("parentName", parentName);
//		return mav;
//	}
//	
//	/**
//	 * 编辑菜单
//	 */
//	@RequestMapping(value="editMenu")
//	@ResponseBody
//	public String editApp(HttpServletRequest request,SysMenu menu){
//		AjaxJson ajax = new AjaxJson();
//		menu.setMenuupdatetime(new Date());
//		if(menu.getEnabledstate()==null){
//			menu.setEnabledstate(0);//禁用
//		}
//		menu.setEnabledstate(1);//强制启用
//		try{
//			String msg = sysMenuService.update(menu);
//			ajax.setMsg(msg);
//			ajax.setSuccess(true);
//		}catch(Exception ex){
//			ex.printStackTrace();
//			ajax.setMsg("操作失败，异常信息："+ex.getMessage());
//			ajax.setSuccess(false);
//		}
//		return JSONObject.toJSONString(ajax);
//	}
//	
//	/**
//	 * 菜单删除
//	 */
//	@RequestMapping(value="delMenu")
//	@ResponseBody
//	public String delMenu(HttpServletRequest request,String id){
//		AjaxJson ajax = new AjaxJson();
//		SysMenu menu = sysMenuService.findById(id);
//		menu.setEnabledstate(-1);
//		try{
//			//只改变状态，不做具体的数据删除
//			String msg = sysMenuService.update(menu);
//			ajax.setMsg("删除成功");
//			ajax.setSuccess(true);
//		}catch(Exception ex){
//			ex.printStackTrace();
//			ajax.setMsg("操作失败，异常信息："+ex.getMessage());
//			ajax.setSuccess(false);
//		}
//		return JSONObject.toJSONString(ajax);
//	}
	
	/**
	 * 验证菜单编码是否存在
	 */
	@RequestMapping(value="/checkMenuCodeExist")
	@ResponseBody
    public String checkMenuCodeExist(String menuCode,String id){
    	//应用编码，不允许重复
		AjaxJson ajaxJson = new AjaxJson();
		try {
			if(StringUtils.isNotEmpty(menuCode)){
				menuCode = URLDecoder.decode(menuCode, "UTF-8");
			}else{
				menuCode = null;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SysMenu menu = sysMenuService.getByMenuCode(menuCode);
		if(menu!=null && !menu.getId().equals(id)){
			ajaxJson.setSuccess(true);
		}else{
			ajaxJson.setSuccess(false);
		}
		return JSONObject.toJSONString(ajaxJson);
    }

}