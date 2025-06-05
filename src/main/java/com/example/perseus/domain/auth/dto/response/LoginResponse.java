package com.example.perseus.domain.auth.dto.response;

import com.example.perseus.domain.user.entity.type.UserRole;

public record LoginResponse (
        String accessToken,
        String refreshToken,
        String role
){
  public WithOutRefreshToken withoutRefreshToken() {
    return new WithOutRefreshToken(this.accessToken, this.role);
  }

  public record WithOutRefreshToken (
          String accessToken,
          String role
  ) {
  }

}
