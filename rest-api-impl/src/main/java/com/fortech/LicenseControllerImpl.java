package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.encrypt.Cipher;
import com.fortech.keys.GeneratedKey;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.json.*;


@CrossOrigin
@RestController
public class LicenseControllerImpl implements LicenseController {

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
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    @DeleteMapping("/delete/{generatedkey}")
    public ResponseEntity deleteByGeneratedKey(@PathVariable("generatedkey") String generatedkey) {

        String jsonDecoded = Cipher.decrypt(generatedkey);
        if (isJSONValid(jsonDecoded)) {
            Gson gson = new Gson();
            GeneratedKey generate = gson.fromJson(jsonDecoded, GeneratedKey.class);
            String json1 = generate.toString();
            return new ResponseEntity<>(licenseService.deleteLicenseDTO(json1), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findone/{generatedkey}")
    public ResponseEntity readOne(@PathVariable("generatedkey") String generatedKey) {

        String jsonDecoded = Cipher.decrypt(generatedKey);
        if (isJSONValid(jsonDecoded)) {
            GeneratedKey generatedKey1 = new GeneratedKey();
            generatedKey1.generateFromString(jsonDecoded);
            String json1 = generatedKey1.toString();
            return new ResponseEntity<>(licenseService.findLicenseDto(json1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> generateLicense(@PathVariable String jsonString) {
        LicenseDto licenseDto = new LicenseDto();
        String jsonDecoded = Cipher.decrypt(jsonString);
        if (isJSONValid(jsonDecoded)) {
            licenseDto = licenseService.generare(jsonDecoded);
            licenseService.saveLicense(licenseDto);
            return new ResponseEntity<>(Cipher.encrypt(licenseDto.getValidationKey()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
