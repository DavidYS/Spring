package com.fortech;

import com.fortech.dto.LicenseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/license")
public interface LicenseController {

    @GetMapping("/readAll")
    List<LicenseDto> readAllLicenses();

    @DeleteMapping("/delete/{generatedkey}")
    ResponseEntity deleteByGeneratedKey(@PathVariable("generatedkey") String generatedkey);

    @GetMapping("/findone/{generatedkey}")
    ResponseEntity readOne(@PathVariable("generatedkey") String generatedKey);

    @GetMapping("/sendjson1/{jsonString}")
    ResponseEntity generateLicense(@PathVariable String jsonString);

}
