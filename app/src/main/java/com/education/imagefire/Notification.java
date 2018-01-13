package com.education.imagefire;

/**
 * Created by NET LINK on 12/16/2017.
 */

public class Notification {
    private String id;
    private String from;
    private String sendto;
    private String type;
    private String data;
    private String url;
    private String name;

    public Notification() {
    }

    public Notification(String id, String from, String sendto, String type, String data, String url, String name) {
        this.id = id;
        this.from = from;
        this.sendto = sendto;
        this.type = type;
        this.data = data;
        this.url = url;
        this.name = name;
    }

    public String getId() {
        return id;
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

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
