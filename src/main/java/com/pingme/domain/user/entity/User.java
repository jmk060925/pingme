package com.pingme.domain.user.entity;

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

@Entity
@Table(name="TB_USER")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID", nullable = false)
    private Long Id;

    @Column(name="EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name="PWD", nullable = false)
    private String pwd;

    @Column(name="USER_NAME", unique = true, nullable = false)
    private String userName;

    @Column(name="USER_PIC")
    private String userPic;

    @Column(name="USER_INTRODUCE")
    private String userIntroduce;
}
