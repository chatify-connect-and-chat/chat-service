package com.chatservice.repository;

import com.chatservice.entity.Message;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

  @Query("""
         SELECT m FROM Message m
         WHERE (m.senderId = :u1 AND m.receiverId = :u2)
            OR (m.senderId = :u2 AND m.receiverId = :u1)
         ORDER BY m.createdAt ASC
         """)
  List<Message> findConversation(@Param("u1") Long user1, @Param("u2") Long user2);
}
