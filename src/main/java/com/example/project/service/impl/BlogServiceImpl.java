package com.example.project.service.impl;

import com.example.project.dao.BlogMapper;
import com.example.project.model.bo.BlogBo;
import com.example.project.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xuhan on 2018/9/15.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public void saveBlogContent(BlogBo bo) {
        this.blogMapper.saveBlogContent_BLOG(bo);
    }

    public void saveBlogDraft(BlogBo bo) {
        this.blogMapper.saveBlogDraft_BLOG_DRAFT(bo);
    }

    public List<BlogBo> selectBlogList() {
        return this.blogMapper.selectBlogList_BLOG();
    }

    public BlogBo selectBlogOne(Integer id) {
        return this.blogMapper.selectBlogOne_BLOG(id);
    }

    public void blogLike(Integer blogId, Integer userId) {
        this.blogMapper.blogLike_BLOG_LIKE(blogId, userId);
    }

    public void blogRemoveLike(Integer blogId, Integer userId) {
        this.blogMapper.blogRemoveLike_BLOG_LIKE(blogId, userId);
    }

    public void increaseLikeCount(Integer type, Integer blogId) {
        this.blogMapper.increaseLikeCount_BLOG_LIKE(type, blogId);
    }

    public void decreaseLikeCount(Integer type, Integer blogId) {
        this.blogMapper.decreaseLikeCount_BLOG_LIKE(type, blogId);
    }
}
