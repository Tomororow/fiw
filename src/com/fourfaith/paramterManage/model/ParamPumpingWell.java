package com.fourfaith.paramterManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @ClassName: ParamPumpingWell
 * @Description: 机井不在线告警信息实体
 * @Author: zhaojx
 * @Date: 2017年5月13日 下午6:03:55
 */
public class ParamPumpingWell implements Serializable{

	private static final long serialVersionUID = -3694300077287874278L;

    private Integer id;

    private String waterareaid;

    private String devicecode;

    private String welluse;

    private Date startTime;
    
    private Date endTime;

    private Integer onnetstate;

    private int waterlow;

    private Integer warningstate;

    private String warningcontent;

    private Date createtime;
    
    private Date edittime;
    
    private String remark;

    private String backone;

    private String backtwo;

    private String backthree;

    private String backfour;

    private String backfive;
    


	/**水井用途分类*/
	private String deviceWellUse;
	private String waterAreaName; // 水管区域名
	private String baseDeviceId;  // 设备Id
	private String deviceName;
	private String deviceCode;
	
	private Date CommTime;
	private BigDecimal UseWater;
	private String OwnerName;
	private String OwnerTelphone;
	
	private BigDecimal leaveWater;
	
	
	
	public BigDecimal getLeaveWater() {
		return leaveWater;
	}
	public void setLeaveWater(BigDecimal leaveWater) {
		this.leaveWater = leaveWater;
	}
	public String getOwnerName() {
		return OwnerName;
	}
	public void setOwnerName(String ownerName) {
		OwnerName = ownerName;
	}
	public String getOwnerTelphone() {
		return OwnerTelphone;
	}
	public void setOwnerTelphone(String ownerTelphone) {
		OwnerTelphone = ownerTelphone;
	}
	public Date getCommTime() {
		return CommTime;
	}
	public void setCommTime(Date commTime) {
		CommTime = commTime;
	}
	public BigDecimal getUseWater() {
		return UseWater;
	}
	public void setUseWater(BigDecimal useWater) {
		UseWater = useWater;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWaterareaid() {
		return waterareaid;
	}
	public void setWaterareaid(String waterareaid) {
		this.waterareaid = waterareaid;
	}
	public String getDevicecode() {
		return devicecode;
	}
	public void setDevicecode(String devicecode) {
		this.devicecode = devicecode;
	}
	public String getWelluse() {
		return welluse;
	}
	public void setWelluse(String welluse) {
		this.welluse = welluse;
	}

	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getOnnetstate() {
		return onnetstate;
	}
	public void setOnnetstate(Integer onnetstate) {
		this.onnetstate = onnetstate;
	}
	public int getWaterlow() {
		return waterlow;
	}
	public void setWaterlow(int waterlow) {
		this.waterlow = waterlow;
	}
	public Integer getWarningstate() {
		return warningstate;
	}
	public void setWarningstate(Integer warningstate) {
		this.warningstate = warningstate;
	}
	public String getWarningcontent() {
		return warningcontent;
	}
	public void setWarningcontent(String warningcontent) {
		this.warningcontent = warningcontent;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getEdittime() {
		return edittime;
	}
	public void setEdittime(Date edittime) {
		this.edittime = edittime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBackone() {
		return backone;
	}
	public void setBackone(String backone) {
		this.backone = backone;
	}
	public String getBacktwo() {
		return backtwo;
	}
	public void setBacktwo(String backtwo) {
		this.backtwo = backtwo;
	}
	public String getBackthree() {
		return backthree;
	}
	public void setBackthree(String backthree) {
		this.backthree = backthree;
	}
	public String getBackfour() {
		return backfour;
	}
	public void setBackfour(String backfour) {
		this.backfour = backfour;
	}
	public String getBackfive() {
		return backfive;
	}
	public void setBackfive(String backfive) {
		this.backfive = backfive;
	}
	public String getDeviceWellUse() {
		return deviceWellUse;
	}
	public void setDeviceWellUse(String deviceWellUse) {
		this.deviceWellUse = deviceWellUse;
	}
	public String getWaterAreaName() {
		return waterAreaName;
	}
	public void setWaterAreaName(String waterAreaName) {
		this.waterAreaName = waterAreaName;
	}
	public String getBaseDeviceId() {
		return baseDeviceId;
	}
	public void setBaseDeviceId(String baseDeviceId) {
		this.baseDeviceId = baseDeviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
	
	
	
	
}