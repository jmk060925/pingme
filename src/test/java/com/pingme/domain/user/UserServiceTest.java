package com.pingme.domain.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.pingme.domain.user.dto.UserResponseDTO;
import com.pingme.domain.user.entity.User;
import com.pingme.domain.user.repository.UserRepository;
import com.pingme.domain.user.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @Mock // 가짜 객체 생성
    private UserRepository userRepository;

    @InjectMocks // Mock 객체를 UserService에 주입
    private UserService userService;

    @Test
    public void testFindUserById() {
        // given (테스트 준비)
        String username = "test11@test.com";

        User mockUser = User.builder().Id(16L).username(username).nickname("Test11").userPic("1").userIntroduce("1").build();
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        // when (실행)
        UserResponseDTO result = userService.getUserProfile(username);

        // then (검증)
        assertNotNull(result);
        assertEquals("Test11", result.getNickname());
    }
}
