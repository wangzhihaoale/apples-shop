package com.edu.wzh.entity;

import java.util.Date;
import java.util.List;

public class Order {
    private  String oid;
    private  int aid;
    private  int addressId;
    private  int ostatus;
    private Date odate;
    private  float ototal;

    private List<OrderInfo> orderInfos;

    public List<OrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<OrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getOstatus() {
        return ostatus;
    }

    public void setOstatus(int ostatus) {
        this.ostatus = ostatus;
    }

    public Date getOdate() {
        return odate;
    }

    public void setOdate(Date odate) {
        this.odate = odate;
    }

    public float getOtotal() {
        return ototal;
    }

    public void setOtotal(float ototal) {
        this.ototal = ototal;
    }
}
