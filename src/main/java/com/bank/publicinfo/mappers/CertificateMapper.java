package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.entity.Certificate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    static CertificateMapper getInstance() {
        return new CertificateMapperImpl();
    }

    Certificate DTOToCertificate(CertificateDTO certificateDTO);
    CertificateDTO CertificateToDTO(Certificate certificate);
    List<CertificateDTO> CertificateListToDTO(List<Certificate> certificateList);
}
