package com.fourfaith.paramterManage.model;

import java.util.Date;

public class Warnerrortype {
    private Integer id;

    private Integer abnormalcode;

    private String abnormaltype;

    private String abnormaldetail;

    private Date createtime;

    private Date edittime;

    private String remark;
    
    private Integer isphone;
    
    private Integer ismess;

    private String backone;

    private String backtwo;

    private String backthree;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAbnormalcode() {
        return abnormalcode;
    }

    public void setAbnormalcode(Integer abnormalcode) {
        this.abnormalcode = abnormalcode;
    }

    public String getAbnormaltype() {
        return abnormaltype;
    }

    public void setAbnormaltype(String abnormaltype) {
        this.abnormaltype = abnormaltype == null ? null : abnormaltype.trim();
    }

    public String getAbnormaldetail() {
        return abnormaldetail;
    }

    public void setAbnormaldetail(String abnormaldetail) {
        this.abnormaldetail = abnormaldetail == null ? null : abnormaldetail.trim();
    }

    public Integer getIsphone() {
		return isphone;
	}

	public void setIsphone(Integer isphone) {
		this.isphone = isphone;
	}

	public Integer getIsmess() {
		return ismess;
	}

	public void setIsmess(Integer ismess) {
		this.ismess = ismess;
	}

	public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getEdittime() {
        return edittime;
    }

    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }



	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getBackone() {
        return backone;
    }

    public void setBackone(String backone) {
        this.backone = backone == null ? null : backone.trim();
    }

    public String getBacktwo() {
        return backtwo;
    }

    public void setBacktwo(String backtwo) {
        this.backtwo = backtwo == null ? null : backtwo.trim();
    }

    public String getBackthree() {
        return backthree;
    }

    public void setBackthree(String backthree) {
        this.backthree = backthree == null ? null : backthree.trim();
    }
}