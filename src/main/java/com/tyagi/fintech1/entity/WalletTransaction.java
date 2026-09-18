package com.tyagi.fintech1.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "wallet_transactions")
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private String counterparty;

    @Column(nullable = false, unique = true, length = 24)
    private String reference;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal fee = BigDecimal.ZERO;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    protected WalletTransaction() { }

    public WalletTransaction(Wallet wallet, String type, BigDecimal amount, String counterparty) {
        this.wallet = wallet;
        this.type = type;
        this.amount = amount;
        this.counterparty = counterparty;
        this.reference = "PF" + java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 18).toUpperCase();
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public String getCounterparty() { return counterparty; }
    public Instant getCreatedAt() { return createdAt; }
    public String getReference() { return reference; }
    public BigDecimal getFee() { return fee; }
}