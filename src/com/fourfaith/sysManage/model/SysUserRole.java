package com.fourfaith.sysManage.model;

/**   
 * @Title: Entity
 * @Description: 用户角色表
 * @author  administrator
 * @date 2016-05-23 10:09:56
 * @version V1.0   
 */
public class SysUserRole implements java.io.Serializable {
	
	private static final long serialVersionUID = -5834455686155111168L;
	
	/**标识*/
    private String id;
    /**用户Id*/
    private String userId;
    /**角色Id*/
    private String roleId;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}