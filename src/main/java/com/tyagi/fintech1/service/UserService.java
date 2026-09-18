package com.tyagi.fintech1.service;

import com.tyagi.fintech1.entity.User;
import com.tyagi.fintech1.repository.UserRepository;
import com.tyagi.fintech1.repository.WalletRepository;
import com.tyagi.fintech1.entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.math.BigDecimal;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Autowired
    public UserService(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    public User registerUser(String name, String email, String password) {
        return registerUser(name, email, "", password);
    }

    public User registerUser(String name, String email, String mobile, String password) {
        Optional<User> existing = userRepository.findByEmail(email);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("User already exists with this email");
        }
        if (mobile != null && !mobile.isBlank() && userRepository.findByMobile(mobile).isPresent()) {
            throw new IllegalArgumentException("User already exists with this mobile number");
        }
        if (name == null || name.isBlank() || email == null || email.isBlank() || mobile == null || !mobile.matches("\\+?[0-9]{10,15}") || password == null || password.length() < 4) {
            throw new IllegalArgumentException("Name, email, valid mobile number and a password of at least 4 characters are required");
        }
        User user = userRepository.save(new User(name.trim(), email.trim().toLowerCase(), mobile.trim(), password));
        walletRepository.save(new Wallet(BigDecimal.ZERO, user));
        return user;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public User findByMobile(String mobile) {
        return userRepository.findByMobile(mobile)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}