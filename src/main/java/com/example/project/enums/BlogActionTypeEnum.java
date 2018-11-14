package com.example.project.enums;

/**
 * Created by xuhan on 2018/11/14.
 */
public enum BlogActionTypeEnum {

    LIKE("like", 0);  // 点赞

    private String name;
    private Integer type;

    BlogActionTypeEnum(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
