package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.LicenseEntity;
import com.fortech.repository.LicenseRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.AssertTrue;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ServiceApiImplTest {

    @InjectMocks
   private LicenseServiceImpl licenseServiceImpl;

    @Mock
 private LicenseRepository licenseRepositoryMock;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void readAllLicenseDTO_shouldReturnANonEmptyList() {

        LicenseEntity licenseEntity1 = new LicenseEntity();
        String generatedKey1 = "abc";
        String validationKey1 = "def";
        licenseEntity1.setGeneratedKey(generatedKey1);
        licenseEntity1.setValidationKey(validationKey1);

        LicenseEntity licenseEntity2 = new LicenseEntity();
        String generatedKey2 = "ghj";
        String validationKey2 = "tyu";
        licenseEntity2.setGeneratedKey(generatedKey2);
        licenseEntity2.setValidationKey(validationKey2);

        List<LicenseEntity> licenseEntities = Arrays.asList(licenseEntity1, licenseEntity2 );
        when(licenseRepositoryMock.findAll()).thenReturn(licenseEntities);

        List<LicenseDto> result = licenseServiceImpl.readAllLicenseDTO();

        assertEquals(2, result.size());

        LicenseDto resultLicense1 = result.get(0);
        LicenseDto resultLicense2 = result.get(1);

        assertEquals(generatedKey1, resultLicense1.getGeneratedKey());
        assertEquals(validationKey1, resultLicense1.getValidationKey());

        assertEquals(generatedKey2, resultLicense2.getGeneratedKey());
        assertEquals(validationKey2, resultLicense2.getValidationKey());
    }

    @Test
    public void readAllLicenseDTO_shouldReturnAnEmptyList() {

        List<LicenseDto> result = licenseServiceImpl.readAllLicenseDTO();

        assertTrue(result.isEmpty());

    }

    @Test
    public void findLicenseDto_ifFound()
    {

    }
    

    @Test
    public void findLicenseDto_ifNotFound()
    {

    }

}