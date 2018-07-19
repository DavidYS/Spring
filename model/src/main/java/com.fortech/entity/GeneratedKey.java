package com.fortech.entity;


import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import javax.persistence.Entity;



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


    public void generateFromString(String json1) throws JsonParseException{

            Gson gson = new Gson();
            GeneratedKey generatedKey = new GeneratedKey();
            boolean thrown=false;
        try {
            generatedKey = gson.fromJson(json1, GeneratedKey.class);
        }
        catch(JsonParseException e)
        {
            System.out.println("String-ul nu poate fi parsat: "+json1);
            thrown=true;
        }
        if(!thrown)
        {
            this.setHostName(generatedKey.getHostName());
            this.setIpAddress(generatedKey.getIpAddress());
            this.setIpMac(generatedKey.getIpMac());
            this.setTimestamp(generatedKey.getTimestamp());
        }
    }

    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;

    }

    public boolean notNull(){
        if((this.getHostName()!=null)&&
        (this.getIpAddress()!=null)&&
                (this.getIpMac()!=null)&&
                (this.getTimestamp()!=null))return true;
        else return false;
    }
}

