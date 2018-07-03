package com.example.project.service.impl;


import com.example.project.dao.UserMapper;

import com.example.project.model.UserBo;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuhan on 2018/2/23.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public Integer insertUser(UserBo userBo) {
        return this.userMapper.insertUser_USER(userBo);
    }

    public Integer countUser(String userName, String password) {
        return this.userMapper.countUser_USER(userName, password);
    }
}
