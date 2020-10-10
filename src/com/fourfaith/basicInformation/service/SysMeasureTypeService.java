package com.fourfaith.basicInformation.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysMeasureType;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysMeasureTypeService
 * @Description: 计量设施类型service接口
 * @Author: zhaojx
 */
public interface SysMeasureTypeService{

	Integer getCount(Map<String, Object> params);

	List<SysMeasureType> getList(Map<String, Object> params);
	
	int deleteByPrimaryKey(String id);

	int insert(SysMeasureType record);

	int insertSelective(SysMeasureType record);
	
	SysMeasureType selectByPrimaryKey(String id);
	
	SysMeasureType findById(String Id);
	
	String add(SysMeasureType model);
	
	AjaxJson deleteDis(String items);

	String delete(String id);
	
	String update(SysMeasureType sysMeasureType);
	
}