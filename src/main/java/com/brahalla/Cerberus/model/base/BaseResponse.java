package com.brahalla.Cerberus.model.base;

/**
 * Created by dani on 10/5/2016.
 */
public class BaseResponse {
    private String status = "1";
    private Object data;

    public BaseResponse(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public BaseResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
