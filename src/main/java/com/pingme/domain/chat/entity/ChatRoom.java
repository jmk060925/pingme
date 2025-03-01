package com.pingme.domain.chat.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MSG_ID", nullable = false)
    private Long msgId;

    @Column(name="USERNAME1", nullable = false)
    private String username1;

    @Column(name="USERNAME2", nullable = false)
    private String username2;
}
