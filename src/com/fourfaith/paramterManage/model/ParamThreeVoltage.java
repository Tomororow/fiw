package com.fourfaith.paramterManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 三相电压参数设置
 * @author Administrator
 * 2016年11月26日
 */
public class ParamThreeVoltage implements Serializable{
	
	private static final long serialVersionUID = 927524987016193179L;

	private String id;                     // 主键ID
	private BigDecimal aPhaseVoltageUp;    // A相电压上限
	private BigDecimal aPhaseVoltageDown;  // A相电压下限
	private BigDecimal bPhaseVoltageUp;    // B相电压上限
	private BigDecimal bPhaseVoltageDown;  // B相电压下限
	private BigDecimal cPhaseVoltageUp;    // C相电压上限
	private BigDecimal cPhaseVoltageDown;  // C相电压下限
	private Date createTime;               // 创建时间
	private Date editTime;                 // 修改时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getaPhaseVoltageUp() {
		return aPhaseVoltageUp;
	}
	public void setaPhaseVoltageUp(BigDecimal aPhaseVoltageUp) {
		this.aPhaseVoltageUp = aPhaseVoltageUp;
	}
	public BigDecimal getaPhaseVoltageDown() {
		return aPhaseVoltageDown;
	}
	public void setaPhaseVoltageDown(BigDecimal aPhaseVoltageDown) {
		this.aPhaseVoltageDown = aPhaseVoltageDown;
	}
	public BigDecimal getbPhaseVoltageUp() {
		return bPhaseVoltageUp;
	}
	public void setbPhaseVoltageUp(BigDecimal bPhaseVoltageUp) {
		this.bPhaseVoltageUp = bPhaseVoltageUp;
	}
	public BigDecimal getbPhaseVoltageDown() {
		return bPhaseVoltageDown;
	}
	public void setbPhaseVoltageDown(BigDecimal bPhaseVoltageDown) {
		this.bPhaseVoltageDown = bPhaseVoltageDown;
	}
	public BigDecimal getcPhaseVoltageUp() {
		return cPhaseVoltageUp;
	}
	public void setcPhaseVoltageUp(BigDecimal cPhaseVoltageUp) {
		this.cPhaseVoltageUp = cPhaseVoltageUp;
	}
	public BigDecimal getcPhaseVoltageDown() {
		return cPhaseVoltageDown;
	}
	public void setcPhaseVoltageDown(BigDecimal cPhaseVoltageDown) {
		this.cPhaseVoltageDown = cPhaseVoltageDown;
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

}