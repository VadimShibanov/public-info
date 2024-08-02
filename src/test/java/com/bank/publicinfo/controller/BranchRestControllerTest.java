package com.bank.publicinfo.controller;

import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.service.BranchService;
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

import java.sql.Time;
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
class BranchRestControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    BranchService branchService;
    List<Branch> testList = new ArrayList<>();
    Branch branch;

    String id;

    @BeforeAll
    void setUp() {

        branch = Branch.builder()
                .address("test_address")
                .phoneNumber(12345678L)
                .city("Perm")
                .startOfWork(new Time(1))
                .endOfWork(new Time(1))
                .build();

        testList = List.of(branch);
        id = branch.getAddress();
    }

    @Test
    void addBranch() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/branch")
                    .content(objectMapper.writeValueAsString(branch))
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteBranch() throws Exception {

        when(branchService.checkIfExistsByAddress(branch.getAddress())).thenReturn(true);

        mockMvc.perform(delete("/branch/" + branch.getAddress()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getBranchesByAddress() throws Exception {

        when(branchService.getBranchesByAddress(id)).thenReturn(testList);

        mockMvc.perform(get("/branch/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[*].address", containsInAnyOrder("test_address")))
                .andExpect(status().isOk());
    }
}