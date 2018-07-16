package com.fortech;

import com.fortech.dto.LicenseDto;
<<<<<<< HEAD
import com.fortech.entity.License1;
import com.fortech.entity.License2;
import com.fortech.entity.LicenseEntity;
import org.springframework.context.annotation.Bean;
=======
>>>>>>> 69223f1280f9a7576219b7aa3b1d1141bd781a5d
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LicenseService {


    LicenseDto generare(String jsonString);

    void saveLicense(LicenseDto licenseDto);

    List<LicenseDto> readAllLicenseDTO();

    LicenseEntity deleteLicenseDTO(String json1);

    LicenseDto findLicenseDto(String generatedKey);



}
