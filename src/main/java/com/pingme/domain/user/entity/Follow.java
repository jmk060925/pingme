package com.pingme.domain.user.entity;

import com.pingme.domain.user.dto.FollowIdentifierDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="TB_FOLLOW")
@Getter
@Setter
@IdClass(FollowIdentifierDTO.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Follow{ 
    
    @Id
    @Column(name="USER_ID", nullable = false)
    private String userId;

    @Id
    @Column(name="FOLLOWER_ID", nullable = false)
    private String followerId;

    @Column(name="STATUS", nullable = false)
    private String status;

}
