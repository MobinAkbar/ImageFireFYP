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
    public String status;
    public String sex;

    public Hostel() {
    }

    public Hostel(String id,String owner,String name, String uri,String status,String sex) {
        this.id=id;
        this.owner=owner;
        this.name = name;
        this.uri = uri;
        this.status=status;
        this.sex=sex;
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

    public String getStatus() {
        return status;
    }

    public String getSex() {
        return sex;
    }
}
