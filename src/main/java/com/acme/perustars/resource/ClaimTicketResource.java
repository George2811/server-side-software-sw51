package com.acme.perustars.resource;

import java.util.Calendar;

public class ClaimTicketResource {
    private Long id;
    private String claimSubject;
    private String claimDescription;
    private Calendar incidentDate;
    private Long reportedPersonId;
    private Long reportMadeById;

    public Long getId() {
        return id;
    }

    public ClaimTicketResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getClaimSubject() {
        return claimSubject;
    }

    public ClaimTicketResource setClaimSubject(String claimSubject) {
        this.claimSubject = claimSubject;
        return this;
    }

    public String getClaimDescription() {
        return claimDescription;
    }

    public ClaimTicketResource setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
        return this;
    }

    public Calendar getIncidentDate() {
        return incidentDate;
    }

    public ClaimTicketResource setIncidentDate(Calendar incidentDate) {
        this.incidentDate = incidentDate;
        return this;
    }

    public Long getReportedPersonId() {
        return reportedPersonId;
    }

    public ClaimTicketResource setReportedPersonId(Long reportedPersonId) {
        this.reportedPersonId = reportedPersonId;
        return this;
    }

    public Long getReportMadeById() {
        return reportMadeById;
    }

    public ClaimTicketResource setReportMadeById(Long reportMadeById) {
        this.reportMadeById = reportMadeById;
        return this;
    }
}
