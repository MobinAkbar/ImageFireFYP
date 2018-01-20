package com.education.imagefire;

/**
 * Created by NET LINK on 1/20/2018.
 */

public class Complain {
    private String id;
    private String s_id;
    private String owner;
    private String hostel;
    private String descrption;

    public Complain() {
    }

    public Complain(String id, String s_id, String owner, String hostel, String descrption) {
        this.id = id;
        this.s_id = s_id;
        this.owner = owner;
        this.hostel = hostel;
        this.descrption = descrption;
    }

    public String getId() {
        return id;
    }

    public String getS_id() {
        return s_id;
    }

    public String getOwner() {
        return owner;
    }

    public String getHostel() {
        return hostel;
    }

    public String getDescrption() {
        return descrption;
    }
}
