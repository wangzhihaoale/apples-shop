package com.edu.wzh.mapper;

import com.edu.wzh.entity.AuthEntity;
import com.edu.wzh.pojo.AuthPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMapper {
    List<AuthPojo> getroles(AuthEntity authEntity);

    void addRoleAuth(@Param("rid") int rid, @Param("auids") int[] auids);

    List<AuthPojo> getAuthsByRid(int rid);
}
