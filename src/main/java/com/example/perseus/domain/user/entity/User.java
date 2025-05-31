package com.example.perseus.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  private String name;
  private String email;
  @Enumerated(EnumType.STRING)
  private SocialType socialType;
  private String socialId;
  private String imageUrl;

  @Enumerated(EnumType.STRING)
  private UserRole role;
  private Boolean visited_counseling_center;
  private String mbti;
  private int age;

  private char sex;
  private Boolean used_cbt;
}
