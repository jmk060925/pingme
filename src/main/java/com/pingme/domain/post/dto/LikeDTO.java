package com.pingme.domain.post.dto;


import com.pingme.domain.post.entity.Like;
import com.pingme.domain.post.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {
    private Long likeId;

    private String targetType;

    private Long docNo;

    private Long commentNo;

    private String username;

     public static LikeDTO fromEntity(Like entity){
        return LikeDTO.builder().likeId(entity.getLikeId()).docNo(entity.getDocNo()).commentNo(entity.getCommentNo()).username(entity.getUsername()).build();
    }

    public Like toEntity(LikeDTO postDto){
        return Like.builder().likeId(postDto.getLikeId()).docNo(postDto.getDocNo()).commentNo(postDto.getCommentNo()).username(postDto.getUsername()).build();
    }

}
