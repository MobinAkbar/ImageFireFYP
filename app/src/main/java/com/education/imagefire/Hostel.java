package com.education.imagefire;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class Hostel {
    public String id;
    public String name;
    public String uri;

    public Hostel() {
    }

    public Hostel(String id,String name, String uri) {
        this.id=id;
        this.name = name;
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }
}
