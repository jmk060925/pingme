package com.pingme.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pingme.domain.user.dto.LoginRequestDTO;
import com.pingme.domain.user.dto.SignupRequestDTO;
import com.pingme.domain.user.dto.UserResponseDTO;
import com.pingme.domain.user.service.UserService;
import com.pingme.infrastructure.jwt.JwtToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponseDTO signup(@RequestBody SignupRequestDTO signupRequestDTO){
        return userService.registerUser(signupRequestDTO);
    }

    @PostMapping("/login")
    public JwtToken login(@RequestBody LoginRequestDTO loginRequestDTO){
        log.debug("aa");
        return userService.signIn(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
    }

    @GetMapping("/info/{email}")
    public UserResponseDTO getUser(@PathVariable String email) {
        return userService.getUserProfile(email);
    }
}