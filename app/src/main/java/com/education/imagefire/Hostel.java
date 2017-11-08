package com.education.imagefire;

import java.io.Serializable;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class Hostel {
    public String id;
    public String owner;
    public String name;
    public String uri;

    public Hostel() {
    }

    public Hostel(String id,String owner,String name, String uri) {
        this.owner=owner;
        this.id=id;
        this.name = name;
        this.uri = uri;
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

    public String getUri() {
        return uri;
    }
}
