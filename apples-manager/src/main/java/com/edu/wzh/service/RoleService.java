package com.edu.wzh.service;

import com.edu.wzh.entity.RoleEntity;
import com.edu.wzh.pojo.RolePojo;

import java.util.List;

public interface RoleService {
     List<RolePojo> getRoles(RoleEntity roleEntity);

    void addRole(RoleEntity roleEntity, int[] auids);

    RolePojo getRoleByRid(int rid);
}
