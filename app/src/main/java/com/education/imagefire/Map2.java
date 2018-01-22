package com.education.imagefire;

/**
 * Created by NET LINK on 1/22/2018.
 */

public class Map2 {
    private String id;
    private String name;
    private String latitude;
    private String longitude;

    public Map2() {
    }

    public Map2(String id, String name, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
