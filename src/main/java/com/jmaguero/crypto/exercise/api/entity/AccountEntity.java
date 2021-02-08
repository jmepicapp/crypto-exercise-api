package com.jmaguero.crypto.exercise.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.Currency;

@Getter
@Entity
@Table(name = "accounts")
public class AccountEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private @Setter String name;

    @Column(name = "currency")
    private @Setter Currency currency;

    @Column(name = "balance")
    private @Setter Double balance;

    @Column(name = "treasury")
    private boolean treasury;

    public AccountEntity(String name, Currency currency, Double balance, boolean treasury) {
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.treasury = treasury;
    }
}
