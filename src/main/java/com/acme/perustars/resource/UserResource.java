package com.acme.perustars.resource;

public class UserResource {
    private Long id;
    private String username;

    public Long getId() {
        return id;
    }

    public UserResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserResource setUsername(String username) {
        this.username = username;
        return this;
    }
}
