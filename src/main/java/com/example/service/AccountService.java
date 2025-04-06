package com.example.service;

import com.example.entity.Account;
import com.example.exception.AccountNotFoundException;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.InvalidInputException;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    public Account registerAccount(Account account) {
        if (account.getUsername() == null || account.getUsername().trim().isEmpty()) {
            throw new InvalidInputException("Username cannot be blank");
        }
        if (account.getPassword() == null || account.getPassword().length() < 4) {
            throw new InvalidInputException("Password must be at least 4 characters long");
        }
        
        // Check if username already exists
        List<Account> existingAccounts = accountRepository.findAll();
        for (Account existingAccount : existingAccounts) {
            if (existingAccount.getUsername().equals(account.getUsername())) {
                throw new DuplicateUsernameException("Username already exists");
            }
        }
        
        return accountRepository.save(account);
    }
    
    public Account login(Account account) {
        List<Account> accounts = accountRepository.findAll();
        for (Account existingAccount : accounts) {
            if (existingAccount.getUsername().equals(account.getUsername()) && 
                existingAccount.getPassword().equals(account.getPassword())) {
                return existingAccount;
            }
        }
        throw new AccountNotFoundException("Invalid username or password");
    }
}
