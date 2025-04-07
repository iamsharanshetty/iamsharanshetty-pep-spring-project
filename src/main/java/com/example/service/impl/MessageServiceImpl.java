package com.example.service.impl;

import com.example.entity.Message;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Message createMessage(Message message) {
        // Check if message text is empty or too long
        if (message.getMessageText() == null || message.getMessageText().trim().isEmpty() || message.getMessageText().length() > 255) {
            return null;
        }
        
        // Check if the user exists
        Optional<Account> account = accountRepository.findById(message.getPostedBy());
        if (!account.isPresent()) {
            return null;
        }
        
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessageById(int messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        return message.orElse(null);
    }

    @Override
    public int deleteMessageById(int messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        if (message.isPresent()) {
            messageRepository.deleteById(messageId);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateMessageText(int messageId, String newText) {
        // Check if message text is empty or too long
        if (newText == null || newText.trim().isEmpty() || newText.length() > 255) {
            return 0;
        }
        
        Optional<Message> messageOpt = messageRepository.findById(messageId);
        if (messageOpt.isPresent()) {
            Message message = messageOpt.get();
            message.setMessageText(newText);
            messageRepository.save(message);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Message> getMessagesByAccountId(int accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
} 