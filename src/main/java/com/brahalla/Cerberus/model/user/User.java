package com.brahalla.Cerberus.model.user;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.brahalla.Cerberus.model.dbmodels.Report;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

@Document
public class User {
    @org.springframework.data.annotation.Id
    private String id;

    @Indexed
    @JsonIgnore
    private String phone;

    @Indexed
    @TextIndexed
    private String name;
    
    @TextIndexed
    private String nickName;
    private String photo;
    private String gcmToken;
    private Date gcmTokenLastUpdate;
    private String apnToken;
    private Date apnTokenLastUpdate;
    private List<Report> reports;
    private List<String> groups;

    public User(String id, String phone, String name, String photo,String nickName) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.nickName = nickName;
        this.photo = photo;
    }

    @JsonIgnore
    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
    public void removeGroup(String group){
        groups.remove(group);
    }
    public void addGroup(String group){
        groups.add(group);
    }
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonIgnore
    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
    }

    @JsonIgnore
    public Date getGcmTokenLastUpdate() {
        return gcmTokenLastUpdate;
    }

    public void setGcmTokenLastUpdate(Date gcmTokenLastUpdate) {
        this.gcmTokenLastUpdate = gcmTokenLastUpdate;
    }

    @JsonIgnore
    public String getApnToken() {
        return apnToken;
    }

    public void setApnToken(String apnToken) {
        this.apnToken = apnToken;
    }

    @JsonIgnore
    public Date getApnTokenLastUpdate() {
        return apnTokenLastUpdate;
    }

    public void setApnTokenLastUpdate(Date apnTokenLastUpdate) {
        this.apnTokenLastUpdate = apnTokenLastUpdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @JsonIgnore
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
    
}
