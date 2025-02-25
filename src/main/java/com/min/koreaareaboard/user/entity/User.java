package com.min.koreaareaboard.user.entity;

import com.min.koreaareaboard.user.dto.UserRole;
import com.min.koreaareaboard.utill.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
    private Long id;

    private UserRole userRole;

    private String email;
    private String password;
    private String nickname;
    private String profile_image;
}
