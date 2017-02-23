package com.brahalla.Cerberus.model.json.response;

/**
 * Created by dani on 10/5/2016.
 */
public class CreateGroupResponse {
    private String groupId;

    public CreateGroupResponse(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
