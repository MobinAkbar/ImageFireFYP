package com.education.imagefire;

/**
 * Created by NET LINK on 1/2/2018.
 */

public class RecyclerUpload2 {
    public String hostel_id;
    public  String name;
    public String address;
    public String Uri;
    public int likes;
    public String type;


    public RecyclerUpload2() {
    }

    public RecyclerUpload2(String hostel_id, String name, String address, String uri, int likes, String type) {
        this.hostel_id = hostel_id;
        this.name = name;
        this.address = address;
        Uri = uri;
        this.likes = likes;
        this.type = type;
    }

    public String getHostel_id() {
        return hostel_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getUri() {
        return Uri;
    }

    public int getLikes() {
        return likes;
    }

    public String getType() {
        return type;
    }
}
