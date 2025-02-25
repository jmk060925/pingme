package com.pingme.domain.user.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.SerializableBlobProxy;

import com.pingme.domain.user.entity.Follow;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FollowDTO{


    private String userId;

    private String followerId;

    private String status;


    public static List<FollowDTO> fromEntityList(List<Follow> follow){

        List<FollowDTO> result = new ArrayList<FollowDTO>();

        for(Follow item : follow){
            result.add(FollowDTO.builder().userId(item.getUserId()).followerId(item.getFollowerId()).status(item.getStatus()).build());
        }

        return result;
    }

    public static FollowDTO fromEntity(Follow follow){
        return FollowDTO.builder().userId(follow.getUserId()).followerId(follow.getFollowerId()).status(follow.getStatus()).build();
    }
    
    public Follow toEntity(){
        return Follow.builder().userId(this.userId).followerId(this.followerId).status(this.status).build();
    }
}
