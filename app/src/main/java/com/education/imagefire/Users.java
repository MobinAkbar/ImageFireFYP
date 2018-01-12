package com.education.imagefire;

/**
 * Created by NET LINK on 11/19/2017.
 */

public class Users {
    String id;
    String name;
    String email;
    String password;
    String number;
    String sex;
    String adress;
    String university;
    String uri;
    String device_token;

    public Users() {
    }

    public Users(String id, String name, String email, String password, String number, String sex, String adress, String university, String uri, String device_token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.number = number;
        this.sex = sex;
        this.adress = adress;
        this.university = university;
        this.uri = uri;
        this.device_token = device_token;
    }

    public String getId() {
        return id;
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

    public String getNumber() {
        return number;
    }

    public String getSex() {
        return sex;
    }

    public String getAdress() {
        return adress;
    }

    public String getUniversity() {
        return university;
    }

    public String getUri() {
        return uri;
    }

    public String getDevice_token() {
        return device_token;
    }
}
