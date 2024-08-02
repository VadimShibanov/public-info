package com.bank.publicinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {

    @NotBlank(message = "Should not be BLANK")
    @Size(min = 1, max = 370, message =
            "Address must be between 1 and 370 characters")
    String address;

    @NotNull(message = "Should not be NULL")
    Long phoneNumber;

    @NotBlank(message = "Should not be BLANK")
    @Size(min = 1, max = 250, message =
            "Address must be between 1 and 250 characters")
    String city;

    @NotNull
    Time startOfWork;

    @NotNull
    Time endOfWork;
}
