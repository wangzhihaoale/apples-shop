package com.edu.wzh.service;

import com.edu.wzh.entity.AuthEntity;
import com.edu.wzh.pojo.AuthPojo;

import java.util.List;

public interface AuthService {
    List<AuthPojo> getroles(AuthEntity authEntity);

    List<AuthPojo> getAuthsByRid(int rid);
}
