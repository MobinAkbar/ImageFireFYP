package com.education.imagefire;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class HostelInfo {
    private String address;
    private String Valuee;
    private String Security;
    private String Staff;
    private String Location;

    public HostelInfo() {
    }

    public HostelInfo(String address, String valuee, String security, String staff, String location) {
        this.address = address;
        Valuee = valuee;
        Security = security;
        Staff = staff;
        Location = location;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setValuee(String valuee) {
        Valuee = valuee;
    }

    public void setSecurity(String security) {
        Security = security;
    }

    public void setStaff(String staff) {
        Staff = staff;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAddress() {
        return address;
    }

    public String getValuee() {
        return Valuee;
    }

    public String getSecurity() {
        return Security;
    }

    public String getStaff() {
        return Staff;
    }

    public String getLocation() {
        return Location;
    }
}
