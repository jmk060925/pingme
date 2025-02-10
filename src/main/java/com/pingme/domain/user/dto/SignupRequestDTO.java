package com.pingme.domain.user.dto;

public class SignupRequestDTO {
    //@NotBlank
    //@Email
    private String email;  // 이메일 (유니크)

    //@NotBlank
    //@Size(min = 8, max = 20)
    private String password;  // 비밀번호 (암호화 필요)

//@NotBlank
    //@Size(min = 2, max = 20)
    private String username;  // 닉네임 (유니크)

    private String profileImageUrl;  // 프로필 이미지 (선택)

    private String userIntroduce;  // 한 줄 소개 (선택)

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getUserIntroduce() {
        return userIntroduce;
    }

    // 기본 생성자 & Getter, Setter

    
}

