package com.fourfaith.paramterManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO: 功率参数实体
 * 2016年11月26日
 * Administrator: xiaogaoxiang
 */
public class ParamPower implements Serializable{
	
	private static final long serialVersionUID = 2029212507437309561L;

	private String id;                    // 主键id
	private BigDecimal powerPercentUp;    // 功率上限
	private BigDecimal powerPercentDown;  // 功率下限
	private Date CreateTime;              // 创建时间
	private Date EditTime;                // 修改时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getPowerPercentUp() {
		return powerPercentUp;
	}
	public void setPowerPercentUp(BigDecimal powerPercentUp) {
		this.powerPercentUp = powerPercentUp;
	}
	public BigDecimal getPowerPercentDown() {
		return powerPercentDown;
	}
	public void setPowerPercentDown(BigDecimal powerPercentDown) {
		this.powerPercentDown = powerPercentDown;
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