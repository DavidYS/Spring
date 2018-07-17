
package com.fortech;

import com.fortech.entity.LicenseEntity;
import com.fortech.repository.LicenseRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = {BootApplication.class}
)
public class  LicenseControllerImplTest {
    @Autowired
    private LicenseRepository licenseRepository;
    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext web;



    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.web).build();

        for(int i = 1; i <= 10; ++i) {
            LicenseEntity license = new LicenseEntity();
            license.setId((long)i);
            license.setGeneratedKey("CE0FCl4HKwIfAFYXSRIKAA0OABABARdfDRsHABNHX00EFWwXAREXFgcPUUdIFF1IHE9XW0AcVUZeXFFHX00EFWASBkFIR0UVRlVKAFhVAD0rWEAdTlouS09HBwYAAF4HBA4CR04PWlBKHFdUG01XQkAUUUpDSxAREh0ZOkkSEQZQX1YcXEhJGkJUHUhXV14PBQEBABANLAsMEUhRX0FDUlkdXEhLHV5fD1VNFh5EBgYbS1lHMAMEAEMHRx4="+i);
            license.setValidationKey("Validation: " + i);
            this.licenseRepository.save(license);
        }

    }

    @After
    public void deleteDatabase() {
        this.licenseRepository.deleteAll();
    }

    @Test
    public void readAllLicenses() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/license/readAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].*", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(10)));
    }

    @Test
    public void deleteOneByKeyIfCorrectKey() throws Exception {
        this.mockMvc.perform((MockMvcRequestBuilders.delete(("/license/delete/CE0FCl4HKwIfAFYXSRIKAA0OABABARdfDRsHABNHX00EFWwXAREXFgcPUUdIFF1IHE9XW0AcVUZeXFFHX00EFWASBkFIR0UVRlVKAFhVAD0rWEAdTlouS09HBwYAAF4HBA4CR04PWlBKHFdUG01XQkAUUUpDSxAREh0ZOkkSEQZQX1YcXEhJGkJUHUhXV14PBQEBABANLAsMEUhRX0FDUlkdXEhLHV5fD1VNFh5EBgYbS1lHMAMEAEMHRx4=1"))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Licența a fost ștearsă."));
    }

    @Test
    public void deleteOneByKeyIfNotFound() throws Exception {
        this.mockMvc.perform((MockMvcRequestBuilders.delete(("/license/delete/CE0FCl4HKwIfAFYXSRIKAA0OABABARdfDRsHABNHX00EFWwXAREXFgcPUUdIFF1IHE9XW0AcVUZeXFFHX00EFWASBkFIR0UVRlVKAFhVAD0rWEAdTlouS09HBwYAAF4HBA4CR04PWlBKHFdUG01XQkAUUUpDSxAREh0ZOkkSEQZQX1YcXEhJGkJUHUhXV14PBQEBABANLAsMEUhRX0FDUlkdXEhLHV5fD1VNFh5EBgYbS1lHMAMEAEMHRx4"))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Licența nu a fost găsită."));
    }


    @Test
    public void readOne() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/license/findone/CE0FCl4HKwIfAFYXSRIKAA0OABABARdfDRsHABNHX00EFWwXAREXFgcPUUdIFF1IHE9XW0AcVUZeXFFHX00EFWASBkFIR0UVRlVKAFhVAD0rWEAdTlouS09HBwYAAF4HBA4CR04PWlBKHFdUG01XQkAUUUpDSxAREh0ZOkkSEQZQX1YcXEhJGkJUHUhXV14PBQEBABANLAsMEUhRX0FDUlkdXEhLHV5fD1VNFh5EBgYbS1lHMAMEAEMHRx4=1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

//    @Test
//    public void generateLicense() throws Exception{
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/license/sendjson1/{lj}"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//    }

}
