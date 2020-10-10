package com.fourfaith.RemoteTopUp.model;
/**
 * ClassName: Product
 * @类描述: 商品实体
 * @author 刘海年
 * @date 2018年8月14日-上午11:18:21
 */
public class Product {
    private String id;

    private String name;

    private String price;

    private String comm1;

    private String comm2;

    private String comm3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
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