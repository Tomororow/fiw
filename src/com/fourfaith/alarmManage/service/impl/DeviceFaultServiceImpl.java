package com.fourfaith.alarmManage.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.alarmManage.dao.DeviceFaultMapper;
import com.fourfaith.alarmManage.model.AlarmDeviceFault;
import com.fourfaith.alarmManage.model.ThreeVoltageAlarmExportVO;
import com.fourfaith.alarmManage.model.ThreeVoltageAlarmVO;
import com.fourfaith.alarmManage.service.DeviceFaultService;

/**
 * @ClassName: DeviceFaultServiceImpl
 * @Description: 故障设备service实现类
 * @Author: zhaojx
 * @Date: 2018年5月9日 上午11:16:06
 */
@Service("DeviceFaultService")
public class DeviceFaultServiceImpl implements DeviceFaultService {
	
	protected Logger logger = Logger.getLogger(DeviceFaultServiceImpl.class);
	
	@Autowired
	private DeviceFaultMapper deviceFaultMapper;

	/**
	 * 获取故障设备列表
	 */
	@Override
	public List<AlarmDeviceFault> getList(Map<String, Object> params) {
		return deviceFaultMapper.getList(params);
	}
	
	/**
	 * 统计故障设备信息数量
	 */
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = deviceFaultMapper.getCount(params);
		return result;
	}

	/**
	 * 三相电压告警信息
	 */
	@Override
	public List<ThreeVoltageAlarmVO> getThreeVoltageAlarmList(
			Map<String, Object> params) {
		return deviceFaultMapper.getThreeVoltageAlarmList(params);
	}

	/**
	 * 统计三相电压告警信息条数
	 */
	@Override
	public Integer getVoltageAlarmCount(Map<String, Object> params) {
		return deviceFaultMapper.getVoltageAlarmCount(params);
	}

	/**
	 * 三相电压告警信息导出
	 */
	@Override
	public List<ThreeVoltageAlarmExportVO> getThreeVoltageAlarmExportList(
			Map<String, Object> params) {
		return deviceFaultMapper.getThreeVoltageAlarmExportList(params);
	}
	
}