package com.fourfaith.sysManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysPermission;

/**
 * @ClassName: SysPermissionMapper
 * @Description: 权限dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:11:30
 */
public interface SysPermissionMapper {

//   int deleteByPrimaryKey(String id);
//
//	int insert(SysRolePermission record);
//
	int insertSelective(SysPermission record);
//
//	SysRolePermission selectByPrimaryKey(String id);
//
//	int updateByPrimaryKeySelective(SysRolePermission record);
//
//	int updateByPrimaryKey(SysRolePermission record);
//
//	Integer getCount(Map<String, Object> params);

	List<SysPermission> getList(Map<String, Object> params);
	
	int deleteByRoleId(String roleId);

//	SysRolePermission findById(String Id);
	
}