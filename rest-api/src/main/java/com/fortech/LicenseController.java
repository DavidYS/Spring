package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.License1;
import com.fortech.entity.License2;
import com.fortech.entity.LicenseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/license")
public interface LicenseController {


    @GetMapping("/readAll")
    List<LicenseDto> readAllLicenses();



    @GetMapping("/licenseId")
    public LicenseDto readOneLicense1();

    @RequestMapping(value = "/sendjson1/{jsonString}", method = RequestMethod.GET)
    String generateLicense(@PathVariable String jsonString);

}
