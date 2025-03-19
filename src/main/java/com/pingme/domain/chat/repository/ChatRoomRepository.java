package com.pingme.domain.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingme.domain.chat.dto.ChatRoomIdentifierDTO;
import com.pingme.domain.chat.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,ChatRoomIdentifierDTO> {
    public List<ChatRoom> findByUsername(String username);

    public List<ChatRoom> findByMsgId(Long msgId);
    
}
