package com.edu.wzh.pojo;

import java.util.List;

public class RolePojo {
    private  int rid;
    private  String rname;
    private  String rdesc;

    private List<AuthPojo> authPojos;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public List<AuthPojo> getAuthPojos() {
        return authPojos;
    }

    public void setAuthPojos(List<AuthPojo> authPojos) {
        this.authPojos = authPojos;
    }
}
