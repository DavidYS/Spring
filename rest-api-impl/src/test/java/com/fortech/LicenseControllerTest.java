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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LicenseControllerTest {
    @InjectMocks
    private LicenseControllerImpl licenseControllerImpl;

    @Mock
    private LicenseService licenseServiceMock;

    private String generatedKey = "CGVNRQ8bChAGKxVADkdDDU0RXlQNHV9EDRwKGw0WGwYdRwF5RUNQDARsDwELSBwVD0NPV0MUUUZeX1t" +
            "LQV5bSxxGV0Feb1QNSQwJYA4FD0NPV0MVTlhcRFRWXispSB9DSFEzR1gnS0VbWQYLSAobFB9dQVJPS1JQQF1eUB1AVltKXUAPYRg";
    private String validationKey = "CE0FCl4HKwIfAFYXSRIKAA0OABABARdfDRsHABNHX00EFWwXAREXFgcPUUdIFF1IHE9XW0AcVUZeXF" +
            "FHX00EFWASBkFIR0UVRlVKAFhVAD0rWEAdTlouS09HBwYAAF4HBA4CR04PWlBKH1xTHUpcTUoVV0pDSxAREh0ZOkkSEQZQX1YfWE" +
            "hJGkJUHUhXV14PBQEBABANLAsMEUhRX0FAVlkdXEhLHV5fD1VNFh5EBgYbS1lHGgEZAF8dFgsbFVZQ";
    private String testKey = "44";

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void readAllLicenses_shouldReturnAList(){

        LicenseEntity licenseEntity = new LicenseEntity();
        licenseEntity.setGeneratedKey(generatedKey);
        licenseEntity.setValidationKey(validationKey);
        List<LicenseDto> licenseEntities = Collections.singletonList(licenseEntity.toDto());
        when(licenseServiceMock.readAllLicenseDTO()).thenReturn(licenseEntities);

        List<LicenseDto> result = licenseControllerImpl.readAllLicenses();

        assertEquals(1, result.size());
        LicenseDto resultLicense = result.get(0);
        assertEquals(generatedKey, resultLicense.getGeneratedKey());
        assertEquals(validationKey, resultLicense.getValidationKey());
    }

    @Test
    public void deleteByGeneratedKey_ShouldReturnAConfirmationString(){
        LicenseEntity licenseEntity = new LicenseEntity();
        licenseEntity.setGeneratedKey(generatedKey);
        licenseEntity.setValidationKey(validationKey);

        List<LicenseDto> licenseEntities = Collections.singletonList(licenseEntity.toDto());
        licenseServiceMock.deleteLicenseDTO(generatedKey);

        ResponseEntity result = licenseControllerImpl.deleteByGeneratedKey(generatedKey);
        assertTrue(result.getStatusCode() != HttpStatus.NOT_FOUND);
    }

    @Test
    public void deleteByGeneratedKey_ShouldReturnANotFoundStatus(){
        LicenseEntity licenseEntity = new LicenseEntity();
        licenseEntity.setGeneratedKey(generatedKey);
        licenseEntity.setValidationKey(validationKey);

        List<LicenseDto> licenseEntities = Collections.singletonList(licenseEntity.toDto());
        licenseServiceMock.deleteLicenseDTO(testKey);

        ResponseEntity result = licenseControllerImpl.deleteByGeneratedKey(testKey);
        assertTrue(result.getStatusCode() == HttpStatus.NOT_FOUND);
    }



    @Test
    public void readOne_ShouldReturnALicenseDto(){
        LicenseEntity licenseEntity = new LicenseEntity();
        licenseEntity.setGeneratedKey(generatedKey);
        licenseEntity.setValidationKey(validationKey);

        licenseServiceMock.findLicenseDto(generatedKey);

        ResponseEntity<String> result = licenseControllerImpl.readOne(generatedKey);
        assertTrue(result.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void readOne_ShouldReturnANotFoundStatus(){
        LicenseEntity licenseEntity = new LicenseEntity();
        licenseEntity.setGeneratedKey(generatedKey);
        licenseEntity.setValidationKey(validationKey);

        licenseServiceMock.findLicenseDto(testKey);

        ResponseEntity<String> result = licenseControllerImpl.readOne(testKey);
        assertTrue(result.getStatusCode() != HttpStatus.OK);
    }

    @Test
    public void generateLicense_ShouldReturnAnEncryptedString(){
        LicenseDto licenseDto = new LicenseDto();
        licenseDto = licenseServiceMock.generare(generatedKey);

        when(licenseServiceMock.generare(generatedKey)).thenReturn(licenseDto);

        ResponseEntity<LicenseDto> result = licenseControllerImpl.readOne(generatedKey);
        assertTrue(result.getStatusCode() == HttpStatus.OK);
    }


    @Test
    public void generateLicense_ShouldReturnABadRequestResponse(){
        LicenseDto licenseDto = new LicenseDto();
        licenseDto = licenseServiceMock.generare(testKey);

        when(licenseServiceMock.generare(testKey)).thenReturn(licenseDto);

        ResponseEntity<LicenseDto> result = licenseControllerImpl.readOne(testKey);
        assertTrue(result.getStatusCode() == HttpStatus.NOT_FOUND);
    }


}