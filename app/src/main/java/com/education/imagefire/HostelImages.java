package com.education.imagefire;

import static android.R.attr.id;

/**
 * Created by NET LINK on 11/25/2017.
 */

public class HostelImages {

    private String ids;
    private String uri_1;
    private String uri_2;
    private String uri_3;
    private String uri_4;
    private String uri_5;
    private String uri_6;

    public HostelImages() {
    }

    public HostelImages(String ids, String uri_1, String uri_2, String uri_3, String uri_4, String uri_5, String uri_6) {
        this.ids = ids;
        this.uri_1 = uri_1;
        this.uri_2 = uri_2;
        this.uri_3 = uri_3;
        this.uri_4 = uri_4;
        this.uri_5 = uri_5;
        this.uri_6 = uri_6;
    }

    public String getIds() {
        return ids;
    }

    public String getUri_1() {
        return uri_1;
    }

    public String getUri_2() {
        return uri_2;
    }

    public String getUri_3() {
        return uri_3;
    }

    public String getUri_4() {
        return uri_4;
    }

    public String getUri_5() {
        return uri_5;
    }

    public String getUri_6() {
        return uri_6;
    }
}
