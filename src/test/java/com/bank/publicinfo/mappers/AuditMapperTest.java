package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.AuditDTO;
import com.bank.publicinfo.entity.Audit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AuditMapperTest {

    private AuditMapper auditMapper = AuditMapper.getInstance();

    @Test
    void getInstance() {

        AuditMapper actualResult = AuditMapper.getInstance();
        AuditMapper expectedResult = new AuditMapperImpl();

        assertThat(actualResult.getClass()).isEqualTo(expectedResult.getClass());
    }

    @Test
    void DTOToAudit() {

        AuditDTO auditDTO = AuditDTO.builder().build();
        Audit actualResult = auditMapper.DTOToAudit(auditDTO);
        Audit expectedResult = Audit.builder().build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void auditToDTO() {

        Audit audit = Audit.builder().build();
        AuditDTO actualResult = auditMapper.AuditToDTO(audit);
        AuditDTO expectedResult = AuditDTO.builder().build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void auditListToDTO() {

        List<Audit> testList = new ArrayList<>();
        testList.add(Audit.builder().build());

        List<AuditDTO> actualResult = auditMapper.AuditListToDTO(testList);

        List<AuditDTO> testDTOList = new ArrayList<>();
        testDTOList.add(AuditDTO.builder().build());

        List<AuditDTO> expectedResult = testDTOList;

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}