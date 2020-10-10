package com.fourfaith.sysManage.model;

/**   
 * @Title: Entity
 * @Description: 系统机构表
 * @author  administrator
 * @date 2016-05-23 10:09:03
 * @version V1.0   
 */
public class SysOrganization implements java.io.Serializable {
	
	private static final long serialVersionUID = 1784798444945012013L;
	
	/**标识*/
	private java.lang.String id;
	/**父级机构标识*/
	private java.lang.String pid;
	/**所属企业标识*/
	private java.lang.String enterpriseid;
	/**机构编码*/
	private java.lang.String organcode;
	/**机构名称*/
	private java.lang.String organname;
	/**启用状态(0:不启用；1：启用)*/
	private java.lang.Integer enabledstate;
	/**创建时间*/
	private java.util.Date createtime;
	/**更新时间*/
	private java.util.Date updatetime;
	/**备注*/
	private java.lang.String organremark;
	
	public java.lang.String getId(){
		return this.id;
	}
	public void setId(java.lang.String id){
		this.id = id;
	}
	public java.lang.String getPid(){
		return this.pid;
	}
	public void setPid(java.lang.String pid){
		this.pid = pid;
	}
	public java.lang.String getEnterpriseid(){
		return this.enterpriseid;
	}
	public void setEnterpriseid(java.lang.String enterpriseid){
		this.enterpriseid = enterpriseid;
	}
	public java.lang.String getOrgancode(){
		return this.organcode;
	}
	public void setOrgancode(java.lang.String organcode){
		this.organcode = organcode;
	}
	public java.lang.String getOrganname(){
		return this.organname;
	}
	public void setOrganname(java.lang.String organname){
		this.organname = organname;
	}
	public java.lang.Integer getEnabledstate(){
		return this.enabledstate;
	}
	public void setEnabledstate(java.lang.Integer enabledstate){
		this.enabledstate = enabledstate;
	}
	public java.util.Date getCreatetime(){
		return this.createtime;
	}
	public void setCreatetime(java.util.Date createtime){
		this.createtime = createtime;
	}
	public java.util.Date getUpdatetime(){
		return this.updatetime;
	}
	public void setUpdatetime(java.util.Date updatetime){
		this.updatetime = updatetime;
	}
	public java.lang.String getOrganremark(){
		return this.organremark;
	}
	public void setOrganremark(java.lang.String organremark){
		this.organremark = organremark;
	}
	
}