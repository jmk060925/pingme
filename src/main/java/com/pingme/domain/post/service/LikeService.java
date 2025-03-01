package com.pingme.domain.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingme.domain.post.dto.LikeDTO;
import com.pingme.domain.post.repository.LikeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;


    public long addLike(LikeDTO request){

        likeRepository.save(request.toEntity());

        return countLike(request);
    }

    public long cancelLike(LikeDTO request){

        likeRepository.deleteById(request.getLikeId());

        return countLike(request);
    }

    private long countLike(LikeDTO request){
        return likeRepository.countByDocNoAndCommentNo(request.getDocNo(), request.getCommentNo());
    }
}
