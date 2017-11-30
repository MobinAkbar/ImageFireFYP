package com.education.imagefire;

/**
 * Created by NET LINK on 11/25/2017.
 */

public class NearBy {
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private String uri;

    public NearBy() {
    }

    public NearBy(String id, String name, double latitude, double longitude, String uri) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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
