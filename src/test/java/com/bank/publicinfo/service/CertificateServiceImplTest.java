package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.repository.CertificateRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CertificateServiceImplTest {

    private Certificate certificate;
    @Mock(lenient = true)
    private CertificateRepository certificateRepository;
    @InjectMocks
    private CertificateServiceImpl certificateServiceImpl;

    @BeforeEach
    void setUp() {
        Byte b = 1;
        certificate = Certificate
                .builder()
                .id(1L)
                .photo(b)
                .bankDetails(new BankDetails())
                .build();

        List<Certificate> testList = new ArrayList<>();
        testList.add(certificate);

        doReturn(testList).when(certificateRepository).findCertificateByBankDetailsId(any());
        doReturn(true).when(certificateRepository).existsByBankDetails(any());
    }

    @Test
    void add() {
        certificateServiceImpl.add(certificate);
        List<Certificate> addResult = certificateRepository
                .findCertificateByBankDetailsId(certificate.getBankDetails().getId());
        assertThat(addResult.get(0).getBankDetails()).isEqualTo(certificate.getBankDetails());
    }

    @Test
    void delete() {
        certificateServiceImpl.add(certificate);
        certificateServiceImpl.delete(certificate.getBankDetails().getId());
        doReturn(false).when(certificateRepository).existsByBankDetails(any());
        boolean checkIfExist = certificateServiceImpl
                .checkIfExistsByBankDetailsId(certificate.getBankDetails().getId());
        assertFalse(checkIfExist);
    }

    @Test
    void getCertificateByBankDetailsId() {
        certificateServiceImpl.add(certificate);
        List<Certificate> addResult = certificateRepository
                .findCertificateByBankDetailsId(certificate.getBankDetails().getId());
        assertThat(addResult.get(0).getBankDetails()).isEqualTo(certificate.getBankDetails());
    }

    @Test
    void checkIfExistsByBankDetailsId() {
        certificateServiceImpl.add(certificate);
        boolean result = certificateServiceImpl.checkIfExistsByBankDetailsId(certificate.getBankDetails().getId());
        assertTrue(result);
    }
}