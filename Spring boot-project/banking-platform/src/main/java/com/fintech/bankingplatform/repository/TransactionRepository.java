package com.fintech.bankingplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.bankingplatform.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountId(Long accountId);

}