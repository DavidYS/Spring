package com.fortech.entity;

import com.google.gson.Gson;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ValidationKey {

    private String hostName;
    private String ipAddress;
    private String ipMac;
    private String timestamp;
    private String start_date;
    private String finish_date;
    private String client;

    public String getHostName() {
        return hostName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getIpMac() {
        return ipMac;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getFinish_date() {
        return finish_date;
    }

    public String getClient() {
        return client;
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

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setFinish_date(String finish_date) {
        this.finish_date = finish_date;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void generate(GeneratedKey generatedKey){
        if(generatedKey.notNull()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Calendar cal = Calendar.getInstance();
            Date date_start = cal.getTime();
            cal.add(Calendar.YEAR, 1); // to get previous year add -1
            Date date_finish = cal.getTime();
            this.setHostName(generatedKey.getHostName());
            this.setIpAddress(generatedKey.getIpAddress());
            this.setIpMac(generatedKey.getIpMac());
            this.setTimestamp(generatedKey.getTimestamp());
            this.setStart_date(sdf.format(date_start));
            this.setFinish_date(sdf.format(date_finish));
            this.setClient(System.getProperty("user.name"));
        }
    }


    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;

    }
}


