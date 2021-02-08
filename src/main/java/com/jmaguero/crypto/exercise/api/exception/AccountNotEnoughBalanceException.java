package com.jmaguero.crypto.exercise.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccountNotEnoughBalanceException extends RuntimeException {

    public AccountNotEnoughBalanceException(String message) {
        super(message);
    }
}
