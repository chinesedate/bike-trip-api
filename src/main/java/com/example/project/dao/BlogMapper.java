package com.example.project.dao;

import com.example.project.model.bo.BlogBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xuhan on 2018/9/15.
 */
@Component
public interface BlogMapper {

    void saveBlogContent_BLOG(BlogBo bo);

    void saveBlogDraft_BLOG_DRAFT(BlogBo bo);

    List<BlogBo> selectBlogList_BLOG();

    BlogBo selectBlogOne_BLOG(Integer id);

    // 博客点赞
    void blogLike_BLOG_LIKE(@Param("blogId") Integer blogId, @Param("userId") Integer userId);

    // 取消博客点赞
    void blogRemoveLike_BLOG_LIKE(@Param("blogId") Integer blogId, @Param("userId") Integer userId);

    // 博客点赞数加1
    void increaseLikeCount_BLOG_LIKE(@Param("type")Integer type, @Param("blogId")Integer blogId);

    // 博客点赞数减1
    void decreaseLikeCount_BLOG_LIKE(@Param("type")Integer type, @Param("blogId")Integer blogId);
}
