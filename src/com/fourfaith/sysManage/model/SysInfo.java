package com.fourfaith.sysManage.model;
import java.io.Serializable;

/**   
 * @Title: Entity
 * @Description: 系统简介表
 * @author  Dan
 * @date 2016-09-26 14:36:05
 * @version V2.0   
 */
public class SysInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**设备标识*/
	private String id;
	/**信息键*/
	private String infoKey;
	/**简介内容*/
	private String infoValue;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfoKey() {
		return infoKey;
	}
	public void setInfoKey(String infoKey) {
		this.infoKey = infoKey;
	}
	public String getInfoValue() {
		return infoValue;
	}
	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
	}
	
}