package com.example.project.web.column.blog;

import com.example.project.model.response.JSONResponse;
import com.example.project.utils.FtpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by xuhan on 2018/7/20.
 */
@Controller
public class BlogController {
    private final static Log log = LogFactory.getLog(BlogController.class);
    @ResponseBody
    @RequestMapping(value = "/blog/image/upload", method = RequestMethod.POST)
    public Object uploadImage(
            @RequestParam("image") MultipartFile image
    ) {
        String imagePath = "";
        try {
            imagePath = FtpUtil.putFile(image.getInputStream(), image.getOriginalFilename(), "blog");

        } catch (IOException ie) {
            log.error(ie.getMessage());
            return JSONResponse.toFail("", "image not saved");
        }
        return JSONResponse.toSuccess(imagePath, "image saved");
    }
}
