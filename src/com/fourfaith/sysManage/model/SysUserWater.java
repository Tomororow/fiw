package com.fourfaith.sysManage.model;

import java.util.Date;

/**
 * @ClassName: SysUserWater
 * @Description: 用户用水表
 * @Author: zhaojx
 * @Date: 2017年4月24日 上午8:36:10
 */
public class SysUserWater implements java.io.Serializable {
	
	private static final long serialVersionUID = -2626148320395973817L;
    
	private String id;                // 主键ID
	private String userCode;          // 用户编码
    private Integer isBuyWater;       // 是否开通售水权限
    private String buyWaterRemark;	  // 售水描述
    private Date createTime;		  // 创建时间
    private Date editTime;			  // 修改时间
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Integer getIsBuyWater() {
		return isBuyWater;
	}
	public void setIsBuyWater(Integer isBuyWater) {
		this.isBuyWater = isBuyWater;
	}
	public String getBuyWaterRemark() {
		return buyWaterRemark;
	}
	public void setBuyWaterRemark(String buyWaterRemark) {
		this.buyWaterRemark = buyWaterRemark;
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