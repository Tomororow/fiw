package com.fourfaith.sysManage.model;

/**   
 * @Title: Entity
 * @Description: 系统日志表
 * @author  administrator
 * @date 2016-05-23 10:09:31
 * @version V1.0   
 */
public class SysLog implements java.io.Serializable {
	
	private static final long serialVersionUID = -6572167629347512063L;
	
	/**标识*/
	private java.lang.String id;
	/**所属企业标识*/
	private java.lang.String enterpriseid;
	/**用户标识*/
	private java.lang.String userid;
	/**操作内容*/
	private java.lang.String logcontent;
	/**登录时间*/
	private java.util.Date logintime;
	/**登录IP*/
	private java.lang.String loginip;
	/**登录区域*/
	private java.lang.String loginarea;
	/**操作时间*/
	private java.util.Date logtime;
	
	/**用户名称*/
	private java.lang.String username;

	/**所属角色名称*/
	private String roleName;

	/**水管区的id*/
	private String waterAreaId;

	public String getWaterAreaId() {
		return waterAreaId;
	}

	public void setWaterAreaId(String waterAreaId) {
		this.waterAreaId = waterAreaId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public java.lang.String getId(){
		return this.id;
	}
	public void setId(java.lang.String id){
		this.id = id;
	}
	public java.lang.String getEnterpriseid(){
		return this.enterpriseid;
	}
	public void setEnterpriseid(java.lang.String enterpriseid){
		this.enterpriseid = enterpriseid;
	}
	public java.lang.String getUserid(){
		return this.userid;
	}
	public void setUserid(java.lang.String userid){
		this.userid = userid;
	}
	public java.lang.String getLogcontent(){
		return this.logcontent;
	}
	public void setLogcontent(java.lang.String logcontent){
		this.logcontent = logcontent;
	}
	public java.util.Date getLogintime(){
		return this.logintime;
	}
	public void setLogintime(java.util.Date logintime){
		this.logintime = logintime;
	}
	public java.lang.String getLoginip(){
		return this.loginip;
	}
	public void setLoginip(java.lang.String loginip){
		this.loginip = loginip;
	}
	public java.lang.String getLoginarea(){
		return this.loginarea;
	}
	public void setLoginarea(java.lang.String loginarea){
		this.loginarea = loginarea;
	}
	public java.util.Date getLogtime(){
		return this.logtime;
	}
	public void setLogtime(java.util.Date logtime){
		this.logtime = logtime;
	}
	public java.lang.String getUsername() {
		return username;
	}
	public void setUsername(java.lang.String username) {
		this.username = username;
	}
	
}