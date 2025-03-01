package com.pingme.domain.user.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    //private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserResponseDTO registerUser(SignupRequestDTO signupRequestDTO){

        if(!userRepository.findByUsername(signupRequestDTO.getUsername()).isEmpty()){
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }
        List<String> roles = new ArrayList<String>();
        roles.add("USER");
        return UserResponseDTO.fromEntity(userRepository.save(signupRequestDTO.toEntity(passwordEncoder.encode(signupRequestDTO.getPassword()), roles)));
    }


    @Transactional
    public UserResponseDTO getUserProfile(String username){

        Optional<User> user = userRepository.findByUsername(username);

        log.info("username : " + username);

        if(user.isPresent()){
            return UserResponseDTO.fromEntity(user.get());    
        }else{
            return new UserResponseDTO();
        }
            

    }

    @Transactional
    public JwtToken signIn(String username, String password) {
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        
         log.info(passwordEncoder.encode(password));
         boolean matchYn = passwordEncoder.matches(password, userRepository.findByUsername(username).get().getPassword()); 
         log.info(Boolean.toString(matchYn));       

        try{
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

            log.info(passwordEncoder.encode(password));
            // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
            // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            //Authentication authentication = authenticationManager.authenticate(authenticationToken);
    
            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
    
            return jwtToken;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
        
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("log test");        return userRepository.findByUsername(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));

    }

    private UserDetails createUserDetails(User user) {

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0]))
                //.authorities(user.getAuthorities())
                
                .build();
    }
    
}
