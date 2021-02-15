package com.jmaguero.crypto.exercise.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmaguero.crypto.exercise.api.entity.dto.AccountDTO;
import com.jmaguero.crypto.exercise.api.exception.AccountNotFoundException;
import com.jmaguero.crypto.exercise.api.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    private AccountDTO account;

    @BeforeEach
    public void setAccountInformation(){
        this.account = new AccountDTO("New Account", "Euro", 6000D, true);
    }

    @Test
    public void shouldSaveAccount() throws Exception {
        given(accountService.save(any(AccountDTO.class))).willReturn(this.account);

        this.mockMvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.account)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(this.account.getName())))
                .andExpect(jsonPath("$.balance", is(this.account.getBalance())))
                .andExpect(jsonPath("$.treasury", is(this.account.isTreasury())));
    }

    @Test
    public void shouldGetAccount() throws Exception {
        Integer accountId = 1;

        given(accountService.find(any(Integer.class))).willReturn(this.account);

        this.mockMvc.perform(get("/account/{id}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(this.account.getName())))
                .andExpect(jsonPath("$.balance", is(this.account.getBalance())))
                .andExpect(jsonPath("$.treasury", is(this.account.isTreasury())));
    }

    @Test
    public void shouldNotGetAccount() throws Exception {
        Integer accountId = 1;

        given(accountService.find(any(Integer.class))).willThrow(AccountNotFoundException.class);

        this.mockMvc.perform(get("/account/{id}", accountId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateAccount() throws Exception {
        Integer accountId = 1;
        given(accountService.update(any(AccountDTO.class), any(Integer.class))).willReturn(this.account);

        this.mockMvc.perform(put("/account/{id}", accountId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.account)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(this.account.getName())))
                .andExpect(jsonPath("$.balance", is(this.account.getBalance())))
                .andExpect(jsonPath("$.treasury", is(this.account.isTreasury())));
    }

    @Test
    public void shouldNotUpdateAccount() throws Exception {
        Integer accountId = 1;

        given(accountService.update(any(AccountDTO.class), any(Integer.class))).willThrow(AccountNotFoundException.class);

        this.mockMvc.perform(put("/account/{id}", accountId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.account)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteAccount() throws Exception {
        Integer accountId = 1;

        doNothing().when(accountService).delete(accountId);

        this.mockMvc.perform(delete("/account/{id}", accountId))
                .andExpect(status().isOk());
    }

}
