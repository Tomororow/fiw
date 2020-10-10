package com.fourfaith.sysManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 机井设备扩展信息表
 * @author  Dan
 * @date 2016-09-18 20:21:05
 * @version V2.0   
 */
public class BaseDeviceExpandInfo implements Serializable {
	
	private static final long serialVersionUID = -7147452726637382832L;
	
	/**设备标识*/
	private String deviceId;
	/**设备编号*/
	private String deviceCode;
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
	/**配置水量*/
	private BigDecimal distWater;
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
	private String wellUse;
	/**灌溉模式*/
	private String irrigationMode;
	/**控制面积*/
	private BigDecimal kZArea;
	/**实际灌溉面积*/
	private BigDecimal sJArea;
	/**实际供水人口*/
	private java.lang.Integer sJSupplyWaterPeople;
	/**是否办理取水许可证*/
	private java.lang.Integer isHandleDocument;
	/**取水许可证编号*/
	private String waterIntakeNo;
	/**年许可取水*/
	private BigDecimal yearWaterSum;
	/**是否办理工业取水手续*/
	private java.lang.Integer isIndustryProcedure;
	/**年核定水量*/
	private BigDecimal industryApprovedWater;
	/**生产规模*/
	private BigDecimal industryProductionCapacity;
	/**工业区面积*/
	private BigDecimal industryArea;
	/**是否安装水量计量设施*/
	private java.lang.Integer waterMeterMeasurement;
	/**水量计量设施类型*/
	private String waterMeterMeasurementType;
	/**水表型号*/
	private String meterType;
	/**水表口径*/
	private BigDecimal meterCaliber;
	/**水表编号*/
	private String meterSerialNo;
	/**有无水表防护罩*/
	private java.lang.Integer meterGuard;
	/**所在地貌类型区*/
	private String landformType;
	/**水资源区域*/
	private String waterArea;
	/**所在灌区类型*/
	private String irrigationAreaType;
	/**备注*/
	private String note;
	
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
	
	public BigDecimal getDistWater() {
		return distWater;
	}
	public void setDistWater(BigDecimal distWater) {
		this.distWater = distWater;
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
	public java.lang.Integer getsJSupplyWaterPeople() {
		return sJSupplyWaterPeople;
	}
	public void setsJSupplyWaterPeople(java.lang.Integer sJSupplyWaterPeople) {
		this.sJSupplyWaterPeople = sJSupplyWaterPeople;
	}
	public java.lang.Integer getIsHandleDocument() {
		return isHandleDocument;
	}
	public void setIsHandleDocument(java.lang.Integer isHandleDocument) {
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
	public java.lang.Integer getIsIndustryProcedure() {
		return isIndustryProcedure;
	}
	public void setIsIndustryProcedure(java.lang.Integer isIndustryProcedure) {
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
	public java.lang.Integer getWaterMeterMeasurement() {
		return waterMeterMeasurement;
	}
	public void setWaterMeterMeasurement(java.lang.Integer waterMeterMeasurement) {
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
	public java.lang.Integer getMeterGuard() {
		return meterGuard;
	}
	public void setMeterGuard(java.lang.Integer meterGuard) {
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