package com.fourfaith.basicInformation.model;
import java.io.Serializable;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 取水类型表
 * @author Hong
 * @date 2016-09-23 13:43:05
 * @version V2.0   
 */
public class SysUseWaterType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**标识*/
	private String id;
	/**取水类型*/
	private String useWaterType;
	/**创建时间*/
	private Date createTime;
	/**修改时间*/
	private Date editTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUseWaterType() {
		return useWaterType;
	}
	public void setUseWaterType(String useWaterType) {
		this.useWaterType = useWaterType;
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