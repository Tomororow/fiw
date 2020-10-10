package com.fourfaith.sysManage.model;

import java.math.BigDecimal;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 水管区域表
 * @author  Dan
 * @date 2016-10-08 21:21:40
 * @version V1.0   
 */
public class SysWaterArea implements java.io.Serializable {
	
	private static final long serialVersionUID = 5565690551262371296L;
	
	/**标识*/
	private String id;
	/**水管区域编码*/
	private String waterAreaCode;
	/**水管区域名称*/
	private String waterAreaName;
	/**水管区域级别*/
	private java.lang.Integer waterAreaLevel;
	/**所属父水管区域Id*/
	private String parentWaterAreaId;
	/**所属父水管区域名称*/
	private String parentWaterAreaName;
	/**创建时间*/
	private Date createTime;
	/**上次修改时间*/
	private Date editTime;
	/**备注*/
	private String remark;

	private BigDecimal sumWater;

	private BigDecimal waterFarming;

	private BigDecimal waterVirest;

	private BigDecimal waterLife;

	private BigDecimal waterIndustry;

	/**水管区域标识集合(非数据库字段)**/
	private String waterAreaIds;

	public BigDecimal getSumWater() {
		return sumWater;
	}

	public void setSumWater(BigDecimal sumWater) {
		this.sumWater = sumWater;
	}

	public BigDecimal getWaterFarming() {
		return waterFarming;
	}

	public void setWaterFarming(BigDecimal waterFarming) {
		this.waterFarming = waterFarming;
	}

	public BigDecimal getWaterVirest() {
		return waterVirest;
	}

	public void setWaterVirest(BigDecimal waterVirest) {
		this.waterVirest = waterVirest;
	}

	public BigDecimal getWaterLife() {
		return waterLife;
	}

	public void setWaterLife(BigDecimal waterLife) {
		this.waterLife = waterLife;
	}

	public BigDecimal getWaterIndustry() {
		return waterIndustry;
	}

	public void setWaterIndustry(BigDecimal waterIndustry) {
		this.waterIndustry = waterIndustry;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWaterAreaCode() {
		return waterAreaCode;
	}
	public void setWaterAreaCode(String waterAreaCode) {
		this.waterAreaCode = waterAreaCode;
	}
	public String getWaterAreaName() {
		return waterAreaName;
	}
	public void setWaterAreaName(String waterAreaName) {
		this.waterAreaName = waterAreaName;
	}
	public java.lang.Integer getWaterAreaLevel() {
		return waterAreaLevel;
	}
	public void setWaterAreaLevel(java.lang.Integer waterAreaLevel) {
		this.waterAreaLevel = waterAreaLevel;
	}
	public String getParentWaterAreaId() {
		return parentWaterAreaId;
	}
	public void setParentWaterAreaId(String parentWaterAreaId) {
		this.parentWaterAreaId = parentWaterAreaId;
	}
	public String getParentWaterAreaName() {
		return parentWaterAreaName;
	}
	public void setParentWaterAreaName(String parentWaterAreaName) {
		this.parentWaterAreaName = parentWaterAreaName;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWaterAreaIds() {
		return waterAreaIds;
	}
	public void setWaterAreaIds(String waterAreaIds) {
		this.waterAreaIds = waterAreaIds;
	}
	
}