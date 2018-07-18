package com.fortech.entity;


import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import javax.persistence.Entity;


@Entity
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


    public GeneratedKey fromString(String json1) throws JsonParseException{
        try {
            Gson gson = new Gson();
            GeneratedKey generatedKey = gson.fromJson(json1, GeneratedKey.class);
            this.setHostName(generatedKey.getHostName());
            this.setIpAddress(generatedKey.getIpAddress());
            this.setIpMac(generatedKey.getIpMac());
            this.setTimestamp(generatedKey.getTimestamp());
            return generatedKey;
        }
        catch(JsonParseException e)
        {
            System.out.println("Exceptie la parsarea string-ului: "+json1);
            return null;
        }

    }

    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;

    }
}

