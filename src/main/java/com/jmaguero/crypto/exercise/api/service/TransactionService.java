package com.jmaguero.crypto.exercise.api.service;

import com.jmaguero.crypto.exercise.api.entity.dto.TransactionDTO;

public interface TransactionService {

    void save(TransactionDTO transaction);

}
