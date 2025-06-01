package br.com.pucminas.edutech.model.dto;

public record RegistrationResponse(
    String access_token,
    Integer expires_in,
    String refresh_token,
    String token_type,
    UserResponse user
) {
} 