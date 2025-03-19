package com.pingme.infrastructure.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pingme.infrastructure.jwt.JwtTokenProvider;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;


    // @Override
    // public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)  throws IOException, ServletException {
    //     // 1. Request Header에서 JWT 토큰 추출
    //     String token = resolveToken((HttpServletRequest) request);

    //     // 2. validateToken으로 토큰 유효성 검사
    //     if (token != null && jwtTokenProvider.validateToken(token)) {
    //         // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장
    //         Authentication authentication = jwtTokenProvider.getAuthentication(token);
    //         SecurityContextHolder.getContext().setAuthentication(authentication);
    //     }
    //     chain.doFilter(request, response);
    // }

    // Request Header에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override   //아예 필터를 거치지 않게하는 방법 중 하나.
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.equals("/users/login") || path.equals("/users/register") || path.equals("/ws/chat");
    }
    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 1. Request Header에서 JWT 토큰 추출
        String token = resolveToken((HttpServletRequest) request);

        log.debug(token);

        // 2. validateToken으로 토큰 유효성 검사
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
        //throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
    }
}
