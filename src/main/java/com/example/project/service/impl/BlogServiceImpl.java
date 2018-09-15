package com.example.project.service.impl;

import com.example.project.dao.BlogMapper;
import com.example.project.model.bo.BlogBo;
import com.example.project.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuhan on 2018/9/15.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public int saveBlogContent(BlogBo bo) {
        return this.blogMapper.saveBlogContent_BLOG(bo);
    }
}
