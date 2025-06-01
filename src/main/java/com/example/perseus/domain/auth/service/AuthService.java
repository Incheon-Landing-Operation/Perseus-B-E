package com.example.perseus.domain.auth.service;

import com.example.perseus.domain.auth.dto.response.TokenResponse;
import com.example.perseus.domain.auth.entity.RefreshToken;
import com.example.perseus.domain.auth.exception.NotFoundRefreshTokenException;
import com.example.perseus.domain.auth.repository.RefreshTokenRepository;
import com.example.perseus.domain.user.entity.type.UserRole;
import com.example.perseus.global.security.auth.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final RefreshTokenRepository refreshTokenRepository;
  private final JwtProvider jwtProvider;

  public TokenResponse reissue(final String token) {
    RefreshToken refreshToken = refreshTokenRepository.findById(token)
            .orElseThrow(NotFoundRefreshTokenException::getInstance);

    String email = refreshToken.getEmail();
    UserRole role = refreshToken.getUserRole();
    TokenResponse tokenResponse = jwtProvider.createTokenResponse(email, role);

    refreshTokenRepository.delete(refreshToken);
    return tokenResponse;
  }

}
