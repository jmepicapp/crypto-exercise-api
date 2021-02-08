package com.jmaguero.crypto.exercise.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Currency;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TransactionId id;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "balance")
    private Double balance;

}
