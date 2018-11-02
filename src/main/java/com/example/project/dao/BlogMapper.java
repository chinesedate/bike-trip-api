package com.example.project.dao;

import com.example.project.model.bo.BlogBo;
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
}
