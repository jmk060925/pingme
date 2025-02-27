package com.pingme.domain.post.dto;

import java.util.ArrayList;
import java.util.List;

import com.pingme.domain.post.entity.Comment;

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
public class CommentDTO {
    
    private Long commentNo;

    private Long docNo;

    private String username;

    private Long replyLevel;

    private String txtCntn;

    public static List<CommentDTO> fromEntityList(List<Comment> commentEntityList){
        List<CommentDTO>  CommentList = new ArrayList<CommentDTO>();

        for(Comment entity : commentEntityList){
            CommentList.add(CommentDTO.builder().commentNo(entity.getCommentNo()).docNo(entity.getDocNo()).username(entity.getUsername()).replyLevel(entity.getReplyLevel()).txtCntn(entity.getTxtCntn()).build());
        }
        return CommentList;
    }

    public static CommentDTO fromEntity(Comment entity){
        return CommentDTO.builder().commentNo(entity.getCommentNo()).docNo(entity.getDocNo()).username(entity.getUsername()).replyLevel(entity.getReplyLevel()).txtCntn(entity.getTxtCntn()).build();
    }

    public Comment toEntity(CommentDTO CommentDTO){
        return Comment.builder().commentNo(CommentDTO.getCommentNo()).docNo(CommentDTO.getDocNo()).username(CommentDTO.getUsername()).replyLevel(CommentDTO.getReplyLevel()).txtCntn(CommentDTO.getTxtCntn()).build();
    }
}
