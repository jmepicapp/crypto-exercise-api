package com.jmaguero.crypto.exercise.api.controller;

import com.jmaguero.crypto.exercise.api.entity.dto.TransactionDTO;
import com.jmaguero.crypto.exercise.api.exception.AccountNotEnoughBalanceException;
import com.jmaguero.crypto.exercise.api.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    @ApiOperation(value = "Creates a new transaction between two accounts")
    public ResponseEntity<?> saveTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            this.transactionService.save(transactionDTO);
        } catch (AccountNotEnoughBalanceException accountNotEnoughBalanceException) {
            return new ResponseEntity<>(accountNotEnoughBalanceException.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
