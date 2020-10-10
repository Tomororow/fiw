package com.fourfaith.sysManage.model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**   
 * @Title: Entity
 * @Description: 机井设备基础信息表
 * @author  Dan
 * @date 2016-09-18 20:21:05
 * @version V2.0   
 */
public class BaseDeviceInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = -2763053135105069820L;
	
	/**设备标识*/
	private String id;
	/**所属行政区域Id*/
	private String areaId;
	/**所属水管区域Id*/
	private String waterAreaId;
	/**机井设备编码*/
	private String deviceCode;
	/**机井行政码*/
	private String deviceAreaCode;
	/**机井水管码*/
	private String deviceWaterAreaCode;
	/**设备号，控制器编号*/
	private String deviceNo;
	/**机井设备名称*/
	private String deviceName;
	/**设备型号*/
	private String deviceModel;
	/**手机卡号*/
	private String simCard;
	/**手机卡运营商*/
	private String simOperator;
	/**安装地点*/
	private String installAddress;
	/**安装时间*/
	private Date installTime;
	/**监测区域*/
	private String monitorArea;
	/**经度*/
	private BigDecimal longitude;
	/**纬度*/
	private BigDecimal latitude;
	/**最近一次登录的Ip:Port*/
	private String ipPort;
	/**IMEI标识*/
	private String imei;
	/**计费卡*/
	private String chargeCard;
	/**允许水量*/
	private BigDecimal allowWater;
	/**业主姓名*/
	private String ownerName;
	/**业主电话*/
	private String ownerTelphone;
	/**DTU号码*/
	private String dtuNo;
	/**机井安装前照片*/
	private String photoBefore;
	/**机井安装后照片*/
	private String photoAfter;
	/**设备版本*/
	private String deviceVersion;
	/**设备类型*/
	private Integer deviceType;
	/**创建时间*/
	private Date CreateTime;
	/**修改时间*/
	private Date EditTime;
	/**取水许可证号*/
	private String waterIntakeNo;
	/**站点类型*/
	private Integer siteType;
	
	//WellInfo数据
	/**设备号*/
	private java.lang.Integer devId;
	/**宏电DTU号码*/
	private String cezhanID;
	
	//WellRtData数据
	/**采集时间*/
	private Date collectTimeq;
	/**当前用水量*/
	private BigDecimal useWt;
	/**当前用电量*/
	private BigDecimal usePw;
	/**本次消费金额*/
	private BigDecimal useMn;
	/**剩余水量*/
	private BigDecimal leftWt;
	/**剩余金额*/
	private BigDecimal leftMn;
	/**水表底数*/
	private BigDecimal curWtBase;
	
	/**报警状态，0：无报警；1：有报警*/
	private java.lang.Integer alarmStateq;
	/**开关机状态*/
	private java.lang.Integer openState;
	/**网络状态*/
	private java.lang.Integer netStateq;
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
	
	// basedevicedynamicinfo数据
	/**瞬时流速*/
	private BigDecimal instantSpeed;
	
	private BigDecimal instantFlow;
	
	/**非数据库字段**/
	/**水井类型**/
	private String wellUse;
	/**行政区域名称*/
	private String areaName;
	/**水管区域名称*/
	private String waterAreaName;
	/**报警状态，0：无报警；1：有报警*/
	private java.lang.Integer alarmStatek;
	/**箱门状态,0:关闭;1:开启*/
	private java.lang.Integer doorState;
	/**水泵状态,0:关闭;1:开启*/
	private java.lang.Integer pumpState;
	/**网络状态*/
	private java.lang.Integer netStatek;
	/**开泵时间*/
	private Date openPumpTime;
	/**关泵时间*/
	private Date stopPumpTime;
	/**当前用水量*/
	private BigDecimal useWater;
	/**剩余水量*/
	private BigDecimal remainWater;
	/**A相电压*/
	private BigDecimal aPhaseVoltage;
	/**B相电压*/
	private BigDecimal bPhaseVoltage;
	/**C相电压*/
	private BigDecimal cPhaseVoltage;
	/**A相电流*/
	private BigDecimal aPhaseCurrent;
	/**B相电流*/
	private BigDecimal bPhaseCurrent;
	/**C相电流*/
	private BigDecimal cPhaseCurrent;
	/**采集时间*/
	private Date collectTimek;
	/**最后一次通讯时间*/
	private Date commTime;
	private String commTimee;
	/**累计用水量*/
	private BigDecimal totalWater;
	/**累计流量*/
	private BigDecimal totalFlow;
	/**新加  本次用水量字段*/
	public double useWaterResult;
	
	private Integer netState;
	
	/**新加  控制器升级状态*/
	private Integer UpgradeType;
	/** 自动设参标记字段 */
	private Integer AutoParamType;
	/**卡号*/
	private String relCardCode;
	/**卡片联系人*/
	private String relOwnerName;
	
	private Date upTime;
	
	//年累计用水量
	private BigDecimal yearUseWater;
	
	/**实际灌溉面积*/
	private BigDecimal sJArea;
	/**实际供水人口*/
	private java.lang.Integer sJSupplyWaterPeople;
	/**年核定水量*/
	private BigDecimal industryApprovedWater;
	
	private List<String> bigMap;
	
	private BigDecimal lo;
	
	private BigDecimal lt;
	
	public BigDecimal getLo() {
		return lo;
	}
	public void setLo(BigDecimal lo) {
		this.lo = lo;
	}
	
	public BigDecimal getLt() {
		return lt;
	}
	public void setLt(BigDecimal lt) {
		this.lt = lt;
	}
	public List<String> getBigMap() {
		return bigMap;
	}
	public void setBigMap(List<String> bigMap) {
		this.bigMap = bigMap;
	}
	public BigDecimal getsJArea() {
		return sJArea;
	}
	public void setsJArea(BigDecimal sJArea) {
		this.sJArea = sJArea;
	}
	public java.lang.Integer getsJSupplyWaterPeople() {
		return sJSupplyWaterPeople;
	}
	public void setsJSupplyWaterPeople(java.lang.Integer sJSupplyWaterPeople) {
		this.sJSupplyWaterPeople = sJSupplyWaterPeople;
	}
	public BigDecimal getIndustryApprovedWater() {
		return industryApprovedWater;
	}
	public void setIndustryApprovedWater(BigDecimal industryApprovedWater) {
		this.industryApprovedWater = industryApprovedWater;
	}
	public BigDecimal getYearUseWater() {
		return yearUseWater;
	}
	public void setYearUseWater(BigDecimal yearUseWater) {
		this.yearUseWater = yearUseWater;
	}
	public Date getUpTime() {
		return upTime;
	}
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}
	//-------2018-10-23----刘海年---------RptUseWaterDetail表中烁光控制器新增字段--------------
	private String shaftnumber;//机井编号

    private BigDecimal totalpower;//累计电量

    private BigDecimal totalwater;//累计水量

    private BigDecimal accumulatedannualwater;//累计年取水量

    private BigDecimal hpurwater;//水表每小时水量

    private BigDecimal nozzlelevel;//取(排)水口水位

    private BigDecimal pipelinepressure;//管道水压

    private String alarmstate;//测控器(终端)状态及报警

    private BigDecimal suminsidecard;//用户卡内余额
    
    private BigDecimal usewater;//用水量，单位吨

    private BigDecimal usepower;//本次用电量，单位0.1度
    
    private String gdatetime;//上报时间
	
	
	
	public String getCommTimee() {
		return commTimee;
	}
	public void setCommTimee(String commTimee) {
		this.commTimee = commTimee;
	}
	public String getRelCardCode() {
		return relCardCode;
	}
	public void setRelCardCode(String relCardCode) {
		this.relCardCode = relCardCode;
	}
	public String getRelOwnerName() {
		return relOwnerName;
	}
	public void setRelOwnerName(String relOwnerName) {
		this.relOwnerName = relOwnerName;
	}
	public BigDecimal getUsewater() {
		return usewater;
	}
	public void setUsewater(BigDecimal usewater) {
		this.usewater = usewater;
	}
	public String getGdatetime() {
		return gdatetime;
	}
	public void setGdatetime(String gdatetime) {
		this.gdatetime = gdatetime;
	}
	public String getShaftnumber() {
		return shaftnumber;
	}
	public void setShaftnumber(String shaftnumber) {
		this.shaftnumber = shaftnumber;
	}
	public BigDecimal getTotalpower() {
		return totalpower;
	}
	public void setTotalpower(BigDecimal totalpower) {
		this.totalpower = totalpower;
	}
	public BigDecimal getTotalwater() {
		return totalwater;
	}
	public void setTotalwater(BigDecimal totalwater) {
		this.totalwater = totalwater;
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
	public String getAlarmstate() {
		return alarmstate;
	}
	public void setAlarmstate(String alarmstate) {
		this.alarmstate = alarmstate;
	}
	public BigDecimal getSuminsidecard() {
		return suminsidecard;
	}
	public void setSuminsidecard(BigDecimal suminsidecard) {
		this.suminsidecard = suminsidecard;
	}
//	public BigDecimal getUsewater() {
//		return usewater;
//	}
//	public void setUsewater(BigDecimal usewater) {
//		this.usewater = usewater;
//	}
	public BigDecimal getUsepower() {
		return usepower;
	}
	public void setUsepower(BigDecimal usepower) {
		this.usepower = usepower;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getWaterAreaId() {
		return waterAreaId;
	}
	public void setWaterAreaId(String waterAreaId) {
		this.waterAreaId = waterAreaId;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getDeviceAreaCode() {
		return deviceAreaCode;
	}
	public void setDeviceAreaCode(String deviceAreaCode) {
		this.deviceAreaCode = deviceAreaCode;
	}
	public String getDeviceWaterAreaCode() {
		return deviceWaterAreaCode;
	}
	public void setDeviceWaterAreaCode(String deviceWaterAreaCode) {
		this.deviceWaterAreaCode = deviceWaterAreaCode;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getSimCard() {
		return simCard;
	}
	public void setSimCard(String simCard) {
		this.simCard = simCard;
	}
	public String getSimOperator() {
		return simOperator;
	}
	public void setSimOperator(String simOperator) {
		this.simOperator = simOperator;
	}
	public String getInstallAddress() {
		return installAddress;
	}
	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
	}
	public Date getInstallTime() {
		return installTime;
	}
	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}
	public String getMonitorArea() {
		return monitorArea;
	}
	public void setMonitorArea(String monitorArea) {
		this.monitorArea = monitorArea;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public String getIpPort() {
		return ipPort;
	}
	public void setIpPort(String ipPort) {
		this.ipPort = ipPort;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getChargeCard() {
		return chargeCard;
	}
	public void setChargeCard(String chargeCard) {
		this.chargeCard = chargeCard;
	}
	public BigDecimal getAllowWater() {
		return allowWater;
	}
	public void setAllowWater(BigDecimal allowWater) {
		this.allowWater = allowWater;
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
	public String getDtuNo() {
		return dtuNo;
	}
	public void setDtuNo(String dtuNo) {
		this.dtuNo = dtuNo;
	}
	public String getPhotoBefore() {
		return photoBefore;
	}
	public void setPhotoBefore(String photoBefore) {
		this.photoBefore = photoBefore;
	}
	public String getPhotoAfter() {
		return photoAfter;
	}
	public void setPhotoAfter(String photoAfter) {
		this.photoAfter = photoAfter;
	}
	public String getDeviceVersion() {
		return deviceVersion;
	}
	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	public Date getEditTime() {
		return EditTime;
	}
	public void setEditTime(Date editTime) {
		EditTime = editTime;
	}
	public java.lang.Integer getDevId() {
		return devId;
	}
	public void setDevId(java.lang.Integer devId) {
		this.devId = devId;
	}
	public String getCezhanID() {
		return cezhanID;
	}
	public void setCezhanID(String cezhanID) {
		this.cezhanID = cezhanID;
	}
	public Date getCollectTimeq() {
		return collectTimeq;
	}
	public void setCollectTimeq(Date collectTimeq) {
		this.collectTimeq = collectTimeq;
	}
	public Date getCollectTimek() {
		return collectTimek;
	}
	public void setCollectTimek(Date collectTimek) {
		this.collectTimek = collectTimek;
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
	public java.lang.Integer getOpenState() {
		return openState;
	}
	public void setOpenState(java.lang.Integer openState) {
		this.openState = openState;
	}
	public java.lang.Integer getNetStateq() {
		return netStateq;
	}
	public void setNetStateq(java.lang.Integer netStateq) {
		this.netStateq = netStateq;
	}
	public java.lang.Integer getNetStatek() {
		return netStatek;
	}
	public void setNetStatek(java.lang.Integer netStatek) {
		this.netStatek = netStatek;
	}
	public String getWellUse() {
		return wellUse;
	}
	public void setWellUse(String wellUse) {
		this.wellUse = wellUse;
	}
	public BigDecimal getInstantSpeed() {
		return instantSpeed;
	}
	public void setInstantSpeed(BigDecimal instantSpeed) {
		this.instantSpeed = instantSpeed;
	}
	
	
	public BigDecimal getInstantFlow() {
		return instantFlow;
	}
	public void setInstantFlow(BigDecimal instantFlow) {
		this.instantFlow = instantFlow;
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
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getWaterAreaName() {
		return waterAreaName;
	}
	public void setWaterAreaName(String waterAreaName) {
		this.waterAreaName = waterAreaName;
	}
	public java.lang.Integer getAlarmStateq() {
		return alarmStateq;
	}
	public void setAlarmStateq(java.lang.Integer alarmStateq) {
		this.alarmStateq = alarmStateq;
	}
	public java.lang.Integer getAlarmStatek() {
		return alarmStatek;
	}
	public void setAlarmStatek(java.lang.Integer alarmStatek) {
		this.alarmStatek = alarmStatek;
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
	public Date getOpenPumpTime() {
		return openPumpTime;
	}
	public void setOpenPumpTime(Date openPumpTime) {
		this.openPumpTime = openPumpTime;
	}
	public Date getStopPumpTime() {
		return stopPumpTime;
	}
	public void setStopPumpTime(Date stopPumpTime) {
		this.stopPumpTime = stopPumpTime;
	}
	public BigDecimal getUseWater() {
		return useWater;
	}
	public void setUseWater(BigDecimal useWater) {
		this.useWater = useWater;
	}
	public BigDecimal getRemainWater() {
		return remainWater;
	}
	public void setRemainWater(BigDecimal remainWater) {
		this.remainWater = remainWater;
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
	public Integer getNetState() {
		return netState;
	}
	public void setNetState(Integer netState) {
		this.netState = netState;
	}
	public double getUseWaterResult() {
		return useWaterResult;
	}
	public void setUseWaterResult(double useWaterResult) {
		this.useWaterResult = useWaterResult;
	}
	public BigDecimal getTotalWater() {
		return totalWater;
	}
	public void setTotalWater(BigDecimal totalWater) {
		this.totalWater = totalWater;
	}
	public BigDecimal getUsePw() {
		return usePw;
	}
	public void setUsePw(BigDecimal usePw) {
		this.usePw = usePw;
	}
	public BigDecimal getUseMn() {
		return useMn;
	}
	public void setUseMn(BigDecimal useMn) {
		this.useMn = useMn;
	}
	public BigDecimal getLeftMn() {
		return leftMn;
	}
	public void setLeftMn(BigDecimal leftMn) {
		this.leftMn = leftMn;
	}
	public Integer getUpgradeType() {
		return UpgradeType;
	}
	public void setUpgradeType(Integer upgradeType) {
		UpgradeType = upgradeType;
	}
	public Integer getAutoParamType() {
		return AutoParamType;
	}
	public void setAutoParamType(Integer autoParamType) {
		AutoParamType = autoParamType;
	}
	public String getWaterIntakeNo() {
		return waterIntakeNo;
	}
	public void setWaterIntakeNo(String waterIntakeNo) {
		this.waterIntakeNo = waterIntakeNo;
	}
	public BigDecimal getCurWtBase() {
		return curWtBase;
	}
	public void setCurWtBase(BigDecimal curWtBase) {
		this.curWtBase = curWtBase;
	}
	public Integer getSiteType() {
		return siteType;
	}
	public void setSiteType(Integer siteType) {
		this.siteType = siteType;
	}
	public BigDecimal getTotalFlow() {
		return totalFlow;
	}
	public void setTotalFlow(BigDecimal totalFlow) {
		this.totalFlow = totalFlow;
	}
	
	public Date getCommTime() {
		return commTime;
	}
	public void setCommTime(Date commTime) {
		this.commTime = commTime;
	}
	
	
}