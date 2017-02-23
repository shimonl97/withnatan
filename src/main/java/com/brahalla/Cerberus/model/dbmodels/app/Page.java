package com.brahalla.Cerberus.model.dbmodels.app;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mymac on 30/10/2016.
 */
public class Page {
    private String id = "";
    private String name= "";
    private String subName= "";
    private List<AboutTile> tiles = new ArrayList<>();

    public Page(String id) {
        this.id = id;
    }

    public Page(List<AboutTile> tiles, String subName, String name, String id) {
        this.tiles = tiles;
        this.subName = subName;
        this.name = name;
        this.id = id;
    }

    public Page(List<AboutTile> tiles, String subName, String name) {
        this.id = UUID.randomUUID().toString();
        this.tiles = tiles;
        this.subName = subName;
        this.name = name;
    }

    public Page() {
    }

    public Page(String name, String subName) {
        this.name = name;
        this.subName = subName;
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

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public List<AboutTile> getTiles() {
        return tiles;
    }

    public void setTiles(List<AboutTile> tiles) {
        this.tiles = tiles;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Page)) {
            return false;
        }
        return (this.id.equals(((Page)obj).getId()));
    }
    @Override
    public int hashCode() {
        int hashCode = 1;

        hashCode = hashCode * 37 + this.id.hashCode();

        return hashCode;
    }
}
