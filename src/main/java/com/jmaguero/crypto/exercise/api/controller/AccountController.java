package com.jmaguero.crypto.exercise.api.controller;

import com.jmaguero.crypto.exercise.api.entity.dto.AccountDTO;
import com.jmaguero.crypto.exercise.api.exception.AccountNotFoundException;
import com.jmaguero.crypto.exercise.api.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Returns the account information matching a given id")
    public ResponseEntity<?> getAccount(@PathVariable Integer id) {
        try {
            AccountDTO response = this.accountService.find(id);
        } catch (AccountNotFoundException accountNotFoundException) {
            return new ResponseEntity<>(accountNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AccountDTO>(this.accountService.find(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Creates a new account")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO newAccount) {
        return new ResponseEntity<AccountDTO>(this.accountService.save(newAccount), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update the account information matching a given id")
    public ResponseEntity<?> updateAccount(@RequestBody AccountDTO accountDTO, @PathVariable Integer id) {
        try {
            AccountDTO response = this.accountService.update(accountDTO, id);
        } catch (AccountNotFoundException accountNotFoundException) {
            return new ResponseEntity<>(accountNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AccountDTO>(this.accountService.update(accountDTO, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Deletes the account information matching a given id")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer id) {
        this.accountService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
