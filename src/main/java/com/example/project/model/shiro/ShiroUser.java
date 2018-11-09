package com.example.project.model.shiro;

import java.io.Serializable;

/**
 * Created by xuhan on 2018/11/9.
 */
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = -5887105471375712075L;
    private Integer uid;   // 用户id

    public ShiroUser() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
