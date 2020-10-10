package com.fourfaith.waterRightManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 水权管理表
 * @author zhaojx
 * @date 2017年10月08日
 */
public class BaseDistWaterPlan implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	/** 配水类型 */
    private Integer distType;
    /** 配水方式 */
    private Integer distMode;
    /** 配水年份 */
    private Integer distYear;
    /** 轮次 */
    private Integer distRound;
    /** 每亩地分配水量 */
    private BigDecimal distWater;
    /** 配水比例 */
    private Integer distRatio;
    /** 此轮配水价格 */
    private BigDecimal distPrice;
    /**创建时间*/
	private Date createTime;
	/**上次修改时间*/
	private Date editTime;
	
	// 追加数据字段
	/**所属机井Id**/
	private String baseDeviceId;
	/**操作人员**/
	private String operator;
	/**是否追加水量**/
	private Integer isAppendWater;
	/**水井用途**/
	private String wellUse;
	/**所选择水管区域ID**/
	private String waterAreaId;
	/**设备机井名称**/
	private String deviceName;
	/**配水水管区域名称**/
	private String waterAreaName;
	/**操作用户**/
	private String userName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Integer getDistType() {
		return distType;
	}
	public void setDistType(Integer distType) {
		this.distType = distType;
	}
	public Integer getDistMode() {
		return distMode;
	}
	public void setDistMode(Integer distMode) {
		this.distMode = distMode;
	}
	public String getBaseDeviceId() {
		return baseDeviceId;
	}
	public void setBaseDeviceId(String baseDeviceId) {
		this.baseDeviceId = baseDeviceId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
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
	public String getWaterAreaId() {
		return waterAreaId;
	}
	public void setWaterAreaId(String waterAreaId) {
		this.waterAreaId = waterAreaId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getWaterAreaName() {
		return waterAreaName;
	}
	public void setWaterAreaName(String waterAreaName) {
		this.waterAreaName = waterAreaName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}