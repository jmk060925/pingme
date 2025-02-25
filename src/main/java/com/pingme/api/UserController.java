package com.pingme.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pingme.domain.user.dto.FollowDTO;
import com.pingme.domain.user.dto.LoginRequestDTO;
import com.pingme.domain.user.dto.SignupRequestDTO;
import com.pingme.domain.user.dto.UserResponseDTO;
import com.pingme.domain.user.service.FollowService;
import com.pingme.domain.user.service.UserService;
import com.pingme.infrastructure.dto.ResponseDTO;
import com.pingme.infrastructure.jwt.JwtToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final FollowService followService;

    @PostMapping("/signup")
    public UserResponseDTO signup(@RequestBody SignupRequestDTO signupRequestDTO){
        return userService.registerUser(signupRequestDTO);
    }

    @PostMapping("/login")
    public JwtToken login(@RequestBody LoginRequestDTO loginRequestDTO){
        return userService.signIn(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
    }

    @GetMapping("/info/{email}")
    public UserResponseDTO getUser(@PathVariable String email) {
        return userService.getUserProfile(email);
    }

    //팔로워 조회(목록, 요청목록)
    @PostMapping("/follow/following")
    public List<FollowDTO> retrieveFollowing(@RequestBody FollowDTO followDTO){
        return followService.retrieveFollowingList(followDTO);
    }

    //팔로우 받은 요청 조회
    @PostMapping("/follow/followed")
    public List<FollowDTO> retrieveFollowed(@RequestBody FollowDTO followDTO){
        return followService.retrieveFollowedList(followDTO);
    }

    //팔로우 신청
    @PostMapping("/follow/request")
    public FollowDTO requestFollow(@RequestBody FollowDTO followDTO){
        return followService.requestFollow(followDTO);
    }
    
    //팔로우 수락
    @PostMapping("/follow/accept")
    public ResponseDTO acceptFollow(@RequestBody FollowDTO followDTO) throws Exception{
        return followService.acceptFollow(followDTO);
    }

    //팔로우 거절
    @PostMapping("/follow/deny")
    public ResponseDTO denyFollow(@RequestBody FollowDTO followDTO){
        return followService.denyFollow(followDTO);
    }


    //언팔로우
    @PostMapping("/follow/delete")
    public ResponseDTO deleteFollow(@RequestBody FollowDTO followDTO){
        return followService.deleteFollow(followDTO);
    }


    //사용자 삭제

    //로그아웃
}