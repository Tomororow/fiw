package com.fourfaith.basicInformation.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysApplyStatus;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysApplyStatusService
 * @Description: 应用状况service接口
 * @Author: zhaojx
 */
public interface SysApplyStatusService{

	Integer getCount(Map<String, Object> params);

	List<SysApplyStatus> getList(Map<String, Object> params);
	
	int deleteByPrimaryKey(String id);

	int insert(SysApplyStatus record);

	int insertSelective(SysApplyStatus record);
	
	SysApplyStatus selectByPrimaryKey(String id);
	
	SysApplyStatus findById(String Id);
	
	String add(SysApplyStatus model);
	
	AjaxJson deleteDis(String items);

	String delete(String id);
	
	String update(SysApplyStatus sysApplyStatus);
	
}