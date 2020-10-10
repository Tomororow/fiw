package com.fourfaith.statisticAnalysis.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * @ClassName: useWaterOfMonthByExcelVO
 * @Description: 月用水报表导出实体
 * @Author: zhaojinxin
 * @Date: 2019年1月22日 下午6:03:24
 */
@ExcelTarget("useWaterOfMonthByExcelVO")
public class useWaterOfMonthByExcelVO{
	
	@Excel(name = "机井编码", orderNum = "1",width=15)
	private String deviceCode;
	
	@Excel(name = "机井名称", orderNum = "2",width=25)
	private String deviceName;
	
	@Excel(name = "水卡卡号", orderNum = "3",width=25)
	private String cardCode;
	
	@Excel(name = "联系电话", orderNum = "4",width=25)
	private String ownerTelphone;
	
	@Excel(name = "采集时间", orderNum = "5", databaseFormat = "yyyyMM", format = "yyyy-MM",width=15)
	private Date createTime;
	
	@Excel(name = "月用水量(m³)", orderNum = "6", type = 10, isStatistics = true,width=15)
	private BigDecimal useWaterOfMonth;
	
	public useWaterOfMonthByExcelVO() {
        super();
    }

	public useWaterOfMonthByExcelVO(String deviceCode, String deviceName,
			String cardCode, String ownerTelphone, Date createTime,
			BigDecimal useWaterOfMonth) {
		super();
		this.deviceCode = deviceCode;
		this.deviceName = deviceName;
		this.cardCode = cardCode;
		this.ownerTelphone = ownerTelphone;
		this.createTime = createTime;
		this.useWaterOfMonth = useWaterOfMonth;
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

	public BigDecimal getUseWaterOfMonth() {
		return useWaterOfMonth;
	}

	public void setUseWaterOfMonth(BigDecimal useWaterOfMonth) {
		this.useWaterOfMonth = useWaterOfMonth;
	}


}