package com.bank.publicinfo.controller;

import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.service.AuditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuditRestControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    AuditService auditService;
    List<Audit> testList = new ArrayList<>();
    Audit audit;
    String id;

    @BeforeAll
    void setUp() {

        audit = Audit.builder()
                .entityType("test_type")
                .operationType("test_operation")
                .createdBy("Bob")
                .modifiedBy("Poll")
                .createdAt(new Timestamp(1))
                .modifiedAt(new Timestamp(1))
                .newEntityJson("new_json")
                .entityJson("json")
                .build();

        testList = List.of(audit);
        id = audit.getEntityType();
    }

    @Test
    void addAudit() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/audit")
                    .content(objectMapper.writeValueAsString(audit))
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteAudit() throws Exception {

        when(auditService.checkIfExistsByEntityType(audit.getEntityType())).thenReturn(true);

        mockMvc.perform(delete("/audit/" + audit.getEntityType()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAuditByEntityType() throws Exception {

        when(auditService.getAuditsByEntityType(id)).thenReturn(testList);

        mockMvc.perform(get("/audit/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[*].entityType", containsInAnyOrder("test_type")))
                .andExpect(status().isOk());
    }
}