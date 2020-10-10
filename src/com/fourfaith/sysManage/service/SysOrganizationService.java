package com.fourfaith.sysManage.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysOrganization;

/**
 * SysOrganization interface
 * @author 
 */
public interface SysOrganizationService{

	int deleteByPrimaryKey(String id);

	int insert(SysOrganization record);

	int insertSelective(SysOrganization record);

	SysOrganization selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysOrganization record);

	int updateByPrimaryKey(SysOrganization record);

	Integer getCount(Map<String, Object> params);

	List<SysOrganization> getList(Map<String, Object> params);

	SysOrganization findById(String Id);
	
	List<String> getAllChildIdList(String id);
	
	String add(SysOrganization model);
	
	String update(SysOrganization model);
	
	String delete(String id);
	
	SysOrganization getByOrgan(Map<String,Object> params);
	
}