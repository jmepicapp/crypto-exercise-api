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
@Setter
@Entity
@Table(name = "accounts")
public class AccountEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "treasury")
    private boolean treasury;

}
