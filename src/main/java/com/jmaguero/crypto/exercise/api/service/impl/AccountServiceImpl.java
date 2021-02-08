package com.jmaguero.crypto.exercise.api.service.impl;

import com.jmaguero.crypto.exercise.api.entity.AccountEntity;
import com.jmaguero.crypto.exercise.api.entity.dto.AccountDTO;
import com.jmaguero.crypto.exercise.api.exception.AccountNotFoundException;
import com.jmaguero.crypto.exercise.api.repository.AccountRepository;
import com.jmaguero.crypto.exercise.api.service.AccountService;
import com.jmaguero.crypto.exercise.api.util.CommonMessage;
import com.jmaguero.crypto.exercise.api.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public AccountDTO find(Integer id) throws AccountNotFoundException{
        Optional<AccountEntity> optionalAccount = this.accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            return ModelMapperUtil.map(optionalAccount, AccountDTO.class);
        }
        throw new AccountNotFoundException(CommonMessage.AccountNotFound);
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        AccountEntity accountEntity = ModelMapperUtil.map(accountDTO, AccountEntity.class);
        return ModelMapperUtil.map(this.accountRepository.save(accountEntity), AccountDTO.class);
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO, Integer id) {
        Optional<AccountEntity> optionalAccount = this.accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            throw new AccountNotFoundException(CommonMessage.AccountNotFound);
        } else {
            AccountEntity accountEntity = optionalAccount.get();
            accountEntity.setBalance(accountDTO.getBalance());
            accountEntity.setName(accountDTO.getName());
            accountEntity.setCurrency(accountDTO.getCurrency());
            return ModelMapperUtil.map(this.accountRepository.save(accountEntity), AccountDTO.class);
        }
    }

    @Override
    public void delete(Integer id) {
        this.accountRepository.deleteById(id);
    }
}
