package com.bank.publicinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.sql.Timestamp;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditDTO {

    @NotBlank(message = "Should not be BLANK")
    String entityType;

    @NotBlank(message = "Should not be BLANK")
    String operationType;

    @NotBlank(message = "Should not be BLANK")
    String createdBy;

    String modifiedBy;

    @NotNull(message = "Should not be BLANK")
    @PastOrPresent
    Timestamp createdAt;

    @PastOrPresent
    Timestamp modifiedAt;

    String newEntityJson;

    @NotBlank(message = "Should not be BLANK")
    String entityJson;
}
