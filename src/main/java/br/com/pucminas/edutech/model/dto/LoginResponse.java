package br.com.pucminas.edutech.model.dto;

public record LoginResponse(
    String access_token,
    Integer expires_in,
    String refresh_token,
    String token_type,
    UserResponse user
) {
} 