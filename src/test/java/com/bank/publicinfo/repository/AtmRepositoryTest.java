package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Branch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AtmRepositoryTest {

    @Autowired
    private AtmRepository atmRepository;
    Atm atm;

    @BeforeAll
    void setUp() {

        atm = Atm.builder()
                .address("test_address")
                .startOfWork(new Time(1))
                .endOfWork(new Time(1))
                .allHours(false)
                .build();
        atmRepository.save(atm);
    }

    @Test
    void findAtmByAddress() {

        assertThat(atm.getId()).isGreaterThan(0);

        List<Atm> resultList = atmRepository.findAtmByAddress(atm.getAddress());

        assertThat(resultList.get(0).getAddress()).isEqualTo(atm.getAddress());
    }

    @Test
    void existsByAddress() {

        boolean checkResult = atmRepository.existsByAddress(atm.getAddress());

        assertTrue(checkResult);

        List<Atm> resultList = atmRepository.findAtmByAddress(atm.getAddress());

        assertThat(resultList.get(0).getAddress()).isEqualTo(atm.getAddress());
    }

    @AfterAll
    void tearDown() {
        atmRepository.delete(atm);
    }
}