package com.fourfaith.chargeManage.model;

import java.io.Serializable;

/**
 * 充值查询返回实体类
 * @author 刘海年
 *
 */
public class MaccVo implements Serializable {
	private static final long serialVersionUID = 1L;
	String userCode;//机井编码
	String messagePwd;  //通讯密码
	String serialNumber;//流水号
	String dateTime;//下发时间
	String himura;//村号
	String family;//户号
	String createTotalMoney;//总购金额
	String createChargeTimes;//充值次数
	String balanc;//剩余金额
	String voltameter;//用户用电量
	String waterYield;//用户用水量
	
	public String getMessagePwd() {
		return messagePwd;
	}
	public void setMessagePwd(String messagePwd) {
		this.messagePwd = messagePwd;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getHimura() {
		return himura;
	}
	public void setHimura(String himura) {
		this.himura = himura;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getCreateTotalMoney() {
		return createTotalMoney;
	}
	public void setCreateTotalMoney(String createTotalMoney) {
		this.createTotalMoney = createTotalMoney;
	}
	public String getCreateChargeTimes() {
		return createChargeTimes;
	}
	public void setCreateChargeTimes(String createChargeTimes) {
		this.createChargeTimes = createChargeTimes;
	}
	public String getBalanc() {
		return balanc;
	}
	public void setBalanc(String balanc) {
		this.balanc = balanc;
	}
	public String getVoltameter() {
		return voltameter;
	}
	public void setVoltameter(String voltameter) {
		this.voltameter = voltameter;
	}
	public String getWaterYield() {
		return waterYield;
	}
	public void setWaterYield(String waterYield) {
		this.waterYield = waterYield;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
