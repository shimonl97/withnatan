package com.brahalla.Cerberus.model.dbmodels.app;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mymac on 30/10/2016.
 */
public class ServicePage {
    private String id = "";
    private String name= "";
    private String description= "";
    private String image="";
    //private List<ServiceTile> tiles = new ArrayList<>();

    public ServicePage(String id) {
        this.id = id;
    }

    public ServicePage(String description, String name, String id) {
        this.description = description;
        this.name = name;
        this.id = id;
    }

    public ServicePage(String description, String name) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.name = name;
    }

    public ServicePage() {
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

    

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ServicePage)) {
            return false;
        }
        return (this.id.equals(((ServicePage)obj).getId()));
    }
    @Override
    public int hashCode() {
        int hashCode = 1;

        hashCode = hashCode * 37 + this.id.hashCode();

        return hashCode;
    }
}
