package com.brahalla.Cerberus.model.json.response;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Me on 9/29/2016.
 */
public class GetGroupsResponse {
    private List<Object> groups = new ArrayList<>();

    public GetGroupsResponse(List groups) {
        this.groups = groups;
    }

    public GetGroupsResponse() {
    }

    public List<Object> getGroups() {
        return groups;
    }

    public void setGroups(List<Object> groups) {
        this.groups = groups;
    }
}
