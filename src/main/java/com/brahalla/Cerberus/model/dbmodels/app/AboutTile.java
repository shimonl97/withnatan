package com.brahalla.Cerberus.model.dbmodels.app;

import java.util.UUID;

/**
 * Created by mymac on 30/10/2016.
 */
public class AboutTile {
    private String id = "";
    private String name = "";
    private String address= "";
    private String email= "";
    private String phone= "";
    private String generalText= "";
    private String imageUrl= "";

    public AboutTile(String id, String name, String address, String email, String phone, String generalText, String imageUrl) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.generalText = generalText;
        this.imageUrl = imageUrl;
    }

    public AboutTile(String name, String address, String email, String phone, String generalText, String imageUrl) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.generalText = generalText;
        this.imageUrl = imageUrl;
    }

    public AboutTile() {
    }

    public AboutTile(String id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGeneralText() {
        return generalText;
    }

    public void setGeneralText(String generalText) {
        this.generalText = generalText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AboutTile)) {
            return false;
        }
        return (this.id.equals(((AboutTile)obj).getId()));
    }
    @Override
    public int hashCode() {
        int hashCode = 1;

        hashCode = hashCode * 37 + this.id.hashCode();

        return hashCode;
    }
}
