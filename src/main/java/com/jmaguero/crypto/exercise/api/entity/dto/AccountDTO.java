package com.jmaguero.crypto.exercise.api.entity.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Currency;

@Getter
@Setter
public class AccountDTO {

    @NotNull
    private Integer id;

    private String name;

    @NotNull
    private Currency currency;

    @NotNull
    private Double balance;

    private boolean treasury;

}
