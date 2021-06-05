package com.acme.perustars.resource;

import com.acme.perustars.domain.model.Person;

import java.util.Calendar;

public class ClaimTicketResource {
    private Long id;
    private String subject;
    private String description;
    private Calendar incidentDate;
    private Person reportedPerson;
    private Person reportMadeBy;


    public Long getId() {
        return id;
    }

    public ClaimTicketResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public ClaimTicketResource setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ClaimTicketResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Calendar getIncidentDate() {
        return incidentDate;
    }

    public ClaimTicketResource setIncidentDate(Calendar incidentDate) {
        this.incidentDate = incidentDate;
        return this;
    }

    public Person getReportedPerson() {
        return reportedPerson;
    }

    public ClaimTicketResource setReportedPerson(Person reportedPerson) {
        this.reportedPerson = reportedPerson;
        return this;
    }

    public Person getReportMadeBy() {
        return reportMadeBy;
    }

    public ClaimTicketResource setReportMadeBy(Person reportMadeBy) {
        this.reportMadeBy = reportMadeBy;
        return this;
    }
}
