package com.brahalla.Cerberus.model.json.response;

import com.brahalla.Cerberus.model.dbmodels.App;

/**
 * Created by mymac on 08/11/2016.
 */
public class NewPageResponse  {
    private App app;
    private String newPageId = "";

    public NewPageResponse() {
    }

    public NewPageResponse(App app, String newPageId) {
        this.app = app;
        this.newPageId = newPageId;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public String getNewPageId() {
        return newPageId;
    }

    public void setNewPageId(String newPageId) {
        this.newPageId = newPageId;
    }
}