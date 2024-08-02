package com.bank.publicinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LicenseDTO {

    @NotNull(message = "Should not be NULL")
    Byte photo;

    Long bankDetailsId;
}
