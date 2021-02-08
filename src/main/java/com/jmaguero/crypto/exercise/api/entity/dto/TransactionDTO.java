package com.jmaguero.crypto.exercise.api.entity.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Currency;

@Getter
@Setter
public class TransactionDTO {

    @NotNull
    private Integer idAccountFrom;

    @NotNull
    private Integer idAccountTo;

    @NotNull
    private Currency currency;

    @NotNull
    private Double balance;
}
