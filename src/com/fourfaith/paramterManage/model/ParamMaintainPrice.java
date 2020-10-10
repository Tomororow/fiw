package com.fourfaith.paramterManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: ParamMaintainPrice
 * @Description: 末级渠系(维修养护)费率实体
 * @Author: zhaojinxin
 * @Date: 2018年9月5日 下午5:49:36
 */
public class ParamMaintainPrice implements Serializable{

	private static final long serialVersionUID = -6992815019897331531L;
	
	private String id;                    // 主键Id
	private BigDecimal maintainPrice;  	  // 末级渠系费率
	private Integer inYear;               // 征收年份
	private Date createTime;              // 创建时间
	private Date EditTime;                // 修改时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getMaintainPrice() {
		return maintainPrice;
	}
	public void setMaintainPrice(BigDecimal maintainPrice) {
		this.maintainPrice = maintainPrice;
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
		return EditTime;
	}
	public void setEditTime(Date editTime) {
		EditTime = editTime;
	}
	
}