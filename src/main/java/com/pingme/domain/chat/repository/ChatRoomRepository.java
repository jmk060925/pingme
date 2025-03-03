package com.pingme.domain.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingme.domain.chat.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
    public List<ChatRoom> findByUsername1OrUsername2(String username1, String username2);

    
}
