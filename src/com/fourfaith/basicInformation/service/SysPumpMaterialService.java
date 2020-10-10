package com.fourfaith.basicInformation.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysPumpMaterial;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysPumpMaterialService
 * @Description: 泵管材质service接口 
 * @Author: zhaojx
 */
public interface SysPumpMaterialService{

	Integer getCount(Map<String, Object> params);

	List<SysPumpMaterial> getList(Map<String, Object> params);
	
	int deleteByPrimaryKey(String id);

	int insert(SysPumpMaterial record);

	int insertSelective(SysPumpMaterial record);
	
	SysPumpMaterial selectByPrimaryKey(String id);
	
	SysPumpMaterial findById(String Id);
	
	String add(SysPumpMaterial model);
	
	AjaxJson deleteDis(String items);

	String delete(String id);
	
	String update(SysPumpMaterial sysPumpMaterial);
	
}