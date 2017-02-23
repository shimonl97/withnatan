package com.brahalla.Cerberus.model.dbmodels;

import com.brahalla.Cerberus.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.client.model.geojson.Point;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Me on 9/26/2016.
 */
@Document
public class Group {
    @Id
    private String id;
    @Indexed
    @TextIndexed
    private String name;
    @Indexed
    private String groupAdmin;
    private String profileImage= "";
    private String coverImage= "";
    private String description= "";
    private String address="";
    private boolean Private = false;
    @GeoSpatialIndexed (type=GeoSpatialIndexType.GEO_2DSPHERE )
    private GeoJsonPoint location=null;
    private boolean anon = false;
    private List<String> members = new ArrayList<>();
    private App app;
    private boolean bizGroup;
    private Double distance=0d;
    private List<Report> reports;
    @Transient
    private String type="group";
    private boolean admin;
    private boolean member;

    public Group(String id, String name, String groupAdmin, String profileImage, String coverImage, String description, boolean aPrivate, GeoJsonPoint location, boolean anon, List<String> members) {
        this.id = id;
        this.name = name;
        this.groupAdmin = groupAdmin;
        this.profileImage = profileImage;
        this.coverImage = coverImage;
        this.description = description;
        Private = aPrivate;
        this.location = location;
        this.anon = anon;
        this.members = members;
    }

    public Group() {
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(String groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public Group(String id, String name, String profileImage, String description, boolean aPrivate, boolean anon, List<String> members, GeoJsonPoint location) {
        this.id = id;
        this.name = name;
        this.profileImage = profileImage;
        this.description = description;
        Private = aPrivate;
        this.anon = anon;
        this.members = members;
        this.location = location;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<String> getMembers() {
        return members;
    }
    public void addMember(String user){
        members.add(user);
    }
    public void removeMember(String user){
        members.remove(user);
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@JsonIgnore
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isMember() {
		return member;
	}

	public void setMember(boolean member) {
		this.member = member;
	}

	
    public void setForUser(String userId) {
    	setAdmin(getGroupAdmin()!=null && getGroupAdmin().equals(userId));
    	setMember(getMembers()!=null && getMembers().contains(userId));
    }
    

}
