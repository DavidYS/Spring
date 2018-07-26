package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.keys.GeneratedKey;
import com.fortech.keys.ValidationKey;
import com.fortech.entity.LicenseEntity;
import com.fortech.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LicenseServiceImpl implements LicenseService{

    @Autowired
    private LicenseRepository licenseRepository;

    public List<LicenseDto> readAllLicenseDTO(){
        return licenseRepository.findAll()
                .stream()
                .map(LicenseEntity::toDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> deleteLicenseDTO(String json) {

        List<LicenseEntity> entities = licenseRepository.findAll();

        for(LicenseEntity e : entities){
            System.out.println(e.getGeneratedKey());
            if(e.getGeneratedKey().equals(json)){
                licenseRepository.delete(e);
                return new ResponseEntity<>("Licența a fost ștearsă.", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Licența nu a fost găsită.", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<LicenseDto> findLicenseDto(String generatedKey){

        List<LicenseEntity> entities = licenseRepository.findAll();
        int nr = 0;
        for(LicenseEntity e : entities){
            if(e.getGeneratedKey().equals(generatedKey)){
                nr++;
            }
        }
        if(nr > 1){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LicenseEntity licenseEntity = licenseRepository.findByGeneratedKey(generatedKey);

        if(licenseEntity == null){
            System.out.println("License : " + generatedKey + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(licenseEntity.toDto(), HttpStatus.OK);
    }

    @Override
    public LicenseDto generare(String jsonString, Integer months)
    {
        GeneratedKey generatedKey = new GeneratedKey();
        ValidationKey validationKey = new ValidationKey();
        generatedKey.generateFromString(jsonString);
        validationKey.generate(generatedKey, months);

        LicenseDto licenseDto = new LicenseDto();
        licenseDto.setGeneratedKey(generatedKey.toString());
        licenseDto.setValidationKey(validationKey.toString());

        return licenseDto;
    }

    @Override
    public ResponseEntity saveLicense(LicenseDto licenseDto) {

        LicenseEntity licenseEntity = new LicenseEntity();
        licenseEntity = licenseDto.toEntity();


        List<LicenseEntity> entities = licenseRepository.findAll();


        for(LicenseEntity e : entities){
            if(e.getGeneratedKey().equals(licenseEntity.getGeneratedKey())){
                return new ResponseEntity<>("License has already been used!", HttpStatus.ALREADY_REPORTED);
            }
        }
        return new ResponseEntity<>(licenseRepository.save(licenseEntity), HttpStatus.OK);
    }

}
