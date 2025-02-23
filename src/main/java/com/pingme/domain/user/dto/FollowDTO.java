package com.pingme.domain.user.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pingme.domain.user.entity.Follow;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowDTO  implements Serializable {

    @Column(name="USER_ID")
    private String userId;

    @Column(name="FOLLOWER_ID")
    private String followerId;

    @Column(name="STATUS")
    private String status;

    public static List<FollowDTO> fromEntityList(List<Follow> follow){

        List<FollowDTO> result = new ArrayList<FollowDTO>();

        for(Follow item : follow){
            result.add(FollowDTO.builder().userId(item.getUserId()).followerId(item.getFollowerId()).status(item.getStatus()).build());
        }

        return result;
    }
}
