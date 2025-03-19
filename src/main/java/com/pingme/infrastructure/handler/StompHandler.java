package com.pingme.infrastructure.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.pingme.infrastructure.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class StompHandler implements ChannelInterceptor {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("test1");
        //StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        log.info("test2");
        if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
            log.info("test3");
            String token = accessor.getFirstNativeHeader("Authorization");
            log.info("test4");
            if (token == null || !tokenProvider.validateToken(token)) {
                log.info("test5");
                throw new AuthenticationCredentialsNotFoundException("Invalid JWT Token");
            }
            log.info("test6");
            Authentication authentication = tokenProvider.getAuthentication(token);
            log.info("test7");
            accessor.setUser(authentication);
        }
        if (accessor != null && StompCommand.DISCONNECT.equals(accessor.getCommand())) {
            log.info("test8");
        }
        return message;
    }
}
