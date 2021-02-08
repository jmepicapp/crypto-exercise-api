package com.jmaguero.crypto.exercise.api.service;

import com.jmaguero.crypto.exercise.api.entity.AccountEntity;
import com.jmaguero.crypto.exercise.api.entity.dto.AccountDTO;
import org.springframework.stereotype.Service;

public interface AccountService {

    AccountDTO find (Integer id);

    AccountDTO save(AccountDTO account);

    AccountDTO update(AccountDTO account, Integer id);

    void delete(Integer id);
}
