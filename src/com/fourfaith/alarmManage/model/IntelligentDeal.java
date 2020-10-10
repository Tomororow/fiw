package com.fourfaith.alarmManage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: IntelligentDeal
 * @Description: 预警信息处理实体
 * @Author: zhaojinxin 修改
 * @Date: 2019年3月10日
 */
public class IntelligentDeal implements Serializable{
	
	private static final long serialVersionUID = -5781213797103323647L;

	private String id;                     // 主键自增长Id
	private String intelligentAnalysisId;  // 异常机井智能分析表Id
	private Integer dealType;              // 处理方式0：电话处理；1：现场处理
	private String serviceManId;           // 技术员Id
	private Integer dealResult;            // 处理结果0：已解决；1：未解决；2：待解决
	private String dealExceptionRemark;    // 异常信息描述
	private String dealRemark;             // 解决方式描述
	private Date fixTime;			       // 报修时间
	private Integer isOverTime;			   // 是否超时
	private Double handleTime;			   // 预计处理时间
	private Date dealTime;				   // 预警故障解决时间
	private Integer overTime;			   // 超时时长
	private String overTimeRemark;         // 超时备注
	private Date createTime;               // 创建时间
	private Date editTime;                 // 修改时间
	
	// 组装VO实体字段
	private String deviceCode;             // 设备编码
	private String deviceName;             // 设备名称
	private String intelligentException;   // 异常信息
	private String serviceMan;             // 技术员姓名
	private String phone; 				   // 技术员电话
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIntelligentAnalysisId() {
		return intelligentAnalysisId;
	}
	public void setIntelligentAnalysisId(String intelligentAnalysisId) {
		this.intelligentAnalysisId = intelligentAnalysisId;
	}
	public Integer getDealType() {
		return dealType;
	}
	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}
	public String getServiceManId() {
		return serviceManId;
	}
	public void setServiceManId(String serviceManId) {
		this.serviceManId = serviceManId;
	}
	public Integer getDealResult() {
		return dealResult;
	}
	public void setDealResult(Integer dealResult) {
		this.dealResult = dealResult;
	}
	public String getDealExceptionRemark() {
		return dealExceptionRemark;
	}
	public void setDealExceptionRemark(String dealExceptionRemark) {
		this.dealExceptionRemark = dealExceptionRemark;
	}
	public String getDealRemark() {
		return dealRemark;
	}
	public void setDealRemark(String dealRemark) {
		this.dealRemark = dealRemark;
	}
	public Date getFixTime() {
		return fixTime;
	}
	public void setFixTime(Date fixTime) {
		this.fixTime = fixTime;
	}
	public Integer getIsOverTime() {
		return isOverTime;
	}
	public void setIsOverTime(Integer isOverTime) {
		this.isOverTime = isOverTime;
	}
	public Double getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Double handleTime) {
		this.handleTime = handleTime;
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public Integer getOverTime() {
		return overTime;
	}
	public void setOverTime(Integer overTime) {
		this.overTime = overTime;
	}
	public String getOverTimeRemark() {
		return overTimeRemark;
	}
	public void setOverTimeRemark(String overTimeRemark) {
		this.overTimeRemark = overTimeRemark;
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
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getIntelligentException() {
		return intelligentException;
	}
	public void setIntelligentException(String intelligentException) {
		this.intelligentException = intelligentException;
	}
	public String getServiceMan() {
		return serviceMan;
	}
	public void setServiceMan(String serviceMan) {
		this.serviceMan = serviceMan;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}