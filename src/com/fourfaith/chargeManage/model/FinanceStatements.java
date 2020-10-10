package com.fourfaith.chargeManage.model;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceStatements {
    private String id;

    private String waterareacode;

    private String userid;

    private String cardcode;

    private String ordercode;

    private String devicecode;

    private String devicename;

    private Integer distyear;

    private Integer distround;

    private BigDecimal distprice;

    private BigDecimal distwater;

    private BigDecimal totalprice;

    private Date  createtime;

    private String standbyone;

    private String standbytwo;

    private String standbythree;

    private String standbyfour;

    private String standbyfive;
    
    private String userName;
    //临时字段
    private BigDecimal sumWater;
    private BigDecimal sumPrice;
    
    private String WaterAreaName;
    
    public String getWaterAreaName() {
		return WaterAreaName;
	}

	public void setWaterAreaName(String waterAreaName) {
		WaterAreaName = waterAreaName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWaterareacode() {
        return waterareacode;
    }

    public void setWaterareacode(String waterareacode) {
        this.waterareacode = waterareacode == null ? null : waterareacode.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode == null ? null : cardcode.trim();
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
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
        this.devicename = devicename == null ? null : devicename.trim();
    }

    public Integer getDistyear() {
        return distyear;
    }

    public void setDistyear(Integer distyear) {
        this.distyear = distyear;
    }

    public Integer getDistround() {
        return distround;
    }

    public void setDistround(Integer distround) {
        this.distround = distround;
    }

    public BigDecimal getDistprice() {
        return distprice;
    }

    public void setDistprice(BigDecimal distprice) {
        this.distprice = distprice;
    }

    public BigDecimal getDistwater() {
        return distwater;
    }

    public void setDistwater(BigDecimal distwater) {
        this.distwater = distwater;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }
    
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStandbyone() {
        return standbyone;
    }

    public void setStandbyone(String standbyone) {
        this.standbyone = standbyone == null ? null : standbyone.trim();
    }

    public String getStandbytwo() {
        return standbytwo;
    }

    public void setStandbytwo(String standbytwo) {
        this.standbytwo = standbytwo == null ? null : standbytwo.trim();
    }

    public String getStandbythree() {
        return standbythree;
    }

    public void setStandbythree(String standbythree) {
        this.standbythree = standbythree == null ? null : standbythree.trim();
    }

    public String getStandbyfour() {
        return standbyfour;
    }

    public void setStandbyfour(String standbyfour) {
        this.standbyfour = standbyfour == null ? null : standbyfour.trim();
    }

    public String getStandbyfive() {
        return standbyfive;
    }

    public void setStandbyfive(String standbyfive) {
        this.standbyfive = standbyfive == null ? null : standbyfive.trim();
    }

	public BigDecimal getSumWater() {
		return sumWater;
	}

	public void setSumWater(BigDecimal sumWater) {
		this.sumWater = sumWater;
	}

	public BigDecimal getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}
    
}