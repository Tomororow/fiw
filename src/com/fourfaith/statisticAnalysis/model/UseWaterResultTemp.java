package com.fourfaith.statisticAnalysis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: UseWaterResultAmount
 * @Description: 每天用水量统计实体-临时表
 * @Author: zhaojinxin
 * @Date: 2018年11月8日 上午11:03:17
 */
public class UseWaterResultTemp implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;               		// 主键ID
	private String deviceCode;       		// 设备编号
	private String cardCode;         		// 卡编号
	private BigDecimal useWaterAmount;      // 用水量
	private Date openPumpTime;       		// 开泵(通电)时间
	private Date stopPumpTime;      		// 关泵(采集)时间
	private Integer inYear;          		// 入库年
	private Integer inMonth;         		// 入库月
	private Integer inDay;           		// 入库天
	private Date createTime;         		// 创建时间
	
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
	public BigDecimal getUseWaterAmount() {
		return useWaterAmount;
	}
	public void setUseWaterAmount(BigDecimal useWaterAmount) {
		this.useWaterAmount = useWaterAmount;
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
	public Integer getInMonth() {
		return inMonth;
	}
	public void setInMonth(Integer inMonth) {
		this.inMonth = inMonth;
	}
	public Integer getInDay() {
		return inDay;
	}
	public void setInDay(Integer inDay) {
		this.inDay = inDay;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}