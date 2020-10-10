package com.fourfaith.siteManage.model;

/**   
 * @Title: Entity
 * @Description: 报警记录
 * @author  administrator
 * @date 2016-06-16 09:45:40
 * @version V1.0   
 */
public class StAlarmInfo implements java.io.Serializable{
	
	private static final long serialVersionUID = 8897723872784834053L;
	
	/**标识*/
	private java.lang.String id;
	/**测站编码*/
	private java.lang.String stcd;
	/**(0:水位；1:每秒流量；2:每小时流量；3:累计流量;4：电压电压，5：信号强度)*/
	private java.lang.Integer type;
	/**报警相关量值*/
	private java.lang.String factorvalue;
	/**报警时间*/
	private java.util.Date tm;
	
	private java.lang.String stnm;
	
	private java.lang.String addvnm;
	
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getStcd() {
		return stcd;
	}
	public void setStcd(java.lang.String stcd) {
		this.stcd = stcd;
	}
	public java.lang.String getFactorvalue() {
		return factorvalue;
	}
	public void setFactorvalue(java.lang.String factorvalue) {
		this.factorvalue = factorvalue;
	}
	public java.lang.Integer getType() {
		return type;
	}
	public void setType(java.lang.Integer type) {
		this.type = type;
	}
	public java.util.Date getTm() {
		return tm;
	}
	public void setTm(java.util.Date tm) {
		this.tm = tm;
	}
	public java.lang.String getStnm() {
		return stnm;
	}
	public void setStnm(java.lang.String stnm) {
		this.stnm = stnm;
	}
	public java.lang.String getAddvnm() {
		return addvnm;
	}
	public void setAddvnm(java.lang.String addvnm) {
		this.addvnm = addvnm;
	}
	
}