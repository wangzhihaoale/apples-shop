package com.edu.wzh.service;

import com.edu.wzh.entity.AdminEntity;
import com.edu.wzh.pojo.AdminPojo;

import java.util.List;

public interface Adminservice {
    AdminPojo login(AdminEntity adminEntity);

    List<AdminPojo> conditionsSelectAdmin(AdminEntity adminEntity);

    AdminPojo selectAuthByAdmin(AdminEntity adminEntity);

    void insertAdmin(int[] rids, AdminEntity adminEntity);

    boolean delectAdmin(int[] data);

    int updateEdit(AdminEntity adminEntity,int[] ids);
}
