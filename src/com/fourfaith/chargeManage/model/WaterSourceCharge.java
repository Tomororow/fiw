package com.fourfaith.chargeManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: WaterSourceCharge
 * @Description: 基本水费-计量水费-水资源费-末级渠系水费统计实体
 * @Author: zhaojx
 * @Date: 2017年9月1日 下午2:49:11
 */
public class WaterSourceCharge implements Serializable{
	
	private static final long serialVersionUID = -7827706695690772265L;
    
	private String deviceCode;					// 机井编码 
	private String deviceName;					// 机井名称
	
	private BigDecimal waterAmount;				// 购水量
	private BigDecimal unitPrice;				// 充值单价
	private BigDecimal chargeAmount;			// 充值总金额
	
	private BigDecimal measureTypePrice;		// 计量水费费率
	private BigDecimal measureTypeAmount;		// 计量水费总金额
	
	private BigDecimal waterSourcePrice;	    // 水资源费费率
	private BigDecimal waterSourceAmount;		// 水资源费总金额
	
	private BigDecimal maintainPrice;			// 末级渠系费率(维修养护基金)
	private BigDecimal maintainAmount;			// 末级渠系费总金额
	
	private Date createTime;					// 收费时间

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}