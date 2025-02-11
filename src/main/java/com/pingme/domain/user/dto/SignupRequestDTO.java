package com.pingme.domain.user.dto;

import com.pingme.domain.user.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class SignupRequestDTO {
    @NotBlank
    @Email
    private String email;  // 이메일 (유니크)

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;  // 비밀번호 (암호화 필요)

    @NotBlank
    @Size(min = 2, max = 20)
    private String username;  // 닉네임 (유니크)

    private String profileImageUrl;  // 프로필 이미지 (선택)

    private String userIntroduce;  // 한 줄 소개 (선택)


    // Entity → DTO 변환 메서드
    public static SignupRequestDTO fromEntity(User user) {
        return new SignupRequestDTO(user.getEmail(), user.getPwd(), user.getUserName(), user.getUserPic(), user.getUserIntroduce());
    }

    public User toEntity(){
        return User.builder().email(this.email)
        .pwd(this.password)
        .userName(this.username)
        .userPic(this.profileImageUrl)
        .userIntroduce(this.getUserIntroduce())
        .build();
    }
    
}

