package com.brahalla.Cerberus.model.dbmodels.app;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mymac on 30/10/2016.
 */
@Document
public class AboutObject {
private List<Page> pages =new ArrayList<>();

    public AboutObject() {
    }

    public AboutObject(List<Page> pages) {
        this.pages = pages;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}
