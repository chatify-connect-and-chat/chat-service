// src/main/java/com/chatservice/service/MessageService.java
package com.chatservice.service;

import com.chatservice.dto.ChatMessage;
import com.chatservice.entity.Message;
import com.chatservice.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

  private final MessageRepository repo;

  public MessageService(MessageRepository repo) {
    this.repo = repo;
  }

  public Message save(ChatMessage dto) {
    Message m = new Message(dto.getSenderId(), dto.getReceiverId(), dto.getContent());
    return repo.save(m);
  }

  public List<Message> getConversation(Long u1, Long u2) {
    return repo.findConversation(u1, u2);
  }
}
