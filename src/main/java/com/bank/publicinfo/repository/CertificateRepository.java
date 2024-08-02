package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для показа сертификата
 */

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findCertificateByBankDetailsId(Long bankDetailsId);
    boolean existsByBankDetails(Long bankDetailsId);
}
