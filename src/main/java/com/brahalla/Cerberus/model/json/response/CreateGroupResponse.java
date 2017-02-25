package com.brahalla.Cerberus.model.json.response;

import com.brahalla.Cerberus.model.dbmodels.Group;

/**
 * Created by dani on 10/5/2016.
 */
public class CreateGroupResponse {
    private Group group;

	public CreateGroupResponse(Group group) {
		super();
		this.group = group;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

    
}
