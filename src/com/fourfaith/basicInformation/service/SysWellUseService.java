package com.fourfaith.basicInformation.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysWellUse;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysWellUseService
 * @Description: 机井用途service接口
 * @Author: zhaojx
 */
public interface SysWellUseService{

	Integer getCount(Map<String, Object> params);

	List<SysWellUse> getList(Map<String, Object> params);
	
	int deleteByPrimaryKey(String id);

	int insert(SysWellUse record);

	int insertSelective(SysWellUse record);
	
	SysWellUse selectByPrimaryKey(String id);
	
	SysWellUse findById(String Id);
	
	String add(SysWellUse model);
	
	AjaxJson deleteDis(String items);

	String delete(String id);
	
	String update(SysWellUse sysWellUse);
	
}