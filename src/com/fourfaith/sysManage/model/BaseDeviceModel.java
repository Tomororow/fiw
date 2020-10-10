package com.fourfaith.sysManage.model;

import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 机井设备型号
 * @author  Dan
 * @date 2016-08-02 22:21:40
 * @version V1.0 
 */
public class BaseDeviceModel implements java.io.Serializable {
	
	private static final long serialVersionUID = -4001037527016707223L;
	
	/**标识*/
    private String id;
    /**设备型号名称*/
    private String modelName;
    /**创建时间*/
    private Date createTime;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}