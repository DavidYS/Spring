package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.LicenseEntity;
import com.fortech.repository.LicenseRepository;
import controller.LicenseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
public class LicenseControllerImpl implements LicenseController {

@Autowired
    private LicenseRepository licenseRepository;
    public String test() {
        return "Hello world!";
    }
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
    public LicenseDto readOneLicense() {
        LicenseDto dto = new LicenseDto();
        dto.setGeneratedKey("string11");
        dto.setValidationKey("string2");
        return dto;
    }

    @Override
    public ResponseEntity<Object> add(LicenseDto input) {
        return null;
    }
}
