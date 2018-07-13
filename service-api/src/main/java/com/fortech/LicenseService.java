package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.License1;
import com.fortech.entity.License2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public interface LicenseService {


    public LicenseDto generare(String jsonString);

    public void saveLicense(LicenseDto licenseDto);



}
