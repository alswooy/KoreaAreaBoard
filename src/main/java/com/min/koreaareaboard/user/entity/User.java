package com.min.koreaareaboard.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.min.koreaareaboard.common.entity.BaseEntity;
import com.min.koreaareaboard.user.enums.OAuthType;
import com.min.koreaareaboard.user.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_role", nullable = false)
    @Builder.Default
    private UserRole userRole = UserRole.USER;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ColumnDefault("0")
    @Column(nullable = false, name = "passwordLock")
    private int passwordLock; // 5회 이상 락

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "oauth_type") // OAuth 제공자 (ex: google, kakao, naver)
    private OAuthType oAuthType;

    @Column(name = "oauth_id")
    private String oAuthId; // OAuth ID
}
