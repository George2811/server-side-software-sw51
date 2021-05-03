package com.perustars.events.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//maybe this attribute should be in the User Entity

    @Column(updatable = true, nullable = true)
    private String brandName;
    @Column(updatable = true,nullable = false,length = 255)
    private String description;
    @Column(updatable = true,nullable = false, length = 100)
    private String phrase;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specialty_id", nullable = false)
    @JsonIgnore
    private Specialty specialtyArt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            mappedBy = "artists")
    private List<Hobbyist> hobbyists;

    public Artist(){
    }
    public Artist(@NotBlank String firstName, @NotBlank String lastName, String brandName, String description, String phrase, Specialty specialtyArt) {
        super(firstName, lastName);
        this.brandName = brandName;
        this.description = description;
        this.phrase = phrase;
        this.specialtyArt = specialtyArt;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public Artist setId(Long id) {
        this.id = id;
        return this;
    }

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

    public Specialty getSpecialtyArt() {
        return specialtyArt;
    }

    public Artist setSpecialtyArt(Specialty specialtyArt) {
        this.specialtyArt = specialtyArt;
        return this;
    }

    public List<Hobbyist> getHobbyists() {
        return hobbyists;
    }
}
