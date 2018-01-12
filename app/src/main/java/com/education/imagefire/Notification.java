package com.education.imagefire;

/**
 * Created by NET LINK on 12/16/2017.
 */

public class Notification {
    private String from;
    private String sendto;
    private String type;
    private String data;

    public Notification() {
    }

    public Notification(String from, String sendto, String type, String data) {
        this.from = from;
        this.sendto = sendto;
        this.type = type;
        this.data = data;
    }

    public String getFrom() {
        return from;
    }

    public String getSendto() {
        return sendto;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
