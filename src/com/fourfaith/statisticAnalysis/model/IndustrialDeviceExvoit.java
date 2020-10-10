package com.fourfaith.statisticAnalysis.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndustrialDeviceExvoit {

    /** 设备名称 */
    @Excel(name="设备名称",orderNum = "1",width = 30)
    private String devicename;

    /** 设备编码 */
    @Excel(name="设备编码",orderNum = "2",width = 15)
    private String devicecode;

    /** 端口号 */
    @Excel(name="端口号",orderNum = "3",width = 15)
    private String deviceport;

    /** 创建时间 */

    private Date createtime;

    @Excel(name="结算时间",orderNum = "7",width = 40)
    private String time;

    @Excel(name="上期累积值",orderNum = "4", replace = { "0_null"},width = 20)
    private BigDecimal maxPosite;

    @Excel(name="当前累积值",orderNum = "5", replace = { "0_null"},width = 20)
    private BigDecimal minPosite;

    @Excel(name="累计用水量",orderNum = "6", replace = { "0_null"},width = 20)
    private BigDecimal positry;

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getDevicecode() {
        return devicecode;
    }

    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode;
    }

    public String getDeviceport() {
        return deviceport;
    }

    public void setDeviceport(String deviceport) {
        this.deviceport = deviceport;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public BigDecimal getMaxPosite() {
        return maxPosite;
    }

    public void setMaxPosite(BigDecimal maxPosite) {
        this.maxPosite = maxPosite;
    }

    public BigDecimal getMinPosite() {
        return minPosite;
    }

    public void setMinPosite(BigDecimal minPosite) {
        this.minPosite = minPosite;
    }

    public BigDecimal getPositry() {
        return positry;
    }

    public void setPositry(BigDecimal positry) {
        this.positry = positry;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}