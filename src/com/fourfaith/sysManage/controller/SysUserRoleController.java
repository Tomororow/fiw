package com.fourfaith.sysManage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourfaith.sysManage.service.SysUserRoleService;

/**   
 * @Title: Controller
 * @Description: 用户角色表
 * @author administrator
 * @date 2016-05-23 10:09:56
 * @version V1.0   
 */
@Controller
@RequestMapping(value ="/sysUserRole")
public class SysUserRoleController {

	@Autowired
	private SysUserRoleService sysUserRoleService;

}