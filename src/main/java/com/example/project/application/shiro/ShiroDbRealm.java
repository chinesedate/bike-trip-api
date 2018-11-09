package com.example.project.application.shiro;

import com.example.project.model.bo.UserBo;
import com.example.project.model.shiro.ShiroUser;
import com.example.project.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by xuhan on 2018/11/5.
 */
public class ShiroDbRealm extends AuthenticatingRealm {

    @Autowired
    private UserService userService;

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String loginName = token.getUsername();

        UserBo userBo = userService.queryUser(loginName);
        if (userBo == null) {
            throw new IncorrectCredentialsException("该用户不存在");
        }
        if (!userBo.getPassword().equals(String.valueOf(token.getPassword()))) {
            throw new IncorrectCredentialsException("密码错误");
        }

        // - 判断用户输入的类型：用户名登录、抑或邮箱登录、抑或手机号码登录
        ShiroUser userSession = new ShiroUser();
        userSession.setUid(userBo.getAutoId());
        // 认证缓存信息
        return new SimpleAuthenticationInfo(userSession, token.getPassword(), getName());
    }
}
