package com.pingme.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingme.domain.chat.dto.ChatMessageIdentifierDTO;
import com.pingme.domain.chat.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,ChatMessageIdentifierDTO> {

}
