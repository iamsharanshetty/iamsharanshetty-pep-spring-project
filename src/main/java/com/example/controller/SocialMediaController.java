package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.AccountNotFoundException;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.InvalidInputException;
import com.example.exception.MessageNotFoundException;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageService messageService;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody Account account) {
        try {
            Account registeredAccount = accountService.registerAccount(account);
            return ResponseEntity.ok(registeredAccount);
        } catch (InvalidInputException e) {
            return ResponseEntity.badRequest().build();
        } catch (DuplicateUsernameException e) {
            return ResponseEntity.status(409).build();
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        try {
            Account loggedInAccount = accountService.login(account);
            return ResponseEntity.ok(loggedInAccount);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(401).build();
        }
    }
    
    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@RequestBody Message message) {
        try {
            Message createdMessage = messageService.createMessage(message);
            return ResponseEntity.ok(createdMessage);
        } catch (InvalidInputException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }
    
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<?> getMessageById(@PathVariable Integer messageId) {
        Message message = messageService.getMessageById(messageId);
        if (message == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(message);
    }
    
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable Integer messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.ok(1);
    }
    
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessage(@PathVariable Integer messageId, @RequestBody Map<String, String> body) {
        try {
            String newMessageText = body.get("messageText");
            Message updatedMessage = messageService.updateMessage(messageId, newMessageText);
            return ResponseEntity.ok(1);
        } catch (InvalidInputException | MessageNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccountId(@PathVariable Integer accountId) {
        return ResponseEntity.ok(messageService.getMessagesByAccountId(accountId));
    }
}
