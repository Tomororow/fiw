package com.fourfaith.statisticAnalysis.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * @ClassName: RptUseWaterDetailVO
 * @Description: 用水信息excel导出实体
 * @Author: zhaojx
 * @String: 2018年4月11日 下午7:56:39
 */
@ExcelTarget("rptUseWaterDetailVO")
public class RptUseWaterDetailVO{
	
	@Excel(name = "机井编码", orderNum = "1")
	private String deviceCode;
	
	@Excel(name = "机井名称", orderNum = "2")
	private String deviceName;
	
	@Excel(name = "水卡卡号", orderNum = "3")
	private String cardCode;
	
	@Excel(name = "业主姓名", orderNum = "4")
	private String ownerName;
	
	@Excel(name = "业主电话", orderNum = "5")
	private String ownerTelphone;
	
	@Excel(name = "已用水量", orderNum = "6", type = 10)
	private BigDecimal useWater;
	
	@Excel(name = "本次用水量", orderNum = "7", type = 10, isStatistics = true)
	private double useWaterResult;
	
	@Excel(name = "剩余水量", orderNum = "8", type = 10)
	private BigDecimal remainWater;
	
	@Excel(name = "通电时间", orderNum = "9", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss")
	private Date openPumpTime;
	
	@Excel(name = "采集时间", orderNum = "10", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd HH:mm:ss")
	private Date stopPumpTime;

	public RptUseWaterDetailVO() {
        super();
    }
	
	public RptUseWaterDetailVO(String deviceName, String deviceCode,
			String cardCode, String ownerName, String ownerTelphone,
			BigDecimal useWater, double useWaterResult,
			BigDecimal remainWater, Date openPumpTime, Date stopPumpTime) {
		this.deviceName = deviceName;
		this.deviceCode = deviceCode;
		this.cardCode = cardCode;
		this.ownerName = ownerName;
		this.ownerTelphone = ownerTelphone;
		this.useWater = useWater;
		this.useWaterResult = useWaterResult;
		this.remainWater = remainWater;
		this.openPumpTime = openPumpTime;
		this.stopPumpTime = stopPumpTime;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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

	public BigDecimal getUseWater() {
		return useWater;
	}

	public void setUseWater(BigDecimal useWater) {
		this.useWater = useWater;
	}

	public double getUseWaterResult() {
		return useWaterResult;
	}

	public void setUseWaterResult(double useWaterResult) {
		this.useWaterResult = useWaterResult;
	}

	public BigDecimal getRemainWater() {
		return remainWater;
	}

	public void setRemainWater(BigDecimal remainWater) {
		this.remainWater = remainWater;
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
	
}