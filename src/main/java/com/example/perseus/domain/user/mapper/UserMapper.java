package com.example.perseus.domain.user.mapper;

import com.example.perseus.domain.user.dto.response.UserResponse;
import com.example.perseus.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserResponse toUserResponse(User user) {
    return UserResponse.builder()
            .nickName(user.getNickName())
            .gender(user.getGender())
            .age(user.getAge())
            .mbti(user.getMbti())
            .imageUrl(user.getImageUrl())
            .build();
  }
}
