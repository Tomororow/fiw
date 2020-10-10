package com.fourfaith.alarmManage.model;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WarningRecordDetail {
    private String id;
    @Excel(name="机井名称",orderNum="1",width=20)
    private String deviceName;
    @Excel(name="机井编码",orderNum="2",width=15)
    private String deviceCode;
    @Excel(name="机井负责人",orderNum="3",width=15)
    private String ownerName;
    @Excel(name="负责人电话",orderNum="4",width=15)
    private String ownerTelphone;

    private String cardCode;

    private String warnType;

    private String warnDetail;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date warnTime;
    
    private String warnTimee;
   
    private Integer warnState;

    private String warnHandler;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date warnHandleTime;

    private String backupOne;

    private String backupTwo;

    private String backupThree;

    private String backupFour;

    private String backupFive;

    private String remark;
    
    private Integer warnCode;
    


	//设备异常查询
    
    private String abnormaltype;//设备异常类型
   // @Excel(name="异常状态",orderNum="6",width=10,replace = {"正在预警_1", "已处理_0"})
    private String abnormalstate;//设备异常状态
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name="时间",orderNum="6",width=20,databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss")
    private Date uptime;//上报时间
    
    //是否关联短信人员
    //@Excel(name="是否关联短信人员",orderNum="7",width=15,replace = {"已关联_1", "未关联_0"})
    private String phone;
    //是否开启短信预警
   // @Excel(name="是否开启短信预警",orderNum="8",width=15,replace = {"已开启_1", "未开启_0"})
    private String mess;
    @Excel(name="异常类型",orderNum="5",width=20)
    private String wabnormaltype;
    


    public Integer getWarnCode() {
		return warnCode;
	}

	public void setWarnCode(Integer warnCode) {
		this.warnCode = warnCode;
	}

	public String getWabnormaltype() {
		return wabnormaltype;
	}

	public void setWabnormaltype(String wabnormaltype) {
		this.wabnormaltype = wabnormaltype;
	}

	public String getWarnTimee() {
		return warnTimee;
	}

	public void setWarnTimee(String warnTimee) {
		this.warnTimee = warnTimee;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAbnormaltype() {
		return abnormaltype;
	}

	public void setAbnormaltype(String abnormaltype) {
		this.abnormaltype = abnormaltype;
	}

	public String getAbnormalstate() {
		return abnormalstate;
	}

	public void setAbnormalstate(String abnormalstate) {
		this.abnormalstate = abnormalstate;
	}

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public String getOwnerTelphone() {
        return ownerTelphone;
    }

    public void setOwnerTelphone(String ownerTelphone) {
        this.ownerTelphone = ownerTelphone == null ? null : ownerTelphone.trim();
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode == null ? null : cardCode.trim();
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType == null ? null : warnType.trim();
    }

    public String getWarnDetail() {
        return warnDetail;
    }

    public void setWarnDetail(String warnDetail) {
        this.warnDetail = warnDetail == null ? null : warnDetail.trim();
    }

    public Date getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(Date warnTime) {
        this.warnTime = warnTime;
    }

    public Integer getWarnState() {
        return warnState;
    }

    public void setWarnState(Integer warnState) {
        this.warnState = warnState;
    }

    public String getWarnHandler() {
        return warnHandler;
    }

    public void setWarnHandler(String warnHandler) {
        this.warnHandler = warnHandler == null ? null : warnHandler.trim();
    }

    public Date getWarnHandleTime() {
        return warnHandleTime;
    }

    public void setWarnHandleTime(Date warnHandleTime) {
        this.warnHandleTime = warnHandleTime;
    }

    public String getBackupOne() {
        return backupOne;
    }

    public void setBackupOne(String backupOne) {
        this.backupOne = backupOne == null ? null : backupOne.trim();
    }

    public String getBackupTwo() {
        return backupTwo;
    }

    public void setBackupTwo(String backupTwo) {
        this.backupTwo = backupTwo == null ? null : backupTwo.trim();
    }

    public String getBackupThree() {
        return backupThree;
    }

    public void setBackupThree(String backupThree) {
        this.backupThree = backupThree == null ? null : backupThree.trim();
    }

    public String getBackupFour() {
        return backupFour;
    }

    public void setBackupFour(String backupFour) {
        this.backupFour = backupFour == null ? null : backupFour.trim();
    }

    public String getBackupFive() {
        return backupFive;
    }

    public void setBackupFive(String backupFive) {
        this.backupFive = backupFive == null ? null : backupFive.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}