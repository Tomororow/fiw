package com.fourfaith.waterRightManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: BaseDistWaterPlanDevice
 * @Description: 配水计划对应机井实体
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:56:19
 */
public class BaseDistWaterPlanDevice implements Serializable{

	private static final long serialVersionUID = 1L;

	/**配水机井设备Id**/
	private String id;
	/**配水Id**/
	private String baseDistWaterId;
	/**机井设备Id**/
	private String baseDeviceId;
	/**操作人员Id**/
	private String userId;
	/**创建时间**/
	private Date createTime;
	/**修改时间**/
	private Date editTime;
	
	/**非数据库字段**/
	/**机井名称**/
	private String deviceName;
	/**配水年份**/
	private String distYear;
	/**配水轮次**/
	private String distRound;
	/**配水水量**/
	private String distWater;
	/**工业年核定用水量**/
	private Integer industryApprovedWater;
	/**实际灌溉面积**/
	private BigDecimal sjArea;
	
	//充值时间的创建时间
	private Date createTime1;
	
	
	
	public Date getCreateTime1() {
		return createTime1;
	}
	public void setCreateTime1(Date createTime1) {
		this.createTime1 = createTime1;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBaseDistWaterId() {
		return baseDistWaterId;
	}
	public void setBaseDistWaterId(String baseDistWaterId) {
		this.baseDistWaterId = baseDistWaterId;
	}
	public String getBaseDeviceId() {
		return baseDeviceId;
	}
	public void setBaseDeviceId(String baseDeviceId) {
		this.baseDeviceId = baseDeviceId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDistYear() {
		return distYear;
	}
	public void setDistYear(String distYear) {
		this.distYear = distYear;
	}
	public String getDistRound() {
		return distRound;
	}
	public void setDistRound(String distRound) {
		this.distRound = distRound;
	}
	public String getDistWater() {
		return distWater;
	}
	public void setDistWater(String distWater) {
		this.distWater = distWater;
	}
	public Integer getIndustryApprovedWater() {
		return industryApprovedWater;
	}
	public void setIndustryApprovedWater(Integer industryApprovedWater) {
		this.industryApprovedWater = industryApprovedWater;
	}
	public BigDecimal getSjArea() {
		return sjArea;
	}
	public void setSjArea(BigDecimal sjArea) {
		this.sjArea = sjArea;
	}
	
}