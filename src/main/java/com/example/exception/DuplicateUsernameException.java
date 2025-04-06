package com.example.exception;

public class DuplicateUsernameException extends SocialMediaException {
    public DuplicateUsernameException(String message) {
        super(message);
    }
} 