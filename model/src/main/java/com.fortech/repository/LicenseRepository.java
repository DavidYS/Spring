package com.fortech.repository;

import com.fortech.entity.LicenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<LicenseEntity, Long> {
}
