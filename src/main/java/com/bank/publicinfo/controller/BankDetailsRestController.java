package com.bank.publicinfo.controller;

import com.bank.publicinfo.Util.ValidUtil;
import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.mappers.BankDetailsMapper;
import com.bank.publicinfo.service.BankDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
public class BankDetailsRestController {

    private final BankDetailsService bankDetailsService;
    private final BankDetailsMapper bankDetailsMapper;

    @Operation(summary = "Add new bank details")
    @PostMapping("/bank-details")
    public ResponseEntity<Object> addBankDetails(@Valid @RequestBody BankDetailsDTO bankDetailsDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.error("Wrong REST API JSON parameter");
            return new ResponseEntity<>(ValidUtil.getErrorsForJson(bindingResult) ,HttpStatus.NOT_ACCEPTABLE);
        }

//        if (bankDetailsService.checkIfExistsByBik(bankDetailsDTO.getBik())) {
//            log.error("Attempt to add bank details record with existing bik");
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }
        bankDetailsService.add(bankDetailsMapper.DTOToBankDetails(bankDetailsDTO));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete all bank details for this bik")
    @DeleteMapping("/bank-details/{bik}")
    public ResponseEntity<Object> deleteBankDetails(@PathVariable("bik") Long bik) {
        if (!bankDetailsService.checkIfExistsByBik(bik)) {
            log.error("Attempt to delete not existing transfer record");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        bankDetailsService.delete(bik);
        return ResponseEntity.ok(HttpStatus.FOUND);
    }

    @Operation(summary = "Get bank details for particular bik")
    @GetMapping("bank-details/{bik}")
    public List<BankDetailsDTO> getBankDetailsByBik (@PathVariable("bik") Long bik) {
        return bankDetailsMapper.BankDetailsListToDTO(bankDetailsService.getBankDetailsByBik(bik));
    }
}
