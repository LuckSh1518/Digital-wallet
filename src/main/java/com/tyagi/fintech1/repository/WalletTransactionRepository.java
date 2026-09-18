package com.tyagi.fintech1.repository;

import com.tyagi.fintech1.entity.Wallet;
import com.tyagi.fintech1.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, String> {
    List<WalletTransaction> findTop20ByWalletOrderByCreatedAtDesc(Wallet wallet);
}