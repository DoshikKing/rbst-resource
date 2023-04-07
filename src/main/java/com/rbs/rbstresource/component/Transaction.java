package com.rbs.rbstresource.component;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "card_transaction")
@Getter
@Setter
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "amount", nullable = false)
    private float amount;

    @Column(name = "is_debit")
    private Boolean isDebit;

    @Column(name = "transaction_time", nullable = false)
    private Date transactionTime;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "transaction_group", nullable = false)
    private String transactionGroup;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    public Account account;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "card_id")
    public Card card;
}
