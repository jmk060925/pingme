package com.pingme.domain.user;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.pingme.domain.user.dto.UserResponseDTO;
import com.pingme.domain.user.service.FollowService;
import com.pingme.domain.user.service.UserService;

//@WebMvcTest(UserController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //@MockBean  // Deprecated from Springboot v3.4.0
    @MockitoBean // 가짜 서비스 주입
    private UserService userService;

    @MockitoBean // 가짜 서비스 주입
    private FollowService followServcie;

    @Test    
    public void testGetUser() throws Exception {
        // given
        String username = "test12@test.com";
        UserResponseDTO mockUser = new UserResponseDTO(16L, username, "Test12", "1", "1");
        when(userService.getUserProfile(username)).thenReturn(mockUser);

        // when & then
       mockMvc.perform(get("/users/info/"+username).contentType("application/json")).andExpect(status().isOk());
        
    }
}
