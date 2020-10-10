package com.fourfaith.chargeManage.model;

import java.math.BigDecimal;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

@ExcelTarget("financeStatementsExcel")
public class FinanceStatementsExcel {
		
	@Excel(name="水管区编码",orderNum="1")
	private String waterareacode;
	@Excel(name="水管区名称",orderNum="2")
    private String waterAreaName;
	@Excel(name="卡号",orderNum="3")
    private String cardcode;
	@Excel(name="订单编号",orderNum="4")
    private String ordercode;
	@Excel(name="机井名称",orderNum="5")
    private String devicename;
	@Excel(name="配水年份",orderNum="6")
    private Integer distyear;
	@Excel(name="配水轮次",orderNum="7")
    private Integer distround;
	@Excel(name="水方单价",orderNum="8")
    private BigDecimal distprice;
	@Excel(name="总用水量",orderNum="9")
    private BigDecimal distwater;
	@Excel(name="总价",orderNum="10")
    private BigDecimal totalprice;
	@Excel(name="充值时间",orderNum="11")
    private String createtime;
	
	
	public String getWaterAreaName() {
		return waterAreaName;
	}
	public void setWaterAreaName(String waterAreaName) {
		this.waterAreaName = waterAreaName;
	}
	
	
	public FinanceStatementsExcel(String waterareacode, String waterAreaName,
			String cardcode, String ordercode, String devicename,
			Integer distyear, Integer distround, BigDecimal distprice,
			BigDecimal distwater, BigDecimal totalprice, String createtime) {
		super();
		this.waterareacode = waterareacode;
		this.waterAreaName = waterAreaName;
		this.cardcode = cardcode;
		this.ordercode = ordercode;
		this.devicename = devicename;
		this.distyear = distyear;
		this.distround = distround;
		this.distprice = distprice;
		this.distwater = distwater;
		this.totalprice = totalprice;
		this.createtime = createtime;
	}
	public FinanceStatementsExcel() {
		super();
	}
	public String getCardcode() {
		return cardcode;
	}
	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public Integer getDistyear() {
		return distyear;
	}
	public void setDistyear(Integer distyear) {
		this.distyear = distyear;
	}
	public Integer getDistround() {
		return distround;
	}
	public void setDistround(Integer distround) {
		this.distround = distround;
	}
	public BigDecimal getDistprice() {
		return distprice;
	}
	public void setDistprice(BigDecimal distprice) {
		this.distprice = distprice;
	}
	public BigDecimal getDistwater() {
		return distwater;
	}
	public void setDistwater(BigDecimal distwater) {
		this.distwater = distwater;
	}
	public BigDecimal getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getWaterareacode() {
		return waterareacode;
	}
	public void setWaterareacode(String waterareacode) {
		this.waterareacode = waterareacode;
	}
	
	
	
	

}
