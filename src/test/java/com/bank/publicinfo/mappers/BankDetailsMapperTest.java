package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.entity.BankDetails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BankDetailsMapperTest {

    private BankDetailsMapper bankDetailsMapper = BankDetailsMapper.getInstance();

    @Test
    void getInstance() {

        BankDetailsMapper actualResult = BankDetailsMapper.getInstance();
        BankDetailsMapper expectedResult = new BankDetailsMapperImpl();

        assertThat(actualResult.getClass()).isEqualTo(expectedResult.getClass());
    }

    @Test
    void DTOToBankDetails() {

        BankDetailsDTO bankDetailsDTO = BankDetailsDTO.builder().build();
        BankDetails actualResult = bankDetailsMapper.DTOToBankDetails(bankDetailsDTO);
        BankDetails expectedResult = BankDetails.builder().build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void bankDetailsToDTO() {

        BankDetails bankDetails = BankDetails.builder().build();
        BankDetailsDTO actualResult = bankDetailsMapper.BankDetailsToDTO(bankDetails);
        BankDetailsDTO expectedResult = BankDetailsDTO.builder().build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void bankDetailsListToDTO() {

        List<BankDetails> testList = new ArrayList<>();
        testList.add(BankDetails.builder().build());

        List<BankDetailsDTO> actualResult = bankDetailsMapper.BankDetailsListToDTO(testList);

        List<BankDetailsDTO> testDTOList = new ArrayList<>();
        testDTOList.add(BankDetailsDTO.builder().build());

        List<BankDetailsDTO> expectedResult = testDTOList;

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}