package com.education.imagefire;

/**
 * Created by NET LINK on 11/25/2017.
 */

public class University {
    private String id;
    private String university_1;
    private String university_2;
    private String university_3;

    public University() {
    }

    public University(String id, String university_1, String university_2, String university_3) {
        this.id = id;
        this.university_1 = university_1;
        this.university_2 = university_2;
        this.university_3 = university_3;
    }

    public String getId() {
        return id;
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
}
