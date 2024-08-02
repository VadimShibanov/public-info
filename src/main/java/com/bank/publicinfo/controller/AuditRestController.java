package com.bank.publicinfo.controller;

import com.bank.publicinfo.Util.ValidUtil;
import com.bank.publicinfo.dto.AuditDTO;
import com.bank.publicinfo.mappers.AuditMapper;
import com.bank.publicinfo.service.AuditService;
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
public class AuditRestController {
    private final AuditService auditService;
    private final AuditMapper auditMapper;
    private static final Logger logger = LoggerFactory.getLogger(AuditRestController.class);

    @Operation(summary = "Add new audit record")
    @PostMapping("/audit")
    public ResponseEntity<HttpStatus> addAudit(@Valid @RequestBody AuditDTO auditDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            logger.error("Wrong REST API JSON parameter");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        auditService.add(auditMapper.DTOToAudit(auditDTO));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete all audit for this entityType")
    @DeleteMapping("/audit/{entityType}")
    public ResponseEntity<Object> deleteAudit(@PathVariable("entityType") String entityType) {
        if (!auditService.checkIfExistsByEntityType(entityType)) {
            log.error("Attempt to delete not existing transfer record");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        auditService.delete(entityType);
        return ResponseEntity.ok(HttpStatus.FOUND);
    }

    @Operation(summary = "Get list of audit records for particular Entity Type")
    @GetMapping("/audit/{entityType}")
    public List<AuditDTO> getAuditByEntityType (@PathVariable("entityType") String entityType) {
        return auditMapper.AuditListToDTO(auditService.getAuditsByEntityType(entityType));
    }
}
