package com.example.perseus.domain.fact.dto.request;

import com.example.perseus.domain.fact.entity.type.Sentiment;

public record FactRequest (
        String content,
        Sentiment sentiment,
        Double latitude,
        Double longitude
) {
}
