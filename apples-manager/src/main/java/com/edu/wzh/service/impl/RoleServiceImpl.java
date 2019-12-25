package com.edu.wzh.service.impl;

import com.edu.wzh.entity.RoleEntity;
import com.edu.wzh.mapper.AuthMapper;
import com.edu.wzh.mapper.RoleMapper;
import com.edu.wzh.pojo.RolePojo;
import com.edu.wzh.service.RoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AuthMapper authMapper;
    @Override
    public List<RolePojo> getRoles(RoleEntity roleEntity) {
        PageHelper.startPage(roleEntity.getPageNum(),roleEntity.getPageSize());
        return roleMapper.getRoles(roleEntity);
    }

    @Override
    public void addRole(RoleEntity roleEntity, int[] auids) {
        roleMapper.addRole(roleEntity);
        authMapper.addRoleAuth(roleEntity.getRid(),auids);
    }

    @Override
    public RolePojo getRoleByRid(int rid) {
        return roleMapper.getRoleByRid(rid);
    }
}
