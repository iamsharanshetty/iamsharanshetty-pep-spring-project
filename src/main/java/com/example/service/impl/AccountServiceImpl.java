package com.example.service.impl;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account register(Account account) {
        // Check if username already exists
        Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
        if (existingAccount.isPresent()) {
            return null;
        }
        return accountRepository.save(account);
    }

    @Override
    public Account login(Account account) {
        Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
        if (existingAccount.isPresent() && existingAccount.get().getPassword().equals(account.getPassword())) {
            return existingAccount.get();
        }
        return null;
    }
} 