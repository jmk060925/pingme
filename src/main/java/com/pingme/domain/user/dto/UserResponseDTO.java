package com.pingme.domain.user.dto;

public class UserResponseDTO {
    private Long id;  // 사용자 ID

    private String email;  // 이메일

    private String username;  // 닉네임

    private String profileImageUrl;  // 프로필 이미지 URL

    private String userIntroduce;  // 한 줄 소개

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getUserIntroduce() {
        return userIntroduce;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setUserIntroduce(String userIntroduce) {
        this.userIntroduce = userIntroduce;
    }


    // 기본 생성자 & Getter, Setter
    
}
