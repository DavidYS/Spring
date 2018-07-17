package com.fortech.repository;


import com.fortech.entity.LicenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<LicenseEntity, Long> {
<<<<<<< HEAD
    LicenseEntity findByGeneratedKey(String generatedKey);

=======
>>>>>>> f1347d801d2fc232dee68f507e2590afcba65d5b
}
