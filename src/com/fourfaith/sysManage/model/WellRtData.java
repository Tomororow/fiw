package com.fourfaith.sysManage.model;
import java.math.BigDecimal;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 实时数据表
 * @author  Dan
 * @date 2016-08-24 22:21:40
 * @version V1.0   
 */
public class WellRtData implements java.io.Serializable {
	
	private static final long serialVersionUID = 3408558404387230471L;
	
	/**机井编码*/
	private String wellId;
	/**采集时间*/
	private Date collectTime;
	/**用户号*/
	private int userNo;
	/**当前用水量*/
	private BigDecimal useWt;
	/**剩余水量*/
	private BigDecimal leftWt;
	/**当前水表底数*/
	private BigDecimal curWtBase;
	/**开关机状态(0:关机，1：开机)*/
	private int openState;
	/**箱门状态(0:箱门关闭，1：箱门打开)*/
	private int doolState;
	/**网络状态(0:离线，1：在线)*/
	private int netState;
	/**模拟量0*/
	private int ai0;
	/**模拟量1*/
	private int ai1;
	/**模拟量2*/
	private int ai2;
	/**模拟量3*/
	private int ai3;
	/**模拟量4*/
	private int ai4;
	/**累计用电量*/
	private BigDecimal usePower;
	/**A相电压*/
	private BigDecimal av;
	/**B相电压*/
	private BigDecimal bv;
	/**C相电压*/
	private BigDecimal cv;
	/**A相电流*/
	private BigDecimal aa;
	/**B相电流*/
	private BigDecimal ba;
	/**C相电流*/
	private BigDecimal ca;
	/**A相状态*/
	private int aState;
	/**B相状态*/
	private int bState;
	/**C相状态*/
	private int cState;
	/**报警时间*/
	private Date alarmTime;
	/**报警状态*/
	private int alarmState;
	/**用水时间*/
	private BigDecimal useWaterTime;
	/***/
	private BigDecimal totalDistri;
	/**累计用电量*/
	private BigDecimal totalPower;
	/**水表状态*/
	private int waterMeterState;
	/**异常信息*/
	private int exception;
	/**异常状态*/
	private String exceptionState;
	/**关泵时间(新加)*/
	private Date pumpCloseTime;
	
	public String getWellId() {
		return wellId;
	}

	public void setWellId(String wellId) {
		this.wellId = wellId;
	}

	public Date getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public BigDecimal getUseWt() {
		return useWt;
	}

	public void setUseWt(BigDecimal useWt) {
		this.useWt = useWt;
	}

	public BigDecimal getLeftWt() {
		return leftWt;
	}

	public void setLeftWt(BigDecimal leftWt) {
		this.leftWt = leftWt;
	}

	public BigDecimal getCurWtBase() {
		return curWtBase;
	}

	public void setCurWtBase(BigDecimal curWtBase) {
		this.curWtBase = curWtBase;
	}

	public int getOpenState() {
		return openState;
	}

	public void setOpenState(int openState) {
		this.openState = openState;
	}

	public int getDoolState() {
		return doolState;
	}

	public void setDoolState(int doolState) {
		this.doolState = doolState;
	}

	public int getNetState() {
		return netState;
	}

	public void setNetState(int netState) {
		this.netState = netState;
	}

	public int getAi0() {
		return ai0;
	}

	public void setAi0(int ai0) {
		this.ai0 = ai0;
	}

	public int getAi1() {
		return ai1;
	}

	public void setAi1(int ai1) {
		this.ai1 = ai1;
	}

	public int getAi2() {
		return ai2;
	}

	public void setAi2(int ai2) {
		this.ai2 = ai2;
	}

	public int getAi3() {
		return ai3;
	}

	public void setAi3(int ai3) {
		this.ai3 = ai3;
	}

	public int getAi4() {
		return ai4;
	}

	public void setAi4(int ai4) {
		this.ai4 = ai4;
	}

	public BigDecimal getUsePower() {
		return usePower;
	}

	public void setUsePower(BigDecimal usePower) {
		this.usePower = usePower;
	}

	public BigDecimal getAv() {
		return av;
	}

	public void setAv(BigDecimal av) {
		this.av = av;
	}

	public BigDecimal getBv() {
		return bv;
	}

	public void setBv(BigDecimal bv) {
		this.bv = bv;
	}

	public BigDecimal getCv() {
		return cv;
	}

	public void setCv(BigDecimal cv) {
		this.cv = cv;
	}

	public BigDecimal getAa() {
		return aa;
	}

	public void setAa(BigDecimal aa) {
		this.aa = aa;
	}

	public BigDecimal getBa() {
		return ba;
	}

	public void setBa(BigDecimal ba) {
		this.ba = ba;
	}

	public BigDecimal getCa() {
		return ca;
	}

	public void setCa(BigDecimal ca) {
		this.ca = ca;
	}

	public int getaState() {
		return aState;
	}

	public void setaState(int aState) {
		this.aState = aState;
	}

	public int getbState() {
		return bState;
	}

	public void setbState(int bState) {
		this.bState = bState;
	}

	public int getcState() {
		return cState;
	}

	public void setcState(int cState) {
		this.cState = cState;
	}

	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public int getAlarmState() {
		return alarmState;
	}

	public void setAlarmState(int alarmState) {
		this.alarmState = alarmState;
	}

	public BigDecimal getUseWaterTime() {
		return useWaterTime;
	}

	public void setUseWaterTime(BigDecimal useWaterTime) {
		this.useWaterTime = useWaterTime;
	}

	public BigDecimal getTotalDistri() {
		return totalDistri;
	}

	public void setTotalDistri(BigDecimal totalDistri) {
		this.totalDistri = totalDistri;
	}

	public BigDecimal getTotalPower() {
		return totalPower;
	}

	public void setTotalPower(BigDecimal totalPower) {
		this.totalPower = totalPower;
	}

	public int getWaterMeterState() {
		return waterMeterState;
	}

	public void setWaterMeterState(int waterMeterState) {
		this.waterMeterState = waterMeterState;
	}

	public int getException() {
		return exception;
	}

	public void setException(int exception) {
		this.exception = exception;
	}

	public String getExceptionState() {
		return exceptionState;
	}

	public void setExceptionState(String exceptionState) {
		this.exceptionState = exceptionState;
	}

	public Date getPumpCloseTime() {
		return pumpCloseTime;
	}

	public void setPumpCloseTime(Date pumpCloseTime) {
		this.pumpCloseTime = pumpCloseTime;
	}
	
}