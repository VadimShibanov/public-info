package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.repository.BranchRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class BranchServiceImplTest {

    private Branch branch;
    @Mock(lenient = true)
    private BranchRepository branchRepository;
    @InjectMocks
    private BranchServiceImpl branchServiceImpl;

    @BeforeEach
    void setUp() {
        branch = Branch.builder()
                .id(123L)
                .address("address_test")
                .phoneNumber(8999123456L)
                .city("Perm")
                .startOfWork(new Time(1))
                .endOfWork(new Time(1))
                .build();

        List<Branch> testList = new ArrayList<>();
        testList.add(branch);

        doReturn(testList).when(branchRepository).findBranchesByAddress(any());
        doReturn(true).when(branchRepository).existsByAddress(any());
    }

    @Test
    void add() {
        branchServiceImpl.add(branch);
        List<Branch> addResult = branchRepository.findBranchesByAddress(branch.getAddress());
        assertThat(addResult.get(0).getAddress()).isEqualTo(branch.getAddress());
    }

    @Test
    void delete() {
        branchServiceImpl.add(branch);
        branchServiceImpl.delete(branch.getAddress());
        doReturn(false).when(branchRepository).existsByAddress(any());
        boolean checkIfExist = branchServiceImpl.checkIfExistsByAddress(branch.getAddress());
        assertFalse(checkIfExist);
    }

    @Test
    void getBranchesByAddress() {
        branchServiceImpl.add(branch);
        List<Branch> addResult = branchRepository.findBranchesByAddress(branch.getAddress());
        assertThat(addResult.get(0).getAddress()).isEqualTo(branch.getAddress());
    }

    @Test
    void checkIfExistsByAddress() {
        branchServiceImpl.add(branch);
        boolean result = branchServiceImpl.checkIfExistsByAddress(branch.getAddress());
        assertTrue(result);
    }
}