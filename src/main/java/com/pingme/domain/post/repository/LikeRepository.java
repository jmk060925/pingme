package com.pingme.domain.post.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pingme.domain.post.entity.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    public void deleteByDocNoAndUsername(Long docNo, String username);

    public void deleteByCommentNoAndUsername(Long commentNo, String username);

    public List<Like> findByDocNo(Long docNo);
    
    public List<Like> findByCommentNo(Long commentNo);

    public Long countByDocNoAndCommentNo(Long docNo, Long commentNo);
}
