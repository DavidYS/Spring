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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

        LicenseEntity licenseEntity = new LicenseEntity();
        String generatedKey = "sd";
        String validationKey = "dsfg";
        licenseEntity.setGeneratedKey(generatedKey);
        licenseEntity.setValidationKey(validationKey);
        List<LicenseEntity> licenseEntities = Collections.singletonList(licenseEntity);
        when(licenseRepositoryMock.findAll()).thenReturn(licenseEntities);

        List<LicenseDto> result = licenseServiceImpl.readAllLicenseDTO();

        assertEquals(1, result.size());
        LicenseDto resultLicense = result.get(0);
        assertEquals(generatedKey, resultLicense.getGeneratedKey());
        assertEquals(validationKey, resultLicense.getValidationKey());
    }

}