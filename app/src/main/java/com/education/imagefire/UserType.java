package com.education.imagefire;

/**
 * Created by NET LINK on 12/13/2017.
 */

public class UserType {
    private String id;
    private String type;

    public UserType() {
    }

    public UserType(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
