package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.repository.LicenseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class LicenseServiceImplTest {

    private License license;
    @Mock(lenient = true)
    private LicenseRepository licenseRepository;
    @InjectMocks
    private LicenseServiceImpl licenseServiceImpl;

    @BeforeEach
    void setUp() {
        Byte b = 22;
        license = License
                .builder()
                .id(1L)
                .photo(b)
                .bankDetails(new BankDetails())
                .build();

        List<License> testList = new ArrayList<>();
        testList.add(license);

        doReturn(testList).when(licenseRepository).findLicenseByBankDetailsId(any());
        doReturn(true).when(licenseRepository).existsByBankDetails(any());
    }

    @Test
    void add() {
        licenseServiceImpl.add(license);
        List<License> addResult = licenseRepository
                .findLicenseByBankDetailsId(license.getBankDetails().getId());
        assertThat(addResult.get(0).getBankDetails()).isEqualTo(license.getBankDetails());
    }

    @Test
    void delete() {
        licenseServiceImpl.add(license);
        licenseServiceImpl.delete(license.getBankDetails().getId());
        doReturn(false).when(licenseRepository).existsByBankDetails(any());
        boolean checkIfExist = licenseServiceImpl
                .checkIfExistsByBankDetailsId(license.getBankDetails().getId());
        assertFalse(checkIfExist);
    }

    @Test
    void getCertificateByBankDetailsId() {
        licenseServiceImpl.add(license);
        List<License> addResult = licenseRepository
                .findLicenseByBankDetailsId(license.getBankDetails().getId());
        assertThat(addResult.get(0).getBankDetails()).isEqualTo(license.getBankDetails());
    }

    @Test
    void checkIfExistsByBankDetailsId() {
        licenseServiceImpl.add(license);
        boolean result = licenseServiceImpl.checkIfExistsByBankDetailsId(license.getBankDetails().getId());
        assertTrue(result);
    }

}