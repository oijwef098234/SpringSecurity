package com.example.springsecurity.global.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, // 여기에 넘긴 에러 넣어서 처리
                         HttpServletResponse response, // 응답할 틀? 같은 느낌임
                         AuthenticationException authException) throws IOException { // 인증이 왜 실패했는지에 대한 정보

        Object err = request.getAttribute("jwtError"); // filter에서 넘긴 에러 여기서 처리함

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 로 설정
        response.setContentType("application/json;charset=UTF-8"); // 응답 형식 json으로 설정

        if (err != null) { // 만약 있다면 그 에러 메시지를 표시 ex) 만료되었을 경우
            response.getWriter().write("{\"message\": \"" + err.toString() + "\"}");
        } else { // 없다면 그냥 인증되지 않았다고 띄우기
            response.getWriter().write("{\"message\": \"UNAUTHORIZED\"}");
        }
    }
}
