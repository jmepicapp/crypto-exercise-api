package com.jmaguero.crypto.exercise.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Currency;

@Getter
@Entity
@Table(name = "transactions")
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_from_id")
    private @Setter AccountEntity accountFrom;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_to_id")
    private @Setter AccountEntity accountTo;

    @Column(name = "currency")
    private @Setter String currency;

    @Column(name = "balance")
    private @Setter Double balance;

}
