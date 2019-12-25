package com.edu.wzh.entity;

public class AuthEntity {
    private  int auid;
    private  String auname;
    private  String aupath;
    private  short autype;
    private  int aupid;

    public int getAuid() {
        return auid;
    }

    public void setAuid(int auid) {
        this.auid = auid;
    }

    public String getAuname() {
        return auname;
    }

    public void setAuname(String auname) {
        this.auname = auname;
    }

    public String getAupath() {
        return aupath;
    }

    public void setAupath(String aupath) {
        this.aupath = aupath;
    }

    public short getAutype() {
        return autype;
    }

    public void setAutype(short autype) {
        this.autype = autype;
    }

    public int getAupid() {
        return aupid;
    }

    public void setAupid(int aupid) {
        this.aupid = aupid;
    }
}
