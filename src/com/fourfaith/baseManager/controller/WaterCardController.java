package com.fourfaith.baseManager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fourfaith.statisticAnalysis.service.BaseCardInfoService;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.utils.CommonUtil;

/**
 * @ClassName: WaterCardController
 * @Description: 水卡管理控制器
 * @Author: zhaojx
 * @Date: 2018年5月16日 下午6:08:47
 */
@Controller()
@RequestMapping("/waterCard")
public class WaterCardController {
	
	// 视图解析路径
	protected static final String indexJsp = "/page/waterCard/index";
	
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private BaseCardInfoService baseCardInfoService;
	
	/**
	 * @Title: index
	 * @Description: 水卡管理首页
	 * @param: @param request
	 * @param: @param menuId
	 * @param: @param menuCode
	 * @return: ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,String menuId,String menuCode){
		ModelAndView mav = new ModelAndView(indexJsp);
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		List<SysMenu> sysMenuList = new ArrayList<SysMenu>();
		//根据权限动态获取菜单列表
		sysMenuList = this.sysMenuService.getListByRoleidAndMenuid(menuId, login_user.getSysrole().getId());
		mav.addObject("sysMenuList", sysMenuList);
		return mav;
	}
	
}