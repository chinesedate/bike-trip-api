package com.example.project.web.column.blog;

import com.example.project.utils.FtpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by xuhan on 2018/7/20.
 */
@Controller
public class BlogController {
    @ResponseBody
    @RequestMapping(value = "/blog/image/upload", method = RequestMethod.POST)
    public void uploadImage(
            @RequestParam("image") MultipartFile image
    ) {
        try {
            FtpUtil.uploadFile("/test", image.getOriginalFilename(), image.getInputStream());

        } catch (IOException ie) {
            int a = 0;
        }
    }
}
