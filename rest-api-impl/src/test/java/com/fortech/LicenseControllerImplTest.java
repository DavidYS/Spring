
package com.fortech;

import com.fortech.entity.LicenseEntity;
import com.fortech.repository.LicenseRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = {BootApplication.class}
)
public class LicenseControllerImplTest {
    @Autowired
    private LicenseRepository licenseRepository;
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext web;

    private String generatedKey = "CE0FCl4HKwIfAFYXSRIKAA0OABABARdfDRsHABNHX00EFWwXAREXFgcPUUdIFF1IHE9XW0AcVUZeXFF" +
            "HX00EFWASBkFIR0UVRlVKAFhVAD0rWEAdTlouS09HBwYAAF4HBA4CR04PWlBKHFdUG01XQkAUUUpDSxAREh0ZOkkSEQZQX1" +
            "YcXEhJGkJUHUhXV14PBQEBABANLAsMEUhRX0FDUlkdXEhLHV5fD1VNFh5EBgYbS1lHMAMEAEMHRx4=";

    @After
    public void deleteDatabase() {
        this.licenseRepository.deleteAll();
    }

    @Test
    public void readAllLicenses() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.web).build();

        LicenseEntity licenseOne = new LicenseEntity();
        licenseOne.setGeneratedKey(generatedKey);
        licenseOne.setValidationKey("validation");
        licenseRepository.save(licenseOne);

        LicenseEntity licenseTwo = new LicenseEntity();
        licenseTwo.setGeneratedKey(generatedKey + "1");
        licenseTwo.setValidationKey("validation2");
        licenseRepository.save(licenseTwo);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/license/readAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].*", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void deleteKeyIfIncorrect() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.web).build();

        LicenseEntity licenseOne = new LicenseEntity();
        licenseOne.setGeneratedKey(generatedKey);
        licenseOne.setValidationKey("validation");
        licenseRepository.save(licenseOne);

        LicenseEntity licenseTwo = new LicenseEntity();
        licenseTwo.setGeneratedKey(generatedKey + "1");
        licenseTwo.setValidationKey("validation2");
        licenseRepository.save(licenseTwo);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/license/delete/" + "Incorrect" + generatedKey))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed())
                .andReturn().getResponse().getContentAsString();
    }


    @Test
    public void readOneIfKeyCorrect() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.web).build();

        LicenseEntity licenseOne = new LicenseEntity();
        licenseOne.setGeneratedKey(generatedKey);
        licenseOne.setValidationKey("validation");
        licenseRepository.save(licenseOne);

        LicenseEntity licenseTwo = new LicenseEntity();
        licenseTwo.setGeneratedKey(generatedKey + "1");
        licenseTwo.setValidationKey("validation2");
        licenseRepository.save(licenseTwo);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/license/findone/" + generatedKey))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void readOneIfKeyIncorrect() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.web).build();

        LicenseEntity licenseOne = new LicenseEntity();
        licenseOne.setGeneratedKey(generatedKey);
        licenseOne.setValidationKey("validation");
        licenseRepository.save(licenseOne);

        LicenseEntity licenseTwo = new LicenseEntity();
        licenseTwo.setGeneratedKey(generatedKey + "1");
        licenseTwo.setValidationKey("validation2");
        licenseRepository.save(licenseTwo);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/license/findone/" + "Incorrect" + generatedKey))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn().getResponse().getContentAsString();

    }

    @Test
    public void generateLicenseIfKeyCorrect() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.web).build();

        LicenseEntity licenseOne = new LicenseEntity();
        licenseOne.setGeneratedKey(generatedKey);
        licenseOne.setValidationKey("validation");
        licenseRepository.save(licenseOne);

        LicenseEntity licenseTwo = new LicenseEntity();
        licenseTwo.setGeneratedKey(generatedKey + "1");
        licenseTwo.setValidationKey("validation2");
        licenseRepository.save(licenseTwo);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/license/sendjson1/" + generatedKey))
                .andExpect(MockMvcResultMatchers.status().isOk());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/license/readAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].*", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));

    }

    @Test
    public void generateLicenseIfKeyIncorrect() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.web).build();

        LicenseEntity licenseOne = new LicenseEntity();
        licenseOne.setGeneratedKey(generatedKey);
        licenseOne.setValidationKey("validation");
        licenseRepository.save(licenseOne);

        LicenseEntity licenseTwo = new LicenseEntity();
        licenseTwo.setGeneratedKey(generatedKey + "1");
        licenseTwo.setValidationKey("validation2");
        licenseRepository.save(licenseTwo);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/license/sendjson1/" + "Incorrect" + generatedKey))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/license/readAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].*", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

}
