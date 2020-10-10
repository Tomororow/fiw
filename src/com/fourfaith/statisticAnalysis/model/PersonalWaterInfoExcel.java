package com.fourfaith.statisticAnalysis.model;

import java.math.BigDecimal;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class PersonalWaterInfoExcel {

	@Excel(name="机井名称",orderNum="1",width=20)
	private String deviceName;
	@Excel(name="机井编码",orderNum="2",width=20)
	private String deviceCode;
	@Excel(name="用水类型",orderNum="3",width=15)
	private String wellUse;
	@Excel(name="已用水量",orderNum="4",width=15)
	private String useWater;
	@Excel(name="剩余水量",orderNum="5",width=15)
	private String remainWater;
	@Excel(name="上报时间",orderNum="6",width=20)
	private String createTime;
	public PersonalWaterInfoExcel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PersonalWaterInfoExcel(String deviceName, String deviceCode,
			String wellUse, String useWater, String remainWater,
			String createTime) {
		super();
		this.deviceName = deviceName;
		this.deviceCode = deviceCode;
		this.wellUse = wellUse;
		this.useWater = useWater;
		this.remainWater = remainWater;
		this.createTime = createTime;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getWellUse() {
		return wellUse;
	}
	public void setWellUse(String wellUse) {
		this.wellUse = wellUse;
	}
	public String getUseWater() {
		return useWater;
	}
	public void setUseWater(String useWater) {
		this.useWater = useWater;
	}
	public String getRemainWater() {
		return remainWater;
	}
	public void setRemainWater(String remainWater) {
		this.remainWater = remainWater;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
}
