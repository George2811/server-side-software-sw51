package com.acme.perustars.service.communication;

public class AuthenticationResponse {
    private Long userId;
    private String username;
    private String token;

    public Long getUserId() {
        return userId;
    }

    public AuthenticationResponse setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AuthenticationResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AuthenticationResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
