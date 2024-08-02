package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.entity.Certificate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CertificateMapperTest {

    private CertificateMapper certificateMapper = CertificateMapper.getInstance();

    @Test
    void getInstance() {

        CertificateMapper actualResult = CertificateMapper.getInstance();
        CertificateMapper expectedResult = new CertificateMapperImpl();

        assertThat(actualResult.getClass()).isEqualTo(expectedResult.getClass());
    }

    @Test
    void DTOToCertificate() {

        CertificateDTO certificateDTO = CertificateDTO.builder().build();
        Certificate actualResult = certificateMapper.DTOToCertificate(certificateDTO);
        Certificate expectedResult = Certificate.builder().build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void certificateToDTO() {

        Certificate certificate = Certificate.builder().build();
        CertificateDTO actualResult = certificateMapper.CertificateToDTO(certificate);
        CertificateDTO expectedResult = CertificateDTO.builder().build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void certificateListToDTO() {

        List<Certificate> testList = new ArrayList<>();
        testList.add(Certificate.builder().build());

        List<CertificateDTO> actualResult = certificateMapper.CertificateListToDTO(testList);

        List<CertificateDTO> testDTOList = new ArrayList<>();
        testDTOList.add(CertificateDTO.builder().build());

        List<CertificateDTO> expectedResult = testDTOList;

        assertThat(actualResult).isEqualTo(expectedResult);
    }

}