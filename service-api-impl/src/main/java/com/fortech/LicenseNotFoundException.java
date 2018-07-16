package com.fortech;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LicenseNotFoundException extends RuntimeException {

    public LicenseNotFoundException(String exc){
        super("License : " + exc + " not found.");
    }

    public String getMessage(String name){
        return "License : " + name + " not found.";
    }
}
