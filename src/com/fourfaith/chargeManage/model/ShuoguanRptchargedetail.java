package com.fourfaith.chargeManage.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 
 * @author liyue 
 *
 */
public class ShuoguanRptchargedetail {
    private Integer id;

    private String address;//机井编码

    private String serialnumber;

    private String himura;//村号

    private String family;//户号

    private String createzzchargetimes;//充值次数

    private Double credit;//充值金额

    private Double totalmoneye8;//总购金额

    private Double voltametere8;

    private Double wateryielde8;

    private String datetimee8;//充值时间

    private Date datetime;

    private String iswin;//充值成功标识
    
    private String Xname;//户名
    private String AreaName;//村名
    private String DeviceName;//机井名称
    private String InstallAddress;//机井地址

    
    
    public String getXname() {
		return Xname;
	}

	public void setXname(String xname) {
		Xname = xname;
	}

	public String getAreaName() {
		return AreaName;
	}

	public void setAreaName(String areaName) {
		AreaName = areaName;
	}

	public String getDeviceName() {
		return DeviceName;
	}

	public void setDeviceName(String deviceName) {
		DeviceName = deviceName;
	}

	public String getInstallAddress() {
		return InstallAddress;
	}

	public void setInstallAddress(String installAddress) {
		InstallAddress = installAddress;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber == null ? null : serialnumber.trim();
    }

    public String getHimura() {
        return himura;
    }

    public void setHimura(String himura) {
        this.himura = himura == null ? null : himura.trim();
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family == null ? null : family.trim();
    }

    public String getCreatezzchargetimes() {
        return createzzchargetimes;
    }

    public void setCreatezzchargetimes(String createzzchargetimes) {
        this.createzzchargetimes = createzzchargetimes == null ? null : createzzchargetimes.trim();
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getTotalmoneye8() {
        return totalmoneye8;
    }

    public void setTotalmoneye8(Double totalmoneye8) {
        this.totalmoneye8 = totalmoneye8;
    }

    public Double getVoltametere8() {
        return voltametere8;
    }

    public void setVoltametere8(Double voltametere8) {
        this.voltametere8 = voltametere8;
    }

    public Double getWateryielde8() {
        return wateryielde8;
    }

    public void setWateryielde8(Double wateryielde8) {
        this.wateryielde8 = wateryielde8;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    public String getDatetimee8() {
        return datetimee8;
    }
    public void setDatetimee8(String datetimee8) {
        this.datetimee8 = datetimee8;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getIswin() {
        return iswin;
    }

    public void setIswin(String iswin) {
        this.iswin = iswin == null ? null : iswin.trim();
    }
}