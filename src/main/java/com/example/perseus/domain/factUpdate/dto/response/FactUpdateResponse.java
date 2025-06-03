package com.example.perseus.domain.factUpdate.dto.response;

import com.example.perseus.domain.fact.entity.Fact;
import com.example.perseus.domain.factUpdate.entity.type.CognitiveDistortion;
import com.example.perseus.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
public record FactUpdateResponse (
        Long factUpdateId,
        User writer,
        CognitiveDistortion cognitive_distortion,
        String content,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt
) {
}
