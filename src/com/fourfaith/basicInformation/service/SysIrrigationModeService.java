package com.fourfaith.basicInformation.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysIrrigationMode;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysIrrigationModeService
 * @Description: 灌溉类型service接口
 * @Author: zhaojx
 */
public interface SysIrrigationModeService{

	Integer getCount(Map<String, Object> params);

	List<SysIrrigationMode> getList(Map<String, Object> params);
	
	int deleteByPrimaryKey(String id);

	int insert(SysIrrigationMode record);

	int insertSelective(SysIrrigationMode record);
	
	SysIrrigationMode selectByPrimaryKey(String id);
	
	SysIrrigationMode findById(String Id);
	
	String add(SysIrrigationMode model);
	
	AjaxJson deleteDis(String items);

	String delete(String id);
	
	String update(SysIrrigationMode sysIrrigationMode);
	
}