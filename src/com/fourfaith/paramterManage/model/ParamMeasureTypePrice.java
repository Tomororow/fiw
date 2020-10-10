package com.fourfaith.paramterManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: ParamMeasureTypePrice
 * @Description: 计量水费实体
 * @Author: zhaojx
 * @Date: 2017年9月10日 下午7:03:41
 */
public class ParamMeasureTypePrice implements Serializable{

	private static final long serialVersionUID = 6774962071717655515L;
	
	private String id;                    // 主键Id
	private Integer distType;             // 水井类型1：灌溉；2：工业；3：生活
	private BigDecimal measureTypePrice;  // 计量水费
	private Integer inYear;               // 征收年份
	private Date createTime;              // 创建时间
	private Date EditTime;                // 修改时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getDistType() {
		return distType;
	}
	public void setDistType(Integer distType) {
		this.distType = distType;
	}
	public BigDecimal getMeasureTypePrice() {
		return measureTypePrice;
	}
	public void setMeasureTypePrice(BigDecimal measureTypePrice) {
		this.measureTypePrice = measureTypePrice;
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