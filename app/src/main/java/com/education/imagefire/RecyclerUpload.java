package com.education.imagefire;

/**
 * Created by NET LINK on 9/29/2017.
 */

public class RecyclerUpload {
    public  String name;
    public String Uri;
    //public double longi;
    //public double lat;

    public RecyclerUpload() {
    }
    public RecyclerUpload(String name,String Uri) {
        this.name = name;
        this.Uri=Uri;
      //  this.longi=longi;
        //this.lat=lat;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    //    public double getLongi() {
//        return longi;
//    }
//
//    public void setLongi(double longi) {
//        this.longi = longi;
//    }
//
//    public double getLat() {
//        return lat;
//    }
//
//    public void setLat(double lat) {
//        this.lat = lat;
//    }
}
