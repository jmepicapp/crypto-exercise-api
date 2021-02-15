package com.jmaguero.crypto.exercise.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmaguero.crypto.exercise.api.entity.dto.AccountDTO;
import com.jmaguero.crypto.exercise.api.entity.dto.TransactionDTO;
import com.jmaguero.crypto.exercise.api.exception.AccountNotEnoughBalanceException;
import com.jmaguero.crypto.exercise.api.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TransactionController.class)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    private AccountDTO accountFrom;
    private AccountDTO accountTo;
    private TransactionDTO transaction;

    @BeforeEach
    public void setTransactionInformation(){
        this.accountFrom = new AccountDTO(1,"New Account", "Euro", 6000D, true);
        this.accountFrom = new AccountDTO(2, "New Account", "Euro", 3500D, false);
        this.transaction = new TransactionDTO(1, 2, "euro", 1500D);
    }

    @Test
    public void shouldSaveTransaction() throws Exception{
        doNothing().when(transactionService).save(transaction);

        this.mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.transaction)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotSaveTransaction() throws Exception {
        doThrow(AccountNotEnoughBalanceException.class).when(transactionService).save(any(TransactionDTO.class));

        this.mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.transaction)))
                .andExpect(status().isForbidden());

    }
}
