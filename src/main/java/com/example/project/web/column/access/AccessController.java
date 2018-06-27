package com.example.project.web.column.access;

import com.example.project.model.UserBo;
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
    @RequestMapping(value = "/sign/in", method = RequestMethod.POST)
    public void signIn(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password
    ) {
        UserBo userBo = new UserBo();
        userBo.setUserName(userName);
        userBo.setPassword(password);
        this.userService.insertUser(userBo);
    }
}
