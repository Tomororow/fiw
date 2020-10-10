package com.fourfaith.sysManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysRole;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysRoleService
 * @Description: 角色dao接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:34:07
 */
public interface SysRoleService{

	int deleteByPrimaryKey(String id);

	int insert(SysRole record);

	int insertSelective(SysRole record);

	SysRole selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysRole record);

	int updateByPrimaryKey(SysRole record);

	Integer getCount(Map<String, Object> params);

	List<SysRole> getList(Map<String, Object> params);

	SysRole findById(String Id);
	
	String add(SysRole model);
	
	String update(SysRole model);
	
	SysRole getByRoleId(Map<String, Object> map);
	
	AjaxJson deleteRole(String items);

	String delete(String id);

	/**
	 * 获取所有角色信息
	 * 2016年11月5日
	 */
	List<SysRole> getAllRoleInfo();
	
	/**
	 * @Title: checkRoleCodeExist
	 * @Description: 角色编码 唯一检验
	 * @param roleCode
	 * @return: List<SysRole>
	 * @Author: zhaojx
	 */
	String checkRoleCodeExist(String roleCode);

}