package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.repository.BankDetailsRepository;
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
class BankDetailsServiceImplTest {

    private BankDetails bankDetails;
    @Mock(lenient = true)
    private BankDetailsRepository bankDetailsRepository;
    @InjectMocks
    private BankDetailsServiceImpl bankDetailsServiceImpl;

    @BeforeEach
    void setUp() {
        bankDetails = BankDetails.builder()
                .id(123L)
                .bik(123L)
                .inn(321L)
                .kpp(999L)
                .corAccount(111111)
                .city("Perm")
                .jointStockCompany("Company")
                .name("Bob")
                .build();

        List<BankDetails> testList = new ArrayList<>();
        testList.add(bankDetails);

        doReturn(testList).when(bankDetailsRepository).findBankDetailsByBik(any());
        doReturn(true).when(bankDetailsRepository).existsByBik(any());
    }

    @Test
    void add() {
        bankDetailsServiceImpl.add(bankDetails);
        List<BankDetails> addResult = bankDetailsRepository.findBankDetailsByBik(bankDetails.getBik());
        assertThat(addResult.get(0).getBik()).isEqualTo(bankDetails.getBik());
    }

    @Test
    void delete() {
        bankDetailsServiceImpl.add(bankDetails);
        bankDetailsServiceImpl.delete(bankDetails.getBik());
        doReturn(false).when(bankDetailsRepository).existsByBik(any());
        boolean checkIfExist = bankDetailsServiceImpl.checkIfExistsByBik(bankDetails.getBik());
        assertFalse(checkIfExist);
    }

    @Test
    void getBankDetailsByBik() {
        bankDetailsServiceImpl.add(bankDetails);
        List<BankDetails> addResult = bankDetailsRepository.findBankDetailsByBik(bankDetails.getBik());
        assertThat(addResult.get(0).getBik()).isEqualTo(bankDetails.getBik());
    }

    @Test
    void checkIfExistsByBik() {
        bankDetailsServiceImpl.add(bankDetails);
        boolean result = bankDetailsServiceImpl.checkIfExistsByBik(bankDetails.getBik());
        assertTrue(result);
    }
}