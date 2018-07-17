
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

        for(int i = 0; i < 10; ++i) {
            LicenseEntity license = new LicenseEntity();
            license.setId((long)i);
            license.setGeneratedKey("Generated " + i + " Key");
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
    public void deleteOneByKey() throws Exception {
        this.mockMvc.perform((MockMvcRequestBuilders.delete(("/license/delete/Generated 50 Key"))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void readOne() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/license/findone/Generated 50 Key"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

//    @Test
//    public void generateLicense() throws Exception{
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/license/sendjson1/{lj}"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//    }

}
