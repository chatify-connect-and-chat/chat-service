package com.chatservice.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message 
{
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  private Long senderId;
	  private Long receiverId;

	  @Column(length = 2000, nullable = false)
	  private String content;

	  private Instant createdAt = Instant.now();
	  
	  public Message(Long senderId, Long receiverId, String content) {
		    this.senderId = senderId;
		    this.receiverId = receiverId;
		    this.content = content;
		    this.createdAt = Instant.now();
	  }
}
