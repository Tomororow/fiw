package com.fourfaith.sysManage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: SysShortMsg
 * @Description: 短信模板实体
 * @Author: zhaojx
 * @Date: 2017年5月2日 下午2:19:59
 */
public class SysShortMsg implements Serializable{

	private static final long serialVersionUID = -8960094543949768031L;
	
	private String Id;              // 主键ID
	private String MsgCode;         // 短信编码
	private String MsgContent;      // 短信内容
	private Date CreateTime;        // 创建时间
	private Date EditTime;          // 修改时间
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getMsgCode() {
		return MsgCode;
	}
	public void setMsgCode(String msgCode) {
		MsgCode = msgCode;
	}
	public String getMsgContent() {
		return MsgContent;
	}
	public void setMsgContent(String msgContent) {
		MsgContent = msgContent;
	}
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	public Date getEditTime() {
		return EditTime;
	}
	public void setEditTime(Date editTime) {
		EditTime = editTime;
	}
	
}
