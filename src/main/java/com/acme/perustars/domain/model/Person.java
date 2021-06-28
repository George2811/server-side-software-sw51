package com.acme.perustars.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persons")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Size(max = 30)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column (name = "user_id")
    private Long userId;
    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "claim_tickets", joinColumns = {@JoinColumn(name = "report_made_by_id")},
               inverseJoinColumns = {@JoinColumn(name = "reported_person_id")})
    private List<Person> claimTickets;  //Reports that the person makes*/

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "claimTickets")
    private List<Person> reportsClaimTickets;   //Reports made to the person*/

    public Person() {
    }

    public Person(@NotBlank String firstName, @NotBlank String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Person setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
/*
    public List<Person> getClaimTickets() {
        return claimTickets;
    }

    public Person setClaimTickets(List<Person> claimTickets) {
        this.claimTickets = claimTickets;
        return this;
    }

    public List<Person> getReportsClaimTickets() {
        return reportsClaimTickets;
    }

    public Person setReportsClaimTickets(List<Person> reportsClaimTickets) {
        this.reportsClaimTickets = reportsClaimTickets;
        return this;
    }*/
}
