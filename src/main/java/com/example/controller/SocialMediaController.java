package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocialMediaController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

    // 1. Register new account
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        Account registeredAccount = accountService.register(account);
        if (registeredAccount == null) {
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.ok(registeredAccount);
    }

    // 2. Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        Account loggedInAccount = accountService.login(account);
        if (loggedInAccount == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(loggedInAccount);
    }

    // 3. Create new message
    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);
        if (createdMessage == null) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok(createdMessage);
    }

    // 4. Get all messages
    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    // 5. Get message by ID
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<?> getMessageById(@PathVariable int messageId) {
        Message message = messageService.getMessageById(messageId);
        if (message == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(message);
    }

    // 6. Delete message by ID
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessageById(@PathVariable int messageId) {
        int result = messageService.deleteMessageById(messageId);
        if (result == 0) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(result);
    }

    // 7. Update message text
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessageText(@PathVariable int messageId, @RequestBody Message message) {
        int result = messageService.updateMessageText(messageId, message.getMessageText());
        if (result == 0) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok(result);
    }

    // 8. Get all messages by accountId
    @GetMapping("/accounts/{accountId}/messages")
    public List<Message> getMessagesByAccountId(@PathVariable int accountId) {
        return messageService.getMessagesByAccountId(accountId);
    }
}
