//package com.rbs.rbstresource.component;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.sql.Date;
//import java.util.List;
//
//@Entity
//@Table(name = "client")
//@Getter
//@Setter
//public class Client implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(name = "client_name", nullable = false)
//    private String clientName;
//
//    @Column(name = "birthday", nullable = false)
//    private Date birthday;
//
//    @Column(name = "address", nullable = false)
//    private String address;
//
//    @Column(name = "is_activated")
//    private boolean isActivated;
//
//    @Column(name = "user_id", nullable = false)
//    private Long userId;
//
//    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    public List<Account> accounts;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "status_id", nullable = false)
//    public Status status;
//}