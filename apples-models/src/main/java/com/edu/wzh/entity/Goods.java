package com.edu.wzh.entity;

public class Goods {
    private  String gid;
    private  String gname;
    private  String gimage;
    private  String gdesc;
    private  float gprice;
    private  float gdiscount;
    private  int isdelete;
    private  int glabel;
    private  int gsex=-1;

    private  GoodsType types;
    private  float gpricemin=-1;
    private  float gpricemax=-1;

    private  int pageSize=4;
    private  int pageNum=1;

    private  int num=1;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public GoodsType getTypes() {
        return types;
    }

    public void setTypes(GoodsType types) {
        this.types = types;
    }

    public float getGpricemin() {
        return gpricemin;
    }

    public void setGpricemin(float gpricemin) {
        this.gpricemin = gpricemin;
    }

    public float getGpricemax() {
        return gpricemax;
    }

    public void setGpricemax(float gpricemax) {
        this.gpricemax = gpricemax;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGimage() {
        return gimage;
    }

    public void setGimage(String gimage) {
        this.gimage = gimage;
    }

    public String getGdesc() {
        return gdesc;
    }

    public void setGdesc(String gdesc) {
        this.gdesc = gdesc;
    }

    public float getGprice() {
        return gprice;
    }

    public void setGprice(float gprice) {
        this.gprice = gprice;
    }

    public float getGdiscount() {
        return gdiscount;
    }

    public void setGdiscount(float gdiscount) {
        this.gdiscount = gdiscount;
    }

    public int getIsdelete() {
        return isdelete;
    }
    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public int getGlabel() {
        return glabel;
    }

    public void setGlabel(int glabel) {
        this.glabel = glabel;
    }

    public int getGsex() {
        return gsex;
    }

    public void setGsex(int gsex) {
        this.gsex = gsex;
    }

}
