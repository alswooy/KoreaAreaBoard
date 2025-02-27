package com.min.koreaareaboard.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * Spring Security 접근 거부시 호출되는 핸들러
 * AccessDeniedException 발생시 /sign/exception 으로 리다이렉트
 */
@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    response.sendRedirect("/sign/exception");
    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
  }
}
