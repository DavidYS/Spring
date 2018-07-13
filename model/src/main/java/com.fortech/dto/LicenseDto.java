package com.fortech.dto;

import com.fortech.entity.LicenseEntity;

public class LicenseDto {
    private String validationKey;
    private String generatedKey;

    public LicenseDto() {

    }


    public LicenseEntity toEntity(){
        LicenseEntity entity=new LicenseEntity();
        entity.setValidationKey(this.validationKey);
        entity.setGeneratedKey(this.generatedKey);
        return entity;
    }



    public void setValidationKey(String validationKey) {
        this.validationKey = validationKey;
    }

    public void setGeneratedKey(String generatedKey) {
        this.generatedKey = generatedKey;
    }

    public String getValidationKey() {
        return validationKey;
    }

    public String getGeneratedKey() {
        return generatedKey;
    }
}
