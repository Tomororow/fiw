package com.fourfaith.basicInformation.model;

import java.io.Serializable;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 灌溉模式表
 * @author  administrator
 * @date 2016-10-08 11:50:18
 * @version V1.0   
 */
public class SysIrrigationMode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**标识*/
    private String id;
    /**灌溉模式*/
    private String irrigationMode;
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
	public String getIrrigationMode() {
		return irrigationMode;
	}
	public void setIrrigationMode(String irrigationMode) {
		this.irrigationMode = irrigationMode;
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