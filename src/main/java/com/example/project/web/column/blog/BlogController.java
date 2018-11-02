package com.example.project.web.column.blog;

import com.example.project.model.bo.BlogBo;
import com.example.project.model.response.JSONResponse;
import com.example.project.service.BlogService;
import com.example.project.utils.FtpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by xuhan on 2018/7/20.
 */
@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    private final static Log log = LogFactory.getLog(BlogController.class);

    @ResponseBody
    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
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

    /**
     * 保存博客草稿
     * @param authorId
     * @param titleImageUrl
     * @param title
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/draft/save", method = RequestMethod.POST)
    public Object saveDraft(
            @RequestParam("authorId") Integer authorId,
            @RequestParam("titleImageUrl") String titleImageUrl,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
        BlogBo bo = new BlogBo(authorId, titleImageUrl, title, content);
        if (content.startsWith("<p>") && !content.startsWith("<p><img")) {
            String briefIntroduction = content.substring(3, content.indexOf("</p>"));
            bo.setBriefIntroduction(briefIntroduction);
        }
        this.blogService.saveBlogContent(bo);
        return JSONResponse.toSuccess("", "blog saved");
    }


    /**
     * 保存博客
     * @param authorId
     * @param titleImageUrl
     * @param title
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    public Object saveContent(
            @RequestParam("authorId") Integer authorId,
            @RequestParam("titleImageUrl") String titleImageUrl,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
        BlogBo bo = new BlogBo(authorId, titleImageUrl, title, content);
        if (content.startsWith("<p>") && !content.startsWith("<p><img")) {
            String briefIntroduction = content.substring(3, content.indexOf("</p>"));
            bo.setBriefIntroduction(briefIntroduction);
        }
        this.blogService.saveBlogContent(bo);
        return JSONResponse.toSuccess("", "blog saved");
    }

    // 获取日志列表
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object queryBlogList() {
        List<BlogBo> list = this.blogService.selectBlogList();
        return JSONResponse.toSuccess(list, "blog list got");
    }

    // 查询一篇日志
    @ResponseBody
    @RequestMapping(value = "/one/{id}", method = RequestMethod.GET)
    public Object queryBlogOne(
            @PathVariable("id") Integer id
    ) {
        BlogBo blogBo = this.blogService.selectBlogOne(id);
        return JSONResponse.toSuccess(blogBo, "one blog got");
    }
}
