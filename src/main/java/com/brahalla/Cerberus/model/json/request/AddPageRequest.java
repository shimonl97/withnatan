package com.brahalla.Cerberus.model.json.request;

import com.brahalla.Cerberus.model.dbmodels.app.Page;

/**
 * Created by mymac on 30/10/2016.
 */
public class AddPageRequest {

    private String appId;
    private Page page;

    public AddPageRequest(String appId, Page page) {
        this.appId = appId;
        this.page = page;
    }

    public Page getPage() {

        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }




    public AddPageRequest() {

    }


}
