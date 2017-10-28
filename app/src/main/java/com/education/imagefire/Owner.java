package com.education.imagefire;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class Owner {
    public String id;
    public String name;
    public String number_1;
    public String number_2;
    public String email;
    public String decsription;
    public String uri;

    public Owner() {
    }

    public Owner(String id,String name, String number_1, String number_2, String email,String decsription, String uri) {

        this.id=id;
        this.name = name;
        this.number_1 = number_1;
        this.number_2 = number_2;
        this.email = email;
        this.decsription=decsription;
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber_1() {
        return number_1;
    }

    public String getNumber_2() {
        return number_2;
    }

    public String getEmail() {
        return email;
    }

    public String getDecsription() {
        return decsription;
    }
    public String getUri() {
        return uri;
    }
}
