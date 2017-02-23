package com.brahalla.Cerberus.model.json.request;

import com.brahalla.Cerberus.model.dbmodels.App;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Me on 9/29/2016.
 */
public class CreateGroupRequest {

    private String name;
    private String description;
    private String profileImage;
    private String coverImage;
    private String address;
    private double lat;
    private double lon;
    @JsonProperty
    private boolean Private;
    private boolean anon;
    private App app;
	private boolean bizGroup;

    public CreateGroupRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public boolean isPrivate() {
        return Private;
    }

    public void setPrivate(boolean aPrivate) {
        Private = aPrivate;
    }

    public boolean isAnon() {
        return anon;
    }

    public void setAnon(boolean anon) {
        this.anon = anon;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public boolean isBizGroup() {
		return bizGroup;
	}

	public void setBizGroup(boolean bizGroup) {
		this.bizGroup = bizGroup;
	}

	
	
    
    
}
