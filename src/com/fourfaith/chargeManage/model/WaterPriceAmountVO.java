package com.fourfaith.chargeManage.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * @ClassName: WaterSourceCharge
 * @Description: 基本水费-计量水费-水资源费-末级渠系水费统计报表实体
 * @Author: zhaojx
 * @Date: 2017年9月1日 下午2:49:11
 */
@ExcelTarget("WaterPriceAmountVO")
public class WaterPriceAmountVO{
	
	@Excel(name = "机井编码", orderNum = "1")
	private String deviceCode;
	@Excel(name = "机井名称", orderNum = "2")
	private String deviceName;
	@Excel(name = "已购水量(m³)", orderNum = "3", type = 10, isStatistics = true)
	private BigDecimal waterAmount;
	@Excel(name = "购水单价(元/m³)", orderNum = "4", type = 10)
	private BigDecimal unitPrice;
	@Excel(name = "购水总金额(元)", orderNum = "5", type = 10, isStatistics = true)
	private BigDecimal chargeAmount;
	@Excel(name = "计量水费费率(元/m³)", orderNum = "6", type = 10)
	private BigDecimal measureTypePrice;
	@Excel(name = "计量水费总金额(元)", orderNum = "7", type = 10, isStatistics = true)
	private BigDecimal measureTypeAmount;
	@Excel(name = "末级渠系费率(元/m³)", orderNum = "8", type = 10)
	private BigDecimal maintainPrice;
	@Excel(name = "末级渠系费总金额(元)", orderNum = "9", type = 10, isStatistics = true)
	private BigDecimal maintainAmount;
	@Excel(name = "水资源费费率(元/m³)", orderNum = "10", type = 10)
	private BigDecimal waterSourcePrice;
	@Excel(name = "水资源费总金额(元)", orderNum = "11", type = 10, isStatistics = true)
	private BigDecimal waterSourceAmount;
	@Excel(name = "缴费时间", orderNum = "12", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	public WaterPriceAmountVO() {
		super();
	}
	
	public WaterPriceAmountVO(String deviceCode, String deviceName,
			BigDecimal waterAmount, BigDecimal unitPrice,
			BigDecimal chargeAmount, BigDecimal measureTypePrice,
			BigDecimal measureTypeAmount, BigDecimal maintainPrice,
			BigDecimal maintainAmount, BigDecimal waterSourcePrice,
			BigDecimal waterSourceAmount, Date createTime) {
		super();
		this.deviceCode = deviceCode;
		this.deviceName = deviceName;
		this.waterAmount = waterAmount;
		this.unitPrice = unitPrice;
		this.chargeAmount = chargeAmount;
		this.measureTypePrice = measureTypePrice;
		this.measureTypeAmount = measureTypeAmount;
		this.maintainPrice = maintainPrice;
		this.maintainAmount = maintainAmount;
		this.waterSourcePrice = waterSourcePrice;
		this.waterSourceAmount = waterSourceAmount;
		this.createTime = createTime;
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

	public BigDecimal getWaterAmount() {
		return waterAmount;
	}

	public void setWaterAmount(BigDecimal waterAmount) {
		this.waterAmount = waterAmount;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public BigDecimal getMeasureTypePrice() {
		return measureTypePrice;
	}

	public void setMeasureTypePrice(BigDecimal measureTypePrice) {
		this.measureTypePrice = measureTypePrice;
	}

	public BigDecimal getMeasureTypeAmount() {
		return measureTypeAmount;
	}

	public void setMeasureTypeAmount(BigDecimal measureTypeAmount) {
		this.measureTypeAmount = measureTypeAmount;
	}

	public BigDecimal getMaintainPrice() {
		return maintainPrice;
	}

	public void setMaintainPrice(BigDecimal maintainPrice) {
		this.maintainPrice = maintainPrice;
	}

	public BigDecimal getMaintainAmount() {
		return maintainAmount;
	}

	public void setMaintainAmount(BigDecimal maintainAmount) {
		this.maintainAmount = maintainAmount;
	}

	public BigDecimal getWaterSourcePrice() {
		return waterSourcePrice;
	}

	public void setWaterSourcePrice(BigDecimal waterSourcePrice) {
		this.waterSourcePrice = waterSourcePrice;
	}

	public BigDecimal getWaterSourceAmount() {
		return waterSourceAmount;
	}

	public void setWaterSourceAmount(BigDecimal waterSourceAmount) {
		this.waterSourceAmount = waterSourceAmount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}