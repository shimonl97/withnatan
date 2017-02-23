package com.brahalla.Cerberus.model.dbmodels;


import com.brahalla.Cerberus.model.dbmodels.app.AboutObject;
import com.brahalla.Cerberus.model.dbmodels.app.ComObject;
import com.brahalla.Cerberus.model.dbmodels.app.ServiceObject;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by dani on 10/5/2016.
 */
@Document
public class App {
    @org.springframework.data.annotation.Id
    private String id;
    @Indexed
    private String name= "";
    private String slogan= "";
    private String description= "";
    private String phone= "";
    private String coverImageUrl= "";
    private String address= "";
    private String logoImageUrl= "";
    private String userId;
    private String pic1="";
    private String pic2="";
    private String pic3="";
    //private AboutObject about = new AboutObject();
    private ServiceObject services = new ServiceObject();
    private ComObject com = new ComObject();
    @Transient
    private String type="app";

    public App(String id, String name, String slogan, String description, String phone, String coverImageUrl, String address, String logoImageUrl, String userId, ServiceObject services, ComObject com, GeoJsonPoint location) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
        this.description = description;
        this.phone = phone;
        this.coverImageUrl = coverImageUrl;
        this.address = address;
        this.logoImageUrl = logoImageUrl;
        this.userId = userId;
       this.services = services;
        this.com = com;
        this.location = location;
    }

    private GeoJsonPoint location=null;

    

   

    public App(String id, String name, String slogan, String phone, String coverImageUrl, String userId, GeoJsonPoint location) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
        this.phone = phone;
        this.coverImageUrl = coverImageUrl;
        this.userId = userId;
        this.location = location;
    }

    public App(String name, String slogan, String description, String phone, String coverImageUrl, String address, String logoImageUrl, String userId, GeoJsonPoint location) {
        this.name = name;
        this.slogan = slogan;
        this.description = description;
        this.phone = phone;
        this.coverImageUrl = coverImageUrl;
        this.address = address;
        this.logoImageUrl = logoImageUrl;
        this.userId = userId;
        this.location = location;
    }

    public App(String id, String name, String slogan, String description, String phone, String coverImageUrl, String address, String logoImageUrl, String userId, GeoJsonPoint location) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
        this.description = description;
        this.phone = phone;
        this.coverImageUrl = coverImageUrl;
        this.address = address;
        this.logoImageUrl = logoImageUrl;
        this.userId = userId;
        this.location = location;
    }

    public ServiceObject getServices() {
        return services;
    }

    public void setServices(ServiceObject services) {
        this.services = services;
    }

    public App(String id, String name, String slogan, String description, String phone, String coverImageUrl, String address, String logoImageUrl, String userId, ServiceObject services, GeoJsonPoint location) {

        this.id = id;
        this.name = name;
        this.slogan = slogan;
        this.description = description;
        this.phone = phone;
        this.coverImageUrl = coverImageUrl;
        this.address = address;
        this.logoImageUrl = logoImageUrl;
        this.userId = userId;
        this.services = services;
        this.location = location;
    }

    public App() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogoImageUrl() {
        return logoImageUrl;
    }

    public void setLogoImageUrl(String logoImageUrl) {
        this.logoImageUrl = logoImageUrl;
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

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    public ComObject getCom() {
        return com;
    }

    public void setCom(ComObject com) {
        this.com = com;
    }

    @Transient
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}
    
    
}
