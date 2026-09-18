package com.tyagi.fintech1.repository;
import com.tyagi.fintech1.entity.User;
import com.tyagi.fintech1.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,String> {
    Optional<Wallet> findByUser(User user);}