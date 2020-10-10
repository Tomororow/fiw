package com.fourfaith.sysManage.model;

import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 系统用户表
 * @author  administrator
 * @date 2016-05-23 10:07:53
 * @version V1.0   
 */
public class SysUser implements java.io.Serializable {

	private static final long serialVersionUID = 2308406223025037873L;
	
	/**标识*/
    private String id;
    /**所属区域Id*/
    private String areaId;
    /**用户编码*/
    private String userCode;
    /**用户名称*/
    private String userName;
    /**用户密码*/
    private String userPassword;
    /**用户姓名*/
    private String fullName;
    /**手机号*/
    private String mobile;
    /**E-mail*/
    private String email;
    /**地址*/
    private String address;
    /**创建时间*/
    private Date createTime;
    /**上次修改时间*/
    private Date editTime;
    /**备注*/
    private String remark;
    /**明文密码字段*/
    private String userPasswordming;
    /**售水权限*/
    private Integer authority;
    /**是否app注册用户*/
    private Integer isAppUser;
    /**app注册手机标识ID*/
    private String mobileID;
    /**审核状态*/
    private Integer auditFlag;
    /**用户区域分配方式*/
    private Integer areaWay;
	
	/**所拥有的角色对象*/
	private SysRole sysrole;
	
	/**登录时间*/
	private Date loginTime;
	/**登录IP*/
	private java.lang.String loginIp;
	/**登录地址*/
	private java.lang.String loginArea;
	
	/**所属水管区域Id*/
	private String waterAreaId;
	
	// 非数据库字段
	/**所属行政区域*/
	private String areaName;
	/**所属水管区域*/
	private String waterAreaName;
	/**所属角色*/
	private String roleName;
	/**所属角色Id*/
	private String roleId;
	/**账号类型(0：地下水)，（1：管道）*/
	private String userTypeJuris;

	//是否可以登录售水软件
    private String Role;
    //是否可以升级卡
    private String IsCardUpdate;

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getIsCardUpdate() {
        return IsCardUpdate;
    }

    public void setIsCardUpdate(String isCardUpdate) {
        IsCardUpdate = isCardUpdate;
    }

    public java.lang.String getId(){
		return this.id;
	}
	public void setId(java.lang.String id){
		this.id = id;
	}
	
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public SysRole getSysrole() {
		return sysrole;
	}

	public void setSysrole(SysRole sysrole) {
		this.sysrole = sysrole;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public java.lang.String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(java.lang.String loginIp) {
		this.loginIp = loginIp;
	}

	public java.lang.String getLoginArea() {
		return loginArea;
	}

	public void setLoginArea(java.lang.String loginArea) {
		this.loginArea = loginArea;
	}

	public String getWaterAreaId() {
		return waterAreaId;
	}

	public void setWaterAreaId(String waterAreaId) {
		this.waterAreaId = waterAreaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getWaterAreaName() {
		return waterAreaName;
	}

	public void setWaterAreaName(String waterAreaName) {
		this.waterAreaName = waterAreaName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserPasswordming() {
		return userPasswordming;
	}

	public void setUserPasswordming(String userPasswordming) {
		this.userPasswordming = userPasswordming;
	}
	
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	
	public Integer getIsAppUser() {
		return isAppUser;
	}
	public void setIsAppUser(Integer isAppUser) {
		this.isAppUser = isAppUser;
	}
	
	public String getMobileID() {
		return mobileID;
	}
	public void setMobileID(String mobileID) {
		this.mobileID = mobileID;
	}
	
	public Integer getAuditFlag() {
		return auditFlag;
	}
	public void setAuditFlag(Integer auditFlag) {
		this.auditFlag = auditFlag;
	}
	
	public Integer getAreaWay() {
		return areaWay;
	}
	public void setAreaWay(Integer areaWay) {
		this.areaWay = areaWay;
	}
	public String getUserTypeJuris() {
		return userTypeJuris;
	}
	public void setUserTypeJuris(String userTypeJuris) {
		this.userTypeJuris = userTypeJuris;
	}
	
}