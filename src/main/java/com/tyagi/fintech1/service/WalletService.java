package com.tyagi.fintech1.service;

import com.tyagi.fintech1.entity.User;
import com.tyagi.fintech1.entity.Wallet;
import com.tyagi.fintech1.repository.WalletRepository;
import com.tyagi.fintech1.repository.WalletTransactionRepository;
import com.tyagi.fintech1.entity.WalletTransaction;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionRepository transactionRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository, WalletTransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public Wallet createWallet(User user) {
        Optional<Wallet> existing = walletRepository.findByUser(user);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Wallet already exists for this user");
        }
        Wallet wallet = new Wallet(BigDecimal.ZERO, user);
        return walletRepository.save(wallet);
    }

    public Wallet getWalletByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found for this user"));
    }

    public Wallet deposit(User user, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        Wallet wallet = getWalletByUser(user);
        wallet.setBalance(wallet.getBalance().add(amount));
        transactionRepository.save(new WalletTransaction(wallet, "ADD", amount, "Money added"));
        return walletRepository.save(wallet);
    }

    public Wallet withdraw(User user, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }
        Wallet wallet = getWalletByUser(user);
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        wallet.setBalance(wallet.getBalance().subtract(amount));
        transactionRepository.save(new WalletTransaction(wallet, "WITHDRAW", amount, "Money withdrawn"));
        return walletRepository.save(wallet);
    }

    @Transactional
    public Wallet transfer(User fromUser, User toUser, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        if (fromUser.getId().equals(toUser.getId())) {
            throw new IllegalArgumentException("You cannot send money to yourself");
        }
        Wallet senderWallet = getWalletByUser(fromUser);
        Wallet recipientWallet = getWalletByUser(toUser);
        if (senderWallet.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        senderWallet.setBalance(senderWallet.getBalance().subtract(amount));
        recipientWallet.setBalance(recipientWallet.getBalance().add(amount));
        walletRepository.save(senderWallet);
        walletRepository.save(recipientWallet);
        transactionRepository.save(new WalletTransaction(senderWallet, "SEND", amount, toUser.getName()));
        transactionRepository.save(new WalletTransaction(recipientWallet, "RECEIVE", amount, fromUser.getName()));
        return recipientWallet;
    }

    public List<WalletTransaction> getTransactions(User user) {
        return transactionRepository.findTop20ByWalletOrderByCreatedAtDesc(getWalletByUser(user));
    }
}