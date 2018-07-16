package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.LicenseEntity;
import com.fortech.repository.LicenseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ServiceApiImplTest {

    @Mock
    LicenseRepository licenseRepositoryMock;

    @InjectMocks
    LicenseServiceImpl licenseServiceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddGeneratedKey() {

        LicenseEntity aMockContact = new LicenseEntity();
        aMockContact.setGeneratedKey("sd");
        aMockContact.setValidationKey("dsfg");

        when(licenseRepositoryMock.save(any(LicenseEntity.class))).thenReturn((aMockContact));

        List<LicenseDto> newLicense = licenseServiceImpl.readAllLicenseDTO();

        assertEquals("sd", aMockContact.getGeneratedKey());


    }
}