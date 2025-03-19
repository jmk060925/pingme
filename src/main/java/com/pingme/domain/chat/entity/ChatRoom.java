package com.pingme.domain.chat.entity;
import com.pingme.domain.chat.dto.ChatRoomIdentifierDTO;

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
@Table(name="TB_PRIVATE_MSG")
@Setter
@Getter
@IdClass(ChatRoomIdentifierDTO.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MSG_ID", nullable = false)
    private Long msgId;

    @Id
    @Column(name="USERNAME", nullable = false)
    private String username;

}
