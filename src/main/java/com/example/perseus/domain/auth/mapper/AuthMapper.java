package com.example.perseus.domain.auth.mapper;

import com.example.perseus.domain.auth.dto.response.LoginResponse;
import com.example.perseus.domain.user.entity.type.UserRole;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
  public LoginResponse toLoginResponse(String accessToken, String refreshToken, UserRole role) {
    return new LoginResponse(accessToken, refreshToken, role);
  }
}
