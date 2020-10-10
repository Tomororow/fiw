package com.fourfaith.statisticAnalysis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * 设备机井基本信息查询报表
 * @author Administrator
 * 2016年11月7日
 */
@ExcelTarget("rptBaseDeviceDetail")
public class RptBaseDeviceDetail implements Serializable{
	
	private static final long serialVersionUID = -2763053135105069820L;
	
	/**设备标识*/
	private String id;
	/**所属行政区域Id*/
	private String areaId;
	/**所属水管区域Id*/
	private String waterAreaId;
	/**机井设备编码*/
	@Excel(name = "机井编码", orderNum = "1",width=18)
	private String deviceCode;
	/**机井行政码*/
	@Excel(name = "机井行政码", orderNum = "2",width=18)
	private String deviceAreaCode;
	/**机井水管码*/
	@Excel(name = "机井水管码", orderNum = "3",width=18)
	private String deviceWaterAreaCode;
	/**设备号，控制器编号*/
	private String deviceNo;
	/**机井设备名称*/
	@Excel(name = "机井设备名称", orderNum = "4",width=30)
	private String deviceName;
	/**设备型号*/
	private String deviceModel;
	/**手机卡号*/
	@Excel(name = "手机卡号", orderNum = "5",width=18)
	private String simCard;
	/**手机卡运营商*/
	private String simOperator;
	/**安装地点*/
	private String installAddress;
	/**安装时间*/
	@Excel(name = "安装时间", orderNum = "8",databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss",width=18)
	private Date installTime;
	/**监测区域*/
	private String monitorArea;
	/**经度*/
	@Excel(name = "经度", orderNum = "9",width=18)
	private BigDecimal longitude;
	/**纬度*/
	@Excel(name = "纬度", orderNum = "10",width=18)
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
	@Excel(name = "业主姓名", orderNum = "11",width=18)
	private String ownerName;
	/**业主电话*/
	@Excel(name = "业主电话", orderNum = "12",width=18)
	private String ownerTelphone;
	/**DTU号码*/
	private String dtuNo;
	/**机井安装前照片*/
	private String photoBefore;
	/**机井安装后照片*/
	private String photoAfter;
	/**瞬时流速*/
	private BigDecimal instantSpeed;
	/**行政区域名称*/
	@Excel(name = "行政区域名称", orderNum = "6",width=18)
	private String areaName;
	/**水管区域名称*/
	@Excel(name = "水管区域名称", orderNum = "7",width=18)
	private String waterAreaName;
	/**设备标识*/
	private String deviceId;
	/**机井号牌*/
	private String devicePlate;
	/**管道口径*/
	private BigDecimal pipeDiameter;
	/**井管内径*/
	private BigDecimal wellDiameter;
	/**泵管材质*/
	private String pumpMaterial;
	/**成井时间*/
	private Date makeTime;
	/**井深，单位m*/
	private BigDecimal wellDepth;
	/**地下水埋深，单位m*/
	private BigDecimal groundWaterDepth;
	/**取水类型,地热水,浅层地下水*/
	private String useWaterType;
	/**水泵型号*/
	private String waterPumpNo;
	/**水泵额定扬程*/
	private BigDecimal ratedHead;
	/**水泵额定流量*/
	private BigDecimal ratedFlow;
	/**水泵额定功率*/
	private BigDecimal ratedPower;
	/**应用状况*/
	private String applyStatus;
	/**水井用途分类*/
	@Excel(name = "水井用途", orderNum = "13",width=18)
	private String wellUse;
	/**灌溉模式*/
	private String irrigationMode;
	/**控制面积*/
	private BigDecimal kZArea;
	/**实际灌溉面积*/
	private BigDecimal sJArea;
	/**实际供水人口*/
	private Integer sJSupplyWaterPeople;
	/**是否办理取水许可证*/
	private Integer isHandleDocument;
	/**取水许可证编号*/
	private String waterIntakeNo;
	/**年许可取水*/
	private BigDecimal yearWaterSum;
	/**是否办理工业取水手续*/
	private Integer isIndustryProcedure;
	/**年核定水量*/
	private BigDecimal industryApprovedWater;
	/**生产规模*/
	private BigDecimal industryProductionCapacity;
	/**工业区面积*/
	private BigDecimal industryArea;
	/**是否安装水量计量设施*/
	private Integer waterMeterMeasurement;
	/**水量计量设施类型*/
	private String waterMeterMeasurementType;
	/**水表型号*/
	private String meterType;
	/**水表口径*/
	private BigDecimal meterCaliber;
	/**水表编号*/
	private String meterSerialNo;
	/**有无水表防护罩*/
	private Integer meterGuard;
	/**所在地貌类型区*/
	private String landformType;
	/**水资源区域*/
	private String waterArea;
	/**所在灌区类型*/
	private String irrigationAreaType;
	/**备注*/
	private String note;
	
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
	public BigDecimal getInstantSpeed() {
		return instantSpeed;
	}
	public void setInstantSpeed(BigDecimal instantSpeed) {
		this.instantSpeed = instantSpeed;
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
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDevicePlate() {
		return devicePlate;
	}
	public void setDevicePlate(String devicePlate) {
		this.devicePlate = devicePlate;
	}
	public BigDecimal getPipeDiameter() {
		return pipeDiameter;
	}
	public void setPipeDiameter(BigDecimal pipeDiameter) {
		this.pipeDiameter = pipeDiameter;
	}
	public BigDecimal getWellDiameter() {
		return wellDiameter;
	}
	public void setWellDiameter(BigDecimal wellDiameter) {
		this.wellDiameter = wellDiameter;
	}
	public String getPumpMaterial() {
		return pumpMaterial;
	}
	public void setPumpMaterial(String pumpMaterial) {
		this.pumpMaterial = pumpMaterial;
	}
	public Date getMakeTime() {
		return makeTime;
	}
	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
	}
	public BigDecimal getWellDepth() {
		return wellDepth;
	}
	public void setWellDepth(BigDecimal wellDepth) {
		this.wellDepth = wellDepth;
	}
	public BigDecimal getGroundWaterDepth() {
		return groundWaterDepth;
	}
	public void setGroundWaterDepth(BigDecimal groundWaterDepth) {
		this.groundWaterDepth = groundWaterDepth;
	}
	public String getUseWaterType() {
		return useWaterType;
	}
	public void setUseWaterType(String useWaterType) {
		this.useWaterType = useWaterType;
	}
	public String getWaterPumpNo() {
		return waterPumpNo;
	}
	public void setWaterPumpNo(String waterPumpNo) {
		this.waterPumpNo = waterPumpNo;
	}
	public BigDecimal getRatedHead() {
		return ratedHead;
	}
	public void setRatedHead(BigDecimal ratedHead) {
		this.ratedHead = ratedHead;
	}
	public BigDecimal getRatedFlow() {
		return ratedFlow;
	}
	public void setRatedFlow(BigDecimal ratedFlow) {
		this.ratedFlow = ratedFlow;
	}
	public BigDecimal getRatedPower() {
		return ratedPower;
	}
	public void setRatedPower(BigDecimal ratedPower) {
		this.ratedPower = ratedPower;
	}
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	public String getWellUse() {
		return wellUse;
	}
	public void setWellUse(String wellUse) {
		this.wellUse = wellUse;
	}
	public String getIrrigationMode() {
		return irrigationMode;
	}
	public void setIrrigationMode(String irrigationMode) {
		this.irrigationMode = irrigationMode;
	}
	public BigDecimal getkZArea() {
		return kZArea;
	}
	public void setkZArea(BigDecimal kZArea) {
		this.kZArea = kZArea;
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
	public Integer getIsHandleDocument() {
		return isHandleDocument;
	}
	public void setIsHandleDocument(Integer isHandleDocument) {
		this.isHandleDocument = isHandleDocument;
	}
	public String getWaterIntakeNo() {
		return waterIntakeNo;
	}
	public void setWaterIntakeNo(String waterIntakeNo) {
		this.waterIntakeNo = waterIntakeNo;
	}
	public BigDecimal getYearWaterSum() {
		return yearWaterSum;
	}
	public void setYearWaterSum(BigDecimal yearWaterSum) {
		this.yearWaterSum = yearWaterSum;
	}
	public Integer getIsIndustryProcedure() {
		return isIndustryProcedure;
	}
	public void setIsIndustryProcedure(Integer isIndustryProcedure) {
		this.isIndustryProcedure = isIndustryProcedure;
	}
	public BigDecimal getIndustryApprovedWater() {
		return industryApprovedWater;
	}
	public void setIndustryApprovedWater(BigDecimal industryApprovedWater) {
		this.industryApprovedWater = industryApprovedWater;
	}
	public BigDecimal getIndustryProductionCapacity() {
		return industryProductionCapacity;
	}
	public void setIndustryProductionCapacity(BigDecimal industryProductionCapacity) {
		this.industryProductionCapacity = industryProductionCapacity;
	}
	public BigDecimal getIndustryArea() {
		return industryArea;
	}
	public void setIndustryArea(BigDecimal industryArea) {
		this.industryArea = industryArea;
	}
	public Integer getWaterMeterMeasurement() {
		return waterMeterMeasurement;
	}
	public void setWaterMeterMeasurement(Integer waterMeterMeasurement) {
		this.waterMeterMeasurement = waterMeterMeasurement;
	}
	public String getWaterMeterMeasurementType() {
		return waterMeterMeasurementType;
	}
	public void setWaterMeterMeasurementType(String waterMeterMeasurementType) {
		this.waterMeterMeasurementType = waterMeterMeasurementType;
	}
	public String getMeterType() {
		return meterType;
	}
	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}
	public BigDecimal getMeterCaliber() {
		return meterCaliber;
	}
	public void setMeterCaliber(BigDecimal meterCaliber) {
		this.meterCaliber = meterCaliber;
	}
	public String getMeterSerialNo() {
		return meterSerialNo;
	}
	public void setMeterSerialNo(String meterSerialNo) {
		this.meterSerialNo = meterSerialNo;
	}
	public Integer getMeterGuard() {
		return meterGuard;
	}
	public void setMeterGuard(Integer meterGuard) {
		this.meterGuard = meterGuard;
	}
	public String getLandformType() {
		return landformType;
	}
	public void setLandformType(String landformType) {
		this.landformType = landformType;
	}
	public String getWaterArea() {
		return waterArea;
	}
	public void setWaterArea(String waterArea) {
		this.waterArea = waterArea;
	}
	public String getIrrigationAreaType() {
		return irrigationAreaType;
	}
	public void setIrrigationAreaType(String irrigationAreaType) {
		this.irrigationAreaType = irrigationAreaType;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}