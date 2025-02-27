package com.min.koreaareaboard.user.entity;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public interface UserDetails {
  Collection<? extends GrantedAuthority> getAuthorities();
  String getUsername();
  String getPassword();
  boolean isAccountNonExpired();
  boolean isAccountNonLocked();
  boolean isCredentialsNonExpired();
  boolean isEnabled();
}
