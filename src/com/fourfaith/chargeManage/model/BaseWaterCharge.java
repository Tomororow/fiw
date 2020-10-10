package com.fourfaith.chargeManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: BaseWaterCharge
 * @Description: 基本水费实体
 * @Author: zhaojinxin
 * @Date: 2018年9月22日 上午10:37:33
 */
public class BaseWaterCharge implements Serializable{

	private static final long serialVersionUID = -7827706695690772265L;

	private String id;                // 主键Id
	private String waterAreaId;       // 水管区域Id
	private String baseDeviceId;      // 设备Id
	private BigDecimal chargeAmount;  // 收费金额
	private Integer chargeMode;       // 收费方式 1：现金 2：银行卡
	private Integer isCharge;         // 是否已缴费 0：未缴费； 1：已缴费 
	private Date createTime;          // 创建时间
	private Date editTime;            // 修改时间
	
	private String waterAreaName;     // 水管区域名称
	private String deviceCode;		  // 设备编码
 	private String deviceName;        // 设备名称
	private BigDecimal sjArea;        // 实际灌溉面积
	
	private String VillageCode; //村号
	private String HouseCode;//户号
	private String AreaName;//村名
	private String Xname;//户名
	private double createTotalMoney;//总购金额
	private int createChargeTimes;//充值次数
	private double balanc;//充值金额
	private double voltameter;//用户用电量
	private double waterYield;//用户用水量
	private String serialNumber;//流水号
	private String NetState;//网络转态
	
	
	public String getNetState() {
		return NetState;
	}
	public void setNetState(String netState) {
		NetState = netState;
	}
	public String getVillageCode() {
		return VillageCode;
	}
	public void setVillageCode(String villageCode) {
		VillageCode = villageCode;
	}
	public String getHouseCode() {
		return HouseCode;
	}
	public void setHouseCode(String houseCode) {
		HouseCode = houseCode;
	}
	public String getAreaName() {
		return AreaName;
	}
	public void setAreaName(String areaName) {
		AreaName = areaName;
	}
	public String getXname() {
		return Xname;
	}
	public void setXname(String xname) {
		Xname = xname;
	}
	public double getCreateTotalMoney() {
		return createTotalMoney;
	}
	public void setCreateTotalMoney(double createTotalMoney) {
		this.createTotalMoney = createTotalMoney;
	}
	public int getCreateChargeTimes() {
		return createChargeTimes;
	}
	public void setCreateChargeTimes(int createChargeTimes) {
		this.createChargeTimes = createChargeTimes;
	}
	public double getBalanc() {
		return balanc;
	}
	public void setBalanc(double balanc) {
		this.balanc = balanc;
	}
	public double getVoltameter() {
		return voltameter;
	}
	public void setVoltameter(double voltameter) {
		this.voltameter = voltameter;
	}
	public double getWaterYield() {
		return waterYield;
	}
	public void setWaterYield(double waterYield) {
		this.waterYield = waterYield;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWaterAreaId() {
		return waterAreaId;
	}
	public void setWaterAreaId(String waterAreaId) {
		this.waterAreaId = waterAreaId;
	}
	public String getBaseDeviceId() {
		return baseDeviceId;
	}
	public void setBaseDeviceId(String baseDeviceId) {
		this.baseDeviceId = baseDeviceId;
	}
	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}
	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}
	public Integer getChargeMode() {
		return chargeMode;
	}
	public void setChargeMode(Integer chargeMode) {
		this.chargeMode = chargeMode;
	}
	public Integer getIsCharge() {
		return isCharge;
	}
	public void setIsCharge(Integer isCharge) {
		this.isCharge = isCharge;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public String getWaterAreaName() {
		return waterAreaName;
	}
	public void setWaterAreaName(String waterAreaName) {
		this.waterAreaName = waterAreaName;
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
	
}