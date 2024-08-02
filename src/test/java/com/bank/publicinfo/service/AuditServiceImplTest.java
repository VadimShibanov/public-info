package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.repository.AuditRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {

    private Audit audit;
    @Mock(lenient = true)
    private AuditRepository auditRepository;
    @InjectMocks
    private AuditServiceImpl auditServiceImpl;

    @BeforeEach
    void setUp() {
        audit = Audit.builder()
                .id(123L)
                .entityType("atm")
                .operationType("withdraw")
                .createdBy("Shrimp")
                .modifiedBy("Ger")
                .createdAt(new Timestamp(1))
                .modifiedAt(new Timestamp(1))
                .newEntityJson("new_json")
                .entityJson("json")
                .build();

        List<Audit> testList = new ArrayList<>();
        testList.add(audit);

        doReturn(testList).when(auditRepository).findByEntityType(any());
        doReturn(true).when(auditRepository).existsByEntityType(any());
    }

    @Test
    void add() {
        auditServiceImpl.add(audit);
        List<Audit> addResult = auditRepository.findByEntityType(audit.getEntityType());
        assertThat(addResult.get(0).getEntityType()).isEqualTo(audit.getEntityType());
    }

    @Test
    void delete() {
        auditServiceImpl.add(audit);
        auditServiceImpl.delete(audit.getEntityType());
        doReturn(false).when(auditRepository).existsByEntityType(any());
        boolean checkIfExist = auditServiceImpl.checkIfExistsByEntityType(audit.getEntityType());
        assertFalse(checkIfExist);
    }

    @Test
    void getAuditsByEntityType() {
        auditServiceImpl.add(audit);
        List<Audit> addResult = auditRepository.findByEntityType(audit.getEntityType());
        assertThat(addResult.get(0).getEntityType()).isEqualTo(audit.getEntityType());
    }

    @Test
    void checkIfExistsByEntityType() {
        auditServiceImpl.add(audit);
        boolean result = auditServiceImpl.checkIfExistsByEntityType(audit.getEntityType());
        assertTrue(result);
    }
}