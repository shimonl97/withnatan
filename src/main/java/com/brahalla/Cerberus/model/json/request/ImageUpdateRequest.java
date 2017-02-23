package com.brahalla.Cerberus.model.json.request;

/**
 * Created by mymac on 31/10/2016.
 */
public class ImageUpdateRequest {
    private String image = null;
    private String appId;
    private String pageId = null;
    private String tileId = null;

    public String getTileId() {
        return tileId;
    }

    public void setTileId(String tileId) {
        this.tileId = tileId;
    }

    public ImageUpdateRequest(String image, String appId, String pageId, String tileId) {

        this.image = image;
        this.appId = appId;
        this.pageId = pageId;
        this.tileId = tileId;
    }

    public ImageUpdateRequest(String appId) {
        this.appId = appId;
    }

    public ImageUpdateRequest() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    @Override
    public String toString() {
        return "ImageUpdateRequest{" +
                "image='" + image + '\'' +
                ", appId='" + appId + '\'' +
                ", pageId='" + pageId + '\'' +
                ", tileId='" + tileId + '\'' +
                '}';
    }
}
