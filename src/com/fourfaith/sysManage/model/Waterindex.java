package com.fourfaith.sysManage.model;

import java.math.BigDecimal;

public class Waterindex {
    private Integer id;

    private String waterareaid;

    private BigDecimal sumwater;

    private BigDecimal waterfarming;

    private BigDecimal watervirest;

    private BigDecimal waterlife;

    private BigDecimal waterindustry;

    private String spare1;

    private String spare2;

    private String spare3;

    private String waterAreaName;

    public String getWaterAreaName() {
        return waterAreaName;
    }

    public void setWaterAreaName(String waterAreaName) {
        this.waterAreaName = waterAreaName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWaterareaid() {
        return waterareaid;
    }

    public void setWaterareaid(String waterareaid) {
        this.waterareaid = waterareaid == null ? null : waterareaid.trim();
    }

    public BigDecimal getSumwater() {
        return sumwater;
    }

    public void setSumwater(BigDecimal sumwater) {
        this.sumwater = sumwater;
    }

    public BigDecimal getWaterfarming() {
        return waterfarming;
    }

    public void setWaterfarming(BigDecimal waterfarming) {
        this.waterfarming = waterfarming;
    }

    public BigDecimal getWatervirest() {
        return watervirest;
    }

    public void setWatervirest(BigDecimal watervirest) {
        this.watervirest = watervirest;
    }

    public BigDecimal getWaterlife() {
        return waterlife;
    }

    public void setWaterlife(BigDecimal waterlife) {
        this.waterlife = waterlife;
    }

    public BigDecimal getWaterindustry() {
        return waterindustry;
    }

    public void setWaterindustry(BigDecimal waterindustry) {
        this.waterindustry = waterindustry;
    }

    public String getSpare1() {
        return spare1;
    }

    public void setSpare1(String spare1) {
        this.spare1 = spare1 == null ? null : spare1.trim();
    }

    public String getSpare2() {
        return spare2;
    }

    public void setSpare2(String spare2) {
        this.spare2 = spare2 == null ? null : spare2.trim();
    }

    public String getSpare3() {
        return spare3;
    }

    public void setSpare3(String spare3) {
        this.spare3 = spare3 == null ? null : spare3.trim();
    }
}