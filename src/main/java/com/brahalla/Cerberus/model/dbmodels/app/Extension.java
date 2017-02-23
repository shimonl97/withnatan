package com.brahalla.Cerberus.model.dbmodels.app;

/**
 * Created by mymac on 30/10/2016.
 */
public class Extension {
    private String id = "";
    private String name= "";
    private String holderName= "";
    private String email= "";
    private String phone= "";
    private String imageUrl= "";

    public Extension(String id, String name, String holderName, String email, String phone, String imageUrl) {
        this.id = id;
        this.name = name;
        this.holderName = holderName;
        this.email = email;
        this.phone = phone;
        this.imageUrl = imageUrl;
    }

    public Extension() {
    }

    public Extension(String id) {
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

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Extension)) {
            return false;
        }
        return (this.id.equals(((Extension)obj).getId()));
    }
    @Override
    public int hashCode() {
        int hashCode = 1;

        hashCode = hashCode * 37 + this.id.hashCode();

        return hashCode;
    }
}
