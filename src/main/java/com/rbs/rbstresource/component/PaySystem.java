package com.rbs.rbstresource.component;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pay_system")
@Getter
@Setter
public class PaySystem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type", nullable = false, length = 30)
    private String type;

    @OneToOne(mappedBy = "paySystem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Card card;
}
