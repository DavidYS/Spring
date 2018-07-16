package com.fortech.entity;

import com.fortech.dto.LicenseDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;


@Entity
public class LicenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "generated_key")
    private String generatedKey;

    @Column(name = "validation_key")
    private String validationKey;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGeneratedKey() {
        return generatedKey;
    }

    public void setGeneratedKey(String generatedKey) {
        this.generatedKey = generatedKey;
    }

    public String getValidationKey() {
        return validationKey;
    }

    public void setValidationKey(String validationKey) {
        this.validationKey = validationKey;
    }

    @Override
    public String toString() {
        return String.format("License[id=%d, validationKey='%s', generatedKey='%s']", id, validationKey, generatedKey);
    }

     public LicenseDto toDto(){
       LicenseDto dto = new LicenseDto();
       dto.setGeneratedKey(this.generatedKey);
     dto.setValidationKey(this.validationKey);
      return dto;
}

}
