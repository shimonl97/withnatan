package com.brahalla.Cerberus.model.dbmodels.app;

import java.util.UUID;

/**
 * Created by mymac on 30/10/2016.
 */
public class ServiceTile {
    private String id = "";
    private String name= "";
    private String link= "";
    private String remarks= "";
    private String generalText= "";
    private String imageUrl= "";

    public ServiceTile(String id, String name, String link, String remarks, String phone, String generalText, String imageUrl) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.remarks = remarks;
        this.generalText = generalText;
        this.imageUrl = imageUrl;
    }

    public ServiceTile(String name, String link, String remarks, String phone, String generalText, String imageUrl) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.link = link;
        this.remarks = remarks;
        this.generalText = generalText;
        this.imageUrl = imageUrl;
    }

    public ServiceTile() {
    }

    public ServiceTile(String id) {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        if (!(obj instanceof ServiceTile)) {
            return false;
        }
        return (this.id.equals(((ServiceTile)obj).getId()));
    }
    @Override
    public int hashCode() {
        int hashCode = 1;

        hashCode = hashCode * 37 + this.id.hashCode();

        return hashCode;
    }
}
