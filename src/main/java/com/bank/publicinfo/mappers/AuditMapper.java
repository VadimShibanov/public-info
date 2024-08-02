package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.AuditDTO;
import com.bank.publicinfo.entity.Audit;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditMapper {

    static AuditMapper getInstance() {
        return new AuditMapperImpl();
    }
    Audit DTOToAudit(AuditDTO auditDTO);
    AuditDTO AuditToDTO(Audit audit);
    List<AuditDTO> AuditListToDTO(List<Audit> auditList);
}
