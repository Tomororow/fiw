package com.fourfaith.waterRightManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: BaseDistAppendWaterPlan
 * @Description: 阶梯性水量追加实体
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:55:39
 */
public class BaseDistAppendWaterPlan implements Serializable{

	private static final long serialVersionUID = 4321481869095285265L;

	private String id;             // 主键Id
	private String waterAreaId;    // 水管区域Id
	private Integer distType;      // 配水类型
	private Integer DistMode;      // 配水方式
	private Integer distYear;      // 配水年份
	private Integer distRound;     // 配水轮次
	private BigDecimal distWater;  // 分配水量
	private Integer distRatio;     // 配水比例
	private BigDecimal distPrice;  // 水量单价
	private Integer isAppendWater; // 是否阶梯性水量追加 0：否，1：是
	private String wellUse;        // 灌溉模式
	private String deviceId;       // 设备Id
	private String useId;          // 操作员Id
	private Date createTime;       // 创建时间
	private Date editTime;         // 修改时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWaterAreaId() {
		return waterAreaId;
	}
	public void setWaterAreaId(String waterAreaId) {
		this.waterAreaId = waterAreaId;
	}
	public Integer getDistType() {
		return distType;
	}
	public void setDistType(Integer distType) {
		this.distType = distType;
	}
	public Integer getDistMode() {
		return DistMode;
	}
	public void setDistMode(Integer distMode) {
		DistMode = distMode;
	}
	public Integer getDistYear() {
		return distYear;
	}
	public void setDistYear(Integer distYear) {
		this.distYear = distYear;
	}
	public Integer getDistRound() {
		return distRound;
	}
	public void setDistRound(Integer distRound) {
		this.distRound = distRound;
	}
	public BigDecimal getDistWater() {
		return distWater;
	}
	public void setDistWater(BigDecimal distWater) {
		this.distWater = distWater;
	}
	public Integer getDistRatio() {
		return distRatio;
	}
	public void setDistRatio(Integer distRatio) {
		this.distRatio = distRatio;
	}
	public BigDecimal getDistPrice() {
		return distPrice;
	}
	public void setDistPrice(BigDecimal distPrice) {
		this.distPrice = distPrice;
	}
	public Integer getIsAppendWater() {
		return isAppendWater;
	}
	public void setIsAppendWater(Integer isAppendWater) {
		this.isAppendWater = isAppendWater;
	}
	public String getWellUse() {
		return wellUse;
	}
	public void setWellUse(String wellUse) {
		this.wellUse = wellUse;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getUseId() {
		return useId;
	}
	public void setUseId(String useId) {
		this.useId = useId;
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