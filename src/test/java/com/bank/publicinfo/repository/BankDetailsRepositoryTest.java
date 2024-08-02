package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.BankDetails;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BankDetailsRepositoryTest {

    @Autowired
    private BankDetailsRepository bankDetailsRepository;
    BankDetails bankDetails;

    @BeforeAll
    void setUp() {

        bankDetails = BankDetails.builder()
                .bik(123L)
                .inn(234L)
                .kpp(333L)
                .corAccount(999)
                .city("Perm")
                .jointStockCompany("test_company")
                .name("test_name")
                .build();
        bankDetailsRepository.save(bankDetails);
    }

    @Test
    void findBankDetailsByBik() {

        assertThat(bankDetails.getId()).isGreaterThan(0);

        List<BankDetails> resultList = bankDetailsRepository.findBankDetailsByBik(bankDetails.getBik());

        assertThat(resultList.get(0).getBik()).isEqualTo(bankDetails.getBik());
    }

    @Test
    void existsByBik() {

        boolean checkResult = bankDetailsRepository.existsByBik(bankDetails.getBik());

        assertTrue(checkResult);

        List<BankDetails> resultList = bankDetailsRepository.findBankDetailsByBik(bankDetails.getBik());

        assertThat(resultList.get(0).getBik()).isEqualTo(bankDetails.getBik());
    }

    @AfterAll
    void tearDown() {
        bankDetailsRepository.delete(bankDetails);
    }
}