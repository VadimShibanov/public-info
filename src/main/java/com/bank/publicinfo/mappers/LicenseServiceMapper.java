package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.entity.License;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LicenseServiceMapper {

    static LicenseServiceMapper getInstance() {
        return new LicenseServiceMapperImpl();
    }

    License DTOToLicense(LicenseDTO licenseDTO);
    LicenseDTO LicenseToDTO(License license);
    List<LicenseDTO> LicenseListToDTO(List<License> licenseList);
}
