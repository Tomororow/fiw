package com.fourfaith.sysManage.service;

import java.util.List;

import com.fourfaith.sysManage.model.BaseDeviceModel;

/**
 * @ClassName: BaseDeviceModelService
 * @Description: 设备型号service接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:27:28
 */
public interface BaseDeviceModelService{

	List<BaseDeviceModel> getList();
	
}