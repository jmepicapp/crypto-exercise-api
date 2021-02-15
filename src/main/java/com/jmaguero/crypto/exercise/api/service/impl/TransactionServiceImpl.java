package com.jmaguero.crypto.exercise.api.service.impl;

import com.jmaguero.crypto.exercise.api.entity.TransactionEntity;
import com.jmaguero.crypto.exercise.api.entity.dto.AccountDTO;
import com.jmaguero.crypto.exercise.api.entity.dto.TransactionDTO;
import com.jmaguero.crypto.exercise.api.exception.AccountNotEnoughBalanceException;
import com.jmaguero.crypto.exercise.api.exception.AccountNotFoundException;
import com.jmaguero.crypto.exercise.api.repository.TransactionRepository;
import com.jmaguero.crypto.exercise.api.service.AccountService;
import com.jmaguero.crypto.exercise.api.service.TransactionService;
import com.jmaguero.crypto.exercise.api.util.CommonMessage;
import com.jmaguero.crypto.exercise.api.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Override
    public void save(TransactionDTO transaction) throws AccountNotEnoughBalanceException, AccountNotFoundException {
        AccountDTO accountFrom = this.accountService.find(transaction.getIdAccountFrom());
        AccountDTO accountTo = this.accountService.find(transaction.getIdAccountTo());

        if (accountFrom.isTreasury() || this.hasFromAccountEnoughBalance(transaction)) {
            accountFrom.setBalance(accountFrom.getBalance() - transaction.getBalance());
            accountTo.setBalance(accountTo.getBalance() + transaction.getBalance());
            this.transactionRepository.save(ModelMapperUtil.map(transaction, TransactionEntity.class));
            this.accountService.update(accountFrom, accountFrom.getId());
            this.accountService.update(accountTo, accountTo.getId());
        } else {
            throw new AccountNotEnoughBalanceException(CommonMessage.AccountNotEnoughBalance);
        }
    }

    private boolean hasFromAccountEnoughBalance(TransactionDTO transaction) {
        AccountDTO accountFrom = this.accountService.find(transaction.getIdAccountFrom());
        return accountFrom.getBalance() > transaction.getBalance();
    }

}
