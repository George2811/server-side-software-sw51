package com.acme.perustars.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveHobbyistResource {
    @NotBlank
    @NotNull
    @Size(max = 25)
    private String firstName;

    @NotBlank
    @NotNull
    @Size(max = 25)
    private String lastName;

    @NotNull
    private Long userId;

    public String getFirstName() {
        return firstName;
    }

    public SaveHobbyistResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveHobbyistResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public SaveHobbyistResource setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
