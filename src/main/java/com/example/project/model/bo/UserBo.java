package com.example.project.model.bo;

/**
 * Created by xuhan on 2018/6/26.
 */
public class UserBo {
    private Integer autoId;
    private String userName;  // 登录名
    private String password;  // 密码
    private String nickname;  // 用户昵称

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
