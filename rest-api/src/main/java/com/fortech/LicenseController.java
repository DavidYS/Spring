package com.fortech;

import com.fortech.dto.LicenseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/license")
public interface LicenseController {


    @GetMapping("/readAll")
    List<LicenseDto> readAllLicenses();



    @GetMapping("/licenseId")
    public LicenseDto readOneLicense1();

    @GetMapping("/sendjson1/{jsonString}")
    String generateLicense(@PathVariable String jsonString);

}
