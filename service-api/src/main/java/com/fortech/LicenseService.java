package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.License1;
import com.fortech.entity.License2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LicenseService {


    LicenseDto generare(String jsonString);

    void saveLicense(LicenseDto licenseDto);

    List<LicenseDto> readAllLicenseDTO();



}
