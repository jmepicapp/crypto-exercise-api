package com.jmaguero.crypto.exercise.api.entity.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TransactionDTO {

    @NotNull
    private Integer idAccountFrom;

    @NotNull
    private Integer idAccountTo;

    @NotNull
    private String currency;

    @NotNull
    private Double balance;

    public TransactionDTO(@NotNull Integer idAccountFrom, @NotNull Integer idAccountTo, @NotNull String currency, @NotNull Double balance) {
        this.idAccountFrom = idAccountFrom;
        this.idAccountTo = idAccountTo;
        this.currency = currency;
        this.balance = balance;
    }
}
