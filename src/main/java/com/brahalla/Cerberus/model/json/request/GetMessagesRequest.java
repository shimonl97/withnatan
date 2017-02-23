package com.brahalla.Cerberus.model.json.request;

/**
 * Created by Me on 9/29/2016.
 */
public class GetMessagesRequest {
    private String id;
    private int index;

    public GetMessagesRequest(String id, int index) {
        this.id = id;
        this.index = index;
    }

    public GetMessagesRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
