package com.bank.publicinfo.controller;

import com.bank.publicinfo.Util.ValidUtil;
import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.mappers.BranchMapper;
import com.bank.publicinfo.service.BranchService;
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
public class BranchRestController {

    private final BranchService branchService;
    private final BranchMapper branchMapper;
    private static final Logger logger = LoggerFactory.getLogger(BranchRestController.class);

    @Operation(summary = "Add new branch")
    @PostMapping("/branch")
    public ResponseEntity<HttpStatus> addBranch(@Valid @RequestBody BranchDTO branchDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            logger.error("Wrong REST API JSON parameter");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        branchService.add(branchMapper.DTOToBranch(branchDTO));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete all branch for this address")
    @DeleteMapping("/branch/{address}")
    public ResponseEntity<HttpStatus> deleteBranch(@PathVariable("address") String address) {
        if (!branchService.checkIfExistsByAddress(address)) {
            logger.error("Attempt to delete not existing transfer record");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        branchService.delete(address);
        return ResponseEntity.ok(HttpStatus.FOUND);
    }

    @Operation(summary = "Get branch for particular address")
    @GetMapping("/branch/{address}")
    public List<BranchDTO> getBranchesByAddress(@PathVariable("address") String address) {
        return branchMapper.BranchListToDTO(branchService.getBranchesByAddress(address));
    }
}
