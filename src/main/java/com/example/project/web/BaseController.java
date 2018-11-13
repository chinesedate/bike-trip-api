package com.example.project.web;

import com.example.project.model.shiro.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by xuhan on 2018/2/11.
 */
public abstract class BaseController {

    /**
     * 获得当前用户
     * @return
     */
    protected Integer getCurrentUserId() {
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if (shiroUser == null) {
            return null;
        }
        return shiroUser.getUid();
    }
}
