package com.fourfaith.basicInformation.model;

import java.io.Serializable;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 灌区类型表
 * @author  administrator
 * @date 2016-10-08 13:39:18
 * @version V1.0   
 */
public class SysIrrigationAreaType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**标识*/
    private String id;
    /**灌区类型*/
    private String irrigationAreaType;
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
	public String getIrrigationAreaType() {
		return irrigationAreaType;
	}
	public void setIrrigationAreaType(String irrigationAreaType) {
		this.irrigationAreaType = irrigationAreaType;
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