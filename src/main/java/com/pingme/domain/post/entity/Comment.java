package com.pingme.domain.post.entity;


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
@Table(name="TB_COMMENT")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMMENT_NO", nullable = false)
    private Long commentNo;

    @Column(name="DOC_NO", nullable = false)
    private Long docNo;

    @Column(name="USERNAME", nullable = false)
    private String username;

    @Column(name="REPLY_LEVEL")
    private Long replyLevel;

    @Column(name="TXT_CNTN", nullable = false)
    private String txtCntn;
}
