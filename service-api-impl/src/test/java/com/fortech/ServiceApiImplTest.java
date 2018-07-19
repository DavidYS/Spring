package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.GeneratedKey;
import com.fortech.entity.LicenseEntity;
import com.fortech.entity.ValidationKey;
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
import static org.mockito.Mockito.*;


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

    /*@Test
    public void unitTest() {
        LicenseDto licenseDto = new LicenseDto();
        LicenseDto licenseDto2 = new LicenseDto();

        EasyMock.expect(personRepository.returnPerson(1L)).andReturn(person2); //expect a fetch, return a "fetched" person;
        personRepository.delete(person2);
        EasyMock.expectLastCall(); //expect a delete for person2 we plan to delete
        replayAll();

        personService.deleteFromPerson(person);

        verifyAll(); //make sure everything was called
    }*/

    @Test
    public void deleteOneLicense_testOk(){

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
        List<LicenseDto> licenseDtoList = new ArrayList<>();

        when(licenseRepositoryMock.findAll()).thenReturn(licenseEntities);

        //licenseRepositoryMock.delete(licenseEntity2);
        String response = licenseServiceImpl.deleteLicenseDTO(generatedKey2);

        assertEquals("Licența a fost ștearsă.",response);
        verify(licenseRepositoryMock, times(1)).delete(licenseEntity2);
    }

    /*@Test
    public void deleteOneLicense_ifFound(){

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
        List<LicenseDto> licenseDtoList = new ArrayList<>();

        when(licenseRepositoryMock.findAll()).thenReturn(licenseEntities);


        List<LicenseDto> result = licenseServiceImpl.readAllLicenseDTO();
        String response = licenseServiceImpl.deleteLicenseDTO(generatedKey2);


        //when(licenseServiceImpl.deleteLicenseDTO(generatedKey2)).thenReturn(licenseDtoList);
        when(licenseServiceImpl.readAllLicenseDTO()).thenReturn(licenseDtoList);
        System.out.println(licenseDtoList.size());
        licenseRepositoryMock.delete(licenseEntity2);

        //when(licenseRepositoryMock.delete(licenseEntity2)).thenReturn();
        when(licenseRepositoryMock.findAll()).thenReturn(licenseEntities);
        System.out.println(licenseEntities.size());
        licenseRepositoryMock.delete(licenseEntity2);

        System.out.println(licenseRepositoryMock.findAll().size() + "PPPP");
        List<LicenseDto> result2 = licenseServiceImpl.readAllLicenseDTO();
        //List<LicenseEntity> ttt = licenseRepositoryMock.findAll();
        //System.out.println(ttt.size());
        System.out.println(response);

        assertEquals(2, result.size());
        assertEquals(1, result2.size());

        LicenseDto resultLicense1 = result.get(0);
        LicenseDto resultLicense2 = result.get(1);

        assertEquals(generatedKey1, resultLicense1.getGeneratedKey());
        assertEquals(validationKey1, resultLicense1.getValidationKey());

        assertEquals(generatedKey2, resultLicense2.getGeneratedKey());
        assertEquals(validationKey2, resultLicense2.getValidationKey());

        //Test
        *//*String json1 =
                "{"+"\""+"hostname"+"\""+":"+"\""+"ws-bh-internship"+"\""+","+
                        "\""+"ipAddress"+"\""+":"+"\""+"192.168.216.152"+"\""+","+
                        "\""+"ipMac"+"\""+":"+"\""+"18-03-73-DD-20-2A"+"\""+","+
                        "\""+"timestamp"+"\""+":"+"\""+"1531919716062"+"\""+"}";

        LicenseDto result = licenseServiceImpl.generare(json1);
        licenseServiceImpl.saveLicense(result);
        System.out.println(result.getGeneratedKey());

        GeneratedKey generatedKey = new GeneratedKey();
        generatedKey.generateFromString(json1);
        ValidationKey validationKey = new ValidationKey();
        validationKey.generate(generatedKey);

        assertEquals(generatedKey.toString(),result.getGeneratedKey().toString());
        assertEquals(validationKey.toString(),result.getValidationKey().toString());
        System.out.println(validationKey);

        List<LicenseDto> licenseDtoList = licenseServiceImpl.readAllLicenseDTO();
        System.out.println(licenseDtoList.size());*//*
    }*/

}