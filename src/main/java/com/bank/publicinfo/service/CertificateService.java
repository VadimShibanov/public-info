package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Certificate;

import java.util.List;

public interface CertificateService {
    void add(Certificate certificate);

    void delete(Long bankDetailsId);

    List<Certificate> getCertificateByBankDetailsId(Long bankDetailsId);

    boolean checkIfExistsByBankDetailsId(Long bankDetailsId);
}
