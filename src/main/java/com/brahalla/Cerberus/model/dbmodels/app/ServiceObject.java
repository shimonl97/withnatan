package com.brahalla.Cerberus.model.dbmodels.app;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mymac on 30/10/2016.
 */
@Document
public class ServiceObject {
    private List<ServicePage> pages =new ArrayList<>();

    public ServiceObject() {
    }

    public ServiceObject(List<ServicePage> pages) {
        this.pages = pages;
    }

    public List<ServicePage> getPages() {
        return pages;
    }

    public void setPages(List<ServicePage> pages) {
        this.pages = pages;
    }
}
