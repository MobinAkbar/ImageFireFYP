package com.education.imagefire;

/**
 * Created by NET LINK on 9/29/2017.
 */

public class RecyclerUpload {
    public  String name;
    public String Uri;

    public RecyclerUpload() {
    }

    public RecyclerUpload(String name, String uri, double longi, double lat) {
        this.name = name;
        Uri = uri;
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
}
