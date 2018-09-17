package com.example.project.dao;

import com.example.project.model.bo.BlogBo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xuhan on 2018/9/15.
 */
@Component
public interface BlogMapper {

    int saveBlogContent_BLOG(BlogBo bo);

    List<BlogBo> selectBlogList_BLOG();
}
