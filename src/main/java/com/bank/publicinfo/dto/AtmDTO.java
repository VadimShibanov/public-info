package com.bank.publicinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.sql.Time;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtmDTO {

    @NotBlank(message = "Should not be BLANK")
    @Size(min = 1, max = 370, message =
            "Address must be between 1 and 370 characters")
    String address;

    Time startOfWork;

    Time endOfWork;

    @NotNull(message = "Should not be NULL")
    Boolean allHours;

    Long branchId;
}
