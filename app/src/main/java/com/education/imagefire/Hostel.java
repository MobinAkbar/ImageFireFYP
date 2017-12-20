package com.education.imagefire;

import java.io.Serializable;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class Hostel {
    public String id;
    public String owner;
    public String name;
    public String addres;
    public String uri;
    public String status;
    public String sex;
    public double latitude;
    public double longitude;
    public String likes;

    public Hostel() {
    }

    public Hostel(String id, String owner, String name, String addres, String uri, String status, String sex, double latitude, double
            longitude, String likes) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.addres = addres;
        this.uri = uri;
        this.status = status;
        this.sex = sex;
        this.latitude = latitude;
        this.longitude = longitude;
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getAddres() {
        return addres;
    }

    public String getUri() {
        return uri;
    }

    public String getStatus() {
        return status;
    }

    public String getSex() {
        return sex;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLikes() {
        return likes;
    }
}
