package com.brahalla.Cerberus.model.json.request;

import com.brahalla.Cerberus.model.dbmodels.app.AboutTile;

/**
 * Created by mymac on 30/10/2016.
 */
public class AddTileRequest {

    private String appId;
    private String pageId;
    private AboutTile tile;

    public AddTileRequest(String appId, String pageId, AboutTile tile) {
        this.appId = appId;
        this.pageId = pageId;
        this.tile = tile;
    }

    public AddTileRequest() {
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

    public AboutTile getTile() {
        return tile;
    }

    public void setTile(AboutTile tile) {
        this.tile = tile;
    }
}
