package com.education.imagefire;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class Facilities {

    private String id;
    private String wifi;
    private String genereter;
    private String breakfast;
    private String camera;
    private String electrition;
    private String guesthouse;
    private String shop;
    private String parking;
    private String washerman;
    private String kitchen;

    public Facilities() {

    }

    public Facilities(String id, String wifi, String genereter, String breakfast, String camera, String electrition, String guesthouse, String shop, String parking, String washerman, String kitchen) {
        this.id = id;
        this.wifi = wifi;
        this.genereter = genereter;
        this.breakfast = breakfast;
        this.camera = camera;
        this.electrition = electrition;
        this.guesthouse = guesthouse;
        this.shop = shop;
        this.parking = parking;
        this.washerman = washerman;
        this.kitchen = kitchen;
    }

    public String getId() {
        return id;
    }

    public String getWifi() {
        return wifi;
    }

    public String getGenereter() {
        return genereter;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public String getCamera() {
        return camera;
    }

    public String getElectrition() {
        return electrition;
    }

    public String getGuesthouse() {
        return guesthouse;
    }

    public String getShop() {
        return shop;
    }

    public String getParking() {
        return parking;
    }

    public String getWasherman() {
        return washerman;
    }

    public String getKitchen() {
        return kitchen;
    }
}
