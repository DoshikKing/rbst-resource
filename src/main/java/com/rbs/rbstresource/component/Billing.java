package com.rbs.rbstresource.component;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Optional;

@Entity
@Table(name = "billing")
@Getter
@Setter
public class Billing implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "billing_name", nullable = false)
    private String billingName;

    @Column(name = "billing_comment", nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    public Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    public Status status;

    public Billing(String billingName, String comment, Status status, Optional<Client> client) {
        this.setBillingName(billingName);
        this.setComment(comment);
        this.setStatus(status);
        this.setClient(client.stream().findFirst().orElse(null));
    }

    public Billing() {

    }
}
