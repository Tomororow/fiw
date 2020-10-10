package com.fourfaith.paramterManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO: 水资源费参数实体
 * @author Administrator
 * 2016年12月5日
 */
public class ParamWaterSourcePrice implements Serializable{

	private static final long serialVersionUID = -7471603789093166718L;

	private String id;                    // 主键Id
	private Integer distType;             // 水井类型1：灌溉；2：工业；3：生活
	private BigDecimal waterSourcePrice;  // 水资源费用
	private Integer inYear;               // 征收年份
	private Date createTime;              // 创建时间
	private Date EditTime;                // 修改时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getDistType() {
		return distType;
	}
	public void setDistType(Integer distType) {
		this.distType = distType;
	}
	public BigDecimal getWaterSourcePrice() {
		return waterSourcePrice;
	}
	public void setWaterSourcePrice(BigDecimal waterSourcePrice) {
		this.waterSourcePrice = waterSourcePrice;
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
	public Date getEditTime() {
		return EditTime;
	}
	public void setEditTime(Date editTime) {
		EditTime = editTime;
	}
	
}