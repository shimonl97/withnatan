package com.brahalla.Cerberus.model.json.response;

import com.brahalla.Cerberus.model.dbmodels.App;

/**
 * Created by mymac on 07/11/2016.
 */
public class NewTileResponse  {
    private App app;
    private String newTileId = "";

    public NewTileResponse() {
    }

    public NewTileResponse(App app, String newTileId) {
        this.app = app;
        this.newTileId = newTileId;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public String getNewTileId() {
        return newTileId;
    }

    public void setNewTileId(String newTileId) {
        this.newTileId = newTileId;
    }
}
