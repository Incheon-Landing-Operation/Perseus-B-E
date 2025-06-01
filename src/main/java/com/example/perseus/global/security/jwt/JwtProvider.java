package com.example.perseus.global.security.jwt;

import com.example.perseus.domain.auth.dto.response.TokenResponse;
import com.example.perseus.domain.auth.entity.RefreshToken;
import com.example.perseus.domain.auth.repository.RefreshTokenRepository;
import com.example.perseus.domain.user.entity.type.UserRole;
import com.example.perseus.global.config.properties.JwtProperties;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.antlr.v4.runtime.Token;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtProvider {
  private final JwtProperties jwtProperties;
  private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
  private final static String REFRESH_TOKEN = "REFRESH_TOKEN";

  private final RefreshTokenRepository refreshTokenRepository;

  public TokenResponse createTokenResponse(String email, UserRole role) {
    String accessToken = createAccessToken(email, role);
    String refreshToken = createRefreshToken(email, role);
    return new TokenResponse(accessToken, refreshToken);
  }

  public String createAccessToken(final String email, final UserRole role) {
    return createToken(email, ACCESS_TOKEN, role, jwtProperties.accessTime());
  }

  public String createRefreshToken(final String email, final UserRole role) {
    String token = createToken(email, REFRESH_TOKEN, role, jwtProperties.refreshTime());
    // redis -> token 저장
    RefreshToken refreshToken = new RefreshToken(token, email, role);
    refreshTokenRepository.save(refreshToken);
    return token;
  }

  private String createToken(final String email, final String type, final UserRole role, final Long time) {
    Date now = new Date();
    return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey())
            .setHeaderParam("type", type)
            .setSubject(email)
            .claim("role", role.getRoleKey())
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + time))
            .compact();
  }

  public String resolveToken(final HttpServletRequest request) {
    String bearer = request.getHeader(jwtProperties.header());
    return parseToken(bearer);
  }

  private String parseToken(final String bearerToken) {
    if (bearerToken != null && bearerToken.startsWith(jwtProperties.prefix())) {
      return bearerToken.replace(jwtProperties.prefix(), "");
    }
    return null;
  }

  public UsernamePasswordAuthenticationToken authorization(final String token) {
    Claims claims = parseClaim(token);
    String email = claims.getSubject();

    List<GrantedAuthority> authorities = List.of(
            new SimpleGrantedAuthority(claims.get("role", String.class))
    );

    UserDetails userDetails = new User(
            email, "", authorities
    );

    return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);

  }

  private Claims parseClaim(final String token) {
    return Jwts.parserBuilder()
            .setSigningKey(jwtProperties.secretKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

}
