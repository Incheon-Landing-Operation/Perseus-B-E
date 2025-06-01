package com.example.perseus.domain.user.entity;

import com.example.perseus.domain.user.dto.request.AdditionalInfoRequest;
import com.example.perseus.domain.user.entity.type.Gender;
import com.example.perseus.domain.user.entity.type.Mbti;
import com.example.perseus.domain.user.entity.type.UserRole;
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
  private String nickName;
  private String imageUrl;
  @Enumerated(EnumType.STRING)
  private UserRole role;
  private Boolean hasCounselingExperience;
  @Enumerated(EnumType.STRING)
  private Mbti mbti;
  private int age;
  @Enumerated(EnumType.STRING)
  private Gender gender;
  private Boolean usedCbt;

  public void addInfo(AdditionalInfoRequest additionalInfoRequest) {
    this.nickName = additionalInfoRequest.nickName();
    this.age = additionalInfoRequest.age();
    this.gender = additionalInfoRequest.gender();
    this.mbti = additionalInfoRequest.mbti();
    this.hasCounselingExperience = additionalInfoRequest.hasCounselingExperience();
    this.usedCbt = additionalInfoRequest.usedCbt();
  }

  public void sign() {
    this.role = UserRole.USER;
  }
}