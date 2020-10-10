package com.fourfaith.alarmManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: ThreeVoltageAlarmVO
 * @Description: 三相电压告警实体
 * @Author: zhaojx
 * @Date: 2018年7月14日 下午4:45:21
 */
public class ThreeVoltageAlarmVO implements Serializable{

	private static final long serialVersionUID = 1661705610259161643L;

	private String deviceCode;				// 机井编码
	private String deviceName;				// 机井名称
	private BigDecimal aPhaseVoltage;		// A相电压
	private BigDecimal bPhaseVoltage;		// B相电压
	private BigDecimal cPhaseVoltage;		// C相电压
	private Date collectTime;				// 采集时间
	
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public BigDecimal getaPhaseVoltage() {
		return aPhaseVoltage;
	}
	public void setaPhaseVoltage(BigDecimal aPhaseVoltage) {
		this.aPhaseVoltage = aPhaseVoltage;
	}
	public BigDecimal getbPhaseVoltage() {
		return bPhaseVoltage;
	}
	public void setbPhaseVoltage(BigDecimal bPhaseVoltage) {
		this.bPhaseVoltage = bPhaseVoltage;
	}
	public BigDecimal getcPhaseVoltage() {
		return cPhaseVoltage;
	}
	public void setcPhaseVoltage(BigDecimal cPhaseVoltage) {
		this.cPhaseVoltage = cPhaseVoltage;
	}
	public Date getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}
	
}