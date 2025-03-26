package com.pingme.domain.chat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingme.domain.chat.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
    public List<ChatRoom> findByTitle(String title);

    public Optional<ChatRoom> findByUsername1AndUsername2(String username1, String username2);
    
}
