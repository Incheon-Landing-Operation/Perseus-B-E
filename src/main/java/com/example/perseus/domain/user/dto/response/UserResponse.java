package com.example.perseus.domain.user.dto.response;

import com.example.perseus.domain.user.entity.type.Gender;
import com.example.perseus.domain.user.entity.type.Mbti;
import lombok.Builder;

@Builder
public record UserResponse (
        String nickName,
        String imageUrl,
        Mbti mbti,
        int age,
        Gender gender
) {
}