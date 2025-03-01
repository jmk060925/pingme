package com.pingme.domain.post.dto;


import com.pingme.domain.post.entity.Like;

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
        return LikeDTO.builder().likeId(entity.getLikeId()).targetType(entity.getTargetType()).docNo(entity.getDocNo()).commentNo(entity.getCommentNo()).username(entity.getUsername()).build();
    }

    public Like toEntity(){
        return Like.builder().likeId(this.likeId).targetType(this.targetType).docNo(this.docNo).commentNo(this.commentNo).username(this.username).build();
    }

}
