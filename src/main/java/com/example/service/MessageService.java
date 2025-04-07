package com.example.service;

import com.example.entity.Message;

import java.util.List;

public interface MessageService {
    Message createMessage(Message message);
    List<Message> getAllMessages();
    Message getMessageById(int messageId);
    int deleteMessageById(int messageId);
    int updateMessageText(int messageId, String newText);
    List<Message> getMessagesByAccountId(int accountId);
}
