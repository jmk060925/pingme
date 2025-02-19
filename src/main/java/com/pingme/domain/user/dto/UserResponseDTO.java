package com.pingme.domain.user.dto;

import com.pingme.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;  // 사용자 ID

    private String email;  // 이메일

    private String username;  // 닉네임

    private String profileImageUrl;  // 프로필 이미지 URL

    private String userIntroduce;  // 한 줄 소개

    public static UserResponseDTO fromEntity(User user){
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getNickname(), user.getUserPic(), user.getUserIntroduce());
    }
    
}
