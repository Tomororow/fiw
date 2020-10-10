package com.fourfaith.baseManager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.utils.CommonUtil;

/**
 * 统计分析控制器
 * @author administrator
 */
@Controller
@RequestMapping(value ="/statistic")
public class StatisticController {
	
	protected static final String indexJsp = "/page/statistic/index";
	
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 统计分析首页
	 * @return
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