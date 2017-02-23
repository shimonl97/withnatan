package com.brahalla.Cerberus.model.json.request;

/**
 * Created by Me on 9/26/2016.
 */
public class BasicRequest {
    private String id;

    public BasicRequest() {
    }

    public BasicRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
