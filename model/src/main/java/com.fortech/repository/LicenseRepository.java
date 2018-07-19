package com.fortech.repository;


import com.fortech.entity.LicenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<LicenseEntity, Long> {
    LicenseEntity findByGeneratedKey(String generatedKey);



}
