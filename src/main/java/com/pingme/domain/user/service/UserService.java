package com.pingme.domain.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.pingme.domain.user.dto.LoginRequestDTO;
import com.pingme.domain.user.dto.SignupRequestDTO;
import com.pingme.domain.user.dto.UserResponseDTO;
import com.pingme.domain.user.repository.UserRepository;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserResponseDTO registerUser(SignupRequestDTO signupRequestDTO){
        return UserResponseDTO.fromEntity(userRepository.save(signupRequestDTO.toEntity()));
    }

    @Transactional
    public UserResponseDTO authenticateUser(LoginRequestDTO loginRequestDTO){
        //로그인 검증 로직 필요
        return UserResponseDTO.fromEntity(userRepository.findByEmail(loginRequestDTO.getEmail()).get());
    }


    @Transactional
    public UserResponseDTO getUserProfile(String email){
        return UserResponseDTO.fromEntity(userRepository.findByEmail(email).get());
    }

    
}
