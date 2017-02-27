package com.brahalla.Cerberus.model.json.request;

/**
 * Created by dani on 10/5/2016.
 */
public class GroupsNearbyRequest {
    private Double lon;
    private Double lat;
    private Double minDistance;
    private Double maxDistance;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public GroupsNearbyRequest() {

    }

    public GroupsNearbyRequest(Double lon, Double lat) {

        this.lon = lon;
        this.lat = lat;
    }

	public Double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(Double minDistance) {
		this.minDistance = minDistance;
	}

	public Double getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(Double maxDistance) {
		this.maxDistance = maxDistance;
	}
    
    
}
