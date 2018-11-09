package com.example.project.web.column.access;

import com.example.project.model.bo.UserBo;
import com.example.project.model.response.JSONResponse;
import com.example.project.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xuhan on 2018/2/11.
 */
@Controller
public class AccessController {


    private final UserService userService;

    @Autowired
    public AccessController(UserService userService) {
        this.userService = userService;
    }

    // 注册用户
    @ResponseBody
    @RequestMapping(value = "/sign/up", method = RequestMethod.POST)
    public Object signUp(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password
    ) {
        UserBo userBo = new UserBo();
        userBo.setUserName(userName);
        userBo.setPassword(password);
        this.userService.insertUser(userBo);

        return JSONResponse.toSuccess(userBo, "注册成功");
    }

    // 用户登录
    @ResponseBody
    @RequestMapping(value = "/sign/in", method = RequestMethod.POST)
    public Object signIn(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password
    ) {
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(userName);
        token.setPassword(password.toCharArray());
        token.setRememberMe(true);

        Subject currentUser = SecurityUtils.getSubject();


        try {
            currentUser.login(token);
            //if no exception, that's it, we're done!
        } catch (UnknownAccountException uae) {
            return JSONResponse.toFail("", "用户名或密码错误");
            //username wasn't in the system, show them an error message?
        } catch (IncorrectCredentialsException ice) {
            return JSONResponse.toFail("", "用户名或密码错误");
            //password didn't match, try again?
        } catch (LockedAccountException lae) {
            return JSONResponse.toFail("", "账户被锁定");
            //account for that username is locked - can't login.  Show them a message?
        }

        Integer count = this.userService.countUser(userName, password);
        if (count > 1) {
            return JSONResponse.toFail("", "查询多个重复账号");
        } else if (count < 1) {
            return JSONResponse.toFail("", "用户不存在");
        } else {
            Integer userId = this.userService.selectUserId(userName);
            return JSONResponse.toSuccess(userId, "登录成功");
        }
    }

    /**
     * 用户登出
     */
    @ResponseBody
    @RequestMapping(value = "/sign/out", method = RequestMethod.POST)
    public Object signOut() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return JSONResponse.toSuccess("", "已退出登录");
    }

}
