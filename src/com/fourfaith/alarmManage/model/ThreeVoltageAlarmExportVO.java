package com.fourfaith.alarmManage.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * @ClassName: ThreeVoltageAlarmExportVO
 * @Description: 三相电压告警信息导出实体
 * @Author: zhaojx
 * @Date: 2018年7月14日 下午4:45:21
 */
@ExcelTarget("ThreeVoltageAlarmExportVO")
public class ThreeVoltageAlarmExportVO{

	@Excel(name = "机井编码", orderNum = "1")
	private String deviceCode;
	
	@Excel(name = "机井名称", orderNum = "2")
	private String deviceName;
	
	@Excel(name = "告警时间", orderNum = "3", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss")
	private Date collectTime;
	
	@Excel(name = "A相电压(V)", orderNum = "4", type = 10)
	private BigDecimal aPhaseVoltage;
	
	@Excel(name = "B相电压(V)", orderNum = "5", type = 10)
	private BigDecimal bPhaseVoltage;
	
	@Excel(name = "C相电压(V)", orderNum = "6", type = 10)
	private BigDecimal cPhaseVoltage;

	public ThreeVoltageAlarmExportVO() {
		super();
	}

	public ThreeVoltageAlarmExportVO(String deviceCode, String deviceName,
			Date collectTime, BigDecimal aPhaseVoltage,
			BigDecimal bPhaseVoltage, BigDecimal cPhaseVoltage) {
		this.deviceCode = deviceCode;
		this.deviceName = deviceName;
		this.collectTime = collectTime;
		this.aPhaseVoltage = aPhaseVoltage;
		this.bPhaseVoltage = bPhaseVoltage;
		this.cPhaseVoltage = cPhaseVoltage;
	}

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

	public Date getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
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
	
}