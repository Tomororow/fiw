package com.fourfaith.siteManage.model;

import java.util.Date;
import java.util.List;

/**   
 * @Title: Entity
 * @Description: 区域代码
 * @author  Dan
 * @date 2016-08-02 22:21:40
 * @version V1.0   
 */
public class SysArea implements java.io.Serializable {
	
	private static final long serialVersionUID = -7210851942684478287L;
	
	/**标识*/
	private String id;
	/**区域编码*/
	private String areaCode;
	/**区域名称*/
	private String areaName;
	/**区域级别*/
	private String areaLevel;
	/**所属父区域Id*/
	private String parentAreaId;
	/**所属父区域名*/
	private String parentAreaName;
	/**创建时间*/
	private Date createTime;
	/**上次修改时间*/
	private Date editTime;
	/**备注*/
	private String remark;
	
	/**行政区域标识集合(非数据库字段)**/
	private String areaIds;
	private String waterAreaId;
	private List<SysArea> children;
	private boolean open;
	/**区域名称*/
	
	private String name;
	
	
	
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}
	public String getParentAreaId() {
		return parentAreaId;
	}
	public void setParentAreaId(String parentAreaId) {
		this.parentAreaId = parentAreaId;
	}
	public String getParentAreaName() {
		return parentAreaName;
	}
	public void setParentAreaName(String parentAreaName) {
		this.parentAreaName = parentAreaName;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWaterAreaId() {
		return waterAreaId;
	}
	public void setWaterAreaId(String waterAreaId) {
		this.waterAreaId = waterAreaId;
	}
	public String getAreaIds() {
		return areaIds;
	}
	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}
	public List<SysArea> getChildren() {
		return children;
	}
	public void setChildren(List<SysArea> children) {
		this.children = children;
	}
	
}