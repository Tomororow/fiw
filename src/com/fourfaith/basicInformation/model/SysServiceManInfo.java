package com.fourfaith.basicInformation.model;

import java.util.Date;

/**
 * @ClassName: SysServiceManInfo
 * @Description: 维修人员实体
 * @Author: zhaojinxin
 * @Date: 2019年3月7日 上午11:20:55
 */
public class SysServiceManInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = -7235539812218221663L;
	
	private String id;
	private String waterAreaId;
	private String waterAreaName;
    private String serviceMan;
    private String phone;
    private String duty;
    private String porary;
    private Date createTime;
    
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
	
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	public String getPorary() {
		return porary;
	}
	public void setPorary(String porary) {
		this.porary = porary;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getWaterAreaName() {
		return waterAreaName;
	}
	public void setWaterAreaName(String waterAreaName) {
		this.waterAreaName = waterAreaName;
	}
    
    
}