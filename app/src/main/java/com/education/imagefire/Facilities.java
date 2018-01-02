package com.education.imagefire;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class Facilities {

    private String id;
    private String wifi;
    private String parking;
    private String breakfast;
    private String aci;
    private String electrition;
    private String shop;
    private String guesthouse;
    private String genereter;
    private String kitchen;
    private String washerman;

    public Facilities() {

    }

    public Facilities(String id, String wifi, String parking, String breakfast, String aci, String electrition, String shop, String guesthouse, String genereter, String kitchen, String washerman) {
        this.id = id;
        this.wifi = wifi;
        this.parking = parking;
        this.breakfast = breakfast;
        this.aci = aci;
        this.electrition = electrition;
        this.shop = shop;
        this.guesthouse = guesthouse;
        this.genereter = genereter;
        this.kitchen = kitchen;
        this.washerman = washerman;
    }

    public String getId() {
        return id;
    }

    public String getWifi() {
        return wifi;
    }

    public String getParking() {
        return parking;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public String getAci() {
        return aci;
    }

    public String getElectrition() {
        return electrition;
    }

    public String getShop() {
        return shop;
    }

    public String getGuesthouse() {
        return guesthouse;
    }

    public String getGenereter() {
        return genereter;
    }

    public String getKitchen() {
        return kitchen;
    }

    public String getWasherman() {
        return washerman;
    }
}
