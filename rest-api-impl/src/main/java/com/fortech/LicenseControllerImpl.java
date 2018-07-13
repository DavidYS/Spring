package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.LicenseEntity;
import com.fortech.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
public class LicenseControllerImpl implements LicenseController {

@Autowired
private LicenseRepository licenseRepository;

    @Autowired
    LicenseService licenseService;

    @Override
    public List<LicenseDto> readAllLicenses() {
        List<LicenseEntity> list = licenseRepository.findAll();
        List<LicenseDto> listDto = new ArrayList<>();

        list.forEach(l -> {
            listDto.add(l.toDto());
        });
        return listDto;

    }

    @Override
    public LicenseDto readOneLicense1() {
        LicenseDto dto = new LicenseDto();
        dto.setGeneratedKey("string11");
        dto.setValidationKey("string2");
        return dto;
    }

    @Override
    public String generateLicense(String jsonString) {
        LicenseDto licenseDto = new LicenseDto();
        licenseDto = licenseService.generare(jsonString);

        licenseService.saveLicense(licenseDto);

        return licenseDto.getValidationKey();

    }


}
