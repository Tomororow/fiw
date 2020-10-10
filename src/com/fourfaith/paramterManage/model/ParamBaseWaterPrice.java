package com.fourfaith.paramterManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: ParamBaseWaterPrice
 * @Description: 基本水费费率实体
 * @Author: zhaojinxin
 * @Date: 2018年8月27日 下午4:53:15
 */
public class ParamBaseWaterPrice implements Serializable{

	private static final long serialVersionUID = 1167811267821680508L;
	
	/** 标识id */
	private String id;
	/** 用水基准单价 */
	private BigDecimal standardPrice;
	/** 超出额定水量单价 */
	private BigDecimal exceedPrice;
	/** 征收年份 */
	private Integer inYear;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date editTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getStandardPrice() {
		return standardPrice;
	}
	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}
	public BigDecimal getExceedPrice() {
		return exceedPrice;
	}
	public void setExceedPrice(BigDecimal exceedPrice) {
		this.exceedPrice = exceedPrice;
	}
	public Integer getInYear() {
		return inYear;
	}
	public void setInYear(Integer inYear) {
		this.inYear = inYear;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	
}