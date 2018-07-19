package com.fortech;

import com.fortech.entity.LicenseEntity;
import com.fortech.repository.LicenseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LicenseRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LicenseRepository licenseRepository;

    @Test
    public void testFindByValidationKey() {

        //setup data scenario
        LicenseEntity license = new LicenseEntity();
        license.setValidationKey("CGVNRQ8bChAGKxVADkdDDU0TXhwdWD1dFwE_BQYdXlhUVQ9fb0NSRx1dKgEdXwoVXltVVVAcUV9BWU1UXV5PSSdTRUEGDBlIGBEYQB9EF1lNREceUlBWXlNURVxaUA95GA");
        entityManager.persist(license);

        //find an inserted record using repository class
        LicenseEntity foundLicense = licenseRepository.findByValidationKey("CGVNRQ8bChAGKxVADkdDDU0TXhwdWD1dFwE_BQYdXlhUVQ9fb0NSRx1dKgEdXwoVXltVVVAcUV9BWU1UXV5PSSdTRUEGDBlIGBEYQB9EF1lNREceUlBWXlNURVxaUA95GA");

        //assertion
        assertThat(foundLicense.getValidationKey(), is(equalTo("CGVNRQ8bChAGKxVADkdDDU0TXhwdWD1dFwE_BQYdXlhUVQ9fb0NSRx1dKgEdXwoVXltVVVAcUV9BWU1UXV5PSSdTRUEGDBlIGBEYQB9EF1lNREceUlBWXlNURVxaUA95GA")));
    }
}
