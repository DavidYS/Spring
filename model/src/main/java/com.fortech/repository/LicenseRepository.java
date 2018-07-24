package com.fortech.repository;


import com.fortech.entity.LicenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LicenseRepository extends JpaRepository<LicenseEntity, Long> {
//    Optional<LicenseEntity> findByGeneratedKey(String generatedKey);
//    Optional<LicenseEntity> findByValidationKey(String validationKey);

    LicenseEntity findByGeneratedKey(String generatedKey);
    LicenseEntity findByValidationKey(String validationKey);


}
