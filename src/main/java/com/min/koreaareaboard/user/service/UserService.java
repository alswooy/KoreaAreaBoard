package com.min.koreaareaboard.user.service;

import com.min.koreaareaboard.common.enums.CommonStatus;
import com.min.koreaareaboard.user.entity.UserDetails;
import com.min.koreaareaboard.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * 사용자 서비스
 * 사용자 정보를 가져오는 서비스
 * 사용자 정보 수정(비밀번호 수정, 닉네임 수정)
 * 아이디찾기
 * 사용자 탈퇴
 */
@Service //서비스 어노테이션
@Slf4j // 로그 어노테이션
@Validated // 유효성검증 어노테이션
@Transactional // 트랜잭션 어노테이션
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    //비밀번호 찾기(비밀번호 수정)
    public void userPasswordUpdate(String email, String password){
        userRepository.save(
            userRepository.findByEmail(email)
                .toBuilder().password(password).build());
    }

    //닉네임 수정
    public void userNicknameUpdate(String email, String nickname){
        userRepository.save(
            userRepository.findByEmail(email)
                .toBuilder().nickname(nickname).build());
    }

    //사용자 탈퇴(상태 수정)
    public void userStatusUpdate(String email){
        userRepository.save(
            userRepository.findByEmail(email)
                .toBuilder().status(CommonStatus.DELETE).build());
    }

}
