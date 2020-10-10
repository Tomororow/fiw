package com.fourfaith.statisticAnalysis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO：水卡信息实体
 * @author Administrator
 * 2016年12月4日
 */
public class BaseCardInfo implements Serializable{

	private static final long serialVersionUID = 7804454064095518947L;

	private String id;                // 主键Id
	private String deviceId;          // 设备Id
	private String cardSerialNo;      // 卡序列号
	private Integer cardType;         // 卡类型 1：用户卡；2：管理卡；3：反写卡
	private String cardCode;          // 卡号
	private String ownerName;         // 持卡人姓名
	private String ownerTelphone;     // 持卡人电话
	private BigDecimal useWater;      // 最新用水量
	private BigDecimal totalWater;    // 累计用水量
	private BigDecimal usePower;      // 最新用电量
	private BigDecimal totalPower;    // 累计用电量
	private BigDecimal useMoney;      // 本次消费金额 单位分
	private BigDecimal totalMoney;    // 累计消费金额 单位分
	private BigDecimal remainWater;   // 剩余水量 单位吨
	private BigDecimal remainMoney;   // 剩余金额 单位分
	private BigDecimal executePrice;  // 执行水价
	private Date openPumpTime;        // 开泵时间
	private Date collectTime;         // 采集时间
	private Date createTime;          // 创建时间
	private Date editTime;            // 修改时间
	// 新加机井名称
	private String deviceName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getCardSerialNo() {
		return cardSerialNo;
	}
	public void setCardSerialNo(String cardSerialNo) {
		this.cardSerialNo = cardSerialNo;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
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
	public BigDecimal getUseWater() {
		return useWater;
	}
	public void setUseWater(BigDecimal useWater) {
		this.useWater = useWater;
	}
	public BigDecimal getTotalWater() {
		return totalWater;
	}
	public void setTotalWater(BigDecimal totalWater) {
		this.totalWater = totalWater;
	}
	public BigDecimal getUsePower() {
		return usePower;
	}
	public void setUsePower(BigDecimal usePower) {
		this.usePower = usePower;
	}
	public BigDecimal getTotalPower() {
		return totalPower;
	}
	public void setTotalPower(BigDecimal totalPower) {
		this.totalPower = totalPower;
	}
	public BigDecimal getUseMoney() {
		return useMoney;
	}
	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public BigDecimal getRemainWater() {
		return remainWater;
	}
	public void setRemainWater(BigDecimal remainWater) {
		this.remainWater = remainWater;
	}
	public BigDecimal getRemainMoney() {
		return remainMoney;
	}
	public void setRemainMoney(BigDecimal remainMoney) {
		this.remainMoney = remainMoney;
	}
	public BigDecimal getExecutePrice() {
		return executePrice;
	}
	public void setExecutePrice(BigDecimal executePrice) {
		this.executePrice = executePrice;
	}
	public Date getOpenPumpTime() {
		return openPumpTime;
	}
	public void setOpenPumpTime(Date openPumpTime) {
		this.openPumpTime = openPumpTime;
	}
	public Date getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
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
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
}