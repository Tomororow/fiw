package com.fourfaith.sysManage.model;
import java.math.BigDecimal;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 机井信息表
 * @author  Dan
 * @date 2016-08-24 22:21:40
 * @version V1.0   
 */
public class WellInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = -1662454859992579038L;
	
	/**机井编码*/
	private String wellId;
	/**机井名称*/
	private String wellName;
	/**纬度*/
	private BigDecimal lat;
	/**经度*/
	private BigDecimal lnt;
	/**建设时间*/
	private Date buildYear;
	
	private String qsxkzh;
	/**井深*/
	private BigDecimal wellDeep;
	/**水深*/
	private BigDecimal waterDeep;
	/**水质*/
	private String waterQuality;
	
	private BigDecimal pumpPower;
	
	private BigDecimal perWtOut;
	
	private BigDecimal perEleOut;
	
	private BigDecimal yc;
	
	private BigDecimal circle;
	
	private java.lang.Integer yearNumber;
	/**管理员姓名*/
	private String managerName;
	/**管理员电话*/
	private String managerTel;
	/**设备号*/
	private java.lang.Integer devID;
	
	private String netType;
	/**宏电DTU号码*/
	private String cezhanID;
	
	private String simID;
	/**备注*/
	private String remark;
	
	public String getWellId() {
		return wellId;
	}
	public void setWellId(String wellId) {
		this.wellId = wellId;
	}
	public String getWellName() {
		return wellName;
	}
	public void setWellName(String wellName) {
		this.wellName = wellName;
	}
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	public BigDecimal getLnt() {
		return lnt;
	}
	public void setLnt(BigDecimal lnt) {
		this.lnt = lnt;
	}
	public Date getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(Date buildYear) {
		this.buildYear = buildYear;
	}
	public String getQsxkzh() {
		return qsxkzh;
	}
	public void setQsxkzh(String qsxkzh) {
		this.qsxkzh = qsxkzh;
	}
	public BigDecimal getWellDeep() {
		return wellDeep;
	}
	public void setWellDeep(BigDecimal wellDeep) {
		this.wellDeep = wellDeep;
	}
	public BigDecimal getWaterDeep() {
		return waterDeep;
	}
	public void setWaterDeep(BigDecimal waterDeep) {
		this.waterDeep = waterDeep;
	}
	public String getWaterQuality() {
		return waterQuality;
	}
	public void setWaterQuality(String waterQuality) {
		this.waterQuality = waterQuality;
	}
	public BigDecimal getPumpPower() {
		return pumpPower;
	}
	public void setPumpPower(BigDecimal pumpPower) {
		this.pumpPower = pumpPower;
	}
	public BigDecimal getPerWtOut() {
		return perWtOut;
	}
	public void setPerWtOut(BigDecimal perWtOut) {
		this.perWtOut = perWtOut;
	}
	public BigDecimal getPerEleOut() {
		return perEleOut;
	}
	public void setPerEleOut(BigDecimal perEleOut) {
		this.perEleOut = perEleOut;
	}
	public BigDecimal getYc() {
		return yc;
	}
	public void setYc(BigDecimal yc) {
		this.yc = yc;
	}
	public BigDecimal getCircle() {
		return circle;
	}
	public void setCircle(BigDecimal circle) {
		this.circle = circle;
	}
	public java.lang.Integer getYearNumber() {
		return yearNumber;
	}
	public void setYearNumber(java.lang.Integer yearNumber) {
		this.yearNumber = yearNumber;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerTel() {
		return managerTel;
	}
	public void setManagerTel(String managerTel) {
		this.managerTel = managerTel;
	}
	public java.lang.Integer getDevID() {
		return devID;
	}
	public void setDevID(java.lang.Integer devID) {
		this.devID = devID;
	}
	public String getNetType() {
		return netType;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}
	public String getCezhanID() {
		return cezhanID;
	}
	public void setCezhanID(String cezhanID) {
		this.cezhanID = cezhanID;
	}
	public String getSimID() {
		return simID;
	}
	public void setSimID(String simID) {
		this.simID = simID;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}