package com.fourfaith.basicInformation.model;

import java.io.Serializable;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 计量设施类型表
 * @author  administrator
 * @date 2016-10-08 14:04:18
 * @version V1.0   
 */
public class SysMeasureType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**标识*/
    private String id;
    /**计量设施类型*/
    private String measureType;
    /**创建时间*/
    private Date createTime;
    /**上次修改时间*/
    private Date editTime;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMeasureType() {
		return measureType;
	}
	public void setMeasureType(String measureType) {
		this.measureType = measureType;
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