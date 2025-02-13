package com.pingme.domain.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pingme.domain.user.dto.LoginRequestDTO;
import com.pingme.domain.user.dto.SignupRequestDTO;
import com.pingme.domain.user.dto.UserResponseDTO;
import com.pingme.domain.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDTO registerUser(SignupRequestDTO signupRequestDTO){
        //암호화 로직 필요
        signupRequestDTO.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        return UserResponseDTO.fromEntity(userRepository.save(signupRequestDTO.toEntity()));
    }

    @Transactional
    public UserResponseDTO authenticateUser(LoginRequestDTO loginRequestDTO) {
        //로그인 검증 로직 필요
        boolean matchYn = passwordEncoder.matches(loginRequestDTO.getPassword(), userRepository.findByEmail(loginRequestDTO.getEmail()).get().getPwd());        

        log.debug(Boolean.toString(matchYn));

        if(matchYn){
            return UserResponseDTO.fromEntity(userRepository.findByEmail(loginRequestDTO.getEmail()).get());
        }else{
            return UserResponseDTO.builder().build();
        }

        
    }

    @Transactional
    public UserResponseDTO getUserProfile(String email){
        return UserResponseDTO.fromEntity(userRepository.findByEmail(email).get());
    }

    
}
