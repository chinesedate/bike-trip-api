package com.example.project.model.bo;

import java.util.Date;

/**
 * Created by xuhan on 2018/9/15.
 */
public class BlogBo {
    private Integer autoId;
    private Integer authorId;           // 用户id
    private String titleImageUrl;   // 题图地址
    private String title;
    private String content;
    private String briefIntroduction;    // 简介
    private Date createdAt;   // 创建时间
    private AuthorBo author;   // 作者信息


    public BlogBo() {
    }

    public BlogBo(Integer authorId, String titleImageUrl, String title, String content) {
        this.authorId = authorId;
        this.titleImageUrl = titleImageUrl;
        this.title = title;
        this.content = content;
    }

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getTitleImageUrl() {
        return titleImageUrl;
    }

    public void setTitleImageUrl(String titleImageUrl) {
        this.titleImageUrl = titleImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public AuthorBo getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBo author) {
        this.author = author;
    }
}
