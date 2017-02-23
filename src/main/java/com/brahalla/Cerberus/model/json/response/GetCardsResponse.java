package com.brahalla.Cerberus.model.json.response;

import com.brahalla.Cerberus.model.dbmodels.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dani on 10/5/2016.
 */
public class GetCardsResponse {
    private List<App> apps = new ArrayList<>();

    public GetCardsResponse(List<App> groups) {
        this.apps = groups;
    }

    public GetCardsResponse() {
    }

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> groups) {
        this.apps = groups;
    }
}

