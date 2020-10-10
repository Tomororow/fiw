package com.fourfaith.alarmManage.model;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class WarningRecordDetailExcel {
	@Excel(name="机井名称",orderNum="1",width=20)
	private String deviceName;
	@Excel(name="机井编码",orderNum="2",width=15)
    private String deviceCode;
	@Excel(name="机井负责人",orderNum="3",width=10)
    private String ownerName;
	@Excel(name="负责人电话",orderNum="4",width=15)
    private String ownerTelphone;
	@Excel(name="卡号",orderNum="5",width=15)
    private String cardCode;
	
    private String warnType;
	@Excel(name="预警详情",orderNum="7",width=30)
    private String warnDetail;
	@Excel(name="预警时间",orderNum="8",width=20)
    private String warnTime;
	@Excel(name="预警状态",orderNum="9",width=15)
    private String warnState;
	@Excel(name="预警处理人员",orderNum="10",width=15)
    private String warnHandler;
	@Excel(name="预警处理时间",orderNum="11",width=20)
    private String warnHandleTime;
	@Excel(name="预警类型",orderNum="6",width=15)
	private String wabnormaltype;
	
	public String getWabnormaltype() {
		return wabnormaltype;
	}
	public void setWabnormaltype(String wabnormaltype) {
		this.wabnormaltype = wabnormaltype;
	}
	public WarningRecordDetailExcel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WarningRecordDetailExcel(String deviceName, String deviceCode,
			String ownerName, String ownerTelphone, String cardCode,
			String warnType, String warnDetail, String warnTime,
			String warnState, String warnHandler, String warnHandleTime) {
		super();
		this.deviceName = deviceName;
		this.deviceCode = deviceCode;
		this.ownerName = ownerName;
		this.ownerTelphone = ownerTelphone;
		this.cardCode = cardCode;
		this.warnType = warnType;
		this.warnDetail = warnDetail;
		this.warnTime = warnTime;
		this.warnState = warnState;
		this.warnHandler = warnHandler;
		this.warnHandleTime = warnHandleTime;
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
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerTelphone() {
		return ownerTelphone;
	}
	public void setOwnerTelphone(String ownerTelphone) {
		this.ownerTelphone = ownerTelphone;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getWarnType() {
		return warnType;
	}
	public void setWarnType(String warnType) {
		this.warnType = warnType;
	}
	public String getWarnDetail() {
		return warnDetail;
	}
	public void setWarnDetail(String warnDetail) {
		this.warnDetail = warnDetail;
	}
	public String getWarnTime() {
		return warnTime;
	}
	public void setWarnTime(String warnTime) {
		this.warnTime = warnTime;
	}
	public String getWarnState() {
		return warnState;
	}
	public void setWarnState(String warnState) {
		this.warnState = warnState;
	}
	public String getWarnHandler() {
		return warnHandler;
	}
	public void setWarnHandler(String warnHandler) {
		this.warnHandler = warnHandler;
	}
	public String getWarnHandleTime() {
		return warnHandleTime;
	}
	public void setWarnHandleTime(String warnHandleTime) {
		this.warnHandleTime = warnHandleTime;
	}
	
}
