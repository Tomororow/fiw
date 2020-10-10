package com.fourfaith.statisticAnalysis.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * @ClassName: useWaterOfDayByExcelVO
 * @Description: 年用水报表导出实体
 * @Author: zhaojinxin
 * @Date: 2019年1月22日 下午6:03:24
 */
@ExcelTarget("useWaterOfYearByExcelVO")
public class useWaterOfYearByExcelVO{
	
	@Excel(name = "机井编码", orderNum = "1",width=15)
	private String deviceCode;
	
	@Excel(name = "机井名称", orderNum = "2",width=25)
	private String deviceName;
	
	@Excel(name = "水卡卡号", orderNum = "3",width=25)
	private String cardCode;
	
	@Excel(name = "联系电话", orderNum = "4",width=25)
	private String ownerTelphone;
	
	@Excel(name = "采集时间", orderNum = "5", databaseFormat = "yyyy", format = "yyyy",width=15)
	private Date createTime;
	
	@Excel(name = "年用水量(m³)", orderNum = "6", type = 10, isStatistics = true,width=15)
	private BigDecimal useWaterOfYear;
	
	public useWaterOfYearByExcelVO() {
        super();
    }

	public useWaterOfYearByExcelVO(String deviceCode, String deviceName,
			String cardCode, String ownerTelphone, Date createTime,
			BigDecimal useWaterOfYear) {
		super();
		this.deviceCode = deviceCode;
		this.deviceName = deviceName;
		this.cardCode = cardCode;
		this.ownerTelphone = ownerTelphone;
		this.createTime = createTime;
		this.useWaterOfYear = useWaterOfYear;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getOwnerTelphone() {
		return ownerTelphone;
	}

	public void setOwnerTelphone(String ownerTelphone) {
		this.ownerTelphone = ownerTelphone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getUseWaterOfYear() {
		return useWaterOfYear;
	}

	public void setUseWaterOfYear(BigDecimal useWaterOfYear) {
		this.useWaterOfYear = useWaterOfYear;
	}

}