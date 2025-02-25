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
@Table(name="TB_BOARD")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DOC_NO", nullable = false)
    private Long docNo;

    @Column(name="USERNAME")
    private String username;

    @Column(name="TXT_CNTN")
    private String txtCntn;

    @Column(name="IMG_CNTN")
    private String imgCntn;

    @Column(name="VIDEO_CNTN")
    private String videoCntn;

    @Column(name="TAG")
    private String tag;
}
