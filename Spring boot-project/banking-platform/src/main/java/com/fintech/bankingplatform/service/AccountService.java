package com.fintech.bankingplatform.service;

import java.util.List;

import com.fintech.bankingplatform.dto.TransactionResponse;
import com.fintech.bankingplatform.model.Account;

public interface AccountService {

    Account createAccount(Long userId);

    Account deposit(Long accountId, Double amount);

    Account withdraw(Long accountId, Double amount);

    Account transfer(Long fromAccountId, Long toAccountId, Double amount);

    List<TransactionResponse> getTransactions(Long accountId);

    Account getAccountByUserId(Long userId);

}