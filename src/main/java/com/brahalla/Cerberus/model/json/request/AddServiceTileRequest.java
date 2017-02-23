package com.brahalla.Cerberus.model.json.request;

import com.brahalla.Cerberus.model.dbmodels.app.AboutTile;
import com.brahalla.Cerberus.model.dbmodels.app.ServiceTile;

/**
 * Created by mymac on 30/10/2016.
 */
public class AddServiceTileRequest {
    private String appId;
    private String pageId;
    private ServiceTile tile;

    public AddServiceTileRequest(String appId, String pageId, ServiceTile tile) {
        this.appId = appId;
        this.pageId = pageId;
        this.tile = tile;
    }

    public AddServiceTileRequest() {
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

    public ServiceTile getTile() {
        return tile;
    }

    public void setTile(ServiceTile tile) {
        this.tile = tile;
    }
}
