package com.education.imagefire;

/**
 * Created by NET LINK on 9/29/2017.
 */

public class RecyclerUpload {
    public String hostel_id;
    public String owner_id;
    public  String name;
    public String address;
    public String uni_name;
    public String Uri;
    public int likes;
    public double distance;


    public RecyclerUpload() {
    }

    public RecyclerUpload(String hostel_id, String owner_id, String name, String address, String uni_name, String uri, int likes, double distance) {
        this.hostel_id = hostel_id;
        this.owner_id = owner_id;
        this.name = name;
        this.address = address;
        this.uni_name = uni_name;
        Uri = uri;
        this.likes = likes;
        this.distance = distance;
    }

    public String getHostel_id() {
        return hostel_id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getUni_name() {
        return uni_name;
    }

    public String getUri() {
        return Uri;
    }

    public int getLikes() {
        return likes;
    }

    public double getDistance() {
        return distance;
    }
}
