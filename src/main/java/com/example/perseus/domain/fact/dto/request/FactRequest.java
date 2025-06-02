package com.example.perseus.domain.fact.dto.request;

import com.example.perseus.domain.fact.entity.type.Sentiment;
import jakarta.validation.constraints.NotNull;

public record FactRequest (
        @NotNull
        String content,
        @NotNull
        String sentiment,
        Double latitude,
        @NotNull
        Double longitude
) {
}
