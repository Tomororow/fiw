package com.fourfaith.basicInformation.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysIrrigationAreaType;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysIrrigationAreaTypeService
 * @Description: 灌溉区域service接口
 * @Author: zhaojx
 */
public interface SysIrrigationAreaTypeService{

	Integer getCount(Map<String, Object> params);

	List<SysIrrigationAreaType> getList(Map<String, Object> params);
	
	int deleteByPrimaryKey(String id);

	int insert(SysIrrigationAreaType record);

	int insertSelective(SysIrrigationAreaType record);
	
	SysIrrigationAreaType selectByPrimaryKey(String id);
	
	SysIrrigationAreaType findById(String Id);
	
	String add(SysIrrigationAreaType model);
	
	AjaxJson deleteDis(String items);

	String delete(String id);
	
	String update(SysIrrigationAreaType sysIrrigationAreaType);
	
}