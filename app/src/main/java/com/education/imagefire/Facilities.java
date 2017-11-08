package com.education.imagefire;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class Facilities {

    private String id;
    private String wifi;
    private String aci;
    private String breakfast;
    private String parking;
    private String reception;
    private String elect;
    private String gym;
    private String kitchen;

    public Facilities() {
    }

    public Facilities(String id, String wifi, String aci, String breakfast, String parking, String reception, String elect, String gym, String kitchen) {
        this.id = id;
        this.wifi = wifi;
        this.aci = aci;
        this.breakfast = breakfast;
        this.parking = parking;
        this.reception = reception;
        this.elect = elect;
        this.gym = gym;
        this.kitchen = kitchen;
    }

    public String getId() {
        return id;
    }

    public String getWifi() {
        return wifi;
    }

    public String getAci() {
        return aci;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public String getParking() {
        return parking;
    }

    public String getReception() {
        return reception;
    }

    public String getElect() {
        return elect;
    }

    public String getGym() {
        return gym;
    }

    public String getKitchen() {
        return kitchen;
    }
}
