package com.fortech.entity;


import com.google.gson.Gson;


public class GeneratedKey {

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


    public GeneratedKey fromString(String json1) {
        Gson gson = new Gson();
        GeneratedKey generatedKey = new GeneratedKey();
        generatedKey = gson.fromJson(json1, GeneratedKey.class);
        this.setHostName(generatedKey.getHostName());
        this.setIpAddress(generatedKey.getIpAddress());
        this.setIpMac(generatedKey.getIpMac());
        this.setTimestamp(generatedKey.getTimestamp());
        return generatedKey;
    }

    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;

    }
}

