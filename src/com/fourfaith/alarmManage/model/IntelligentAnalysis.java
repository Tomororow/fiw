package com.fourfaith.alarmManage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: IntelligentAnalysis
 * @Description: 机井预警信息实体
 * @Author: zhaojinxin
 * @Date: 2019年2月27日
 */
public class IntelligentAnalysis implements Serializable{

	private static final long serialVersionUID = -2933167616312392396L;
	
	private String id;                    // 主键ID
	private String deviceCode;            // 设备编码
	private String deviceName;            // 设备名称
	private String intelligentException;  // 异常信息
	private Date createTime;              // 创建时间
	
	// 非数据库字段
	private Integer dealType;             // 处理类型0：电话处理；1：现场处理
	private Integer dealResult;           // 处理结果0：已解决；1：未解决；2待解决
	private String dealExceptionRemark;   // 异常信息描述
	private String dealRemark;            // 处理结果描述
	
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getDealType() {
		return dealType;
	}
	public void setDealType(Integer dealType) {
		this.dealType = dealType;
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
	
}