package com.pingme.domain.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingme.domain.user.dto.FollowDTO;
import com.pingme.domain.user.repository.FollowRepository;
import com.pingme.infrastructure.dto.ResponseDTO;

import jakarta.transaction.Transactional;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;


    //팔로워 리스트 조회
    @Transactional
    public List<FollowDTO> retrieveFollowingList(FollowDTO request){
        return FollowDTO.fromEntityList(followRepository.findAllByUserIdAndStatus(request.getUserId(), request.getStatus()));
    }

    @Transactional
    public List<FollowDTO> retrieveFollowedList(FollowDTO request){
        return FollowDTO.fromEntityList(followRepository.findAllByFollowerIdAndStatus(request.getFollowerId(), request.getStatus()));
    }

    @Transactional
    public FollowDTO requestFollow(FollowDTO request){
        return followRepository.save(request);
    }

    @Transactional
    public ResponseDTO acceptFollow(FollowDTO request){
        
        String status = request.getStatus();

        if("Requested".equals(status)){
            String requesterId = request.getFollowerId();
            String followerId = request.getUserId();

            followRepository.save(FollowDTO.builder().userId(requesterId).followerId(followerId).status("Accepted").build());

            request.setStatus("Accepted");
            followRepository.save(request);
        }else{
            return ResponseDTO.builder().resultcode("F").msg("Error Occured. Please Contact Administrator.").build();
        }

        return ResponseDTO.builder().resultcode("S").build();
    }

    @Transactional
    public ResponseDTO denyFollow(FollowDTO request){
       
        followRepository.deleteByUserIdAndFollowerId(request.getUserId(), request.getFollowerId());

        return ResponseDTO.builder().resultcode("S").build();
    }

    @Transactional
    public ResponseDTO deleteFollow(FollowDTO request){
       
        followRepository.deleteByUserIdAndFollowerId(request.getUserId(), request.getFollowerId());
        followRepository.deleteByUserIdAndFollowerId(request.getFollowerId(), request.getUserId());

        return ResponseDTO.builder().resultcode("S").build();
    }
}
