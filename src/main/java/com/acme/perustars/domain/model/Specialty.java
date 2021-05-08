package com.acme.perustars.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "specialties")
public class Specialty implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "specialties")
    private List<Hobbyist> hobbyists;

    public Specialty() {
    }

    public Specialty(String name) {
        this.name = name;
    }
    //Getters and Setters

    public Long getId() {
        return id;
    }

    public Specialty setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Specialty setName(String name) {
        this.name = name;
        return this;
    }

    public List<Hobbyist> getHobbyists() {
        return hobbyists;
    }
}

