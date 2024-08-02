package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.entity.Atm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AtmMapperTest {

    private AtmMapper atmMapper = AtmMapper.getInstance();

    @Test
    void getInstance() {

        AtmMapper actualResult = AtmMapper.getInstance();
        AtmMapper expectedResult = new AtmMapperImpl();

        assertThat(actualResult.getClass()).isEqualTo(expectedResult.getClass());
    }

    @Test
    void DTOToAtm() {

        AtmDTO atmDTO = AtmDTO.builder().build();
        Atm actualResult = atmMapper.DTOToAtm(atmDTO);
        Atm expectedResult = Atm.builder().build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void atmToDTO() {

        Atm atm = Atm.builder().build();
        AtmDTO actualResult = atmMapper.AtmToDTO(atm);
        AtmDTO expectedResult = AtmDTO.builder().build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void atmListToDTO() {

        List<Atm> testList = new ArrayList<>();
        testList.add(Atm.builder().build());

        List<AtmDTO> actualResult = atmMapper.AtmListToDTO(testList);

        List<AtmDTO> testDTOList = new ArrayList<>();
        testDTOList.add(AtmDTO.builder().build());

        List<AtmDTO> expectedResult = testDTOList;

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}