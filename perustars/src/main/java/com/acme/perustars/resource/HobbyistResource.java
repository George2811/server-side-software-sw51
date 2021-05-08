package com.acme.perustars.resource;

public class HobbyistResource {
    private String firstName;
    private String lastName;
    private String id;

    public String getId() {
        return id;
    }

    public HobbyistResource setId(String id) {
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
