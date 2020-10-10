package com.fourfaith.statisticAnalysis.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * @ClassName: BaseCardInfoVO
 * @Description: 水卡信息导出实体
 * @Author: zhaojx
 * @Date: 2018年4月12日 下午6:02:04
 */
@ExcelTarget("baseCardInfoVO")
public class BaseCardInfoVO{

	@Excel(name = "机井名称", orderNum = "1")
	private String deviceName;
	
	@Excel(name = "水卡卡号", orderNum = "2")
	private String cardCode;
	
	@Excel(name = "持卡人姓名", orderNum = "3")
	private String ownerName;
	
	@Excel(name = "持卡人电话", orderNum = "4")
	private String ownerTelphone;
	
	@Excel(name = "卡累计用水量(m³)", orderNum = "5", type = 10)
	private BigDecimal totalWater;
	
	@Excel(name = "卡内剩余水量(m³)", orderNum = "6", type = 10)
	private BigDecimal remainWater;
	
	@Excel(name = "剩余金额(元)", orderNum = "7", type = 10)
	private BigDecimal remainMoney;
	
	@Excel(name = "执行水价(元)", orderNum = "8", type = 10)
	private BigDecimal executePrice;
	
	@Excel(name = "发卡日期", orderNum = "9", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	public BaseCardInfoVO() {
		super();
	}

	public BaseCardInfoVO(String deviceName, String cardCode, String ownerName,
			String ownerTelphone, BigDecimal totalWater,
			BigDecimal remainWater, BigDecimal remainMoney,
			BigDecimal executePrice, Date createTime) {
		super();
		this.deviceName = deviceName;
		this.cardCode = cardCode;
		this.ownerName = ownerName;
		this.ownerTelphone = ownerTelphone;
		this.totalWater = totalWater;
		this.remainWater = remainWater;
		this.remainMoney = remainMoney;
		this.executePrice = executePrice;
		this.createTime = createTime;
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

	public BigDecimal getTotalWater() {
		return totalWater;
	}

	public void setTotalWater(BigDecimal totalWater) {
		this.totalWater = totalWater;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}