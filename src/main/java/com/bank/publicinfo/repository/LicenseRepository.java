package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для показа лицензии
 */

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
    List<License> findLicenseByBankDetailsId(Long bankDetailsId);
    boolean existsByBankDetails(Long bankDetailsId);
}
