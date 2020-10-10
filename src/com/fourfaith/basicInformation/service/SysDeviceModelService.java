package com.fourfaith.basicInformation.service;

import java.util.List;
import java.util.Map;

import com.fourfaith.basicInformation.model.SysDeviceModel;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysDeviceModelService
 * @Description: 设备型号service接口
 * @Author: zhaojx
 */
public interface SysDeviceModelService{

	Integer getCount(Map<String, Object> params);

	List<SysDeviceModel> getList(Map<String, Object> params);
	
	int deleteByPrimaryKey(String id);

	int insert(SysDeviceModel record);

	int insertSelective(SysDeviceModel record);
	
	SysDeviceModel selectByPrimaryKey(String id);
	
	SysDeviceModel findById(String Id);
	
	String add(SysDeviceModel model);
	
	AjaxJson deleteDis(String items);

	String delete(String id);
	
	String update(SysDeviceModel sysDeviceModel);
	
}