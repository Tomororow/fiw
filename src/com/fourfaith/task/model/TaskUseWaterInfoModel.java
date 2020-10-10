package com.fourfaith.task.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 定时任务记录设备最新用水量信息表
 * @author Administrator
 * 2017年2月24日
 */
public class TaskUseWaterInfoModel implements Serializable{
	
	private static final long serialVersionUID = -2522011616046919795L;

	private String id;               // 主键ID
	private String deviceCode;       // 设备编码
	private BigDecimal useWater;     // 当前用水量
	private BigDecimal remainWater;  // 剩余水量
	private Date CreateTime;         // 创建时间
	private Date EditTime;           // 修改时间
	
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
	public BigDecimal getUseWater() {
		return useWater;
	}
	public void setUseWater(BigDecimal useWater) {
		this.useWater = useWater;
	}
	public BigDecimal getRemainWater() {
		return remainWater;
	}
	public void setRemainWater(BigDecimal remainWater) {
		this.remainWater = remainWater;
	}
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	public Date getEditTime() {
		return EditTime;
	}
	public void setEditTime(Date editTime) {
		EditTime = editTime;
	}
	
}