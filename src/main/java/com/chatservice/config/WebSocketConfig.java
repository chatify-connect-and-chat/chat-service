// src/main/java/com/chatservice/config/WebSocketConfig.java
package com.chatservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws")
        // IMPORTANT: allow Vite dev origin (and SockJS XHR fallbacks)
        .setAllowedOriginPatterns("http://localhost:5173")
        .withSockJS();
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // client sends to /app/...
    registry.setApplicationDestinationPrefixes("/app");
    // enable simple broker for /queue (user-specific) and /topic (broadcast)
    registry.enableSimpleBroker("/queue", "/topic");
    // user destinations like /user/{userId}/queue/messages
    registry.setUserDestinationPrefix("/user");
  }
}
