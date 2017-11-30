package com.education.imagefire;

import java.io.Serializable;

/**
 * Created by NET LINK on 10/18/2017.
 */

public class PropertyInfo implements Serializable {
    private String id;
    private String property;
    private String nearby_place1;
    private String nearby_place2;
    private String nearby_place3;
    private String nearby_place4;
    private String nearby_place5;
    private String nearby_place6;
    private String nearby_place7;
    private String nearby_place8;
    private String nearby_place9;
    private String nearby_place10;


    public PropertyInfo() {
    }

    public PropertyInfo(String id, String property, String nearby_place1, String nearby_place2, String nearby_place3, String nearby_place4, String nearby_place5, String nearby_place6, String nearby_place7, String nearby_place8, String nearby_place9, String nearby_place10) {
        this.id = id;
        this.property = property;
        this.nearby_place1 = nearby_place1;
        this.nearby_place2 = nearby_place2;
        this.nearby_place3 = nearby_place3;
        this.nearby_place4 = nearby_place4;
        this.nearby_place5 = nearby_place5;
        this.nearby_place6 = nearby_place6;
        this.nearby_place7 = nearby_place7;
        this.nearby_place8 = nearby_place8;
        this.nearby_place9 = nearby_place9;
        this.nearby_place10 = nearby_place10;
    }

    public String getId() {
        return id;
    }

    public String getProperty() {
        return property;
    }

    public String getNearby_place1() {
        return nearby_place1;
    }

    public String getNearby_place2() {
        return nearby_place2;
    }

    public String getNearby_place3() {
        return nearby_place3;
    }

    public String getNearby_place4() {
        return nearby_place4;
    }

    public String getNearby_place5() {
        return nearby_place5;
    }

    public String getNearby_place6() {
        return nearby_place6;
    }

    public String getNearby_place7() {
        return nearby_place7;
    }

    public String getNearby_place8() {
        return nearby_place8;
    }

    public String getNearby_place9() {
        return nearby_place9;
    }

    public String getNearby_place10() {
        return nearby_place10;
    }
}
