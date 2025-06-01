package com.example.perseus.domain.auth.dto.response;

public record TokenResponse (
        String accessToken,
        String refreshToken
) {
}
