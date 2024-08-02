package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий информации о банке
 */

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {

    List<BankDetails> findBankDetailsByBik(Long bik);
    boolean existsByBik(Long bik);
}
