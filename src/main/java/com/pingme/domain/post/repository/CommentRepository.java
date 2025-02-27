package com.pingme.domain.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingme.domain.post.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    public List<Comment> findByDocNo(Long docNo);
}
