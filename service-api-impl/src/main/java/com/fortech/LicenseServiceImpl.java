package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.GeneratedKey;
import com.fortech.entity.ValidationKey;
import com.fortech.entity.LicenseEntity;
import com.fortech.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LicenseServiceImpl implements LicenseService{

    @Autowired
    LicenseRepository licenseRepository;

    public List<LicenseDto> readAllLicenseDTO(){
        List<LicenseEntity> list = licenseRepository.findAll();

        List<LicenseDto> listDto = new ArrayList<>();

        list.forEach(l -> {
            listDto.add(l.toDto());
            System.out.println(l.getGeneratedKey());
        });

        return listDto;
    }

    public LicenseEntity deleteLicenseDTO(String json1) {

        List<LicenseEntity> entities = licenseRepository.findAll();
        int nr = 0;
        for(LicenseEntity e : entities){
            if(e.getGeneratedKey().equals(json1)){
                nr++;
            }
        }
        if(nr > 1){
            System.out.println("Sunt mai multe");
            return null;
        }

        LicenseEntity licenseEntity = licenseRepository.findByGeneratedKey(json1);
        List<LicenseEntity> list = licenseRepository.findAll();
        System.out.println(json1);
        System.out.println(list.size());
        System.out.println(1111111);
        if (licenseEntity != null) {
            System.out.println(licenseEntity.getValidationKey());
            licenseRepository.delete(licenseEntity);

            return licenseEntity;
        } else {
            System.out.println("License : " + json1 + " not found.");
        }

        return null;

    }

    public LicenseDto findLicenseDto(String generatedKey){

        List<LicenseEntity> entities = licenseRepository.findAll();
        int nr = 0;
        for(LicenseEntity e : entities){
            if(e.getGeneratedKey().equals(generatedKey)){
                nr++;
            }
        }
        if(nr > 1){
            System.out.println("Sunt mai multe");
            return null;
        }

        LicenseEntity licenseEntity = licenseRepository.findByGeneratedKey(generatedKey);

        if(licenseEntity == null){
            System.out.println("License : " + generatedKey + " not found.");
            return null;
        }

        LicenseDto licenseDto = licenseEntity.toDto();

        return licenseDto;
    }


    @Override
    public LicenseDto generare(String jsonString)
    {
        GeneratedKey generatedKey = new GeneratedKey();
        ValidationKey validationKey = new ValidationKey();
        validationKey.generate(generatedKey.fromString(jsonString));

        LicenseDto licenseDto = new LicenseDto();
        licenseDto.setGeneratedKey(generatedKey.toString());
        licenseDto.setValidationKey(validationKey.toString());

        return licenseDto;
    }

    @Override
    public void saveLicense(LicenseDto licenseDto) {
        LicenseEntity licenseEntity = new LicenseEntity();
        licenseEntity = licenseDto.toEntity();
        licenseRepository.save(licenseEntity);
    }

}