package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.License1;
import com.fortech.entity.License2;
import com.fortech.entity.LicenseEntity;
import com.fortech.repository.LicenseRepository;
import io.swagger.annotations.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LicenseServiceImpl implements LicenseService{

    @Autowired
    LicenseRepository licenseRepository;

    /*LicenseServiceImpl(LicenseRepository licenseRepository){
        this.licenseRepository = licenseRepository;
    }*/

    public List<LicenseDto> readAllLicenseDTO(){
        List<LicenseEntity> list = licenseRepository.findAll();

        List<LicenseDto> listDto = new ArrayList<>();

        list.forEach(l -> {
            listDto.add(l.toDto());
            System.out.println(l.getGeneratedKey());
        });

        return listDto;
    }


    @Override
    public LicenseDto generare(String jsonString)
    {
        License1 license1 = new License1();
        License2 license2 = new License2();
        license2.generate(license1.fromString(jsonString));

        LicenseDto licenseDto = new LicenseDto();
        licenseDto.setGeneratedKey(license1.toString());
        licenseDto.setValidationKey(license2.toString());

        return licenseDto;
    }

    @Override
    public void saveLicense(LicenseDto licenseDto) {
        LicenseEntity licenseEntity = new LicenseEntity();
        licenseEntity = licenseDto.toEntity();
        licenseRepository.save(licenseEntity);

    }


}