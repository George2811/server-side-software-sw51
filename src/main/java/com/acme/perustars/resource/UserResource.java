package com.acme.perustars.resource;

public class UserResource {
    private String username;

    public String getUsername() {
        return username;
    }

    public UserResource setUsername(String username) {
        this.username = username;
        return this;
    }
}
