package com.bank.publicinfo.repository;

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
class BranchRepositoryTest {

    @Autowired
    private BranchRepository branchRepository;
    Branch branch;

    @BeforeAll
    void setUp() {

        branch = Branch.builder()
                .address("test_address")
                .phoneNumber(1234556L)
                .city("Perm")
                .startOfWork(new Time(1))
                .endOfWork(new Time(1))
                .build();
        branchRepository.save(branch);
    }

    @Test
    void findBranchesByAddress() {

        assertThat(branch.getId()).isGreaterThan(0);

        List<Branch> resultList = branchRepository.findBranchesByAddress(branch.getAddress());

        assertThat(resultList.get(0).getAddress()).isEqualTo(branch.getAddress());
    }

    @Test
    void existsByAddress() {

        boolean checkResult = branchRepository.existsByAddress(branch.getAddress());

        assertTrue(checkResult);

        List<Branch> resultList = branchRepository.findBranchesByAddress(branch.getAddress());

        assertThat(resultList.get(0).getAddress()).isEqualTo(branch.getAddress());
    }

    @AfterAll
    void tearDown() {
        branchRepository.delete(branch);
    }
}