package com.fourfaith.basicInformation.model;

import java.io.Serializable;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 水井用途表
 * @author  administrator
 * @date 2016-10-08 11:37:18
 * @version V1.0   
 */
public class SysWellUse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**标识*/
    private String id;
    /**水井用途*/
    private String wellUse;
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
	public String getWellUse() {
		return wellUse;
	}
	public void setWellUse(String wellUse) {
		this.wellUse = wellUse;
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