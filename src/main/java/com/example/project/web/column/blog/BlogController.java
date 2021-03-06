package com.example.project.web.column.blog;

import com.example.project.enums.BlogActionTypeEnum;
import com.example.project.model.bo.BlogBo;
import com.example.project.model.response.JSONResponse;
import com.example.project.service.BlogService;
import com.example.project.utils.FtpUtil;
import com.example.project.web.BaseController;
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
public class BlogController extends BaseController {

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
     *
     * @param titleImageUrl
     * @param title
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/draft/save", method = RequestMethod.POST)
    public Object saveDraft(
            @RequestParam("titleImageUrl") String titleImageUrl,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
        Integer authorId = getCurrentUserId();
        BlogBo bo = new BlogBo(authorId, titleImageUrl, title, content);
        if (content.startsWith("<p>") && !content.startsWith("<p><img")) {
            String briefIntroduction = content.substring(3, content.indexOf("</p>"));
            bo.setBriefIntroduction(briefIntroduction);
        }
        this.blogService.saveBlogDraft(bo);
        return JSONResponse.toSuccess("", "blog saved");
    }


    /**
     * 保存博客
     *
     * @param titleImageUrl
     * @param title
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    public Object saveContent(
            @RequestParam("titleImageUrl") String titleImageUrl,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
        Integer authorId = getCurrentUserId();
        BlogBo bo = new BlogBo(authorId, titleImageUrl, title, content);
        if (content.startsWith("<p>") && !content.startsWith("<p><img")) {
            String briefIntroduction = content.substring(3, content.indexOf("</p>"));
            bo.setBriefIntroduction(briefIntroduction);
        }
        this.blogService.saveBlogContent(bo);
        return JSONResponse.toSuccess("", "blog saved");
    }

    /**
     * 获取博客列表
     * @return
     */
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

    /**
     * 给博客点赞
     *
     * @param id 博客id
     * @param status 点赞状态（1表示点赞，0表示取消点赞）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/like/{id}", method = RequestMethod.POST)
    public Object likeBlog(
            @PathVariable("id") Integer id,
            @RequestParam("status") Integer status
    ) {
        Integer userId = getCurrentUserId();
        // userId为null表示当前用户未登录，操作不做记录
        if (userId != null) {
            if (status == 0) {
                // 取消点赞
                this.blogService.blogRemoveLike(id, userId);
                // 点赞数减1
                this.blogService.decreaseLikeCount(BlogActionTypeEnum.LIKE.getType(),id);
            } else {
                // 点赞
                this.blogService.blogLike(id, userId);
                // 点赞数加1
                this.blogService.increaseLikeCount(BlogActionTypeEnum.LIKE.getType(), id);
            }
        }
        return JSONResponse.toSuccess("", "blog liked");
    }
}
