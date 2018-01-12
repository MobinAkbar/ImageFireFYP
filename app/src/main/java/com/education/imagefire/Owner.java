package com.education.imagefire;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class Owner {
    public String id;
    public String name;
    public String address;
    public String number_1;
    public String number_2;
    public String number_3;
    public String email;
    public String password;
    public String professionn;
    public String uri;
    public String device_token;

    public Owner() {
    }

    public Owner(String id, String name, String address, String number_1, String number_2, String number_3, String email, String password, String professionn, String uri, String device_token) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.number_1 = number_1;
        this.number_2 = number_2;
        this.number_3 = number_3;
        this.email = email;
        this.password = password;
        this.professionn = professionn;
        this.uri = uri;
        this.device_token = device_token;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber_1() {
        return number_1;
    }

    public String getNumber_2() {
        return number_2;
    }

    public String getNumber_3() {
        return number_3;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getProfessionn() {
        return professionn;
    }

    public String getUri() {
        return uri;
    }

    public String getDevice_token() {
        return device_token;
    }
}
