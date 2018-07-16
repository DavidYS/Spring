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
<<<<<<< HEAD:model/src/main/java/com.fortech/entity/License1.java
        License1 license1 = new License1();
        license1 = gson.fromJson(json1, License1.class);
        this.setHostName(license1.getHostName());
        this.setIpAddress(license1.getIpAddress());
        this.setIpMac(license1.getIpMac());
        this.setTimestamp(license1.getTimestamp());
        return license1;
=======
        GeneratedKey generatedKey = new GeneratedKey();
        generatedKey = gson.fromJson(json1, GeneratedKey.class);
        return generatedKey;
>>>>>>> 69223f1280f9a7576219b7aa3b1d1141bd781a5d:model/src/main/java/com.fortech/entity/GeneratedKey.java
    }

    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;

    }
}

