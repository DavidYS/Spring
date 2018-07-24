package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.keys.GeneratedKey;
import com.fortech.entity.LicenseEntity;
import com.fortech.keys.ValidationKey;
import com.fortech.repository.LicenseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.Silent.class)
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

        List<LicenseEntity> licenseEntities = Arrays.asList(licenseEntity1, licenseEntity2);
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
    public void findLicenseDto_ifFound() {
        LicenseEntity licenseEntity = new LicenseEntity();
        String generatedKey = "abc";
        String validationKey = "def";
        licenseEntity.setGeneratedKey(generatedKey);
        licenseEntity.setValidationKey(validationKey);

        when(licenseRepositoryMock.findByGeneratedKey(generatedKey)).thenReturn(licenseEntity);

        ResponseEntity result = licenseServiceImpl.findLicenseDto(generatedKey);
        assertSame(result.getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void findLicenseDto_ifNotFound() {
        LicenseEntity licenseEntity = new LicenseEntity();
        String generatedKey = "abc";
        String validationKey = "def";
        licenseEntity.setGeneratedKey(generatedKey);
        licenseEntity.setValidationKey(validationKey);

        when(licenseRepositoryMock.findByGeneratedKey(generatedKey + "12515")).thenReturn(licenseEntity);

        ResponseEntity result = licenseServiceImpl.findLicenseDto(generatedKey);
        assertSame(result.getStatusCode(), HttpStatus.NOT_FOUND);
    }


    @Test
    public void generare_shouldReturnALicenseDTO() {
        String json1 =
                "{" + "\"" + "hostname" + "\"" + ":" + "\"" + "ws-bh-internship" + "\"" + "," +
                        "\"" + "ipAddress" + "\"" + ":" + "\"" + "192.168.216.152" + "\"" + "," +
                        "\"" + "ipMac" + "\"" + ":" + "\"" + "18-03-73-DD-20-2A" + "\"" + "," +
                        "\"" + "timestamp" + "\"" + ":" + "\"" + "1531919716062" + "\"" + "}";

        LicenseDto result = licenseServiceImpl.generare(json1);

        GeneratedKey generatedKey = new GeneratedKey();
        generatedKey.generateFromString(json1);
        ValidationKey validationKey = new ValidationKey();
        validationKey.generate(generatedKey);

        assertEquals(generatedKey.toString(), result.getGeneratedKey().toString());
        assertEquals(validationKey.toString(), result.getValidationKey().toString());
    }


    @Test
    public void generare_shouldReturnAnEmptyLicenseDTO() {
        String json1 =
                "hostname" + "\"" + ":" + "\"" + "ws-bh-internship" + "\"" + "," +
                        "\"" + "ipAddress" + "\"" + ":" + "\"" + "192.168.216.152" + "\"" + "," +
                        "\"" + "ipMac" + "\"" + ":" + "\"" + "18-03-73-DD-20-2A" + "\"" + "," +
                        "\"" + "timestamp" + "\"" + ":" + "\"" + "1531919716062" + "\"" + "}";

        LicenseDto result = licenseServiceImpl.generare(json1);

        assertEquals("{}", result.getGeneratedKey());
        assertEquals("{}", result.getValidationKey());
    }

    @Test
    public void saveLicense_shouldVerifyIfSaveLicenseMethodIsCalled() {

        LicenseDto licenseDto = new LicenseDto();
        String generatedKeyTest = "generatedKeyTest";
        licenseDto.setGeneratedKey(generatedKeyTest);
        String validationKeyDto = "validationKeyDto";
        licenseDto.setValidationKey(validationKeyDto);

        licenseServiceImpl.saveLicense(licenseDto);

        Mockito.verify(licenseRepositoryMock,
                times(1)).save(argThat(license -> license.getValidationKey().equals
                (validationKeyDto) && license.getGeneratedKey().equals(generatedKeyTest)));
    }

    @Test

    public void deleteLicenseDTO_shouldVerifyIfDeleteMethodIsCalled() {
        LicenseDto licenseEntity = new LicenseDto();
        String generatedKeyTest = "generatedKeyTest";
        String validationKeyDto = "validationKeyDto";
        licenseEntity.setGeneratedKey(generatedKeyTest);
        licenseEntity.setValidationKey(validationKeyDto);

        licenseRepositoryMock.findByGeneratedKey("generatedKeyTest");

        licenseServiceImpl.deleteLicenseDTO(generatedKeyTest);

        verify(licenseRepositoryMock).delete(licenseRepositoryMock.findByGeneratedKey(generatedKeyTest));
    }

    @Test
    public void deleteOneLicense_testOk() {

        LicenseEntity deleteOneLicenseEntityTest = new LicenseEntity();
        String generatedKeyTest = "generatedKeyTest";
        String validationKeyDto = "validationKeyDto";
        deleteOneLicenseEntityTest.setGeneratedKey(generatedKeyTest);
        deleteOneLicenseEntityTest.setValidationKey(validationKeyDto);

        LicenseEntity deleteOneLicenseDtoTest = new LicenseEntity();
        String generatedKeyExample = "generatedKeyExample";
        String validationKeyExample = "validationKeyExample";
        deleteOneLicenseDtoTest.setGeneratedKey(generatedKeyExample);
        deleteOneLicenseDtoTest.setValidationKey(validationKeyExample);

        List<LicenseEntity> licenseEntities = Arrays.asList(deleteOneLicenseEntityTest, deleteOneLicenseDtoTest);

        when(licenseRepositoryMock.findAll()).thenReturn(licenseEntities);

        licenseServiceImpl.deleteLicenseDTO(generatedKeyExample);
        verify(licenseRepositoryMock, times(1)).delete(deleteOneLicenseDtoTest);
    }
}