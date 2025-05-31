package com.example.perseus.global.security.auth.jwt;

import com.example.perseus.domain.user.entity.UserRole;
import com.example.perseus.global.config.properties.JwtProperties;


import com.example.perseus.global.security.auth.oauth.CustomOAuth2User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

  public String createAccessToken(String email, UserRole role) {
    return createToken(email, ACCESS_TOKEN, role, jwtProperties.accessTime());
  }

  public String createRefreshToken(String email, UserRole role) {
    String token = createToken(email, REFRESH_TOKEN, role, jwtProperties.refreshTime());
    // reids -> token 저장
    return token;
  }

  private String createToken(String email, String type, UserRole role, Long time) {
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

  public UsernamePasswordAuthenticationToken authorization(String token) {
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

  private Claims parseClaim(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(jwtProperties.secretKey())
            .build()
            .parseClaimsJwt(token)
            .getBody();
  }

}
