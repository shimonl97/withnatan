package com.brahalla.Cerberus.model.json.response;

/**
 * Created by mymac on 08/11/2016.
 */
public class ImageUpdateResponse {
    private String newImageUrl;
    private Object tile;

    public ImageUpdateResponse(String newImageUrl, Object tile) {
        this.newImageUrl = newImageUrl;
        this.tile = tile;
    }

    public ImageUpdateResponse(String newImageUrl) {
        this.newImageUrl = newImageUrl;
    }

    public ImageUpdateResponse() {
    }

    public Object getTile() {
        return tile;
    }

    public void setTile(Object tile) {
        this.tile = tile;
    }

    public String getNewImageUrl() {

        return newImageUrl;
    }

    public void setNewImageUrl(String newImageUrl) {
        this.newImageUrl = newImageUrl;
    }
}
