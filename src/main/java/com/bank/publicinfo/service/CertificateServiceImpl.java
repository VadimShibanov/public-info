package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.repository.CertificateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService{

    private final CertificateRepository certificateRepository;

    /**
     * Метод добавляет сертификат
     */
    @Override
    public void add(Certificate certificate) {
        certificateRepository.save(certificate);
    }

    /**
     * Метод удаляет сертификат по bankDetailsId
     */
    @Override
    public void delete(Long bankDetailsId) {
        List<Certificate> certificateList = certificateRepository.findCertificateByBankDetailsId(bankDetailsId);

        for (Certificate acc: certificateList) {
            certificateRepository.delete(acc);
        }
    }

    /**
     * Метод возвращает сертификат по данным банка
     */
    @Override
    public List<Certificate> getCertificateByBankDetailsId(Long bankDetailsId) {
        return certificateRepository.findCertificateByBankDetailsId(bankDetailsId);
    }

    /**
     * Метод проверяет существование сертификата по bankDetailsId
     */
    @Override
    public  boolean checkIfExistsByBankDetailsId(Long bankDetailsId) {
        return certificateRepository.existsByBankDetails(bankDetailsId);
    }
}
