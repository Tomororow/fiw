package com.fourfaith.basicInformation.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysLandformType;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysLandformTypeService
 * @Description: 地貌类型service接口
 * @Author: zhaojx
 */
public interface SysLandformTypeService{

	Integer getCount(Map<String, Object> params);

	List<SysLandformType> getList(Map<String, Object> params);
	
	int deleteByPrimaryKey(String id);

	int insert(SysLandformType record);

	int insertSelective(SysLandformType record);
	
	SysLandformType selectByPrimaryKey(String id);
	
	SysLandformType findById(String Id);
	
	String add(SysLandformType model);
	
	AjaxJson deleteDis(String items);

	String delete(String id);
	
	String update(SysLandformType sysLandformType);
	
}