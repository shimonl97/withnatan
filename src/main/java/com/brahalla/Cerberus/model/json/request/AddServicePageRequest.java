package com.brahalla.Cerberus.model.json.request;

import com.brahalla.Cerberus.model.dbmodels.app.Page;
import com.brahalla.Cerberus.model.dbmodels.app.ServicePage;

/**
 * Created by mymac on 30/10/2016.
 */
public class AddServicePageRequest {

    private String appId;
    private ServicePage page;

    public AddServicePageRequest(String appId, ServicePage page) {
        this.appId = appId;
        this.page = page;
    }

    public ServicePage getPage() {

        return page;
    }

    public void setPage(ServicePage page) {
        this.page = page;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }




    public AddServicePageRequest() {

    }


}
