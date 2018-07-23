//
//    package com.fortech;
//
//import com.fortech.entity.LicenseEntity;
//import com.fortech.repository.LicenseRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//
//    @RunWith(SpringRunner.class)
//    @DataJpaTest
//    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//    public class LicenseRepositoryIntegrationTest {
//
//        @Autowired
//        private TestEntityManager entityManager;
//
//        @Autowired
//        private LicenseRepository licenseRepository;
//
//        @Test
//        public void testFindByValidationKey() {
//
//            //setup data scenario
//            LicenseEntity license = new LicenseEntity();
//            license.setValidationKey("CE0FCl4HKwIfAFYXSRIKAA0OABABARdfDRsHABNHX00EFWwXAREXFgcPUUdIFF1IHE9XW0AcVUZeXFFHX00EFWASBkFIR0UVRlVKAFhVAD0rWEAdTlouS09HBwYAAF4HBA4CR04PWlBKH19WG0BWRkcdW0pDSxAREh0ZOkkSEQZQX1YcUkhJGkJUHUhXV14PBQEBABANLAsMEUhRX0FDXFkdXEhLHV5fD1VNFh5EBgYbS1lHGgEZAF8dFgsbFVZQ");
//            entityManager.persist(license);
//
//            //find an inserted record using repository class
//            LicenseEntity foundLicense = licenseRepository.findByValidationKey("CE0FCl4HKwIfAFYXSRIKAA0OABABARdfDRsHABNHX00EFWwXAREXFgcPUUdIFF1IHE9XW0AcVUZeXFFHX00EFWASBkFIR0UVRlVKAFhVAD0rWEAdTlouS09HBwYAAF4HBA4CR04PWlBKH19WG0BWRkcdW0pDSxAREh0ZOkkSEQZQX1YcUkhJGkJUHUhXV14PBQEBABANLAsMEUhRX0FDXFkdXEhLHV5fD1VNFh5EBgYbS1lHGgEZAF8dFgsbFVZQ");
//
//            //assertion
//            assertThat(foundLicense.getValidationKey(), is(equalTo("CE0FCl4HKwIfAFYXSRIKAA0OABABARdfDRsHABNHX00EFWwXAREXFgcPUUdIFF1IHE9XW0AcVUZeXFFHX00EFWASBkFIR0UVRlVKAFhVAD0rWEAdTlouS09HBwYAAF4HBA4CR04PWlBKH19WG0BWRkcdW0pDSxAREh0ZOkkSEQZQX1YcUkhJGkJUHUhXV14PBQEBABANLAsMEUhRX0FDXFkdXEhLHV5fD1VNFh5EBgYbS1lHGgEZAF8dFgsbFVZQ")));
//        }
//    }
//}
