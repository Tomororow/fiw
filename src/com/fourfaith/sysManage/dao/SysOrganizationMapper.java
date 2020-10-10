package com.fourfaith.sysManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.sysManage.model.SysOrganization;

/**
 * @ClassName: SysOrganizationMapper
 * @Description: dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:11:45
 */
public interface SysOrganizationMapper {

   	int deleteByPrimaryKey(String id);

	int insert(SysOrganization record);

	int insertSelective(SysOrganization record);

	SysOrganization selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SysOrganization record);

	int updateByPrimaryKey(SysOrganization record);

	Integer getCount(Map<String, Object> params);

	List<SysOrganization> getList(Map<String, Object> params);

	SysOrganization getByOrgan(Map<String,Object> params);
	
}