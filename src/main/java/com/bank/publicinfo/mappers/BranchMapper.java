package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.entity.Branch;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    static BranchMapper getInstance() {
        return new BranchMapperImpl();
    }

    Branch DTOToBranch(BranchDTO branchDTO);
    BranchDTO BranchToDTO(Branch branch);
    List<BranchDTO> BranchListToDTO(List<Branch> branchList);
}
