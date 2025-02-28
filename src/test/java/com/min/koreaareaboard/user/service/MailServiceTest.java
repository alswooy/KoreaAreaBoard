package com.min.koreaareaboard.user.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class MailServiceTest {
  private final MailService mailService;
  private static Logger log = LoggerFactory.getLogger(MailServiceTest.class);
  private static final String invalidEmail = "invalid-email";

  @Autowired
  MailServiceTest(MailService mailService) {
    this.mailService = mailService;
  }
  @Mock
  private JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  String mail;

  @DisplayName("메일 전송 완료 테스트")
  @Test
  void sendMailSuccess() {
      // given
    int i = mailService.sendMail(mail);
    // when
    assertNotNull(i);
    log.info("메일 전송 성공 = " + i);
    // then
  }
  @DisplayName("메일 전송 실패 테스트")
  @Test
  void sendMailFailed() {
    // given

    // when
    assertThrows(MailSendException.class, () -> {
      mailService.sendMail(invalidEmail);
    });
    // then

  }
  @DisplayName("메일 3회 전송 실패 테스트")
  @Test
  void testMethodNameHere() {
      // given
    // JavaMailSender가 항상 MessagingException을 던지도록 설정
    doThrow(new MailSendException("메일 전송 실패")).when(javaMailSender).send((MimeMessage) any());


    // when
    assertThrows(MailSendException.class, () -> {
      mailService.sendMail(invalidEmail);
    });
      // then
    log.info("-메일 전송 3회 실패 후 recover 메서드 호출 확인-");
  }
}