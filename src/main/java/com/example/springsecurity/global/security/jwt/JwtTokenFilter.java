package com.example.springsecurity.global.security.jwt;

import com.example.springsecurity.domain.user.exception.ExpiredTokenException;
import com.example.springsecurity.domain.user.exception.InvalidTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain) throws IOException, ServletException {

        String token = jwtTokenProvider.resolveToken(request);

        if (token == null) { // 토큰이 있으면
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (ExpiredTokenException | InvalidTokenException e) {
            request.setAttribute("jwtError", e);
        }

        filterChain.doFilter(request, response);
    }
}
