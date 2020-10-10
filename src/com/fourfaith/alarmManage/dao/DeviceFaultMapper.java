package com.fourfaith.alarmManage.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.alarmManage.model.AlarmDeviceFault;
import com.fourfaith.alarmManage.model.ThreeVoltageAlarmExportVO;
import com.fourfaith.alarmManage.model.ThreeVoltageAlarmVO;

/**
 * @ClassName: DeviceFaultMapper
 * @Description: 设备故障dao接口
 * @Author: zhaojx
 * @Date: 2018年5月9日 上午9:35:45
 */
public interface DeviceFaultMapper {
	
	/**
	 * @Title: getList
	 * @Description: 获取故障设备列表
	 * @param: @param params
	 * @return: List<AlarmDeviceFault>
	 */
	List<AlarmDeviceFault> getList(Map<String, Object> params);
	
	/**
	 * @Title: getCount
	 * @Description: 统计设备数量
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getCount(Map<String, Object> params);
	
	/**
     * @Title: getThreeVoltageAlarmList
     * @Description: 三相电压告警信息
     * @param: @param params
     * @return: List<ThreeVoltageAlarmVO>
     */
    List<ThreeVoltageAlarmVO> getThreeVoltageAlarmList(Map<String, Object> params);
    
    /**
     * @Title: getVoltageAlarmCount
     * @Description: 统计三相电压告警信息条数
     * @param: @param params
     * @return: Integer
     */
    Integer getVoltageAlarmCount(Map<String, Object> params);
    
    /**
     * @Title: getThreeVoltageAlarmExportList
     * @Description: 三相电压告警信息导出
     * @param: @param params
     * @return: List<ThreeVoltageAlarmVO>
     */
    List<ThreeVoltageAlarmExportVO> getThreeVoltageAlarmExportList(Map<String, Object> params);
	
}