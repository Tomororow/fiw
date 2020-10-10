package com.fourfaith.sysManage.model;

import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 角色表
 * @author  administrator
 * @date 2016-05-23 10:08:43
 * @version V1.0   
 */
public class SysRole implements java.io.Serializable {
	
	private static final long serialVersionUID = -2626148320395973817L;
	
	/**标识*/
    private String id;
    /**角色编码*/
    private String roleCode;
    /**角色名称*/
    private String roleName;
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
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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