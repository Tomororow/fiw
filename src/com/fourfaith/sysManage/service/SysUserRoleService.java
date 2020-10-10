package com.fourfaith.sysManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserRole;

/**
 * @ClassName: SysUserRoleService
 * @Description: 角色权限dao接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:34:31
 */
public interface SysUserRoleService{

	int deleteByPrimaryKey(String id);

	int insert(SysUserRole record);

	int insertSelective(SysUserRole record);

	SysUserRole selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysUserRole record);

	int updateByPrimaryKey(SysUserRole record);

	Integer getCount(Map<String, Object> params);

	List<SysUserRole> getList(Map<String, Object> params);

	SysUserRole findById(String Id);
	
	String add(SysUserRole model);
	
	String update(SysUserRole model);
	
	String deleteByRoleId(String roleId);
	
	List<String> getUserRoleId(String userId);
	
	String delete(String id);
	
	List<String> getRoleIdByUserId(String userId);

	/**
	 * 根据用户Id，查询用户角色关联表的roleId
	 * @param user
	 * 2016年11月5日
	 */
	SysUserRole findByUserId(SysUser user);
	
}