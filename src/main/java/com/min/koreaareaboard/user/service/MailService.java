package com.min.koreaareaboard.user.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MailService {
  private final Logger log = LoggerFactory.getLogger(MailService.class);

  private final JavaMailSender javaMailSender;
  @Value("${spring.mail.username}")
  private String senderEmail;
  private static int number;

  // 랜덤으로 숫자 생성
  private static void createNumber() {
    number = (int)(Math.random() * (90000)) + 100000;
  }

  private MimeMessage CreateMail(String mail) {
    createNumber();
    MimeMessage message = javaMailSender.createMimeMessage();

    try {
      message.setFrom(senderEmail);
      message.setRecipients(MimeMessage.RecipientType.TO, mail);
      message.setSubject("이메일 인증");

      String body = "<div style='font-family: Arial, sans-serif; text-align: center;'>";
      body += "<h2 style='color: #4CAF50;'>이메일 인증</h2>";
      body += "<p>요청하신 인증 번호입니다:</p>";
      body += "<div style='font-size: 24px; font-weight: bold; margin: 20px 0;'>" + number + "</div>";
      body += "<p>KAB를 이용해주셔서 감사합니다.</p>";
      body += "</div>";

      message.setText(body,"UTF-8", "html");
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    return message;
  }

  @Retryable(
      value = { MessagingException.class },
      maxAttempts = 3,
      backoff = @Backoff(delay = 2000)
  )
  @Transactional
  public int sendMail(String mail) {
    MimeMessage message = CreateMail(mail);
    javaMailSender.send(message);

    return number;
  }

  @Recover
  public void recover(MessagingException e, String mail) {
    // 재시도 실패 후의 처리 로직
    log.error("메일 전송 실패: " + mail);
    log.error("재시도 실패: " + e.getMessage());
  }
}
