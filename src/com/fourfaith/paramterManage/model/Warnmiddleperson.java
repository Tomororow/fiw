package com.fourfaith.paramterManage.model;

public class Warnmiddleperson {
    private Integer id;

    private Integer abnormalcode;

    private Integer abnormalperson;

    private String remark;

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

    public Integer getAbnormalperson() {
        return abnormalperson;
    }

    public void setAbnormalperson(Integer abnormalperson) {
        this.abnormalperson = abnormalperson;
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