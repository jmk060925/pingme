package com.pingme.domain.chat.entity;

import com.pingme.domain.chat.dto.ChatMessageIdentifierDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TB_MSG_CTNT")
@Setter
@Getter
@IdClass(ChatMessageIdentifierDTO.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SEQ", nullable = false)
    private Long seq;

    @Id
    @Column(name="MSG_ID", nullable = false)
    private Long msgId;

    @Column(name="SENDER", nullable = false)
    private String sender;

    @Column(name="CONTENT", nullable = false)
    private String content;

    @Column(name="READ_YN", nullable = false)
    private int readYn;
}
