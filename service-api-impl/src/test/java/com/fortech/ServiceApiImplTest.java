package com.fortech;

import com.fortech.dto.LicenseDto;
import com.fortech.entity.GeneratedKey;
import com.fortech.entity.License1;
import com.fortech.repository.LicenseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static sun.misc.ClassFileTransformer.add;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ServiceApiImplTest {

    @Mock
    private LicenseRepository licenseRepository;

    @InjectMocks
    private LicenseServiceImpl licenseServiceImpl;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddLicense1(){

        GeneratedKey aMockContact = new GeneratedKey();
        aMockContact.setHostName("assdgs");

        when(LicenseRepository.save(any(GeneratedKey.class))).thenReturn(aMockContact);

        GeneratedKey newLicense = licenseServiceImpl.add(null);

        assertEquals("assdgs", newLicense.getHostName());
    }
}
