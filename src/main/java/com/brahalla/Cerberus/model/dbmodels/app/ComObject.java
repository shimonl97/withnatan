package com.brahalla.Cerberus.model.dbmodels.app;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mymac on 30/10/2016.
 */
@Document
public class ComObject {
    private List<Extension> extensions =new ArrayList<>();

    public ComObject() {
    }

    public ComObject(List<Extension> extensions) {
        this.extensions = extensions;
    }

    public List<Extension> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
    }
}
