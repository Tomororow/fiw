package com.fourfaith.alarmManage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 故障设备实体
 * @author zzh
 * @date 2016年9月27日
 */
public class AlarmDeviceFault implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
    /** 设备编码 */
    private String deviceCode;
    /** 警报类型 */
    private Integer alarmType;
    /** 警报详情 */
    private String alarmDetail;
    /** 警报时间 */
    private Date alarmTime;
    /** 设备名称 */
    private String deviceName;
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public Integer getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}
	public String getAlarmDetail() {
		return alarmDetail;
	}
	public void setAlarmDetail(String alarmDetail) {
		this.alarmDetail = alarmDetail;
	}
	public Date getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
}