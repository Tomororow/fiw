package com.fourfaith.statisticAnalysis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: RptChargeDetail
 * @Description: 水卡充值记录实体
 * @Author: zhaojx
 * @Date: 2018年4月13日 下午3:29:14
 */
public class RptChargeDetail implements Serializable{

	private static final long serialVersionUID = -116859055351248345L;
	
	private String id;
	private String deviceCode;
	private String cardCode;
	private String distWaterPlanId;
	private int distYear;
	private int distRound;
	private BigDecimal chargeAmount;
	private BigDecimal waterAmount;
	private BigDecimal unitPrice;
	private String operator;
	private int chargeType;
	private String ipToken;
	private Date createTime;
	private String remark;
	
	// 非数据库字段
	// 设备名称
	private String deviceName;
    // 行政区域名称
	private String areaName;
	// 水管区域名称
	private String waterAreaName;
	
	
	
	public int getChargeType() {
		return chargeType;
	}
	public void setChargeType(int chargeType) {
		this.chargeType = chargeType;
	}
	
	public String getIpToken() {
		return ipToken;
	}
	public void setIpToken(String ipToken) {
		this.ipToken = ipToken;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
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
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getDistWaterPlanId() {
		return distWaterPlanId;
	}
	public void setDistWaterPlanId(String distWaterPlanId) {
		this.distWaterPlanId = distWaterPlanId;
	}
	public int getDistYear() {
		return distYear;
	}
	public void setDistYear(int distYear) {
		this.distYear = distYear;
	}
	public int getDistRound() {
		return distRound;
	}
	public void setDistRound(int distRound) {
		this.distRound = distRound;
	}
	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}
	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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
	
}