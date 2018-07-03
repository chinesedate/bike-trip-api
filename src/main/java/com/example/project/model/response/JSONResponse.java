package com.example.project.model.response;

import javax.swing.plaf.PanelUI;

/**
 * Created by xuhan on 2018/7/3.
 */
public class JSONResponse {
    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;


    private Object data;
    private String message;
    private Integer status;

    private JSONResponse(Object data, String message, Integer status) {

        this.data = data;
        this.message = message;
        this.status = status;
    }

    public static JSONResponse toSuccess(Object data, String message) {
        return new JSONResponse(data, message, SUCCESS);
    }

    public static JSONResponse toFail(Object data, String message) {
        return new JSONResponse(data, message, FAILURE);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
