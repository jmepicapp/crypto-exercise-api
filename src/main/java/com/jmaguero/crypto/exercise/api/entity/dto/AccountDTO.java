package com.jmaguero.crypto.exercise.api.entity.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Currency;

@Getter
@Setter
public class AccountDTO {

    private Integer id;

    private String name;

    @NotNull
    private String currency;

    @NotNull
    private Double balance;

    private boolean treasury;

    public AccountDTO() {}

    public AccountDTO(String name, @NotNull String currency, @NotNull Double balance, boolean treasury) {
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.treasury = treasury;
    }

    public AccountDTO(Integer id, String name, @NotNull String currency, @NotNull Double balance, boolean treasury) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.treasury = treasury;
    }
}
