package com.acme.perustars.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist extends Person {
    @Column(name = "brand_name")
    private String brandName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, length = 100)
    private String phrase;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specialty_id", nullable = false)
    @JsonIgnore
    private Specialty specialty;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "follows")
    private List<Hobbyist> followers;

    public Artist() {
    }

    public Artist(@NotBlank String firstName, @NotBlank String lastName, String brandName, String description,
                  String phrase, Specialty specialty, Long userId) {
        super(firstName, lastName, userId);
        this.brandName = brandName;
        this.description = description;
        this.phrase = phrase;
        this.specialty = specialty;
    }

    //Getters and Setters
    public String getBrandName() {
        return brandName;
    }

    public Artist setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Artist setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPhrase() {
        return phrase;
    }

    public Artist setPhrase(String phrase) {
        this.phrase = phrase;
        return this;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public Artist setSpecialty(Specialty specialtyArt) {
        this.specialty = specialtyArt;
        return this;
    }

    public List<Hobbyist> getFollowers() {
        return followers;
    }
}
