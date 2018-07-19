package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.encrypt.Cipher;
import com.fortech.entity.GeneratedKey;
import com.google.gson.Gson;
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

        Cipher cipher = new Cipher();
        String jsonDecoded = cipher.decrypt(generatedkey);

        Gson gson = new Gson();
        GeneratedKey generate = gson.fromJson(jsonDecoded, GeneratedKey.class);

        String json1 = generate.toString();
        System.out.println(generate);

        System.out.println(json1);
        return licenseService.deleteLicenseDTO(json1);

        //return new LicenseEntity();
    }

    @GetMapping("/findone/{generatedkey}")
    public LicenseDto readOne(@PathVariable("generatedkey") String generatedKey){

        Cipher cipher = new Cipher();
        String jsonDecoded = cipher.decrypt(generatedKey);

        GeneratedKey generatedKey1 = new GeneratedKey();
        generatedKey1.fromString(jsonDecoded);
        String json1 = generatedKey1.toString();

        System.out.println(json1);
        return licenseService.findLicenseDto(json1);

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
