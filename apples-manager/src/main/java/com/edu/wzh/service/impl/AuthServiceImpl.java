package com.edu.wzh.service.impl;

import com.edu.wzh.entity.AuthEntity;
import com.edu.wzh.mapper.AuthMapper;
import com.edu.wzh.pojo.AuthPojo;
import com.edu.wzh.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;
    @Override
    public List<AuthPojo> getroles(AuthEntity authEntity) {
        return  authMapper.getroles(authEntity);
    }

    @Override
    public List<AuthPojo> getAuthsByRid(int rid) {
        return authMapper.getAuthsByRid(rid);
    }
}
