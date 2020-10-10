package com.fourfaith.basicInformation.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysUseWaterType;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysUseWaterTypeService
 * @Description: 取水类型service接口
 * @Author: zhaojx
 */
public interface SysUseWaterTypeService{
	
	Integer getCount(Map<String, Object> params);

	List<SysUseWaterType> getList(Map<String, Object> params);
	
	int deleteByPrimaryKey(String id);

	int insert(SysUseWaterType record);

	int insertSelective(SysUseWaterType record);
	
	SysUseWaterType selectByPrimaryKey(String id);
	
	SysUseWaterType findById(String Id);
	
	String add(SysUseWaterType model);
	
	AjaxJson deleteDis(String items);

	String delete(String id);
	
	String update(SysUseWaterType sysUseWaterType);
	
}