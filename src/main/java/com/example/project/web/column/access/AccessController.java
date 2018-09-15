package com.example.project.web.column.access;

import com.example.project.model.bo.UserBo;
import com.example.project.model.response.JSONResponse;
import com.example.project.service.UserService;
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

        return JSONResponse.toSuccess("", "注册成功");
    }

    //FIXME: 这里暂时没有做登录权限的控制
    // 用户登录
    @ResponseBody
    @RequestMapping(value = "/sign/in", method = RequestMethod.POST)
    public Object signIn(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password
    ) {
        Integer count = this.userService.countUser(userName, password);
        if (count > 1) {
            return JSONResponse.toFail("", "查询多个重复账号");
        } else if (count < 1) {
            return JSONResponse.toFail("", "用户不存在");
        } else {
            return JSONResponse.toSuccess("", "登录成功");
        }
    }

}
