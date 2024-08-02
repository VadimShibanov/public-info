package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.entity.License;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LicenseServiceMapperTest {

    private LicenseServiceMapper licenseServiceMapper = LicenseServiceMapper.getInstance();

    @Test
    void getInstance() {

        LicenseServiceMapper actualResult = LicenseServiceMapper.getInstance();
        LicenseServiceMapper expectedResult = new LicenseServiceMapperImpl();

        assertThat(actualResult.getClass()).isEqualTo(expectedResult.getClass());
    }

    @Test
    void DTOToLicense() {

        LicenseDTO licenseDTO = LicenseDTO.builder().build();
        License actualResult = licenseServiceMapper.DTOToLicense(licenseDTO);
        License expectedResult = License.builder().build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void licenseToDTO() {

        License license = License.builder().build();
        LicenseDTO actualResult = licenseServiceMapper.LicenseToDTO(license);
        LicenseDTO expectedResult = LicenseDTO.builder().build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void licenseListToDTO() {

        List<License> testList = new ArrayList<>();
        testList.add(License.builder().build());

        List<LicenseDTO> actualResult = licenseServiceMapper.LicenseListToDTO(testList);

        List<LicenseDTO> testDTOList = new ArrayList<>();
        testDTOList.add(LicenseDTO.builder().build());

        List<LicenseDTO> expectedResult = testDTOList;

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}