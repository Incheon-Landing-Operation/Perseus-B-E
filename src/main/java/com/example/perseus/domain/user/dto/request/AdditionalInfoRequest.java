package com.example.perseus.domain.user.dto.request;


import com.example.perseus.domain.user.entity.type.Gender;
import com.example.perseus.domain.user.entity.type.Mbti;

public record AdditionalInfoRequest (
        String nickName,
        Integer age,
        Gender gender,
        Mbti mbti,
        Boolean hasCounselingExperience,
        Boolean usedCbt
) {
}
