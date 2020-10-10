package com.fourfaith.sysManage.model;

/**   
 * @Title: Entity
 * @Description: 权限表
 * @author  Dan
 * @date 2016-08-02 22:21:40
 * @version V1.0   
 */
public class SysPermission implements java.io.Serializable {
	
	private static final long serialVersionUID = 1489080059452420782L;
	
	/**标识*/
    private String id;
    /**角色Id*/
    private String roleId;
    /**菜单Id*/
    private String menuId;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}