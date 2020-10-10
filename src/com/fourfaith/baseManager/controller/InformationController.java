package com.fourfaith.baseManager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.sysManage.model.SysInfo;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysInfoService;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;

/**
 * 信息发布Controller
 * @author administrator
 */
@Controller
@RequestMapping(value ="/information")
public class InformationController {
	
	protected static final String indexJsp="/page/information/index";
	protected static final String introductionJsp="/page/information/introduction";
	protected static final String uploadJsp="/page/information/upload_json";
	protected static final String fileJsp="/page/information/file_manager_json";
	
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysInfoService sysInfoService;
	
	//以下参数为添加日志所需
    public String logContent = "";
    
	/**
	 * 信息发布首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
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
	 * 系统简介页面
	 */
	@RequestMapping(value = "/introduction")
	public ModelAndView data(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(introductionJsp);
		mav.addObject("sysInfo", sysInfoService.findByData("SysBrief"));
		return mav;
	}
	
	/**
	 * 系统简介保存
	 */
	@RequestMapping(value = "/saveHtml")
	@ResponseBody
	public String saveHtml(HttpServletRequest request,String infoValue){
		logContent = "";
		SysInfo sysInfo = sysInfoService.findByData("SysBrief");
		sysInfo.setInfoValue(infoValue);
		AjaxJson ajaxJson = new AjaxJson();
		try{
			String msg = sysInfoService.update(sysInfo);
			logContent = "系统简介设置";
			ajaxJson.setMsg(msg);
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}catch(Exception e){
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setObj("系统简介设置，抛出异常！具体异常信息："+ e.getMessage());
			ajaxJson.setMsg("系统简介设置失败，请联系管理人员");
		}
		return JSONObject.toJSONString(ajaxJson);
	}
	
}