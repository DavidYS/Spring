package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.LicenseEntity;
import com.fortech.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
public class LicenseControllerImpl implements LicenseController {

/*@Autowired
private LicenseRepository licenseRepository;*/

    @Autowired
    LicenseService licenseService;

    @Override
    public List<LicenseDto> readAllLicenses() {

        return licenseService.readAllLicenseDTO();

    }

    @Override
    public LicenseDto readOneLicense1() {
        LicenseDto dto = new LicenseDto();
        dto.setGeneratedKey("string11");
        dto.setValidationKey("string2");
        return dto;
    }

    @Override
    public String generateLicense(@PathVariable String jsonString) {
        LicenseDto licenseDto = new LicenseDto();
        licenseDto = licenseService.generare(jsonString);

        licenseService.saveLicense(licenseDto);

        System.out.println(licenseDto.getValidationKey());

        return licenseDto.getValidationKey();

    }


}
