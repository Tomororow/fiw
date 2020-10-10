package com.fourfaith.sysManage.model;

import java.math.BigDecimal;

public class Abnormalstate {
    private String devicecode;

    private Boolean yxPmmpErr;

    private Boolean yxWaterErr;

    private Boolean yxElectErr;

    private Boolean yxTtsErr;

    private Boolean yxVolageUnbalance;

    private Boolean yxDoorOpen;

    private Boolean yxElectUnbalance;

    private Boolean yxNfcErr;

    private Boolean yxPhaseEleaErr;

    private Boolean yxPhaseElebErr;

    private Boolean yxPhaseElecErr;

    private Boolean yxPhaseVolaErr;

    private Boolean yxPhaseVolbErr;

    private Boolean yxPhaseVolcErr;

    private Boolean yxPowerVolErr;

    private Boolean yxTotalflowErr;

    private Boolean yxTotalelectErr;

    private Boolean reserve1;

    private Boolean reserve2;

    private Boolean reserve3;

    private Boolean reserve4;
    
    /**箱门状态,0:关闭;1:开启*/
	private java.lang.Integer doorState;
	/**上下卡状态 1为上卡 0为下卡*/
	private java.lang.Integer cardState;
	/**无水检测状态 1表示无水检测触发 0.表示正常*/
	private java.lang.Integer notFlow;
	/**无电检测状态 1表示被触发 0表示正常*/
	private java.lang.Integer notElect;
	/**主控状态 1表示开启 0表示关闭*/
	private java.lang.Integer manctrlStatus;
	/**备用通道启动原因*/
	private java.lang.Integer bckchnnaState;
	/**A相运行功率*/
	private BigDecimal aPhasePower;
	/**B相运行功率*/
	private BigDecimal bPhasePower;
	/**C相运行功率*/
	private BigDecimal cPhasePower;
	/**总功率*/
	private BigDecimal tTPhasePower;
	
	

    public BigDecimal getaPhasePower() {
		return aPhasePower;
	}

	public void setaPhasePower(BigDecimal aPhasePower) {
		this.aPhasePower = aPhasePower;
	}

	public BigDecimal getbPhasePower() {
		return bPhasePower;
	}

	public void setbPhasePower(BigDecimal bPhasePower) {
		this.bPhasePower = bPhasePower;
	}

	public BigDecimal getcPhasePower() {
		return cPhasePower;
	}

	public void setcPhasePower(BigDecimal cPhasePower) {
		this.cPhasePower = cPhasePower;
	}

	public BigDecimal gettTPhasePower() {
		return tTPhasePower;
	}

	public void settTPhasePower(BigDecimal tTPhasePower) {
		this.tTPhasePower = tTPhasePower;
	}

	public java.lang.Integer getDoorState() {
		return doorState;
	}

	public void setDoorState(java.lang.Integer doorState) {
		this.doorState = doorState;
	}

	public java.lang.Integer getCardState() {
		return cardState;
	}

	public void setCardState(java.lang.Integer cardState) {
		this.cardState = cardState;
	}

	public java.lang.Integer getNotFlow() {
		return notFlow;
	}

	public void setNotFlow(java.lang.Integer notFlow) {
		this.notFlow = notFlow;
	}

	public java.lang.Integer getNotElect() {
		return notElect;
	}

	public void setNotElect(java.lang.Integer notElect) {
		this.notElect = notElect;
	}

	public java.lang.Integer getManctrlStatus() {
		return manctrlStatus;
	}

	public void setManctrlStatus(java.lang.Integer manctrlStatus) {
		this.manctrlStatus = manctrlStatus;
	}

	public java.lang.Integer getBckchnnaState() {
		return bckchnnaState;
	}

	public void setBckchnnaState(java.lang.Integer bckchnnaState) {
		this.bckchnnaState = bckchnnaState;
	}

	public String getDevicecode() {
        return devicecode;
    }

    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode == null ? null : devicecode.trim();
    }

    public Boolean getYxPmmpErr() {
        return yxPmmpErr;
    }

    public void setYxPmmpErr(Boolean yxPmmpErr) {
        this.yxPmmpErr = yxPmmpErr;
    }

    public Boolean getYxWaterErr() {
        return yxWaterErr;
    }

    public void setYxWaterErr(Boolean yxWaterErr) {
        this.yxWaterErr = yxWaterErr;
    }

    public Boolean getYxElectErr() {
        return yxElectErr;
    }

    public void setYxElectErr(Boolean yxElectErr) {
        this.yxElectErr = yxElectErr;
    }

    public Boolean getYxTtsErr() {
        return yxTtsErr;
    }

    public void setYxTtsErr(Boolean yxTtsErr) {
        this.yxTtsErr = yxTtsErr;
    }

    public Boolean getYxVolageUnbalance() {
        return yxVolageUnbalance;
    }

    public void setYxVolageUnbalance(Boolean yxVolageUnbalance) {
        this.yxVolageUnbalance = yxVolageUnbalance;
    }

    public Boolean getYxDoorOpen() {
        return yxDoorOpen;
    }

    public void setYxDoorOpen(Boolean yxDoorOpen) {
        this.yxDoorOpen = yxDoorOpen;
    }

    public Boolean getYxElectUnbalance() {
        return yxElectUnbalance;
    }

    public void setYxElectUnbalance(Boolean yxElectUnbalance) {
        this.yxElectUnbalance = yxElectUnbalance;
    }

    public Boolean getYxNfcErr() {
        return yxNfcErr;
    }

    public void setYxNfcErr(Boolean yxNfcErr) {
        this.yxNfcErr = yxNfcErr;
    }

    public Boolean getYxPhaseEleaErr() {
        return yxPhaseEleaErr;
    }

    public void setYxPhaseEleaErr(Boolean yxPhaseEleaErr) {
        this.yxPhaseEleaErr = yxPhaseEleaErr;
    }

    public Boolean getYxPhaseElebErr() {
        return yxPhaseElebErr;
    }

    public void setYxPhaseElebErr(Boolean yxPhaseElebErr) {
        this.yxPhaseElebErr = yxPhaseElebErr;
    }

    public Boolean getYxPhaseElecErr() {
        return yxPhaseElecErr;
    }

    public void setYxPhaseElecErr(Boolean yxPhaseElecErr) {
        this.yxPhaseElecErr = yxPhaseElecErr;
    }

    public Boolean getYxPhaseVolaErr() {
        return yxPhaseVolaErr;
    }

    public void setYxPhaseVolaErr(Boolean yxPhaseVolaErr) {
        this.yxPhaseVolaErr = yxPhaseVolaErr;
    }

    public Boolean getYxPhaseVolbErr() {
        return yxPhaseVolbErr;
    }

    public void setYxPhaseVolbErr(Boolean yxPhaseVolbErr) {
        this.yxPhaseVolbErr = yxPhaseVolbErr;
    }

    public Boolean getYxPhaseVolcErr() {
        return yxPhaseVolcErr;
    }

    public void setYxPhaseVolcErr(Boolean yxPhaseVolcErr) {
        this.yxPhaseVolcErr = yxPhaseVolcErr;
    }

    public Boolean getYxPowerVolErr() {
        return yxPowerVolErr;
    }

    public void setYxPowerVolErr(Boolean yxPowerVolErr) {
        this.yxPowerVolErr = yxPowerVolErr;
    }

    public Boolean getYxTotalflowErr() {
        return yxTotalflowErr;
    }

    public void setYxTotalflowErr(Boolean yxTotalflowErr) {
        this.yxTotalflowErr = yxTotalflowErr;
    }

    public Boolean getYxTotalelectErr() {
        return yxTotalelectErr;
    }

    public void setYxTotalelectErr(Boolean yxTotalelectErr) {
        this.yxTotalelectErr = yxTotalelectErr;
    }

    public Boolean getReserve1() {
        return reserve1;
    }

    public void setReserve1(Boolean reserve1) {
        this.reserve1 = reserve1;
    }

    public Boolean getReserve2() {
        return reserve2;
    }

    public void setReserve2(Boolean reserve2) {
        this.reserve2 = reserve2;
    }

    public Boolean getReserve3() {
        return reserve3;
    }

    public void setReserve3(Boolean reserve3) {
        this.reserve3 = reserve3;
    }

    public Boolean getReserve4() {
        return reserve4;
    }

    public void setReserve4(Boolean reserve4) {
        this.reserve4 = reserve4;
    }
}