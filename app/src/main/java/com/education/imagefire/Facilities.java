package com.education.imagefire;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class Facilities {
    public boolean wifi;
    public boolean Air_Conditioning;
    public boolean Breakfast;
    public boolean Parking;
    public boolean Reception_24_Hours;
    public boolean Elevator;
    public boolean Gym;
    public boolean Barber;
    public boolean Kitchen;

    public Facilities() {
    }

    public Facilities(boolean wifi, boolean air_Conditioning, boolean breakfast, boolean parking, boolean reception_24_Hours, boolean elevator, boolean gym, boolean barber, boolean kitchen) {
        this.wifi = wifi;
        this.Air_Conditioning = air_Conditioning;
        this.Breakfast = breakfast;
        this.Parking = parking;
        this.Reception_24_Hours = reception_24_Hours;
        this.Elevator = elevator;
        this.Gym = gym;
        this.Barber = barber;
        this.Kitchen = kitchen;
    }

    public boolean isWifi() {
        return wifi;
    }

    public boolean isAir_Conditioning() {
        return Air_Conditioning;
    }

    public boolean isBreakfast() {
        return Breakfast;
    }

    public boolean isParking() {
        return Parking;
    }

    public boolean isReception_24_Hours() {
        return Reception_24_Hours;
    }

    public boolean isElevator() {
        return Elevator;
    }

    public boolean isGym() {
        return Gym;
    }

    public boolean isBarber() {
        return Barber;
    }

    public boolean isKitchen() {
        return Kitchen;
    }
}
