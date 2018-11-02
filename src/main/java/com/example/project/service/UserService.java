package com.example.project.service;


import com.example.project.model.bo.UserBo;

/**
 * Created by xuhan on 2018/2/23.
 */
public interface UserService {

    Integer insertUser(UserBo userBo);

    Integer countUser(String userName, String password);

    // 查询用户id
    Integer selectUserId(String userName);
}
