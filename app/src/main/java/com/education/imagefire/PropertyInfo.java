package com.education.imagefire;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class PropertyInfo {
    private String property;
    private String university_1;
    private String university_2;
    private String university_3;
    private String location_1;
    private String location_2;
    private String location_3;

    public PropertyInfo() {
    }

    public PropertyInfo(String property, String university_1, String university_2, String university_3, String location_1, String location_2, String location_3) {
        this.property = property;
        this.university_1 = university_1;
        this.university_2 = university_2;
        this.university_3 = university_3;
        this.location_1 = location_1;
        this.location_2 = location_2;
        this.location_3 = location_3;
    }

    public String getProperty() {
        return property;
    }

    public String getUniversity_1() {
        return university_1;
    }

    public String getUniversity_2() {
        return university_2;
    }

    public String getUniversity_3() {
        return university_3;
    }

    public String getLocation_1() {
        return location_1;
    }

    public String getLocation_2() {
        return location_2;
    }

    public String getLocation_3() {
        return location_3;
    }
}
