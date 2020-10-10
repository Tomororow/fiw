package com.fourfaith.statisticAnalysis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: UseWaterDataOfYear
 * @Description: 获取机井年统计用水量 (从真实用水量表提取)
 * @Author: zhaojinxin
 * @Date: 2018年12月4日
 */
public class UseWaterDataOfYear implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;               		// 主键ID
	private String deviceCode;       		// 设备编号
	private String cardCode;         		// 卡编号
	private BigDecimal useWaterOfYear;      // 用水量
	private Date openPumpTime;       		// 开泵(通电)时间
	private Date stopPumpTime;      		// 关泵(采集)时间
	private Integer inYear;          		// 入库年
	private Date createTime;         		// 创建时间
	private Date assTime;					//组合时间
	private String deviceName;       		// 设备名称
	private String ownerName;       		// 业主姓名
	private String ownerTelphone;    		// 业主电话
	
	private BigDecimal remainWater;			// 新加剩余水量
	private BigDecimal sumWater;
	
	public Date getAssTime() {
		return assTime;
	}
	public void setAssTime(Date assTime) {
		this.assTime = assTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public BigDecimal getUseWaterOfYear() {
		return useWaterOfYear;
	}
	public void setUseWaterOfYear(BigDecimal useWaterOfYear) {
		this.useWaterOfYear = useWaterOfYear;
	}
	public Date getOpenPumpTime() {
		return openPumpTime;
	}
	public void setOpenPumpTime(Date openPumpTime) {
		this.openPumpTime = openPumpTime;
	}
	public Date getStopPumpTime() {
		return stopPumpTime;
	}
	public void setStopPumpTime(Date stopPumpTime) {
		this.stopPumpTime = stopPumpTime;
	}
	public Integer getInYear() {
		return inYear;
	}
	public void setInYear(Integer inYear) {
		this.inYear = inYear;
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
	public BigDecimal getRemainWater() {
		return remainWater;
	}
	public void setRemainWater(BigDecimal remainWater) {
		this.remainWater = remainWater;
	}
	public BigDecimal getSumWater() {
		return sumWater;
	}
	public void setSumWater(BigDecimal sumWater) {
		this.sumWater = sumWater;
	}
	
}