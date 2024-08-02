package com.bank.publicinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDetailsDTO {

    @NotNull(message = "Should not be NULL")
    @Min(5)
    @Max(1_000_000_000L)
    Long bik;

    @NotNull(message = "Should not be NULL")
    @Min(5)
    @Max(1_000_000_000L)
    Long inn;

    @NotNull(message = "Should not be NULL")
    @Min(5)
    @Max(1_000_000_000L)
    Long kpp;

    @NotNull(message = "Should not be NULL")
    @Min(5)
    @Max(1_000_000)
    Integer corAccount;

    @NotBlank(message = "Should not be BLANK")
    String city;

    @NotBlank(message = "Should not be BLANK")
    String jointStockCompany;

    @NotBlank(message = "Should not be BLANK")
    String name;

    LicenseDTO licenseDTO;

    CertificateDTO certificateDTO;
}
