package com.bank.publicinfo.controller;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.service.LicenseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LicenseRestControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    LicenseService licenseService;
    List<License> testList = new ArrayList<>();
    License license;
    Long id;
    Byte b = 1;
    @BeforeEach
    void setUp() {

        license = License.builder()
                .photo(b)
                .bankDetails(new BankDetails())
                .build();

        testList = List.of(license);
        id = license.getBankDetails().getId();
    }

    @Test
    void addLicense() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/license")
                        .content(objectMapper.writeValueAsString(license))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}