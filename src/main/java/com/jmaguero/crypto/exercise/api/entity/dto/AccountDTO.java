package com.jmaguero.crypto.exercise.api.entity.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Currency;

@Getter
public class AccountDTO {

    @NotNull
    private Integer id;

    private @Setter String name;

    @NotNull
    private @Setter Currency currency;

    @NotNull
    private @Setter Double balance;

    private boolean treasury;

}
