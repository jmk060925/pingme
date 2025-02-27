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
@Table(name="TB_LIKE")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="LIKE_ID", nullable = false)
    private Long likeId;

    @Column(name="TARGET_TYPE", nullable = false)
    private String targetType;

    @Column(name="DOC_NO")
    private Long docNo;

    @Column(name="COMMENT_NO")
    private Long commentNo;

    @Column(name="USERNAME", nullable = false)
    private String username;

}
