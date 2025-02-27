package com.min.koreaareaboard.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 인증되지 않은 사용자가 보호된 리소스에 접근하려고 할 때 호출되는 엔트리 포인트
 */
@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    ObjectMapper objectMapper = new ObjectMapper();

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json");
    response.setCharacterEncoding("utf-8");
    response.getWriter().write(objectMapper.writeValueAsString("Unauthorized"));
  }
}
