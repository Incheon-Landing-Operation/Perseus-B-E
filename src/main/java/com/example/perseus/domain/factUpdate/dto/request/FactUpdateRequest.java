package com.example.perseus.domain.factUpdate.dto.request;

import jakarta.validation.constraints.NotNull;

public record FactUpdateRequest (
        @NotNull
        Long targetId,
        @NotNull
        String cognitive_distortion,
        @NotNull
        String content
) {
}