package com.fourfaith.sysManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**   
 * @Title: Entity
 * @Description: 机井设备动态信息表
 * @author  Dan
 * @date 2016-09-18 20:21:05
 * @version V2.0   
 */
public class BaseDeviceDynamicInfo implements Serializable {
	
	private static final long serialVersionUID = 4889351543419979089L;
	
	/**设备标识*/
	private String deviceId;
	/**设备编号*/
	private String deviceCode;
	/**当前卡号*/
	private String cardCode;
	/**采集时间*/
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private String collectTime;
	/**离线时间*/
	private Date offlineTime;
	/**最新通讯时间(心跳会更新该时间)*/
	private Date commTime;
	/**网络状态,0:离线;1:在线*/
	private java.lang.Integer netState;
	/**网络类型*/
	private java.lang.Integer netType;
	/**箱门状态,0:关闭;1:开启*/
	private java.lang.Integer doorState;
	/**水泵状态,0:关闭;1:开启*/
	private java.lang.Integer pumpState;
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
	/**最新用水量*/
	private BigDecimal useWater;
	/**最新用电量*/
	private BigDecimal usePower;
	/**累计用水量*/
	private BigDecimal totalWater;
	/**累计用电量*/
	private BigDecimal totalPower;
	/**瞬时流量*/
	private BigDecimal instantFlow;
	/**累计流量*/
	private BigDecimal totalFlow;
	/**备份通道总累计流量*/
	private BigDecimal backupFlowTotal;
	/**备份通道总时长*/
	private BigDecimal backupTimeTotal;
	/**剩余水量*/
	private Integer leaveWater;
	/**剩余金额*/
	private BigDecimal leaveMoney;
	/**单价*/
	private BigDecimal waterPrice;
	/**瞬时流速*/
	private BigDecimal instantSpeed;
	/**A相电流*/
	private BigDecimal aPhaseCurrent;
	/**B相电流*/
	private BigDecimal bPhaseCurrent;
	/**C相电流*/
	private BigDecimal cPhaseCurrent;
	/**A相电压*/
	private BigDecimal aPhaseVoltage;
	/**B相电压*/
	private BigDecimal bPhaseVoltage;
	/**C相电压*/
	private BigDecimal cPhaseVoltage;
	/**A相运行功率*/
	private BigDecimal aPhasePower;
	/**B相运行功率*/
	private BigDecimal bPhasePower;
	/**C相运行功率*/
	private BigDecimal cPhasePower;
	/**总功率*/
	private BigDecimal tTPhasePower;
	/**ip地址*/
	private String ipPort;
	//年累计用水量
	private BigDecimal yearUseWater;
	
	private String shaftnumber;

    private String  gdatetime;

    private String versions;

    private BigDecimal accumulatedannualwater;

    private BigDecimal hpurwater;

    private BigDecimal nozzlelevel;

    private BigDecimal pipelinepressure;

    private String userid;

    private BigDecimal suminsidecard;

    private BigDecimal userpowerconsumption;

    private BigDecimal userwaterconsumption;

    private String signals;

    private String divs;

    private String comm1;

    private String comm2;

    private String comm3;
	
	
    
	public BigDecimal getYearUseWater() {
		return yearUseWater;
	}
	public void setYearUseWater(BigDecimal yearUseWater) {
		this.yearUseWater = yearUseWater;
	}
	//非数据库字段
	/**设备名称*/
	private String deviceName;
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}
	public Date getOfflineTime() {
		return offlineTime;
	}
	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}
	public java.lang.Integer getNetState() {
		return netState;
	}
	public void setNetState(java.lang.Integer netState) {
		this.netState = netState;
	}
	public java.lang.Integer getNetType() {
		return netType;
	}
	public void setNetType(java.lang.Integer netType) {
		this.netType = netType;
	}
	public java.lang.Integer getDoorState() {
		return doorState;
	}
	public void setDoorState(java.lang.Integer doorState) {
		this.doorState = doorState;
	}
	public java.lang.Integer getPumpState() {
		return pumpState;
	}
	public void setPumpState(java.lang.Integer pumpState) {
		this.pumpState = pumpState;
	}
	
	public BigDecimal getUseWater() {
		return useWater;
	}
	public void setUseWater(BigDecimal useWater) {
		this.useWater = useWater;
	}
	public BigDecimal getTotalWater() {
		return totalWater;
	}
	public void setTotalWater(BigDecimal totalWater) {
		this.totalWater = totalWater;
	}
	public BigDecimal getTotalPower() {
		return totalPower;
	}
	public void setTotalPower(BigDecimal totalPower) {
		this.totalPower = totalPower;
	}
	public BigDecimal getUsePower() {
		return usePower;
	}
	public void setUsePower(BigDecimal usePower) {
		this.usePower = usePower;
	}
	public BigDecimal getInstantFlow() {
		return instantFlow;
	}
	public void setInstantFlow(BigDecimal instantFlow) {
		this.instantFlow = instantFlow;
	}
	public BigDecimal getTotalFlow() {
		return totalFlow;
	}
	public void setTotalFlow(BigDecimal totalFlow) {
		this.totalFlow = totalFlow;
	}
	public BigDecimal getInstantSpeed() {
		return instantSpeed;
	}
	public void setInstantSpeed(BigDecimal instantSpeed) {
		this.instantSpeed = instantSpeed;
	}
	public BigDecimal getaPhaseCurrent() {
		return aPhaseCurrent;
	}
	public void setaPhaseCurrent(BigDecimal aPhaseCurrent) {
		this.aPhaseCurrent = aPhaseCurrent;
	}
	public BigDecimal getbPhaseCurrent() {
		return bPhaseCurrent;
	}
	public void setbPhaseCurrent(BigDecimal bPhaseCurrent) {
		this.bPhaseCurrent = bPhaseCurrent;
	}
	public BigDecimal getcPhaseCurrent() {
		return cPhaseCurrent;
	}
	public void setcPhaseCurrent(BigDecimal cPhaseCurrent) {
		this.cPhaseCurrent = cPhaseCurrent;
	}
	public BigDecimal getaPhaseVoltage() {
		return aPhaseVoltage;
	}
	public void setaPhaseVoltage(BigDecimal aPhaseVoltage) {
		this.aPhaseVoltage = aPhaseVoltage;
	}
	public BigDecimal getbPhaseVoltage() {
		return bPhaseVoltage;
	}
	public void setbPhaseVoltage(BigDecimal bPhaseVoltage) {
		this.bPhaseVoltage = bPhaseVoltage;
	}
	public BigDecimal getcPhaseVoltage() {
		return cPhaseVoltage;
	}
	public void setcPhaseVoltage(BigDecimal cPhaseVoltage) {
		this.cPhaseVoltage = cPhaseVoltage;
	}
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
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getShaftnumber() {
		return shaftnumber;
	}
	public void setShaftnumber(String shaftnumber) {
		this.shaftnumber = shaftnumber;
	}
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	public String getGdatetime() {
		return gdatetime;
	}
	public void setGdatetime(String gdatetime) {
		this.gdatetime = gdatetime;
	}
	public String getVersions() {
		return versions;
	}
	public void setVersions(String versions) {
		this.versions = versions;
	}
	public BigDecimal getAccumulatedannualwater() {
		return accumulatedannualwater;
	}
	public void setAccumulatedannualwater(BigDecimal accumulatedannualwater) {
		this.accumulatedannualwater = accumulatedannualwater;
	}
	public BigDecimal getHpurwater() {
		return hpurwater;
	}
	public void setHpurwater(BigDecimal hpurwater) {
		this.hpurwater = hpurwater;
	}
	public BigDecimal getNozzlelevel() {
		return nozzlelevel;
	}
	public void setNozzlelevel(BigDecimal nozzlelevel) {
		this.nozzlelevel = nozzlelevel;
	}
	public BigDecimal getPipelinepressure() {
		return pipelinepressure;
	}
	public void setPipelinepressure(BigDecimal pipelinepressure) {
		this.pipelinepressure = pipelinepressure;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public BigDecimal getSuminsidecard() {
		return suminsidecard;
	}
	public void setSuminsidecard(BigDecimal suminsidecard) {
		this.suminsidecard = suminsidecard;
	}
	public BigDecimal getUserpowerconsumption() {
		return userpowerconsumption;
	}
	public void setUserpowerconsumption(BigDecimal userpowerconsumption) {
		this.userpowerconsumption = userpowerconsumption;
	}
	public BigDecimal getUserwaterconsumption() {
		return userwaterconsumption;
	}
	public void setUserwaterconsumption(BigDecimal userwaterconsumption) {
		this.userwaterconsumption = userwaterconsumption;
	}
	public String getSignals() {
		return signals;
	}
	public void setSignals(String signals) {
		this.signals = signals;
	}
	public String getDivs() {
		return divs;
	}
	public void setDivs(String divs) {
		this.divs = divs;
	}
	public String getComm1() {
		return comm1;
	}
	public void setComm1(String comm1) {
		this.comm1 = comm1;
	}
	public String getComm2() {
		return comm2;
	}
	public void setComm2(String comm2) {
		this.comm2 = comm2;
	}
	public String getComm3() {
		return comm3;
	}
	public void setComm3(String comm3) {
		this.comm3 = comm3;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public Date getCommTime() {
		return commTime;
	}
	public void setCommTime(Date commTime) {
		this.commTime = commTime;
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
	public BigDecimal getBackupFlowTotal() {
		return backupFlowTotal;
	}
	public void setBackupFlowTotal(BigDecimal backupFlowTotal) {
		this.backupFlowTotal = backupFlowTotal;
	}
	public BigDecimal getBackupTimeTotal() {
		return backupTimeTotal;
	}
	public void setBackupTimeTotal(BigDecimal backupTimeTotal) {
		this.backupTimeTotal = backupTimeTotal;
	}
	public Integer getLeaveWater() {
		return leaveWater;
	}
	public void setLeaveWater(Integer leaveWater) {
		this.leaveWater = leaveWater;
	}
	public BigDecimal getLeaveMoney() {
		return leaveMoney;
	}
	public void setLeaveMoney(BigDecimal leaveMoney) {
		this.leaveMoney = leaveMoney;
	}
	public BigDecimal getWaterPrice() {
		return waterPrice;
	}
	public void setWaterPrice(BigDecimal waterPrice) {
		this.waterPrice = waterPrice;
	}
	public BigDecimal gettTPhasePower() {
		return tTPhasePower;
	}
	public void settTPhasePower(BigDecimal tTPhasePower) {
		this.tTPhasePower = tTPhasePower;
	}
	public String getIpPort() {
		return ipPort;
	}
	public void setIpPort(String ipPort) {
		this.ipPort = ipPort;
	}
	
}