package com.fintech.bankingplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.bankingplatform.dto.TransactionResponse;
import com.fintech.bankingplatform.dto.TransferRequest;
import com.fintech.bankingplatform.model.Account;
import com.fintech.bankingplatform.service.AccountService;
import com.fintech.bankingplatform.dto.DepositRequest;
import com.fintech.bankingplatform.dto.WithdrawRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/{userId}")
    public Account createAccount(@PathVariable Long userId) {
        return accountService.createAccount(userId);
    }

    @PostMapping("/deposit")
    public Account deposit(@RequestBody DepositRequest request) {
        return accountService.deposit(request.getAccountId(), request.getAmount());
    }

    @PostMapping("/withdraw")
    public Account withdraw(@RequestBody WithdrawRequest request) {
        return accountService.withdraw(request.getAccountId(), request.getAmount());
    }

    @GetMapping("/{accountId}/transactions")
    public List<TransactionResponse> getTransactions(@PathVariable Long accountId) {
        return accountService.getTransactions(accountId);
    }

    @PostMapping("/transfer")
    public Account transfer(@RequestBody TransferRequest request) {

        return accountService.transfer(
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount());
    }
    @GetMapping("/user/{userId}")
    public Account getAccountByUser(@PathVariable Long userId){

        return accountService.getAccountByUserId(userId);

    }
}