package com.pingme.domain.chat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingme.domain.chat.dto.ChatMessageIdentifierDTO;
import com.pingme.domain.chat.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,ChatMessageIdentifierDTO> {

    public Optional<ChatMessage> findFirstByMsgIdOrderBySeqDesc(Long msgId);
    
    public Long countByMsgIdAndSenderAndReadYn(Long msgId, String sender, int readYn);

    public List<ChatMessage> findByMsgIdOrderBySeqDesc(Long msgId);


}
