package com.fortech;

import com.fortech.dto.LicenseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LicenseService {


    LicenseDto generare(String jsonString);

    void saveLicense(LicenseDto licenseDto);

    List<LicenseDto> readAllLicenseDTO();



}
