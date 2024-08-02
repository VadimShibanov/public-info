package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.entity.Branch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BranchMapperTest {

    private BranchMapper branchMapper = BranchMapper.getInstance();

    @Test
    void getInstance() {

        BranchMapper actualResult = BranchMapper.getInstance();
        BranchMapper expectedResult = new BranchMapperImpl();

        assertThat(actualResult.getClass()).isEqualTo(expectedResult.getClass());
    }

    @Test
    void DTOToBranch() {

        BranchDTO branchDTO = BranchDTO.builder().build();
        Branch actualResult = branchMapper.DTOToBranch(branchDTO);
        Branch expectedResult = Branch.builder().build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void branchToDTO() {

        Branch branch = Branch.builder().build();
        BranchDTO actualResult = branchMapper.BranchToDTO(branch);
        BranchDTO expectedResult = BranchDTO.builder().build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void branchListToDTO() {

        List<Branch> testList = new ArrayList<>();
        testList.add(Branch.builder().build());

        List<BranchDTO> actualResult = branchMapper.BranchListToDTO(testList);

        List<BranchDTO> testDTOList = new ArrayList<>();
        testDTOList.add(BranchDTO.builder().build());

        List<BranchDTO> expectedResult = testDTOList;

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}