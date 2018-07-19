package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.LicenseEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LicenseControllerTest {
    @InjectMocks
    private LicenseControllerImpl licenseControllerImpl;

    @Mock
    private LicenseService licenseServiceMock;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void readAllLicenses_shouldReturnAList(){

        LicenseEntity licenseEntity = new LicenseEntity();
        String generatedKey = "sd";
        String validationKey = "dsfg";
        licenseEntity.setGeneratedKey(generatedKey);
        licenseEntity.setValidationKey(validationKey);
        List<LicenseDto> licenseEntities = Collections.singletonList(licenseEntity.toDto());
        when(licenseServiceMock.readAllLicenseDTO()).thenReturn(licenseEntities);

        List<LicenseDto> result = licenseServiceMock.readAllLicenseDTO();

        assertEquals(1, result.size());
        LicenseDto resultLicense = result.get(0);
        assertEquals(generatedKey, resultLicense.getGeneratedKey());
        assertEquals(validationKey, resultLicense.getValidationKey());
    }

    @Test
    public void deleteByGeneratedKey_ShouldReturnAConfirmationString(){
        LicenseEntity licenseEntity = new LicenseEntity();
        String generatedKey = "sd";
        String validationKey = "dsfg";
        licenseEntity.setGeneratedKey(generatedKey);
        licenseEntity.setValidationKey(validationKey);
        List<LicenseDto> licenseEntities = Collections.singletonList(licenseEntity.toDto());
        when(licenseServiceMock.deleteLicenseDTO(generatedKey)).thenReturn("Licența a fost ștearsă.");

        String result = licenseServiceMock.deleteLicenseDTO(generatedKey);

        assertEquals("Licența a fost ștearsă.", result);
    }

    @Test
    public void readOne_ShouldReturnALicenseDto(){
        LicenseEntity licenseEntity = new LicenseEntity();
        String generatedKey = "sd";
        String validationKey = "dsfg";
        licenseEntity.setGeneratedKey(generatedKey);
        licenseEntity.setValidationKey(validationKey);
        when(licenseServiceMock.findLicenseDto(generatedKey)).thenReturn(licenseEntity.toDto());

       LicenseDto result = licenseServiceMock.findLicenseDto(generatedKey);

        assertEquals(generatedKey, result.getGeneratedKey());
        assertEquals(validationKey, result.getValidationKey());

    }

    @Test
    public void generateLicense_ShouldReturnAnEncryptedString(){
        LicenseDto licenseDto = new LicenseDto();
        licenseDto = licenseServiceMock.generare("sd");
        when(licenseServiceMock.generare("sd")).thenReturn(licenseDto);

        LicenseDto result = licenseServiceMock.findLicenseDto("sd");

        assertEquals(licenseDto, result);
    }
}
