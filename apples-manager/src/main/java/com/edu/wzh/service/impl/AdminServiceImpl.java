package com.edu.wzh.service.impl;

import com.edu.wzh.code.Msg;
import com.edu.wzh.entity.AdminEntity;
import com.edu.wzh.mapper.AdminMapper;
import com.edu.wzh.mapper.RoleMapper;
import com.edu.wzh.pojo.AdminPojo;
import com.edu.wzh.service.Adminservice;
import com.edu.wzh.service.RoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements Adminservice {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public AdminPojo login(AdminEntity adminEntity) {

        return adminMapper.login(adminEntity);
    }
/*
* 带条件查询
* */
    @Override
    public List<AdminPojo> conditionsSelectAdmin(AdminEntity adminEntity) {
        //分页
        PageHelper.startPage(adminEntity.getPageNum(),adminEntity.getPageSize());
        return adminMapper.conditionsSelectAdmin(adminEntity);
    }

    @Override
    public AdminPojo selectAuthByAdmin(AdminEntity adminEntity) {
        return adminMapper.selectAuthByAdmin(adminEntity);
    }

    @Override
    public void insertAdmin(int[] rids, AdminEntity adminEntity) {
        adminMapper.insertAdmin(adminEntity);
        roleMapper.bindRoles(rids,adminEntity.getAid());
    }

    @Override
    public boolean delectAdmin(int[] data) {
        return adminMapper.delectAdmin(data);
    }

    @Override
    public int updateEdit(AdminEntity adminEntity,int[] rids) {
        roleMapper.bindRoles(rids,adminEntity.getAid());

        return adminMapper.updateEdit(adminEntity);
    }
}
