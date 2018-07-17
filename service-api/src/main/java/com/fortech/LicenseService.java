package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.GeneratedKey;
import com.fortech.entity.ValidationKey;
import com.fortech.entity.LicenseEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LicenseService {


    LicenseDto generare(String jsonString);

    void saveLicense(LicenseDto licenseDto);

    List<LicenseDto> readAllLicenseDTO();

    String deleteLicenseDTO(String json1);

    LicenseDto findLicenseDto(String generatedKey);



}
