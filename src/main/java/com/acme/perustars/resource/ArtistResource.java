package com.acme.perustars.resource;

public class ArtistResource {
    private Long id;
    private String firstName;
    private String lastName;
    private String brandName;
    private String description;
    private String phrase;

    public Long getId() {
        return id;
    }

    public ArtistResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ArtistResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ArtistResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public ArtistResource setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ArtistResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPhrase() {
        return phrase;
    }

    public ArtistResource setPhrase(String phrase) {
        this.phrase = phrase;
        return this;
    }
}
