package com.fourfaith.statisticAnalysis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * 配水信息实体
 * @author Administrator
 * 2016年11月7日
 */
@ExcelTarget("rptbasedistWaterDetail")
public class RptBaseDistWaterDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	/** 配水类型 */
    private Integer distType;
	
	@Excel(name = "用水类型", orderNum = "5",width=15)
    private String distTypename;
    /** 配水方式 */
    private Integer distMode;
    
    @Excel(name = "配水名称", orderNum = "2",width=20)
    private String distModename;
    /** 配水年份 */
    @Excel(name = "配水年份", orderNum = "3",width=15)
    private Integer distYear;
    /** 轮次 */
    @Excel(name = "配水轮次", orderNum = "4",width=15)
    private Integer distRound;
    /** 每亩地分配水量 */
    @Excel(name = "每亩地分配水量(m³)", orderNum = "7",width=15)
    private BigDecimal distWater;
    /** 配水比例 */
    private Integer distRatio;
    /** 此轮配水价格 */
    @Excel(name = "配水价格", orderNum = "13",width=15)
    private BigDecimal distPrice;
    /**创建时间*/
    @Excel(name = "配水时间", orderNum = "14",databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss",width=20)
	private Date createTime;
	/**上次修改时间*/
	private Date editTime;
	/**
	 * 存储配水计划
	 */
	private List<RptBaseDistWaterDetail> children;
	
	private String sumWaterAmout;
	
	private String waterAmout;
	
	private String mayAmout;
	
	// 追加数据字段
	/**所属机井Id**/
	private String baseDeviceId;
	/**操作人员**/
	private String operator;
	/**是否追加水量**/
	private Integer isAppendWater;
	/**水井用途**/
	private String wellUse;
	/**所选择水管区域ID**/
	private String waterAreaId;
	/**设备机井名称**/
	private String deviceName;
	/**操作人员姓名*/
	@Excel(name = "操作人员", orderNum = "15",width=15)
	private String userName;
	/**水管区域姓名*/
	@Excel(name = "配水区域", orderNum = "1",width=15)
	private String waterAreaName;
	/**灌溉面积*/
	@Excel(name = "实际灌溉面积(m³)", orderNum = "6",width=15)
	private BigDecimal sJArea;
	/**供水人口*/
	@Excel(name = "总人口(人)", orderNum = "9",width=15)
	private Integer sJSupplyWaterPeople;
	/**年核定水量*/
	@Excel(name = "年核定水量(m³)", orderNum = "12",width=15)
	private BigDecimal industryApprovedWater;
	//灌溉总配水量
	@Excel(name = "总配水量(m³)", orderNum = "8",width=15)
	 private BigDecimal sumWater;
	 //人均分配水量
	@Excel(name = "人均分配水量(m³)", orderNum = "10",width=15)
	 private BigDecimal peopleWater;
	//生活总水量
	@Excel(name = "总配水量(m³)", orderNum = "11",width=15)
	 private BigDecimal peoplesumWater;
	
	 
	public String getDistModename() {
		return distModename;
	}
	public void setDistModename(String distModename) {
		this.distModename = distModename;
	}
	public String getDistTypename() {
		return distTypename;
	}
	public void setDistTypename(String distTypename) {
		this.distTypename = distTypename;
	}
	public BigDecimal getPeopleWater() {
		return peopleWater;
	}
	public void setPeopleWater(BigDecimal peopleWater) {
		this.peopleWater = peopleWater;
	}
	public BigDecimal getPeoplesumWater() {
		return peoplesumWater;
	}
	public void setPeoplesumWater(BigDecimal peoplesumWater) {
		this.peoplesumWater = peoplesumWater;
	}
	public BigDecimal getSumWater() {
		return sumWater;
	}
	public void setSumWater(BigDecimal sumWater) {
		this.sumWater = sumWater;
	}
	public String getMayAmout() {
		return mayAmout;
	}
	public void setMayAmout(String mayAmout) {
		this.mayAmout = mayAmout;
	}
	public String getSumWaterAmout() {
		return sumWaterAmout;
	}
	public void setSumWaterAmout(String sumWaterAmout) {
		this.sumWaterAmout = sumWaterAmout;
	}
	public String getWaterAmout() {
		return waterAmout;
	}
	public void setWaterAmout(String waterAmout) {
		this.waterAmout = waterAmout;
	}
	public List<RptBaseDistWaterDetail> getChildren() {
		return children;
	}
	public void setChildren(List<RptBaseDistWaterDetail> children) {
		this.children = children;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getDistType() {
		return distType;
	}
	public void setDistType(Integer distType) {
		this.distType = distType;
	}
	public Integer getDistMode() {
		return distMode;
	}
	public void setDistMode(Integer distMode) {
		this.distMode = distMode;
	}
	public Integer getDistYear() {
		return distYear;
	}
	public void setDistYear(Integer distYear) {
		this.distYear = distYear;
	}
	public Integer getDistRound() {
		return distRound;
	}
	public void setDistRound(Integer distRound) {
		this.distRound = distRound;
	}
	public BigDecimal getDistWater() {
		return distWater;
	}
	public void setDistWater(BigDecimal distWater) {
		this.distWater = distWater;
	}
	public Integer getDistRatio() {
		return distRatio;
	}
	public void setDistRatio(Integer distRatio) {
		this.distRatio = distRatio;
	}
	public BigDecimal getDistPrice() {
		return distPrice;
	}
	public void setDistPrice(BigDecimal distPrice) {
		this.distPrice = distPrice;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public String getBaseDeviceId() {
		return baseDeviceId;
	}
	public void setBaseDeviceId(String baseDeviceId) {
		this.baseDeviceId = baseDeviceId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Integer getIsAppendWater() {
		return isAppendWater;
	}
	public void setIsAppendWater(Integer isAppendWater) {
		this.isAppendWater = isAppendWater;
	}
	public String getWellUse() {
		return wellUse;
	}
	public void setWellUse(String wellUse) {
		this.wellUse = wellUse;
	}
	public String getWaterAreaId() {
		return waterAreaId;
	}
	public void setWaterAreaId(String waterAreaId) {
		this.waterAreaId = waterAreaId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWaterAreaName() {
		return waterAreaName;
	}
	public void setWaterAreaName(String waterAreaName) {
		this.waterAreaName = waterAreaName;
	}
	public BigDecimal getsJArea() {
		return sJArea;
	}
	public void setsJArea(BigDecimal sJArea) {
		this.sJArea = sJArea;
	}
	public Integer getsJSupplyWaterPeople() {
		return sJSupplyWaterPeople;
	}
	public void setsJSupplyWaterPeople(Integer sJSupplyWaterPeople) {
		this.sJSupplyWaterPeople = sJSupplyWaterPeople;
	}
	public BigDecimal getIndustryApprovedWater() {
		return industryApprovedWater;
	}
	public void setIndustryApprovedWater(BigDecimal industryApprovedWater) {
		this.industryApprovedWater = industryApprovedWater;
	}
	
}