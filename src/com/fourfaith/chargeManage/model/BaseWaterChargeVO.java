package com.fourfaith.chargeManage.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * @ClassName: BaseWaterChargeVO
 * @Description: 基本水费导出报表实体
 * @Author: zhaojinxin
 * @Date: 2018年9月22日 上午10:37:24
 */
@ExcelTarget("BaseWaterChargeVO")
public class BaseWaterChargeVO{
	
	@Excel(name = "机井编码", orderNum = "1")
	private String deviceCode;
	@Excel(name = "机井名称", orderNum = "2")
 	private String deviceName;
	@Excel(name = "实灌面积(亩)", orderNum = "3", type = 10)
 	private BigDecimal sjArea;
	@Excel(name = "基本水费费率(亩/元)", orderNum = "4", type = 10)
 	private BigDecimal standardPrice;
	@Excel(name = "收费金额(元)", orderNum = "5", type = 10, isStatistics = true)
	private BigDecimal chargeAmount;
	@Excel(name = "是否缴费", orderNum = "6")
	private String isCharge;
	@Excel(name = "缴费方式", orderNum = "7")
	private String chargeMode;
	@Excel(name = "缴费时间", orderNum = "8", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss")
	private Date editTime;
	
	public BaseWaterChargeVO() {
		super();
	}

	public BaseWaterChargeVO(String deviceCode, String deviceName,
			BigDecimal sjArea, BigDecimal standardPrice,
			BigDecimal chargeAmount, String isCharge, String chargeMode,
			Date editTime) {
		super();
		this.deviceCode = deviceCode;
		this.deviceName = deviceName;
		this.sjArea = sjArea;
		this.standardPrice = standardPrice;
		this.chargeAmount = chargeAmount;
		this.isCharge = isCharge;
		this.chargeMode = chargeMode;
		this.editTime = editTime;
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

	public BigDecimal getSjArea() {
		return sjArea;
	}

	public void setSjArea(BigDecimal sjArea) {
		this.sjArea = sjArea;
	}

	public BigDecimal getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public String getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(String isCharge) {
		this.isCharge = isCharge;
	}

	public String getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(String chargeMode) {
		this.chargeMode = chargeMode;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	
}