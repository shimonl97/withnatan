package com.brahalla.Cerberus.model.json.request;

/**
 * Created by Me on 9/26/2016.
 */
public class UserToGroupRequest {
    private String userId;
    private String groupId;

    public UserToGroupRequest() {
    }

	public UserToGroupRequest(String userId, String groupId) {
		super();
		this.userId = userId;
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
