package com.fourfaith.statisticAnalysis.model;

import java.math.BigDecimal;
import java.util.Date;

public class Cardhistory {
    private Integer id;

    private String devicecode;

    private String cardcode;

    private Integer curwater;
   
    private BigDecimal curele;

    private Integer cursec;

    private BigDecimal curprice;

    private Integer leavewater;

    private BigDecimal leavemoney;

    private Integer backupflowtotal;

    private Integer backuptimetotal;

    private Integer backupflow;

    private Integer backuptime;

    private Date bgntime;

    private Date endtime;

    private Integer re1;

    private Integer re2;

    private Integer re3;
    
    /**非本实体字段*/
    
    private Integer useWt;
    
    private BigDecimal usePw;
    
    private Integer leftWt;
    
    private BigDecimal leftMn;
    
    private Date openPumpTime;
    
    private Date stopPumpTime;

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
		this.devicecode = devicecode;
	}

	public String getCardcode() {
		return cardcode;
	}

	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}

	public Integer getCurwater() {
        return curwater;
    }

    public void setCurwater(Integer curwater) {
        this.curwater = curwater;
    }

    public BigDecimal getCurele() {
        return curele;
    }

    public void setCurele(BigDecimal curele) {
        this.curele = curele;
    }

    public Integer getCursec() {
        return cursec;
    }

    public void setCursec(Integer cursec) {
        this.cursec = cursec;
    }

    public BigDecimal getCurprice() {
        return curprice;
    }

    public void setCurprice(BigDecimal curprice) {
        this.curprice = curprice;
    }

    public Integer getLeavewater() {
        return leavewater;
    }

    public void setLeavewater(Integer leavewater) {
        this.leavewater = leavewater;
    }

    public BigDecimal getLeavemoney() {
        return leavemoney;
    }

    public void setLeavemoney(BigDecimal leavemoney) {
        this.leavemoney = leavemoney;
    }

    public Integer getBackupflowtotal() {
        return backupflowtotal;
    }

    public void setBackupflowtotal(Integer backupflowtotal) {
        this.backupflowtotal = backupflowtotal;
    }

    public Integer getBackuptimetotal() {
        return backuptimetotal;
    }

    public void setBackuptimetotal(Integer backuptimetotal) {
        this.backuptimetotal = backuptimetotal;
    }

    public Integer getBackupflow() {
        return backupflow;
    }

    public void setBackupflow(Integer backupflow) {
        this.backupflow = backupflow;
    }

    public Integer getBackuptime() {
        return backuptime;
    }

    public void setBackuptime(Integer backuptime) {
        this.backuptime = backuptime;
    }

    public Date getBgntime() {
        return bgntime;
    }

    public void setBgntime(Date bgntime) {
        this.bgntime = bgntime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getRe1() {
        return re1;
    }

    public void setRe1(Integer re1) {
        this.re1 = re1;
    }

    public Integer getRe2() {
        return re2;
    }

    public void setRe2(Integer re2) {
        this.re2 = re2;
    }

    public Integer getRe3() {
        return re3;
    }

    public void setRe3(Integer re3) {
        this.re3 = re3;
    }

	public Integer getUseWt() {
		return useWt;
	}

	public void setUseWt(Integer useWt) {
		this.useWt = useWt;
	}

	public BigDecimal getUsePw() {
		return usePw;
	}

	public void setUsePw(BigDecimal usePw) {
		this.usePw = usePw;
	}

	public Integer getLeftWt() {
		return leftWt;
	}

	public void setLeftWt(Integer leftWt) {
		this.leftWt = leftWt;
	}

	public BigDecimal getLeftMn() {
		return leftMn;
	}

	public void setLeftMn(BigDecimal leftMn) {
		this.leftMn = leftMn;
	}

	public Date getOpenPumpTime() {
		return openPumpTime;
	}

	public void setOpenPumpTime(Date openPumpTime) {
		this.openPumpTime = openPumpTime;
	}

	public Date getStopPumpTime() {
		return stopPumpTime;
	}

	public void setStopPumpTime(Date stopPumpTime) {
		this.stopPumpTime = stopPumpTime;
	}
    
    
    
}