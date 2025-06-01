package com.example.perseus.global.dto;

public record ResponseDto<T> (
        int status,
        String message,
        T data
) {
}
