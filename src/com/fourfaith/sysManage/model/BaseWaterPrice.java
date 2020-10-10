package com.fourfaith.sysManage.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 水价设置表
 * @author  Hong
 * @date 2016-09-21 15:04:05
 * @version V2.0   
 */
public class BaseWaterPrice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**标识*/
	private String id;
	/**用水基准单价*/
	private BigDecimal standardPrice;
	/**超出额定水量单价*/
	private BigDecimal exceedPrice;
	/**创造时间*/
	private Date createTime;
	/**编辑时间*/
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