package com.example.perseus.domain.auth.service;

import com.example.perseus.domain.auth.mapper.AuthMapper;
import com.example.perseus.domain.auth.dto.response.TokenResponse;
import com.example.perseus.domain.auth.entity.RefreshToken;
import com.example.perseus.domain.auth.exception.NotFoundRefreshTokenException;
import com.example.perseus.domain.auth.repository.RefreshTokenRepository;
import com.example.perseus.domain.auth.dto.response.LoginResponse;
import com.example.perseus.domain.user.entity.User;
import com.example.perseus.domain.user.entity.type.UserRole;
import com.example.perseus.domain.user.service.UserService;
import com.example.perseus.global.dto.GoogleInformationResponse;
import com.example.perseus.global.feign.GoogleInformationClient;
import com.example.perseus.global.security.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final RefreshTokenRepository refreshTokenRepository;
  private final JwtProvider jwtProvider;
  private final GoogleInformationClient googleInformationClient;
  private final AuthMapper authMapper;
  private final UserService userService;


  public LoginResponse login(final String token) {
    GoogleInformationResponse googleInformationResponse = googleInformationClient.getUserInformation(token);
    String email = googleInformationResponse.email();
    String name = googleInformationResponse.name();
    String imageUrl = googleInformationResponse.imageUrl();

    User user = userService.login(email, name, imageUrl);

    UserRole role = user.getRole();
    TokenResponse tokenSet = jwtProvider.createTokenResponse(email, role);
    LoginResponse loginResponse = authMapper.toLoginResponse(tokenSet.accessToken(), tokenSet.refreshToken(), role);
    return loginResponse;
  }


  public TokenResponse reissue(final String token) {
    RefreshToken refreshToken = refreshTokenRepository.findById(token)
            .orElseThrow(NotFoundRefreshTokenException::getInstance);

    String email = refreshToken.getEmail();
    UserRole role = refreshToken.getUserRole();
    TokenResponse tokenResponse = jwtProvider.createTokenResponse(email, role);

    refreshTokenRepository.delete(refreshToken);
    return tokenResponse;
  }

  public void logout(final String token) {
    refreshTokenRepository.deleteById(token);
  }
}
