package com.brahalla.Cerberus.model.json.request;

/**
 * Created by dani on 10/5/2016.
 */
public class GroupsNearbyRequest {
    private Double lon;
    private Double lat;

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
}
