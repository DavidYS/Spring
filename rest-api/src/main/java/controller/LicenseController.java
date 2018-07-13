package controller;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.LicenseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/license")
public interface LicenseController {

    @GetMapping("/readAll")
    List<LicenseDto> readAllLicenses();

    @GetMapping("/test")
    String test();

    @GetMapping("/licenseId")
    public LicenseDto readOneLicense();


    @PostMapping
    ResponseEntity<Object> add(@RequestBody LicenseDto input);

//    @RequestMapping(value="/read", method = RequestMethod.POST)
//    public void process(@RequestBody String license1) throws Exception {
//
//    }


}
