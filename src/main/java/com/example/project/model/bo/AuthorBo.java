package com.example.project.model.bo;

/**
 * Created by xuhan on 2018/10/22.
 */
public class AuthorBo {
    private Integer authorId;   // 作者id
    private String avatarUrl;   // 用户头像图片地址
    private String nickname;    // 用户昵称

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
