package com.fortech.entity;


import com.google.gson.Gson;

import javax.persistence.Entity;


public class License1 {

    private String hostName;
    private String ipAddress;
    private String ipMac;
    private String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public String getHostName() {
        return hostName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getIpMac() {
        return ipMac;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setIpMac(String ipMac) {
        this.ipMac = ipMac;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public License1 fromString(String json1) {
        Gson gson = new Gson();
        License1 license1 = new License1();
        license1 = gson.fromJson(json1, License1.class);
        return license1;
    }

    public String toString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;

    }
}

