package com.acme.perustars.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Calendar;

public class ClaimTicket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 40)
    private String subject;

    @NotBlank
    @Column(nullable = false, length = 250)
    private String description;

    @NotBlank
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar incidentDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reported_made_by", nullable = false)
    @JsonIgnore
    private Person reportedMadeBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reported_person_id", nullable = false)
    @JsonIgnore
    private Person reportedPerson;

    public ClaimTicket() {
    }

    public ClaimTicket(String subject, String description, Calendar incidentDate) {
        this.subject = subject;
        this.description = description;
        this.incidentDate = incidentDate;
    }

    public Long getId() {
        return id;
    }

    public ClaimTicket setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public ClaimTicket setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClaimTicket setDescription(String description) {
        this.description = description;
        return this;
    }

    public Calendar getIncidentDate() {
        return incidentDate;
    }

    public ClaimTicket setIncidentDate(Calendar incidentDate) {
        this.incidentDate = incidentDate;
        return this;
    }

    public Person getReportedMadeBy() {
        return reportedMadeBy;
    }

    public ClaimTicket setReportedMadeBy(Person reportedMadeBy) {
        this.reportedMadeBy = reportedMadeBy;
        return this;
    }

    public Person getReportedPerson() {
        return reportedPerson;
    }

    public ClaimTicket setReportedPerson(Person reportedPerson) {
        this.reportedPerson = reportedPerson;
        return this;
    }
}
