package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.encrypt.Cipher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete/{generatedkey}")
    public String deleteByGeneratedKey(@PathVariable("generatedkey") String generatedkey){

        System.out.println(generatedkey);
        return licenseService.deleteLicenseDTO(generatedkey);

        //return new LicenseEntity();
    }

    @GetMapping("/findone/{generatedkey}")
    public LicenseDto readOne(@PathVariable("generatedkey") String generatedKey){

        System.out.println(generatedKey);
        return licenseService.findLicenseDto(generatedKey);

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

        Cipher cipher = new Cipher();
        String jsonDecoded = cipher.decrypt(jsonString);

        licenseDto = licenseService.generare(jsonDecoded);
        
        licenseService.saveLicense(licenseDto);

        System.out.println(licenseDto.getValidationKey());

        return cipher.encrypt(licenseDto.getValidationKey());
    }


}
