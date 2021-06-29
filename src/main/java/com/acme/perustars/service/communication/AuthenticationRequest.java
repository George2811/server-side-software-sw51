package com.acme.perustars.service.communication;

public class AuthenticationRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public AuthenticationRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthenticationRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
