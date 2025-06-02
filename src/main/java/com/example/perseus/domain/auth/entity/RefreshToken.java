package com.example.perseus.domain.auth.entity;

import com.example.perseus.domain.user.entity.type.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value="refreshToken", timeToLive = 86400L)
@AllArgsConstructor
@Getter
public class RefreshToken {
  @Id
  private String refreshToken;
  private String email;
  private UserRole userRole;
}
