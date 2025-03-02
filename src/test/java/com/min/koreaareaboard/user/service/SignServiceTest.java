package com.min.koreaareaboard.user.service;

import static org.junit.jupiter.api.Assertions.*;

import com.min.koreaareaboard.user.dto.request.JoinRequestDto;
import com.min.koreaareaboard.user.dto.request.LoginRequestDto;
import com.min.koreaareaboard.user.dto.response.SignInResultDto;
import com.min.koreaareaboard.user.dto.response.SignUpResultDto;
import com.min.koreaareaboard.user.entity.User;
import com.min.koreaareaboard.user.enums.UserRole;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class SignServiceTest {
  Logger log = LoggerFactory.getLogger(SignServiceTest.class);

  @Autowired
  private SignService signService;

  private User testUser;
  @BeforeEach
  void setUp() {
      testUser = User.builder()
        .email("test@gmail.com")
        .password("1234")
        .userRole(Arrays.asList(UserRole.USER, UserRole.ADMIN))
        .nickname("test")
    .build();
  }

  @Test
  void signUp() {
    SignUpResultDto result = signService.signUp(JoinRequestDto.builder()
        .email(testUser.getEmail())
        .password(testUser.getPassword())
        .nickname(testUser.getNickname())
        .role(testUser.getUserRole().get(1).name())
        .build());

    assertTrue(result.isSuccess());
    assertEquals("SUCCESS", result.getMsg());
  }
  @Test
  void signUpWithExistingEmail() {
    signService.signUp(JoinRequestDto.builder()
        .email(testUser.getEmail())
        .password(testUser.getPassword())
        .nickname(testUser.getNickname())
        .role(testUser.getUserRole().get(1).name())
        .build());
    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
      signService.signUp(JoinRequestDto.builder()
          .email(testUser.getEmail())
          .password(testUser.getPassword())
          .nickname(testUser.getNickname())
          .role(testUser.getUserRole().get(1).name())
          .build());
    });
    assertEquals("이미 존재하는 회원입니다.", exception.getMessage());
  }

  @Test
  void signIn() {
    SignUpResultDto result = signService.signUp(JoinRequestDto.builder()
        .email(testUser.getEmail())
        .password(testUser.getPassword())
        .nickname(testUser.getNickname())
        .role(testUser.getUserRole().get(1).name())
        .build());
    assertTrue(result.isSuccess());
    assertEquals("SUCCESS", result.getMsg());
    SignInResultDto signInResult = signService.signIn(
        LoginRequestDto.builder()
            .email(testUser.getEmail())
            .password(testUser.getPassword())
            .build());
    assertTrue(signInResult.isSuccess());
    assertEquals("SUCCESS", signInResult.getMsg());
    assertNotNull(signInResult.getToken());
    log.info("token: {}", signInResult.getToken());
  }

  @Test
  void signInTooEmail(){
    SignUpResultDto result = signService.signUp(JoinRequestDto.builder()
        .email(testUser.getEmail())
        .password(testUser.getPassword())
        .nickname(testUser.getNickname())
        .role(testUser.getUserRole().get(1).name())
        .build());
    assertTrue(result.isSuccess());
    assertEquals("SUCCESS", result.getMsg());
    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
          signService.signUp(JoinRequestDto.builder()
              .email(testUser.getEmail())
              .password(testUser.getPassword())
              .nickname(testUser.getNickname())
              .role(testUser.getUserRole().get(1).name()).build());
        });
      assertEquals("이미 존재하는 회원입니다.", exception.getMessage());
  }

  @Test
  void signInWithWrongPassword() {
    signService.signUp(JoinRequestDto.builder()
        .email(testUser.getEmail())
        .password(testUser.getPassword())
        .nickname(testUser.getNickname())
        .role(testUser.getUserRole().get(1).name())
        .build());
    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
      signService.signIn(LoginRequestDto.builder()
          .email(testUser.getEmail())
          .password("wrongpassword")
          .build());
    });
    assertEquals("비밀번호가 일치하지 않습니다.", exception.getMessage());
  }

  @Test
  void signInWithNonExistingEmail() {
    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
      signService.signIn(LoginRequestDto.builder()
          .email("nonexisting@gmail.com")
          .password(testUser.getPassword())
          .build());
    });
    assertEquals("존재하지 않는 회원입니다.", exception.getMessage());
  }
}