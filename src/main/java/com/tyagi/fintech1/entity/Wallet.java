package com.tyagi.fintech1.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private User user;

    // No-arg constructor (required by JPA)
    public Wallet() {
    }

    // Parameterized constructor
    public Wallet(BigDecimal balance, User user) {
        this.balance = balance;
        this.user = user;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}