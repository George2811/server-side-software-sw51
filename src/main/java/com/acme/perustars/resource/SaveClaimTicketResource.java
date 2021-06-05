package com.acme.perustars.resource;

import com.acme.perustars.domain.model.Person;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;

public class SaveClaimTicketResource {
    @NotNull
    @NotBlank
    @Size(max = 40)
    private String subject;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String description;

    @NotNull
    @NotBlank
    private Calendar incidentDate;

    @NotNull
    @NotBlank
    private Person reportedPerson;

    @NotNull
    @NotBlank
    private Person reportMadeBy;


    public String getSubject() {
        return subject;
    }

    public SaveClaimTicketResource setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveClaimTicketResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Calendar getIncidentDate() {
        return incidentDate;
    }

    public SaveClaimTicketResource setIncidentDate(Calendar incidentDate) {
        this.incidentDate = incidentDate;
        return this;
    }

    public Person getReportedPerson() {
        return reportedPerson;
    }

    public SaveClaimTicketResource setReportedPerson(Person reportedPerson) {
        this.reportedPerson = reportedPerson;
        return this;
    }

    public Person getReportMadeBy() {
        return reportMadeBy;
    }

    public SaveClaimTicketResource setReportMadeBy(Person reportMadeBy) {
        this.reportMadeBy = reportMadeBy;
        return this;
    }
}
