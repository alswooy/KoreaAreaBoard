package com.min.koreaareaboard.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.min.koreaareaboard.common.entity.BaseEntity;
import com.min.koreaareaboard.user.enums.OAuthType;
import com.min.koreaareaboard.user.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity implements UserDetails{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_role", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<UserRole> userRole = new ArrayList<>();

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




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userRole.stream()
            .map(userRole -> new SimpleGrantedAuthority(userRole.name()))
            .collect(Collectors.toList());//계정이 가지고 있는 권한
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.email;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {//계정의 만료확인
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() { //계정이 잠겨있는지
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() { //비밀번호가 만료 되었는지
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() { //계정이 활성화 되어있는지
        return true;
    }

}
