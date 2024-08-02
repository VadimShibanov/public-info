package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.mappers.AtmMapper;
import com.bank.publicinfo.service.AtmService;
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
public class AtmRestController {

    private final AtmService atmService;
    private final AtmMapper atmMapper;
    private static final Logger logger = LoggerFactory.getLogger(AtmRestController.class);

    @Operation(summary = "Add new atm")
    @PostMapping("/atm")
    public ResponseEntity<HttpStatus> addAtm(@Valid @RequestBody AtmDTO atmDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            logger.error("Wrong REST API JSON parameter");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        atmService.add(atmMapper.DTOToAtm(atmDTO));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete all atm for this address")
    @DeleteMapping("/atm/{address}")
    public ResponseEntity<Object> deleteAtm(@PathVariable("address") String address) {
        if (!atmService.checkIfExistsByAddress(address)) {
            log.error("Attempt to delete not existing transfer record");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        atmService.delete(address);
        return ResponseEntity.ok(HttpStatus.FOUND);
    }

    @Operation(summary = "Get list of atm for particular Address")
    @GetMapping("/atm/{address}")
    public List<AtmDTO> getAtmByAddress (@PathVariable("address") String address) {
        return atmMapper.AtmListToDTO(atmService.getAtmByAddress(address));
    }
}
