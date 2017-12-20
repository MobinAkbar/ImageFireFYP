package com.education.imagefire;

/**
 * Created by NET LINK on 9/29/2017.
 */

public class RecyclerUpload {
    public  String name;
    public String Uri;
    public String likes;
    public double distance;


    public RecyclerUpload() {
    }

    public RecyclerUpload(String name, String uri, String likes, double distance) {
        this.name = name;
        Uri = uri;
        this.likes = likes;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return Uri;
    }

    public String getLikes() {
        return likes;
    }

    public double getDistance() {
        return distance;
    }
}
