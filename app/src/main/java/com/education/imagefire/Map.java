package com.education.imagefire;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class Map {
    private double latitude;
    private double longitude;
    private String uri;

    public Map() {
    }

    public Map(double latitude, double longitude, String uri) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.uri = uri;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getUri() {
        return uri;
    }
}

