package com.fourfaith.sysManage.service.impl;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.sysManage.dao.BaseDeviceModelMapper;
import com.fourfaith.sysManage.model.BaseDeviceModel;
import com.fourfaith.sysManage.service.BaseDeviceModelService;

/**
 * @ClassName: BaseDeviceModelServiceImpl
 * @Description:  设备型号service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:38:47
 */
@Service("BaseDeviceModelService")
public class BaseDeviceModelServiceImpl implements BaseDeviceModelService {
	
	protected Logger logger = Logger.getLogger(BaseDeviceModelServiceImpl.class);
	
	@Autowired
	private BaseDeviceModelMapper baseDeviceModelMapper;
	
	@Override
	public List<BaseDeviceModel> getList() {
		return baseDeviceModelMapper.getList();
	}
	
}