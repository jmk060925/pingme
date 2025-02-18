package com.pingme.domain.user.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pingme.domain.user.dto.LoginRequestDTO;
import com.pingme.domain.user.dto.SignupRequestDTO;
import com.pingme.domain.user.dto.UserResponseDTO;
import com.pingme.domain.user.entity.User;
import com.pingme.domain.user.repository.UserRepository;
import com.pingme.infrastructure.jwt.JwtToken;
import com.pingme.infrastructure.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserResponseDTO registerUser(SignupRequestDTO signupRequestDTO){

        if(!userRepository.findByEmail(signupRequestDTO.getEmail()).isEmpty()){
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }
        List<String> roles = new ArrayList<String>();
        roles.add("USER");
        return UserResponseDTO.fromEntity(userRepository.save(signupRequestDTO.toEntity(passwordEncoder.encode(signupRequestDTO.getPassword()), roles)));
    }

    @Transactional
    public UserResponseDTO authenticateUser(LoginRequestDTO loginRequestDTO) {
        //로그인 검증 로직 필요
        boolean matchYn = passwordEncoder.matches(loginRequestDTO.getPassword(), userRepository.findByEmail(loginRequestDTO.getEmail()).get().getPwd());        

        
        List<String> roles = new ArrayList<String>();
        roles.add("USER");
        

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

    @Transactional
    public JwtToken signIn(String username, String password) {
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        return jwtToken;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(User user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(passwordEncoder.encode(user.getPwd()))
                .roles(user.getRoles().toArray(new String[0]))
                .build();
    }
    
}
