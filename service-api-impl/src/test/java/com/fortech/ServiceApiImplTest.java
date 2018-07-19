package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.GeneratedKey;
import com.fortech.entity.LicenseEntity;
import com.fortech.entity.ValidationKey;
import com.fortech.repository.LicenseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


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
        LicenseEntity licenseEntity = new LicenseEntity();
        String generatedKey = "abc";
        String validationKey = "def";
        licenseEntity.setGeneratedKey(generatedKey);
        licenseEntity.setValidationKey(validationKey);

        when(licenseRepositoryMock.findByGeneratedKey(generatedKey)).thenReturn(licenseEntity);



        LicenseEntity result = licenseRepositoryMock.findByGeneratedKey(generatedKey);

        assertEquals(generatedKey, result.getGeneratedKey());
        assertEquals(validationKey, result.getValidationKey());

    }
    

    @Test
    public void findLicenseDto_ifNotFound()
    {
        LicenseEntity licenseEntity = new LicenseEntity();
        String generatedKey = "abc";
        String validationKey = "def";
        licenseEntity.setGeneratedKey(generatedKey);
        licenseEntity.setValidationKey(validationKey);

        when(licenseRepositoryMock.findByGeneratedKey(generatedKey+"12515")).thenReturn(licenseEntity);


        LicenseEntity result = licenseRepositoryMock.findByGeneratedKey(generatedKey);

        assertEquals(null, result);
    }


    @Test
    public void generare_shouldReturnALicenseDTO(){
        String json1 =
                "{"+"\""+"hostname"+"\""+":"+"\""+"ws-bh-internship"+"\""+","+
                "\""+"ipAddress"+"\""+":"+"\""+"192.168.216.152"+"\""+","+
                "\""+"ipMac"+"\""+":"+"\""+"18-03-73-DD-20-2A"+"\""+","+
                "\""+"timestamp"+"\""+":"+"\""+"1531919716062"+"\""+"}";

        LicenseDto result = licenseServiceImpl.generare(json1);

        GeneratedKey generatedKey = new GeneratedKey();
        generatedKey.generateFromString(json1);
        ValidationKey validationKey = new ValidationKey();
        validationKey.generate(generatedKey);

        assertEquals(generatedKey.toString(),result.getGeneratedKey().toString());
        assertEquals(validationKey.toString(),result.getValidationKey().toString());
    }


    @Test
    public void generare_shouldReturnAnEmptyLicenseDTO(){
        String json1 =
                "hostname"+"\""+":"+"\""+"ws-bh-internship"+"\""+","+
                        "\""+"ipAddress"+"\""+":"+"\""+"192.168.216.152"+"\""+","+
                        "\""+"ipMac"+"\""+":"+"\""+"18-03-73-DD-20-2A"+"\""+","+
                        "\""+"timestamp"+"\""+":"+"\""+"1531919716062"+"\""+"}";

        LicenseDto result = licenseServiceImpl.generare(json1);

        assertEquals("{}",result.getGeneratedKey());
        assertEquals("{}",result.getValidationKey());
    }

    @Test
    public void saveLicense_shouldVerifyIfSaveLicenseMethodIsCalled() {

        LicenseDto licenseDto=new LicenseDto();
        String generatedKeyTest = "generatedKeyTest";
        licenseDto.setGeneratedKey(generatedKeyTest);
        String validationKeyDto = "validationKeyDto";
        licenseDto.setValidationKey(validationKeyDto);

        licenseServiceImpl.saveLicense(licenseDto);

        Mockito.verify(licenseRepositoryMock, timeout(1)).save(argThat(license -> license.getValidationKey().equals(validationKeyDto) && license.getGeneratedKey().equals(generatedKeyTest)));
    }


    @Test
    public void deleteLicenseDTO_ShouldReturnAResponse(){
        LicenseDto licenseEntity = new LicenseDto();
        String generatedKey = "abc";
        String validationKey = "def";
        licenseEntity.setGeneratedKey(generatedKey);
        licenseEntity.setValidationKey(validationKey);

        licenseServiceImpl.deleteLicenseDTO(generatedKey);

        Mockito.verify(licenseRepositoryMock, times(1)).delete(licenseEntity.getValidationKey() , licenseEntity.getGeneratedKey()zz);

    }
}