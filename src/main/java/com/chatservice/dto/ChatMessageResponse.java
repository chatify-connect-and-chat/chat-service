package com.chatservice.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageResponse
{
	private Long id;
	private Long senderId;
    private Long receiverId;
	private String content;
	private Instant createdAt;
}
