package com.fortech.entity;

import java.util.Date;

public class License2 {

    private String hostName;
    private String ipAddress;
    private String ipMac;
    private String timestamp;
    private Date start_date;
    private Date finish_date;
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

    public Date getStart_date() {
        return start_date;
    }

    public Date getFinish_date() {
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

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }

    public void setClient(String client) {
        this.client = client;
    }

//    public License2 generate(License1 license1){
//        Date date_start= new Date();
//        License2 generate = new License2();
//        generate.setHostName(license1.getHostName();
//        generate.setIpAddress(license1.getIpAddress());
//        generate.setIpMac(license1.getIpMac());


    }


