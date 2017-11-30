package com.education.imagefire;

/**
 * Created by NET LINK on 11/25/2017.
 */

public class Rooms {
    private String id;
    private String total_rooms;
    private String empty_rooms;
    private String total_beds;
    private String empty_beds;
    private String r_monthprize;
    private String b_monthprize;
    private String r_sixmonth;
    private String b_sixmonth;

    public Rooms() {
    }

    public Rooms(String id, String total_rooms, String empty_rooms, String total_beds, String empty_beds, String r_monthprize, String b_monthprize, String r_sixmonth, String b_sixmonth) {
        this.id = id;
        this.total_rooms = total_rooms;
        this.empty_rooms = empty_rooms;
        this.total_beds = total_beds;
        this.empty_beds = empty_beds;
        this.r_monthprize = r_monthprize;
        this.b_monthprize = b_monthprize;
        this.r_sixmonth = r_sixmonth;
        this.b_sixmonth = b_sixmonth;
    }

    public String getId() {
        return id;
    }

    public String getTotal_rooms() {
        return total_rooms;
    }

    public String getEmpty_rooms() {
        return empty_rooms;
    }

    public String getTotal_beds() {
        return total_beds;
    }

    public String getEmpty_beds() {
        return empty_beds;
    }

    public String getR_monthprize() {
        return r_monthprize;
    }

    public String getB_monthprize() {
        return b_monthprize;
    }

    public String getR_sixmonth() {
        return r_sixmonth;
    }

    public String getB_sixmonth() {
        return b_sixmonth;
    }
}
