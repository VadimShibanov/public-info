package com.bank.publicinfo.controller;

import com.bank.publicinfo.Util.ValidUtil;
import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.mappers.CertificateMapper;
import com.bank.publicinfo.service.CertificateService;
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
public class CertificateRestController {

    private final CertificateService certificateService;
    private final CertificateMapper certificateMapper;
    private static final Logger logger = LoggerFactory.getLogger(CertificateRestController.class);

    @Operation(summary = "Add new certificate")
    @PostMapping("/certificate")
    public ResponseEntity<HttpStatus> addCertificate(@Valid @RequestBody CertificateDTO certificateDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            logger.error("Wrong REST API JSON parameter");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        certificateService.add(certificateMapper.DTOToCertificate(certificateDTO));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete all certificate for this bank detail id")
    @DeleteMapping("/certificate/{bankDetailsId}")
    public ResponseEntity<HttpStatus> deleteCertificate(@PathVariable("bankDetailsId") Long bankDetailsId) {
        if (!certificateService.checkIfExistsByBankDetailsId(bankDetailsId)) {
            logger.error("Attempt to delete not existing transfer record");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        certificateService.delete(bankDetailsId);
        return ResponseEntity.ok(HttpStatus.FOUND);
    }

    @Operation(summary = "Get certificate for particular bank details id")
    @GetMapping("/certificate/{bankDetailsId}")
    public List<CertificateDTO> getCertificateByBankDetailsId (@PathVariable("bankDetailsId") Long bankDetailsId) {
        return certificateMapper.CertificateListToDTO(certificateService.getCertificateByBankDetailsId(bankDetailsId));
    }
}
