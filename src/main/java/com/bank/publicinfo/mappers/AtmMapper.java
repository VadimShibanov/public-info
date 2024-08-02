package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.entity.Atm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AtmMapper {

    static AtmMapper getInstance() {
        return new AtmMapperImpl();
    }

    Atm DTOToAtm(AtmDTO atmDTO);
    AtmDTO AtmToDTO(Atm atm);
    List<AtmDTO> AtmListToDTO(List<Atm> atmList);

}
