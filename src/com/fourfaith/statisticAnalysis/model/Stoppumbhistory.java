package com.fourfaith.statisticAnalysis.model;

import java.math.BigDecimal;
import java.util.Date;

public class Stoppumbhistory {
    private Integer id;
    
    private String devicecode;
    
    private String relCardCode;
    
    private String deviceName;

    private String deviceCode;
    
    private BigDecimal leftWt;
    
    private String cardcode;

    private Integer userdwater;

    private Integer userdele;

    private Integer userdmoney;

    private Integer leavemoney;

    private Integer leavewater;

    private Date starttime;

    private Date stoptime;

    private Integer ismissreport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    

    public BigDecimal getLeftWt() {
		return leftWt;
	}

	public void setLeftWt(BigDecimal leftWt) {
		this.leftWt = leftWt;
	}

	public String getRelCardCode() {
		return relCardCode;
	}

	public void setRelCardCode(String relCardCode) {
		this.relCardCode = relCardCode;
	}

	public String getDevicecode() {
		return devicecode;
	}

	public void setDevicecode(String devicecode) {
		this.devicecode = devicecode;
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

	public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode == null ? null : cardcode.trim();
    }

    public Integer getUserdwater() {
        return userdwater;
    }

    public void setUserdwater(Integer userdwater) {
        this.userdwater = userdwater;
    }

    public Integer getUserdele() {
        return userdele;
    }

    public void setUserdele(Integer userdele) {
        this.userdele = userdele;
    }

    public Integer getUserdmoney() {
        return userdmoney;
    }

    public void setUserdmoney(Integer userdmoney) {
        this.userdmoney = userdmoney;
    }

    public Integer getLeavemoney() {
        return leavemoney;
    }

    public void setLeavemoney(Integer leavemoney) {
        this.leavemoney = leavemoney;
    }

    public Integer getLeavewater() {
        return leavewater;
    }

    public void setLeavewater(Integer leavewater) {
        this.leavewater = leavewater;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getStoptime() {
        return stoptime;
    }

    public void setStoptime(Date stoptime) {
        this.stoptime = stoptime;
    }

    public Integer getIsmissreport() {
        return ismissreport;
    }

    public void setIsmissreport(Integer ismissreport) {
        this.ismissreport = ismissreport;
    }
}