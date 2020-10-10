package com.fourfaith.statisticAnalysis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 用水统计表
 * @author zzh
 * @date 2016年9月27日
 */
public class RptUseWaterDetail implements Serializable{
	
	private static final long serialVersionUID = -494563654242058501L;
	
	private String id;               // 主键ID
	private String deviceCode;       // 设备编号
	private String cardCode;         // 卡编号
	private BigDecimal useWater;     // 用水量
	private BigDecimal usePower;     // 本次用电量
	private BigDecimal useMoney;     // 本次消费金额
	private BigDecimal remainWater;  // 剩余水量
	private BigDecimal remainMoney;  // 剩余金额
	private Integer backupflow;
	private Integer backuptime;       
	private Date lastUpTime;       // 上次上报时间
	private Integer totalWater;	//累计用水量
	private BigDecimal totalPower;		//累计用电量，单位度
	private Date upTime;			//本次上报时间
	private Integer inYear;          // 入库年
	private Integer inMonth;         // 入库月
	private Integer inDay;           // 入库天
	private Integer inHour;          // 入库时
	private Date createTime;         // 创建时间
	private Integer isMissReport;	//是否为补报 1为补报 0为正常报
	private Integer flag;  //标识当前水量信息是否已经计算（0：未计算，1：已计算）
	
	/**非数据库字段*/
	private String deviceName;       // 设备名称
	private String ownerName;        // 业主姓名
	private String ownerTelphone;    // 业主电话
	private BigDecimal totalUseWater;// 总用水量
	private String areaId;           // 行政区域ID
	private Integer useWaterDay;     // 最长未用水天数
	private String sumusewater;//小时总用水量
	/**实际灌溉面积*/
	private BigDecimal sJArea;
	/**水井用途分类*/
	private String wellUse;
	
	
	
	public String getSumusewater() {
		return sumusewater;
	}
	public void setSumusewater(String sumusewater) {
		this.sumusewater = sumusewater;
	}
	public BigDecimal getsJArea() {
		return sJArea;
	}
	public void setsJArea(BigDecimal sJArea) {
		this.sJArea = sJArea;
	}
	public String getWellUse() {
		return wellUse;
	}
	public void setWellUse(String wellUse) {
		this.wellUse = wellUse;
	}
	/** 本次用水量 新加 */
	private double useWaterResult;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public BigDecimal getUseWater() {
		return useWater;
	}
	public void setUseWater(BigDecimal useWater) {
		this.useWater = useWater;
	}
	public BigDecimal getUsePower() {
		return usePower;
	}
	public void setUsePower(BigDecimal usePower) {
		this.usePower = usePower;
	}
	public BigDecimal getUseMoney() {
		return useMoney;
	}
	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}
	public BigDecimal getRemainWater() {
		return remainWater;
	}
	public void setRemainWater(BigDecimal remainWater) {
		this.remainWater = remainWater;
	}
	public BigDecimal getRemainMoney() {
		return remainMoney;
	}
	public void setRemainMoney(BigDecimal remainMoney) {
		this.remainMoney = remainMoney;
	}
	public Integer getInYear() {
		return inYear;
	}
	public void setInYear(Integer inYear) {
		this.inYear = inYear;
	}
	public Integer getInMonth() {
		return inMonth;
	}
	public void setInMonth(Integer inMonth) {
		this.inMonth = inMonth;
	}
	public Integer getInDay() {
		return inDay;
	}
	public void setInDay(Integer inDay) {
		this.inDay = inDay;
	}
	public Integer getInHour() {
		return inHour;
	}
	public void setInHour(Integer inHour) {
		this.inHour = inHour;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
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
	public BigDecimal getTotalUseWater() {
		return totalUseWater;
	}
	public void setTotalUseWater(BigDecimal totalUseWater) {
		this.totalUseWater = totalUseWater;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public Integer getUseWaterDay() {
		return useWaterDay;
	}
	public void setUseWaterDay(Integer useWaterDay) {
		this.useWaterDay = useWaterDay;
	}
	public double getUseWaterResult() {
		return useWaterResult;
	}
	public void setUseWaterResult(double useWaterResult) {
		this.useWaterResult = useWaterResult;
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
	public Date getLastUpTime() {
		return lastUpTime;
	}
	public void setLastUpTime(Date lastUpTime) {
		this.lastUpTime = lastUpTime;
	}
	public Integer getTotalWater() {
		return totalWater;
	}
	public void setTotalWater(Integer totalWater) {
		this.totalWater = totalWater;
	}
	public BigDecimal getTotalPower() {
		return totalPower;
	}
	public void setTotalPower(BigDecimal totalPower) {
		this.totalPower = totalPower;
	}
	public Date getUpTime() {
		return upTime;
	}
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}
	public Integer getIsMissReport() {
		return isMissReport;
	}
	public void setIsMissReport(Integer isMissReport) {
		this.isMissReport = isMissReport;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}