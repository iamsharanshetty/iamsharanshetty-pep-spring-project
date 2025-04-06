package com.example.exception;

public class MessageNotFoundException extends SocialMediaException {
    public MessageNotFoundException(String message) {
        super(message);
    }
} 