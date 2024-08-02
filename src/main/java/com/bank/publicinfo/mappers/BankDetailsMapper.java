package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.entity.BankDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankDetailsMapper {

    static BankDetailsMapper getInstance() {
        return new BankDetailsMapperImpl();
    };

    BankDetails DTOToBankDetails(BankDetailsDTO bankDetailsDTO);
    BankDetailsDTO BankDetailsToDTO(BankDetails bankDetails);
    List<BankDetailsDTO> BankDetailsListToDTO(List<BankDetails> bankDetailsList);
}
