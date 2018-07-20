package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.encrypt.Cipher;
import com.fortech.entity.GeneratedKey;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.json.*;


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

    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    @DeleteMapping("/delete/{generatedkey}")
    public String deleteByGeneratedKey(@PathVariable("generatedkey") String generatedkey){

        //Cipher cipher = new Cipher();
        String jsonDecoded = Cipher.decrypt(generatedkey);

        //System.out.println(jsonDecoded);
        if(isJSONValid(jsonDecoded)) {

            Gson gson = new Gson();
            GeneratedKey generate = gson.fromJson(jsonDecoded, GeneratedKey.class);

            String json1 = generate.toString();
            System.out.println(generate);

            System.out.println(json1);
            return licenseService.deleteLicenseDTO(json1);
        }
        return null;

        //return new LicenseEntity();
    }

    @GetMapping("/findone/{generatedkey}")
    public LicenseDto readOne(@PathVariable("generatedkey") String generatedKey){

        //Cipher cipher = new Cipher();
        String jsonDecoded = Cipher.decrypt(generatedKey);
        System.out.println(jsonDecoded);
        System.out.println("STRING");

        if(isJSONValid(jsonDecoded)) {

            GeneratedKey generatedKey1 = new GeneratedKey();
            generatedKey1.generateFromString(jsonDecoded);
            String json1 = generatedKey1.toString();

            System.out.println(json1);
            return licenseService.findLicenseDto(json1);
        }
        System.out.println("NU A MERS MAI DEPARTE");
        return null;

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

        //Cipher cipher = new Cipher();
        String jsonDecoded = Cipher.decrypt(jsonString);

        System.out.println(jsonDecoded);
        System.out.println("STRING");

        if(isJSONValid(jsonDecoded)) {
            licenseDto = licenseService.generare(jsonDecoded);

            licenseService.saveLicense(licenseDto);

            System.out.println(licenseDto.getValidationKey());

            return Cipher.encrypt(licenseDto.getValidationKey());
        }
        System.out.println("NU A MERS MAI DEPARTE");
        return null;

    }


}
