package com.example.exception;

public class AccountNotFoundException extends SocialMediaException {
    public AccountNotFoundException(String message) {
        super(message);
    }
} 