package com.fourfaith.alarmManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO: 智能机井用水分析实体(最长用水量不变天数)
 * @author Administrator
 * 2017年1月10日
 * Administrator: xiaogaoxiang
 */
public class IntelligentUseWater implements Serializable{

	private static final long serialVersionUID = -6881765578338891306L;

	private String Id;              // 主键ID
	private String DeviceCode;      // 设备编号
	private BigDecimal TotalWater;  // 累计用水量
	private Date CreateTime;        // 创建时间
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getDeviceCode() {
		return DeviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		DeviceCode = deviceCode;
	}
	public BigDecimal getTotalWater() {
		return TotalWater;
	}
	public void setTotalWater(BigDecimal totalWater) {
		TotalWater = totalWater;
	}
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

}