package com.fourfaith.statisticAnalysis.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * @ClassName: RptChargeDetailVO
 * @Description: 水卡充值记录报表导出实体
 * @Author: zhaojx
 * @Date: 2018年4月13日 下午3:29:14
 */
@ExcelTarget("rptChargeDetailVO")
public class RptChargeDetailVO{
	
	@Excel(name = "机井编码", orderNum = "1")
	private String deviceCode;
	
	@Excel(name = "机井名称", orderNum = "2")
	private String deviceName;
	
	@Excel(name = "水卡卡号", orderNum = "3")
	private String cardCode;
	
	@Excel(name = "购买水量(吨)", orderNum = "4", type = 10, isStatistics = true)
	private BigDecimal waterAmount;
	
	@Excel(name = "充值总金额(元)", orderNum = "5", type = 10, isStatistics = true)
	private BigDecimal chargeAmount;
	
	@Excel(name = "充值单价(元)", orderNum = "6", type = 10)
	private BigDecimal unitPrice;
	
	@Excel(name = "充值时间", orderNum = "7", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	@Excel(name = "所属行政区域", orderNum = "8")
	private String areaName;
	
	@Excel(name = "所属水管区域", orderNum = "9")
	private String waterAreaName;
	
	@Excel(name = "操作员", orderNum = "10")
	private String operator;

	public RptChargeDetailVO() {
		super();
	}

	public RptChargeDetailVO(String deviceCode, String deviceName,
			String cardCode, BigDecimal waterAmount, BigDecimal chargeAmount,
			BigDecimal unitPrice, Date createTime, String areaName,
			String waterAreaName, String operator) {
		this.deviceCode = deviceCode;
		this.deviceName = deviceName;
		this.cardCode = cardCode;
		this.waterAmount = waterAmount;
		this.chargeAmount = chargeAmount;
		this.unitPrice = unitPrice;
		this.createTime = createTime;
		this.areaName = areaName;
		this.waterAreaName = waterAreaName;
		this.operator = operator;
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

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public BigDecimal getWaterAmount() {
		return waterAmount;
	}

	public void setWaterAmount(BigDecimal waterAmount) {
		this.waterAmount = waterAmount;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getWaterAreaName() {
		return waterAreaName;
	}

	public void setWaterAreaName(String waterAreaName) {
		this.waterAreaName = waterAreaName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}