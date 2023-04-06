package com.rbs.rbstresource.component;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "card")
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "card_hash_number", unique = true, nullable = false, length = 16)
    private String code;

    @Column(name = "balance",nullable = false, precision = 2)
    private float balance;

    @Column(name = "limit_per_day", nullable = false, precision = 2)
    private float limitPerDay;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tariff_id", nullable = false)
    public Tariff tariff;

    @Column(name = "status_time", nullable = false)
    private Date statusTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    public Status status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    public Account account;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    public Client client;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pay_system_id", nullable = false)
    public PaySystem paySystem;

    @OneToMany(mappedBy = "bankCard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactionsList;
}
