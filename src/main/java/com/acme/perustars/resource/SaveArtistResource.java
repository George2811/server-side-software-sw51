package com.acme.perustars.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveArtistResource {
    @NotBlank
    @NotNull
    @Size(max = 25)
    private String firstName;

    @NotBlank
    @NotNull
    @Size(max = 25)
    private String lastName;

    @NotBlank
    @NotNull
    @Size(max = 30)
    private String brandName;

    @NotBlank
    @Lob
    private String description;

    @NotBlank
    @NotNull
    @Size(max = 50)
    private String phrase;


    public String getBrandName() {
        return brandName;
    }

    public SaveArtistResource setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveArtistResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPhrase() {
        return phrase;
    }

    public SaveArtistResource setPhrase(String phrase) {
        this.phrase = phrase;
        return this;
    }
}
