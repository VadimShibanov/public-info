package com.bank.publicinfo.controller;

import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.service.AtmService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AtmRestControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    AtmService atmService;
    List<Atm> testList = new ArrayList<>();
    Atm atm;
    String id;

    @BeforeAll
    void setUp() {

        atm = Atm.builder()
                .address("test_address")
                .startOfWork(new Time(1))
                .endOfWork(new Time(1))
                .allHours(false)
                .build();

        testList = List.of(atm);
        id = atm.getAddress();
    }

    @Test
    void addAtm() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/atm")
                    .content(objectMapper.writeValueAsString(atm))
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteAtm() throws Exception {

        when(atmService.checkIfExistsByAddress(atm.getAddress())).thenReturn(true);

        mockMvc.perform(delete("/atm/" + atm.getAddress()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAtmByAddress() throws Exception {

        when(atmService.getAtmByAddress(id)).thenReturn(testList);

        mockMvc.perform(get("/atm/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[*].address", containsInAnyOrder("test_address")))
                .andExpect(status().isOk());
    }
}