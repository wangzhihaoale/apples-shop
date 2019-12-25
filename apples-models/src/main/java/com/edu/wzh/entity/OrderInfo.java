package com.edu.wzh.entity;

public class OrderInfo {
    private  String odid;
    private  String oid;
    private  String gid;
    private  float odprice;
    private  int odnumber;

    private  Goods goods;

    public String getOdid() {
        return odid;
    }

    public void setOdid(String odid) {
        this.odid = odid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public float getOdprice() {
        return odprice;
    }

    public void setOdprice(float odprice) {
        this.odprice = odprice;
    }

    public int getOdnumber() {
        return odnumber;
    }

    public void setOdnumber(int odnumber) {
        this.odnumber = odnumber;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
