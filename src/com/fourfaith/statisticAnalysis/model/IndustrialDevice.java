package com.fourfaith.statisticAnalysis.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Date;

public class IndustrialDevice {
    /**  */
    private Integer id;

    /** 设备名称 */
    @Excel(name="设备名称",orderNum = "2",width = 30)
    private String devicename;

    /** 设备编码 */
    @Excel(name="设备编码",orderNum = "1",width = 15)
    private String devicecode;

    /** 端口号 */
    private String deviceport;

    /** 瞬时流量，立方米/秒（m3/s） */
    private BigDecimal instantflow;

    /** 瞬时流速，米/秒（m/s） */
    private BigDecimal instantspeed;

    /** 流量百分比 */
    private BigDecimal flowpercent;

    /** 流体电导比 */
    private BigDecimal ratiopercent;

    /** 正向累积值 */
    @Excel(name="正向累积值",orderNum = "5", replace = { "0_null"},isStatistics = true,width = 30)
    private BigDecimal positivetotal;

    /** 反向累积值 */
    @Excel(name="反向累积值",orderNum = "4",replace = { "0_null"},isStatistics = true,width = 30)
    private BigDecimal reversetotal;

    /** 备用字段1 */
    private BigDecimal backone;

    /** 备用字段2 */
    private String backtwo;

    /** 备用字段3 */
    private String backthree;

    /** 备用字段4 */
    private String backfour;

    /** 备用字段5 */
    private String backfive;

    /** 创建时间 */
    @Excel(name="上报时间",orderNum = "3",databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss",width = 20)
    private Date createtime;


    private String upTime;

    private Integer ids;

    private BigDecimal maxPosite;

    private BigDecimal minPosite;

    private BigDecimal positry;

    /**
     * 
     * @return Id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 设备名称
     * @return DeviceName 设备名称
     */
    public String getDevicename() {
        return devicename;
    }

    /**
     * 设备名称
     * @param devicename 设备名称
     */
    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    /**
     * 设备编码
     * @return DeviceCode 设备编码
     */
    public String getDevicecode() {
        return devicecode;
    }

    /**
     * 设备编码
     * @param devicecode 设备编码
     */
    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode;
    }

    /**
     * 端口号
     * @return DevicePort 端口号
     */
    public String getDeviceport() {
        return deviceport;
    }

    /**
     * 端口号
     * @param deviceport 端口号
     */
    public void setDeviceport(String deviceport) {
        this.deviceport = deviceport;
    }

    /**
     * 瞬时流量，立方米/秒（m3/s）
     * @return InstantFlow 瞬时流量，立方米/秒（m3/s）
     */
    public BigDecimal getInstantflow() {
        return instantflow;
    }

    /**
     * 瞬时流量，立方米/秒（m3/s）
     * @param instantflow 瞬时流量，立方米/秒（m3/s）
     */
    public void setInstantflow(BigDecimal instantflow) {
        this.instantflow = instantflow;
    }

    /**
     * 瞬时流速，米/秒（m/s）
     * @return InstantSpeed 瞬时流速，米/秒（m/s）
     */
    public BigDecimal getInstantspeed() {
        return instantspeed;
    }

    /**
     * 瞬时流速，米/秒（m/s）
     * @param instantspeed 瞬时流速，米/秒（m/s）
     */
    public void setInstantspeed(BigDecimal instantspeed) {
        this.instantspeed = instantspeed;
    }

    /**
     * 流量百分比
     * @return FlowPercent 流量百分比
     */
    public BigDecimal getFlowpercent() {
        return flowpercent;
    }

    /**
     * 流量百分比
     * @param flowpercent 流量百分比
     */
    public void setFlowpercent(BigDecimal flowpercent) {
        this.flowpercent = flowpercent;
    }

    /**
     * 流体电导比
     * @return RatioPercent 流体电导比
     */
    public BigDecimal getRatiopercent() {
        return ratiopercent;
    }

    /**
     * 流体电导比
     * @param ratiopercent 流体电导比
     */
    public void setRatiopercent(BigDecimal ratiopercent) {
        this.ratiopercent = ratiopercent;
    }

    /**
     * 正向累积值
     * @return PositiveTotal 正向累积值
     */
    public BigDecimal getPositivetotal() {
        return positivetotal;
    }

    /**
     * 正向累积值
     * @param positivetotal 正向累积值
     */
    public void setPositivetotal(BigDecimal positivetotal) {
        this.positivetotal = positivetotal;
    }

    /**
     * 反向累积值
     * @return ReverseTotal 反向累积值
     */
    public BigDecimal getReversetotal() {
        return reversetotal;
    }

    /**
     * 反向累积值
     * @param reversetotal 反向累积值
     */
    public void setReversetotal(BigDecimal reversetotal) {
        this.reversetotal = reversetotal;
    }

    /**
     * 备用字段1
     * @return BackOne 备用字段1
     */
    public BigDecimal getBackone() {
        return backone;
    }

    /**
     * 备用字段1
     * @param backone 备用字段1
     */
    public void setBackone(BigDecimal backone) {
        this.backone = backone;
    }

    /**
     * 备用字段2
     * @return BackTwo 备用字段2
     */
    public String getBacktwo() {
        return backtwo;
    }

    /**
     * 备用字段2
     * @param backtwo 备用字段2
     */
    public void setBacktwo(String backtwo) {
        this.backtwo = backtwo;
    }

    /**
     * 备用字段3
     * @return BackThree 备用字段3
     */
    public String getBackthree() {
        return backthree;
    }

    /**
     * 备用字段3
     * @param backthree 备用字段3
     */
    public void setBackthree(String backthree) {
        this.backthree = backthree;
    }

    /**
     * 备用字段4
     * @return BackFour 备用字段4
     */
    public String getBackfour() {
        return backfour;
    }

    /**
     * 备用字段4
     * @param backfour 备用字段4
     */
    public void setBackfour(String backfour) {
        this.backfour = backfour;
    }

    /**
     * 备用字段5
     * @return BackFive 备用字段5
     */
    public String getBackfive() {
        return backfive;
    }

    /**
     * 备用字段5
     * @param backfive 备用字段5
     */
    public void setBackfive(String backfive) {
        this.backfive = backfive;
    }

    /**
     * 创建时间
     * @return CreateTime 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 创建时间
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public Integer getIds() {
        return ids;
    }

    public void setIds(Integer ids) {
        this.ids = ids;
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
}