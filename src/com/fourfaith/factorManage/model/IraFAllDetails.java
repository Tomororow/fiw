package com.fourfaith.factorManage.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 要素明细表
 * @author chenweiyi
 * @date 2016年6月13日
 */
public class IraFAllDetails {
   
	private String id;
    /** 设备编码 */
    private String stcd;
    /** 水位值 */
    private BigDecimal water;
    /** 每秒流量 */
    private BigDecimal flowratepers;
    /** 每小时流量 */
    private BigDecimal flowrateperh;
    /** 累计流量 */
    private BigDecimal flowrateadd;
    /** 电源电压 */
    private BigDecimal voltage;
    /** 信号强度 */
    private BigDecimal signalinten;
    /** 流量计状态 */
    private Integer flowmeterstatus;
    /** 采集时间 */
    private Date tm;

    /** 设备编码 */
    private String stnm;
    /**区域名称*/
    private String addvnm;
    /** 各要素平均值、最小值、最大值 */
    private BigDecimal wateravg;
    private BigDecimal watermin;
    private BigDecimal watermax;
    
    private BigDecimal flowratepersavg;
    private BigDecimal flowratepersmin;
    private BigDecimal flowratepersmax;
    
    private BigDecimal flowrateperhavg;
    private BigDecimal flowrateperhmin;
    private BigDecimal flowrateperhmax;
    
    private BigDecimal flowrateaddavg;
    private BigDecimal flowrateaddmin;
    private BigDecimal flowrateaddmax;
    
    private BigDecimal voltageavg;
    private BigDecimal voltagemin;
    private BigDecimal voltagemax;
    
    private BigDecimal signalintenavg;
    private BigDecimal signalintenmin;
    private BigDecimal signalintenmax;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd == null ? null : stcd.trim();
    }

    public BigDecimal getWater() {
        return water;
    }

    public void setWater(BigDecimal water) {
        this.water = water;
    }

    public BigDecimal getFlowratepers() {
        return flowratepers;
    }

    public void setFlowratepers(BigDecimal flowratepers) {
        this.flowratepers = flowratepers;
    }

    public BigDecimal getFlowrateperh() {
        return flowrateperh;
    }

    public void setFlowrateperh(BigDecimal flowrateperh) {
        this.flowrateperh = flowrateperh;
    }

    public BigDecimal getFlowrateadd() {
        return flowrateadd;
    }

    public void setFlowrateadd(BigDecimal flowrateadd) {
        this.flowrateadd = flowrateadd;
    }

    public BigDecimal getVoltage() {
        return voltage;
    }

    public void setVoltage(BigDecimal voltage) {
        this.voltage = voltage;
    }

    public BigDecimal getSignalinten() {
        return signalinten;
    }

    public void setSignalinten(BigDecimal signalinten) {
        this.signalinten = signalinten;
    }

    public Date getTm() {
        return tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

	public BigDecimal getWateravg() {
		return wateravg;
	}

	public void setWateravg(BigDecimal wateravg) {
		this.wateravg = wateravg;
	}

	public BigDecimal getWatermin() {
		return watermin;
	}

	public void setWatermin(BigDecimal watermin) {
		this.watermin = watermin;
	}

	public BigDecimal getWatermax() {
		return watermax;
	}

	public void setWatermax(BigDecimal watermax) {
		this.watermax = watermax;
	}

	public BigDecimal getFlowratepersavg() {
		return flowratepersavg;
	}

	public void setFlowratepersavg(BigDecimal flowratepersavg) {
		this.flowratepersavg = flowratepersavg;
	}

	public BigDecimal getFlowratepersmin() {
		return flowratepersmin;
	}

	public void setFlowratepersmin(BigDecimal flowratepersmin) {
		this.flowratepersmin = flowratepersmin;
	}

	public BigDecimal getFlowratepersmax() {
		return flowratepersmax;
	}

	public void setFlowratepersmax(BigDecimal flowratepersmax) {
		this.flowratepersmax = flowratepersmax;
	}

	public BigDecimal getFlowrateperhavg() {
		return flowrateperhavg;
	}

	public void setFlowrateperhavg(BigDecimal flowrateperhavg) {
		this.flowrateperhavg = flowrateperhavg;
	}

	public BigDecimal getFlowrateperhmin() {
		return flowrateperhmin;
	}

	public void setFlowrateperhmin(BigDecimal flowrateperhmin) {
		this.flowrateperhmin = flowrateperhmin;
	}

	public BigDecimal getFlowrateperhmax() {
		return flowrateperhmax;
	}

	public void setFlowrateperhmax(BigDecimal flowrateperhmax) {
		this.flowrateperhmax = flowrateperhmax;
	}

	public BigDecimal getFlowrateaddavg() {
		return flowrateaddavg;
	}

	public void setFlowrateaddavg(BigDecimal flowrateaddavg) {
		this.flowrateaddavg = flowrateaddavg;
	}

	public BigDecimal getFlowrateaddmin() {
		return flowrateaddmin;
	}

	public void setFlowrateaddmin(BigDecimal flowrateaddmin) {
		this.flowrateaddmin = flowrateaddmin;
	}

	public BigDecimal getFlowrateaddmax() {
		return flowrateaddmax;
	}

	public void setFlowrateaddmax(BigDecimal flowrateaddmax) {
		this.flowrateaddmax = flowrateaddmax;
	}

	public BigDecimal getVoltageavg() {
		return voltageavg;
	}

	public void setVoltageavg(BigDecimal voltageavg) {
		this.voltageavg = voltageavg;
	}

	public BigDecimal getVoltagemin() {
		return voltagemin;
	}

	public void setVoltagemin(BigDecimal voltagemin) {
		this.voltagemin = voltagemin;
	}

	public BigDecimal getVoltagemax() {
		return voltagemax;
	}

	public void setVoltagemax(BigDecimal voltagemax) {
		this.voltagemax = voltagemax;
	}

	public BigDecimal getSignalintenavg() {
		return signalintenavg;
	}

	public void setSignalintenavg(BigDecimal signalintenavg) {
		this.signalintenavg = signalintenavg;
	}

	public BigDecimal getSignalintenmin() {
		return signalintenmin;
	}

	public void setSignalintenmin(BigDecimal signalintenmin) {
		this.signalintenmin = signalintenmin;
	}

	public BigDecimal getSignalintenmax() {
		return signalintenmax;
	}

	public void setSignalintenmax(BigDecimal signalintenmax) {
		this.signalintenmax = signalintenmax;
	}

	public String getStnm() {
		return stnm;
	}

	public void setStnm(String stnm) {
		this.stnm = stnm;
	}

	public String getAddvnm() {
		return addvnm;
	}

	public void setAddvnm(String addvnm) {
		this.addvnm = addvnm;
	}

	public Integer getFlowmeterstatus() {
		return flowmeterstatus;
	}

	public void setFlowmeterstatus(Integer flowmeterstatus) {
		this.flowmeterstatus = flowmeterstatus;
	}
	
}