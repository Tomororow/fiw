package com.fourfaith.RemoteTopUp.model;

import java.util.Date;
/**
 * ClassName: Orders
 * @类描述: 订单实体
 * @author 刘海年
 * @date 2018年8月14日-上午11:17:54
 */
public class Orders {
    private String id;

    private String orderNum;

    private String devicecode;

    private String waterusercode;

    private String orderStatus;

    private String orderAmount;

    private String paidAmount;

    private String productId;

    private Integer buyCounts;

    private Date createTime;

    private Date paidTime;

    private String comm1;

    private String comm2;

    private String comm3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getDevicecode() {
        return devicecode;
    }

    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode == null ? null : devicecode.trim();
    }

    public String getWaterusercode() {
        return waterusercode;
    }

    public void setWaterusercode(String waterusercode) {
        this.waterusercode = waterusercode == null ? null : waterusercode.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount == null ? null : orderAmount.trim();
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount == null ? null : paidAmount.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public Integer getBuyCounts() {
        return buyCounts;
    }

    public void setBuyCounts(Integer buyCounts) {
        this.buyCounts = buyCounts;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public String getComm1() {
        return comm1;
    }

    public void setComm1(String comm1) {
        this.comm1 = comm1 == null ? null : comm1.trim();
    }

    public String getComm2() {
        return comm2;
    }

    public void setComm2(String comm2) {
        this.comm2 = comm2 == null ? null : comm2.trim();
    }

    public String getComm3() {
        return comm3;
    }

    public void setComm3(String comm3) {
        this.comm3 = comm3 == null ? null : comm3.trim();
    }
}