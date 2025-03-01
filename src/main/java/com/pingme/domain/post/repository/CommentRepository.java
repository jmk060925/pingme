package com.pingme.domain.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingme.domain.post.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    public List<Comment> findByDocNo(Long docNo);

    public Optional<Comment> findByDocNoAndCommentNo(Long docNo, Long commentNo);
}
