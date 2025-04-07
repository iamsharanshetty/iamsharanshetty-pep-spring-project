package com.example.service;

import com.example.entity.Account;

public interface AccountService {
    Account register(Account account);
    Account login(Account account);
}
