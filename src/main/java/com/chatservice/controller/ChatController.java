package com.chatservice.controller;

import com.chatservice.dto.ChatMessage;
import com.chatservice.dto.ChatMessageResponse;
import com.chatservice.entity.Message;
import com.chatservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // allow your Vite app
@RequestMapping // base left empty so paths below are absolute
public class ChatController {

  @Autowired
  private MessageService messageService;

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/chat.private")
  public void processMessage(ChatMessage message) {
    // persist
    Message saved = messageService.save(message);

    ChatMessageResponse payload = new ChatMessageResponse(
        saved.getId(),
        saved.getSenderId(),
        saved.getReceiverId(),
        saved.getContent(),
        saved.getCreatedAt()
    );

    // deliver to receiver's personal queue
    messagingTemplate.convertAndSendToUser(
        String.valueOf(saved.getReceiverId()),
        "/queue/messages",
        payload
    );

    // echo back to sender so their UI updates instantly
    messagingTemplate.convertAndSendToUser(
        String.valueOf(saved.getSenderId()),
        "/queue/messages",
        payload
    );
  }

  // REST: load conversation history (query-string version your code already had)
  @GetMapping("/messages/history")
  public List<Message> getHistory(@RequestParam("user1") Long user1,
                                  @RequestParam("user2") Long user2) {
    return messageService.getConversation(user1, user2);
  }

  // REST: load conversation history (path-variable version to match your frontend call)
  // Now your existing fetch to /messages/3/4 works as well.
  @GetMapping("/messages/{user1}/{user2}")
  public List<Message> getHistoryPath(@PathVariable Long user1,
                                      @PathVariable Long user2) {
    return messageService.getConversation(user1, user2);
  }
}
