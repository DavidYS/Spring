package com.fortech;

import com.fortech.dto.LicenseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LicenseService {

    LicenseDto generare(String jsonString);

    ResponseEntity saveLicense(LicenseDto licenseDto);

    List<LicenseDto> readAllLicenseDTO();

    ResponseEntity<String> deleteLicenseDTO(String json1);

    ResponseEntity findLicenseDto(String generatedKey);

}
