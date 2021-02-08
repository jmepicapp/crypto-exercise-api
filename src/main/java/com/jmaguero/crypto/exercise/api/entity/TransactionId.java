package com.jmaguero.crypto.exercise.api.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TransactionId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_account_from")
    private Integer idAccountFrom;

    @Column(name = "id_account_to")
    private Integer idAccountTo;

    public TransactionId() {}

    public TransactionId(Integer idAccountFrom, Integer idAccountTo) {
        this.idAccountFrom = idAccountFrom;
        this.idAccountTo = idAccountTo;
    }
}
