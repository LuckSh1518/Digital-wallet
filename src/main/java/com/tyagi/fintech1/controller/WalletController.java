package com.tyagi.fintech1.controller;

import com.tyagi.fintech1.entity.User;
import com.tyagi.fintech1.entity.Wallet;
import com.tyagi.fintech1.service.UserService;
import com.tyagi.fintech1.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;
    private final UserService userService;

    @Autowired
    public WalletController(WalletService walletService, UserService userService) {
        this.walletService = walletService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Wallet> createWallet(@RequestParam String email) {
        User user = userService.findByEmail(email);
        Wallet wallet = walletService.createWallet(user);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping
    public ResponseEntity<Wallet> getWallet(@RequestParam String email) {
        User user = userService.findByEmail(email);
        Wallet wallet = walletService.getWalletByUser(user);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> dashboard(@RequestParam String email) {
        User user = userService.findByEmail(email);
        Wallet wallet = walletService.getWalletByUser(user);
        return ResponseEntity.ok(Map.of("user", user, "wallet", wallet,
                "transactions", walletService.getTransactions(user)));
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> transactions(@RequestParam String email) {
        return ResponseEntity.ok(walletService.getTransactions(userService.findByEmail(email)));
    }

    @PostMapping("/deposit")
    public ResponseEntity<Wallet> deposit(@RequestParam String email, @RequestParam BigDecimal amount) {
        User user = userService.findByEmail(email);
        Wallet wallet = walletService.deposit(user, amount);
        return ResponseEntity.ok(wallet);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Wallet> withdraw(@RequestParam String email, @RequestParam BigDecimal amount) {
        User user = userService.findByEmail(email);
        Wallet wallet = walletService.withdraw(user, amount);
        return ResponseEntity.ok(wallet);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Wallet> transfer(@RequestParam String fromEmail,
                                            @RequestParam String toEmail,
                                            @RequestParam BigDecimal amount) {
        User fromUser = userService.findByEmail(fromEmail);
        User toUser = userService.findByEmail(toEmail);
        Wallet wallet = walletService.transfer(fromUser, toUser, amount);
        return ResponseEntity.ok(wallet);
    }
}