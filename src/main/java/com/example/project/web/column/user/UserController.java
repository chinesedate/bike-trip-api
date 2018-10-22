package com.example.project.web.column.user;

import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xuhan on 2018/10/22.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object userInfo(
            @RequestParam("id") Integer id
    ) {
        return null;
    }
}
