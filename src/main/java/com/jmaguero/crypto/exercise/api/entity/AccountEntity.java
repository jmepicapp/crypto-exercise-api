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
    private @Setter String currency;

    @Column(name = "balance")
    private @Setter Double balance;

    @Column(name = "treasury")
    private @Setter boolean treasury;

    public AccountEntity() {}

    public AccountEntity(String name, String currency, Double balance, boolean treasury) {
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.treasury = treasury;
    }
}
