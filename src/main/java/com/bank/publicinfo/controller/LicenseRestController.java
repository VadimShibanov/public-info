package com.bank.publicinfo.controller;

import com.bank.publicinfo.Util.ValidUtil;
import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.mappers.LicenseServiceMapper;
import com.bank.publicinfo.service.LicenseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
public class LicenseRestController {

    private final LicenseService licenseService;
    private final LicenseServiceMapper licenseMapper;
    private static final Logger logger = LoggerFactory.getLogger(LicenseRestController.class);

    @Operation(summary = "Add new license")
    @PostMapping("/license")
    public ResponseEntity<HttpStatus> addLicense(@Valid @RequestBody LicenseDTO licenseDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            logger.error("Wrong REST API JSON parameter");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        licenseService.add(licenseMapper.DTOToLicense(licenseDTO));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete all license for this bank details id")
    @DeleteMapping("/license/{bankDetailsId}")
    public ResponseEntity<HttpStatus> deleteLicense(@PathVariable("bankDetailsId") Long bankDetailsId) {
        if (!licenseService.checkIfExistsByBankDetailsId(bankDetailsId)) {
            logger.error("Attempt to delete not existing transfer record");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        licenseService.delete(bankDetailsId);
        return ResponseEntity.ok(HttpStatus.FOUND);
    }

    @Operation(summary = "Get license for particular bank details id")
    @GetMapping("/license/{bankDetailsId}")
    public List<LicenseDTO> getLicenseByBankDetailsId (@PathVariable("bankDetailsId") Long bankDetailsId) {
        return licenseMapper.LicenseListToDTO(licenseService.getLicenseByBankDetailsId(bankDetailsId));
    }
}
