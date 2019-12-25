package com.edu.wzh.mapper;

import com.edu.wzh.entity.RoleEntity;
import com.edu.wzh.pojo.RolePojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<RolePojo> getRoles(RoleEntity roleEntity);

    void bindRoles(@Param("rids") int[] rids, @Param("aid") int aid);

    void addRole(RoleEntity roleEntity);

    RolePojo getRoleByRid(int rid);
}
