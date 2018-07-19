package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.GeneratedKey;
import com.fortech.entity.ValidationKey;
import com.fortech.entity.LicenseEntity;
import com.fortech.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class LicenseServiceImpl implements LicenseService{

    @Autowired
    LicenseRepository licenseRepository;

    public List<LicenseDto> readAllLicenseDTO(){
        return licenseRepository.findAll()
                .stream()
                .map(LicenseEntity::toDto)
                .collect(Collectors.toList());
    }

    public String deleteLicenseDTO(String json) {

        List<LicenseEntity> entities = licenseRepository.findAll();

        int ok=0;

        for(LicenseEntity e : entities){
            System.out.println();
            System.out.println(e.getGeneratedKey());
            if(e.getGeneratedKey().equals(json)){
                licenseRepository.delete(e);
                ok=1;
            }
        }

        if(ok==1){
            return "Licența a fost ștearsă.";
        } else {
            return "Licența nu a fost găsită.";
        }

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
        generatedKey.generateFromString(jsonString);
        validationKey.generate(generatedKey);

        LicenseDto licenseDto = new LicenseDto();
        licenseDto.setGeneratedKey(generatedKey.toString());
        licenseDto.setValidationKey(validationKey.toString());

        return licenseDto;
    }

    @Override
    public void saveLicense(LicenseDto licenseDto) {
        
        LicenseEntity licenseEntity = new LicenseEntity();
        licenseEntity = licenseDto.toEntity();



        List<LicenseEntity> entities = licenseRepository.findAll();
        
        int nr = 0;
        for(LicenseEntity e : entities){
            if(e.getGeneratedKey().equals(licenseEntity.getGeneratedKey())){
                nr++;
            }
        }
        if(nr >= 1){
            System.out.println("Sunt mai multe");
        }
        
        if(nr == 0){
            licenseRepository.save(licenseEntity);
        }
    }

}
