package com.fourfaith.sysManage.model;

import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 菜单表
 * @author  administrator
 * @date 2016-05-23 10:08:18
 * @version V1.0   
 */
public class SysMenu implements java.io.Serializable {
	
	private static final long serialVersionUID = 5115554480833173036L;
	
	/**标识*/
    private String id;
    /**菜单编码*/
    private String menuCode;
    /**菜单名称*/
    private String menuName;
    /**菜单层级*/
    private String menuLevel;
    /**所属父菜单Id*/
    private String parentMenuId;
    /**菜单URL*/
    private String menuUrl;
    /**菜单图标*/
    private String menuImage;
    /**菜单排序位置*/
    private String menuOrder;
    /**创建时间*/
    private Date createTime;
    /**上次修改时间*/
    private Date editTime;
    /**备注*/
    private String remark;
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuImage() {
		return menuImage;
	}
	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
	}
	public String getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
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
	
}