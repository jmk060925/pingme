package com.pingme.domain.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingme.domain.post.dto.CommentDTO;
import com.pingme.domain.post.entity.Comment;
import com.pingme.domain.post.repository.CommentRepository;
import com.pingme.infrastructure.dto.ResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public abstract class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<CommentDTO> retrieveComment(CommentDTO request){
        return CommentDTO.fromEntityList(commentRepository.findByDocNo(request.getDocNo()));
    }

    public  CommentDTO createComment(CommentDTO request){
        return CommentDTO.fromEntity(commentRepository.save(request.toEntity()));
    }

    public ResponseDTO updateComment(CommentDTO request){
        //commentRepository.findByDocNo(null)
        Comment comment = commentRepository.findByDocNoAndCommentNo(request.getDocNo(), request.getCommentNo()).get();
        comment.setTxtCntn(request.getTxtCntn());

        return ResponseDTO.builder().resultcode("S").msg("").build();
    }

    public ResponseDTO deleteComment(CommentDTO request){

        commentRepository.delete(request.toEntity());

        return ResponseDTO.builder().resultcode("S").msg("").build();
    }
}
