package com.rbs.rbstresource.service.ORMRepository;

import com.rbs.rbstresource.component.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByTransactionGroup(String transaction_group);
    Transaction findByAccountId(Long id);
    Transaction findByCardId(Long id);
}
