package com.acme.perustars.resource;

public class HobbyistResource {
    private Long id;
    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public HobbyistResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public HobbyistResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public HobbyistResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
