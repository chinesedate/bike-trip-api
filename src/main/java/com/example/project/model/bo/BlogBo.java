package com.example.project.model.bo;

/**
 * Created by xuhan on 2018/9/15.
 */
public class BlogBo {
    private Integer autoId;
    private String title;
    private String content;


    public BlogBo() {
    }

    public BlogBo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
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
}
