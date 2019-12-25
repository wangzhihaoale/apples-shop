package com.edu.wzh.mapper;

import com.edu.wzh.entity.AdminEntity;
import com.edu.wzh.pojo.AdminPojo;

import java.util.List;

public interface AdminMapper {
    AdminPojo login(AdminEntity adminEntity);

    List<AdminPojo> conditionsSelectAdmin(AdminEntity adminEntity);

    AdminPojo selectAuthByAdmin(AdminEntity adminEntity);

    void insertAdmin(AdminEntity adminEntity);

    boolean delectAdmin(int[] data);

    int updateEdit(AdminEntity adminEntity);
}
