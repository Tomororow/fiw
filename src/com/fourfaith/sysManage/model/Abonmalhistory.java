package com.fourfaith.sysManage.model;

import java.util.Date;

public class Abonmalhistory {
    private Integer id;

    private String devicecode;
    
    private String devicename;

    private Integer abnormaltype;

    private Integer abnormalstate;
    
    private String abnormalname;

    private Date uptime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevicecode() {
        return devicecode;
    }

    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode == null ? null : devicecode.trim();
    }

    public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public Integer getAbnormaltype() {
        return abnormaltype;
    }

    public String getAbnormalname() {
		return abnormalname;
	}

	public void setAbnormalname(String abnormalname) {
		this.abnormalname = abnormalname;
	}

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	public void setAbnormaltype(Integer abnormaltype) {
        this.abnormaltype = abnormaltype;
    }

    public Integer getAbnormalstate() {
        return abnormalstate;
    }

    public void setAbnormalstate(Integer abnormalstate) {
        this.abnormalstate = abnormalstate;
    }

}