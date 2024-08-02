package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.repository.AtmRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AtmServiceImplTest {

    private Atm atm;
    @Mock(lenient = true)
    private AtmRepository atmRepository;
    @InjectMocks
    private AtmServiceImpl atmServiceImpl;

    @BeforeEach
    void setUp() {
        atm = Atm.builder()
                .id(123L)
                .address("address_test")
                .startOfWork(new Time(1))
                .endOfWork(new Time(1))
                .allHours(true)
                .branch(new Branch())
                .build();

        List<Atm> testList = new ArrayList<>();
        testList.add(atm);

        doReturn(testList).when(atmRepository).findAtmByAddress(any());
        doReturn(true).when(atmRepository).existsByAddress(any());
    }

    @Test
    void add() {
        atmServiceImpl.add(atm);
        List<Atm> addResult = atmRepository.findAtmByAddress(atm.getAddress());
        assertThat(addResult.get(0).getAddress()).isEqualTo(atm.getAddress());
    }

    @Test
    void delete() {
        atmServiceImpl.add(atm);
        atmServiceImpl.delete(atm.getAddress());
        doReturn(false).when(atmRepository).existsByAddress(any());
        boolean checkIfExist = atmServiceImpl.checkIfExistsByAddress(atm.getAddress());
        assertFalse(checkIfExist);
    }

    @Test
    void getAtmByAddress() {
        atmServiceImpl.add(atm);
        List<Atm> addResult = atmRepository.findAtmByAddress(atm.getAddress());
        assertThat(addResult.get(0).getAddress()).isEqualTo(atm.getAddress());
    }

    @Test
    void checkIfExistsByAddress() {
        atmServiceImpl.add(atm);
        boolean result = atmServiceImpl.checkIfExistsByAddress(atm.getAddress());
        assertTrue(result);
    }
}