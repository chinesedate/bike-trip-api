package com.example.project.dao;

import com.example.project.model.bo.UserBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by xuhan on 2018/6/26.
 */
@Component
public interface UserMapper {
    Integer insertUser_USER(UserBo userBo);

    Integer countUser_USER(@Param("userName") String userName, @Param("password") String password);

    Integer selectUserId_USER(String userName);
}
