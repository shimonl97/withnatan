package com.brahalla.Cerberus.model.base;

/**
 * Created by dani on 10/5/2016.
 */
public class BaseUrlResponse {
    private String baseUrl;

    public BaseUrlResponse(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
