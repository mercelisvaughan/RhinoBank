package com.stayready.domains;

import com.stayready.enums.AccountType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "ACCOUNT_ID")
    private long id;

    @Column(name = "BALANCE")
    @NotEmpty
    private double balance;

    private AccountType type;
    private String nickname;
    private int rewards;
    private Customer customer;



}
