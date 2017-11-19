package com.education.imagefire;

/**
 * Created by NET LINK on 11/19/2017.
 */

public class Users {
    String name;
    String email;
    String password;
    String uri;

    public Users() {
    }

    public Users(String name, String email, String password, String uri) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUri() {
        return uri;
    }
}
