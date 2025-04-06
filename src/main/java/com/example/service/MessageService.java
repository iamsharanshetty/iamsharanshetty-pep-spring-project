package com.example.service;

import com.example.entity.Message;
import com.example.exception.InvalidInputException;
import com.example.exception.MessageNotFoundException;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;
    
    public Message createMessage(Message message) {
        if (message.getMessageText() == null || message.getMessageText().trim().isEmpty()) {
            throw new InvalidInputException("Message text cannot be blank");
        }
        if (message.getMessageText().length() > 255) {
            throw new InvalidInputException("Message text cannot exceed 255 characters");
        }
        return messageRepository.save(message);
    }
    
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
    
    public Message getMessageById(Integer messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }
    
    public void deleteMessage(Integer messageId) {
        messageRepository.deleteById(messageId);
    }
    
    public Message updateMessage(Integer messageId, String newMessageText) {
        Message message = messageRepository.findById(messageId).orElse(null);
        if (message == null) {
            throw new MessageNotFoundException("Message not found");
        }
        if (newMessageText == null || newMessageText.trim().isEmpty()) {
            throw new InvalidInputException("Message text cannot be blank");
        }
        if (newMessageText.length() > 255) {
            throw new InvalidInputException("Message text cannot exceed 255 characters");
        }
        message.setMessageText(newMessageText);
        return messageRepository.save(message);
    }
    
    public List<Message> getMessagesByAccountId(Integer accountId) {
        return messageRepository.findAll().stream()
                .filter(message -> message.getPostedBy().equals(accountId))
                .collect(Collectors.toList());
    }
}
