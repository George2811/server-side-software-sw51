package com.perustars.events.domain.model;

import javax.persistence.*;

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

    @Column(updatable = true,nullable = false)
    private Specialty specialtyArt;

    //Getters and Setters


}
