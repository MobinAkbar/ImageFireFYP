package com.education.imagefire;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class Owner {
    public String id;
    public String name;
    public String number_1;
    public String number_2;
    public String number_3;
    public String email;
    public String password;
    public String professionn;
    public String uri;

    public Owner() {
    }

    public Owner(String id, String name, String number_1, String number_2, String number_3, String email, String password, String professionn, String uri) {
        this.id = id;
        this.name = name;
        this.number_1 = number_1;
        this.number_2 = number_2;
        this.number_3 = number_3;
        this.email = email;
        this.password = password;
        this.professionn = professionn;
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber_1() {
        return number_1;
    }

    public void setNumber_1(String number_1) {
        this.number_1 = number_1;
    }

    public String getNumber_2() {
        return number_2;
    }

    public void setNumber_2(String number_2) {
        this.number_2 = number_2;
    }

    public String getNumber_3() {
        return number_3;
    }

    public void setNumber_3(String number_3) {
        this.number_3 = number_3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfessionn() {
        return professionn;
    }

    public void setProfessionn(String professionn) {
        this.professionn = professionn;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
