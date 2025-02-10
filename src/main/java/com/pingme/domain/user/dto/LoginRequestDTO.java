package com.pingme.domain.user.dto;

public class LoginRequestDTO {
    //@NotBlank
    //@Email
    private String email;  // 로그인 ID (이메일)

    //@NotBlank
    private String password;  // 비밀번호 (암호화된 상태로 비교)

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
}
