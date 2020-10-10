package com.fourfaith.sysManage.model;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Tripshistory {
    private Integer id;

    private String devicecode;
    
    private String devicename;
    
    private Integer tripstype;
    
    private String tripsname;

    private Date tripstime;

    private Date uptime;
    
    
    
    private String ownerName;
    
    private String ownerTelphone;
    
    private String wabnormaltype;
    
    private String phone;
    
    private String mess;
    

    public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerTelphone() {
		return ownerTelphone;
	}

	public void setOwnerTelphone(String ownerTelphone) {
		this.ownerTelphone = ownerTelphone;
	}

	public String getWabnormaltype() {
		return wabnormaltype;
	}

	public void setWabnormaltype(String wabnormaltype) {
		this.wabnormaltype = wabnormaltype;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

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

	public Integer getTripstype() {
        return tripstype;
    }

    public void setTripstype(Integer tripstype) {
        this.tripstype = tripstype;
    }

    public String getTripsname() {
		return tripsname;
	}

	public void setTripsname(String tripsname) {
		this.tripsname = tripsname;
	}

	public Date getTripstime() {
        return tripstime;
    }

    public void setTripstime(Date tripstime) {
        this.tripstime = tripstime;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
}