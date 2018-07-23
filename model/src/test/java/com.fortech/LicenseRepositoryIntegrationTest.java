package com.fortech;

import com.fortech.entity.LicenseEntity;
import com.fortech.repository.LicenseRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

////@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//public class LicenseRepositoryIntegrationTest {
//
////    @Autowired
////    private TestEntityManager entityManager;
//
//    @Autowired
//    private LicenseRepository licenseRepository;
//
//
//    @Before
//    public void setup() {
//
//        for (int i = 1; i <= 10; ++i) {
//            LicenseEntity license = new LicenseEntity();
//            license.setId((long) i);
//            license.setGeneratedKey("CE0FCl4HKwIfAFYXSRIKAA0OABABARdfDRsHABNHX00EFWwXAREXFgcPUUdIFF1IHE9XW0AcVUZeXFF" +
//                    "HX00EFWASBkFIR0UVRlVKAFhVAD0rWEAdTlouS09HBwYAAF4HBA4CR04PWlBKHFdUG01XQkAUUUpDSxAREh0ZOkkSEQZQX1" +
//                    "YcXEhJGkJUHUhXV14PBQEBABANLAsMEUhRX0FDUlkdXEhLHV5fD1VNFh5EBgYbS1lHMAMEAEMHRx4=" + i);
//            license.setValidationKey("Validation: " + i);
//            this.licenseRepository.save(license);
//        }
//    }
//
//    @After
//    public void deleteDatabase() {
//        this.licenseRepository.deleteAll();
//    }
//    @Test
//    public void findByGeneratedKey_ExpectALicenseDto(){
//        LicenseEntity foundLicense = licenseRepository.findByGeneratedKey("CE0FCl4HKwIfAFYXSRIKAA0OABABARdfDRsHABNHX00EFWwXAREXFgcPUUdIFF1IHE9XW0AcVUZeXFF" +
//                "HX00EFWASBkFIR0UVRlVKAFhVAD0rWEAdTlouS09HBwYAAF4HBA4CR04PWlBKHFdUG01XQkAUUUpDSxAREh0ZOkkSEQZQX1" +
//                "YcXEhJGkJUHUhXV14PBQEBABANLAsMEUhRX0FDUlkdXEhLHV5fD1VNFh5EBgYbS1lHMAMEAEMHRx4=1");
//
//        assertEquals(foundLicense.getGeneratedKey(), "CE0FCl4HKwIfAFYXSRIKAA0OABABARdfDRsHABNHX00EFWwXAREXFgcPUUdIFF1IHE9XW0AcVUZeXFF" +
//                "HX00EFWASBkFIR0UVRlVKAFhVAD0rWEAdTlouS09HBwYAAF4HBA4CR04PWlBKHFdUG01XQkAUUUpDSxAREh0ZOkkSEQZQX1" +
//                "YcXEhJGkJUHUhXV14PBQEBABANLAsMEUhRX0FDUlkdXEhLHV5fD1VNFh5EBgYbS1lHMAMEAEMHRx4=1");
//    }
//    @Test
//    public void testFindByValidationKey() {
//
//        LicenseEntity foundLicense = licenseRepository.findByValidationKey("sdfghfds");
//
//        assertThat(foundLicense.getValidationKey(), is(equalTo("sdfghfds")));
//    }
//
////    @Test
////    public void testFindSpecificLicenseById() {
////
////        LicenseEntity foundLicense = entityManager.find(LicenseEntity.class, new Long("2"));
////
////        assertThat(foundLicense.getValidationKey(), is(equalTo("sdfghfds")));
////    }
//
//
//}