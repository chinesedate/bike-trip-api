package com.example.project.service;

import com.example.project.model.bo.BlogBo;

import java.util.List;

/**
 * Created by xuhan on 2018/9/15.
 */
public interface BlogService {
    // 保存博客信息
    void saveBlogContent(BlogBo bo);

    // 保存博客草稿
    void saveBlogDraft(BlogBo bo);

    // 查询博客列表
    List<BlogBo> selectBlogList();

    // 查询一篇博客
    BlogBo selectBlogOne(Integer id);

    // 博客点赞
    void blogLike(Integer blogId, Integer userId);

    // 取消博客点赞
    void blogRemoveLike(Integer blogId, Integer userId);

    // 博客点赞数加1
    void increaseLikeCount(Integer type, Integer blogId);

    // 博客点赞数减1
    void decreaseLikeCount(Integer type, Integer blogId);
}
