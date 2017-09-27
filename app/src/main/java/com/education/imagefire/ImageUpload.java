package com.education.imagefire;

/**
 * Created by NET LINK on 8/30/2017.
 */

public class ImageUpload {
    public  String name;
    public double longi;
    public double lat;
    public String url;

    public ImageUpload() {
    }

    public String getName() {
        return name;
    }

//    public double getLongi() {
//        return longi;
//    }
//
//    public double getLat() {
//        return lat;
//    }

    public String getUrl() {
        return url;
    }

    public ImageUpload(String name, double longi, double lat, String url) {
        this.name = name;
        this.longi=longi;
        this.lat=lat;
        this.url=url;
    }


}
