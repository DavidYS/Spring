package com.fortech.repository;


import com.fortech.entity.LicenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<LicenseEntity, Long> {
    LicenseEntity findByGeneratedKey(String generatedKey);

<<<<<<< HEAD
    LicenseEntity findByValidationKey(String validationKey);
=======


>>>>>>> 4fe3c615203f3fa96192c2f3104e97943623cff3
}
