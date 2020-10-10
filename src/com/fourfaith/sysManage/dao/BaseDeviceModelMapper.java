package com.fourfaith.sysManage.dao;

import java.util.List;

import com.fourfaith.sysManage.model.BaseDeviceModel;

/**
 * @ClassName: BaseDeviceModelMapper
 * @Description: 设备型号dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:07:58
 */
public interface BaseDeviceModelMapper {

	List<BaseDeviceModel> getList();
	
}