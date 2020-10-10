package com.fourfaith.sysManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserRole;

/**
 * @ClassName: SysUserRoleMapper
 * @Description: 角色权限dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:12:54
 */
public interface SysUserRoleMapper {

   	int deleteByPrimaryKey(String id);

	int insert(SysUserRole record);

	int insertSelective(SysUserRole record);

	SysUserRole selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysUserRole record);

	int updateByPrimaryKey(SysUserRole record);

	Integer getCount(Map<String, Object> params);

	List<SysUserRole> getList(Map<String, Object> params);

	SysUserRole findById(String Id);
	
    int deleteByRoleId(String roleId);
    
    List<String> getUserRoleId(String userId);
    
    List<String> getRoleIdByUserId(String userId);

    /**
     * 根据用户Id，查询用户角色关联表的roleId
     * @param user
     * 2016年11月5日
     */
	SysUserRole findByUserId(SysUser user);
	
}