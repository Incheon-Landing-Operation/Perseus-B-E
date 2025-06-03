package com.example.perseus.domain.user.dto.request;


import com.example.perseus.domain.user.entity.type.Gender;
import com.example.perseus.domain.user.entity.type.Mbti;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AdditionalInfoRequest (
        @NotNull
        String nickName,
        @NotNull
        @Min(0)
        Integer age,
        @NotNull
        Gender gender,
        @NotNull
        Mbti mbti,
        @NotNull
        Boolean hasCounselingExperience,
        @NotNull
        Boolean usedCbt
) {
}
