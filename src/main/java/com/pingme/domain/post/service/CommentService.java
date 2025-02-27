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

    public List<Comment> retrieveComment(CommentDTO request){
        return commentRepository.findByDocNo(request.getDocNo());
    }

    public abstract ResponseDTO createComment(CommentDTO request);

    public abstract ResponseDTO updateComment(CommentDTO request);

    public abstract ResponseDTO deleteComment(CommentDTO request);
}
