package com.fourfaith.sysManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysPermission;

/**
 * @ClassName: SysPermissionService
 * @Description: 权限service
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:33:48
 */
public interface SysPermissionService{
//
//	int deleteByPrimaryKey(String id);
//
//	int insert(SysRolePermission record);
//
//	int insertSelective(SysRolePermission record);
//
//	SysRolePermission selectByPrimaryKey(String id);
//
//	int updateByPrimaryKeySelective(SysRolePermission record);
//
//	int updateByPrimaryKey(SysRolePermission record);
//
//	Integer getCount(Map<String, Object> params);

	List<SysPermission> getList(Map<String, Object> params);

//	SysRolePermission findById(String Id);
//	
	String deleteByRoleId(String roleId);
	
	String add(SysPermission model);
	
	SysPermission findByMenuIdAndRoleId(String menuId, String roleId);
	
}