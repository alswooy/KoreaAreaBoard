package com.min.koreaareaboard.comments.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service //서비스 어노테이션
@Slf4j // 로그 어노테이션
@RequiredArgsConstructor // 생성자 어노테이션
@Validated // 유효성검증 어노테이션
@Transactional // 트랜잭션 어노테이션
public class CommentService {
}
