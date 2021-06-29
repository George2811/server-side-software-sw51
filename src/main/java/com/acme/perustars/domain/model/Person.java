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
    @Column(name = "user_id")
    private Long userId;

    public Person() {
    }

    public Person(@NotBlank String firstName, @NotBlank String lastName, Long userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
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
}
