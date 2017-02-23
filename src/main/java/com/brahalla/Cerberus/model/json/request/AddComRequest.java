package com.brahalla.Cerberus.model.json.request;

import com.brahalla.Cerberus.model.dbmodels.app.Extension;

/**
 * Created by mymac on 30/10/2016.
 */
public class AddComRequest {
    private String appId;
    private Extension extension;

    public AddComRequest(String appId, Extension extension) {
        this.appId = appId;
        this.extension = extension;
    }

    public AddComRequest() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }
}
