package com.education.imagefire;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class HostelInfo {
    public String address;
    public String Value;
    public String Security;
    public String Cleanliness;
    public String Staff;
    public String Location;

    public HostelInfo() {
    }

    public HostelInfo(String address, String value, String security, String cleanliness, String staff, String location) {
        this.address = address;
        Value = value;
        Security = security;
        Cleanliness = cleanliness;
        Staff = staff;
        Location = location;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setValue(String value) {
        Value = value;
    }

    public void setSecurity(String security) {
        Security = security;
    }

    public void setCleanliness(String cleanliness) {
        Cleanliness = cleanliness;
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

    public String getValue() {
        return Value;
    }

    public String getSecurity() {
        return Security;
    }

    public String getCleanliness() {
        return Cleanliness;
    }

    public String getStaff() {
        return Staff;
    }

    public String getLocation() {
        return Location;
    }
}
