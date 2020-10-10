package com.fourfaith.statisticAnalysis.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.statisticAnalysis.dao.DeviceInfoMapper;
import com.fourfaith.statisticAnalysis.model.RptBaseDeviceDetail;
import com.fourfaith.statisticAnalysis.service.DeviceInfoService;

/**
 * @ClassName: DeviceInfoServiceImpl
 * @Description: 机井基础信息service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午6:12:32
 */
@Service("DeviceInfoService")
public class DeviceInfoServiceImpl implements DeviceInfoService{
	
	protected Logger logger = Logger.getLogger(DeviceInfoServiceImpl.class);

	@Autowired
	private DeviceInfoMapper deviceInfoMapper;

	/**
	 * 分页查询记录条数
	 * @param params
	 * 2016年11月6日
	 */
	public Integer getCount(Map<String, Object> params) {
		int result = deviceInfoMapper.getCount(params);
		return result;
	}

	/**
	 * 获取设备机井基础信息历史数据
	 * @param params
	 * 2016年11月6日
	 */
	public List<RptBaseDeviceDetail> getList(Map<String, Object> params) {
		return deviceInfoMapper.getList(params);
	}

	@Override
	public RptBaseDeviceDetail selectDeviceCodeByDetail(String deviceCode) {
		return deviceInfoMapper.selectDeviceCodeByDetail(deviceCode);
	}
	
}