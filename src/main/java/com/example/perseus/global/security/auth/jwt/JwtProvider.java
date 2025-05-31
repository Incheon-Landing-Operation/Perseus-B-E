package com.example.perseus.global.security.auth.jwt;

import com.example.perseus.global.config.properties.JwtProperties;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {
  private final JwtProperties jwtProperties;
  private final static String ACCESS_TOKEN = "ACCESS_TOKEN";
  private final static String REFRESH_TOKEN = "REFRESH_TOKEN";

  public String createAccessToken(String email) {
    return createToken(email, ACCESS_TOKEN, jwtProperties.accessTime());
  }

  public String createRefreshToken(String email) {
    String token = createToken(email, REFRESH_TOKEN, jwtProperties.refreshTime());
    // reids -> token 저장
    return token;
  }

  private String createToken(String email, String type, Long time) {
    Date now = new Date();
    return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey())
            .setHeaderParam("type", type)
            .setSubject(email)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + time))
            .compact();
  }

  public String resolveToken(HttpServletRequest request) {
    String bearer = request.getHeader(jwtProperties.header());
    return parseToken(bearer);
  }

  private String parseToken(String bearerToken) {
    if (bearerToken.startsWith(jwtProperties.prefix())) {
      return bearerToken.replace(jwtProperties.prefix(), "");
    }
    return null;
  }

}
