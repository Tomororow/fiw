package com.fourfaith.basicInformation.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysOperator;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysOperatorService
 * @Description: 运营商service接口
 * @Author: zhaojx
 */
public interface SysOperatorService{

	Integer getCount(Map<String, Object> params);

	List<SysOperator> getList(Map<String, Object> params);
	
	int deleteByPrimaryKey(String id);

	int insert(SysOperator record);

	int insertSelective(SysOperator record);
	
	SysOperator selectByPrimaryKey(String id);
	
	SysOperator findById(String Id);
	
	String add(SysOperator model);
	
	AjaxJson deleteDis(String items);

	String delete(String id);
	
	String update(SysOperator sysOperator);
	
}