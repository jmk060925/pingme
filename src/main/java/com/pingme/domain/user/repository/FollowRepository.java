package com.pingme.domain.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pingme.domain.user.dto.FollowDTO;
import com.pingme.domain.user.entity.Follow;

@Repository
public interface FollowRepository  extends JpaRepository<Follow,FollowDTO> {

    Optional<Follow> findByUserId(String userId);

    //List<Follow> findAllByFollowerId(String followerId);

    Optional<Follow> findByUserIdAndStatus(String userId, String status);

    FollowDTO save(FollowDTO request);

    void deleteByUserIdAndFollowerId(String userId, String followerId);


    List<Follow> findAllByUserIdAndStatus(String userId, String status);

    List<Follow> findAllByFollowerIdAndStatus(String followerId, String status);


}
