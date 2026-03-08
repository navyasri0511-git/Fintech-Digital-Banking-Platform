package com.fintech.bankingplatform.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.bankingplatform.dto.TransactionResponse;
import com.fintech.bankingplatform.exception.ResourceNotFoundException;
import com.fintech.bankingplatform.model.Account;
import com.fintech.bankingplatform.model.Transaction;
import com.fintech.bankingplatform.model.User;
import com.fintech.bankingplatform.repository.AccountRepository;
import com.fintech.bankingplatform.repository.TransactionRepository;
import com.fintech.bankingplatform.repository.UserRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // CREATE ACCOUNT
    @Override
    public Account createAccount(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Account account = new Account();
        account.setUser(user);
        account.setBalance(0.0);

        return accountRepository.save(account);
    }

    // DEPOSIT
    @Override
    public Account deposit(Long accountId, Double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType("DEPOSIT");
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);

        return account;
    }

    // WITHDRAW
    @Override
    public Account withdraw(Long accountId, Double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient Balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType("WITHDRAW");
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);

        return account;
    }

    // TRANSFER
    @Override
    public Account transfer(Long fromAccountId, Long toAccountId, Double amount) {

        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient Balance");
        }

        // update balances
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // sender transaction
        Transaction senderTransaction = new Transaction();
        senderTransaction.setAccount(fromAccount);
        senderTransaction.setAmount(amount);
        senderTransaction.setType("TRANSFER_OUT");
        senderTransaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(senderTransaction);

        // receiver transaction
        Transaction receiverTransaction = new Transaction();
        receiverTransaction.setAccount(toAccount);
        receiverTransaction.setAmount(amount);
        receiverTransaction.setType("TRANSFER_IN");
        receiverTransaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(receiverTransaction);

        return fromAccount;
    }

    // GET TRANSACTIONS
    @Override
    public List<TransactionResponse> getTransactions(Long accountId) {

        List<Transaction> transactions =
                transactionRepository.findByAccountId(accountId);

        return transactions.stream()
                .map(t -> new TransactionResponse(
                        t.getId(),
                        t.getType(),
                        t.getAmount(),
                        t.getTimestamp()))
                .toList();
    }

    // GET ACCOUNT BY USER ID
    @Override
    public Account getAccountByUserId(Long userId) {

        return accountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}