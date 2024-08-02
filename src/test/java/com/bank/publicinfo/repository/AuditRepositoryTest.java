package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Audit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuditRepositoryTest {

    @Autowired
    private AuditRepository auditRepository;
    Audit audit;

    @BeforeAll
    void setUp() {

        audit = Audit.builder()
                .entityType("test_type")
                .operationType("test_operation")
                .createdBy("Bob")
                .modifiedBy("Jon")
                .createdAt(new Timestamp(1))
                .modifiedAt(new Timestamp(1))
                .newEntityJson("new_json")
                .entityJson("json")
                .build();
        auditRepository.save(audit);
    }

    @Test
    void findByEntityType() {

        assertThat(audit.getId()).isGreaterThan(0);

        List<Audit> resultList = auditRepository.findByEntityType(audit.getEntityType());

        assertThat(resultList.get(0).getEntityType()).isEqualTo(audit.getEntityType());
    }

    @Test
    void existsByEntityType() {

        boolean checkResult = auditRepository.existsByEntityType(audit.getEntityType());

        assertTrue(checkResult);

        List<Audit> resultList = auditRepository.findByEntityType(audit.getEntityType());

        assertThat(resultList.get(0).getEntityType()).isEqualTo(audit.getEntityType());
    }

    @AfterAll
    void tearDown() {
        auditRepository.delete(audit);
    }
}