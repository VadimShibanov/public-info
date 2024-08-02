package com.bank.publicinfo.controller;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.service.BankDetailsService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BankDetailsRestControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    BankDetailsService bankDetailsService;
    List<BankDetails> testList = new ArrayList<>();
    BankDetails bankDetails;
    Long id;

    @BeforeAll
    void setUp() {

        bankDetails = BankDetails.builder()
                .bik(1L)
                .inn(2L)
                .kpp(3L)
                .corAccount(1234)
                .city("Perm")
                .jointStockCompany("test")
                .name("name")
                .build();

        testList = List.of(bankDetails);
        id = bankDetails.getBik();
    }

    @Test
    void deleteBankDetails() throws Exception {

        when(bankDetailsService.checkIfExistsByBik(bankDetails.getBik())).thenReturn(true);

        mockMvc.perform(delete("/bank-details/" + bankDetails.getBik()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getBankDetailsByBik() throws Exception {

        when(bankDetailsService.getBankDetailsByBik(id)).thenReturn(testList);

        mockMvc.perform(get("/bank-details/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].bik").value(1))
                .andExpect(status().isOk());
    }
}