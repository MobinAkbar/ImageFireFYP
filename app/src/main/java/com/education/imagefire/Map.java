package com.education.imagefire;

import java.lang.ref.SoftReference;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class Map {
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private String uri;

    public Map() {
    }

    public Map(String id,String name,double latitude, double longitude, String uri) {
        this.id=id;
        this.name=name;
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

