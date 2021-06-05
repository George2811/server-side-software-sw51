package com.acme.perustars.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;

public class SaveClaimTicketResource {
    @NotNull
    @NotBlank
    @Size(max = 40)
    private String claimSubject;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String claimDescription;

    @NotNull
    @NotBlank
    private Calendar incidentDate;

    @NotNull
    @NotBlank
    private Long reportedPersonId;

    @NotNull
    @NotBlank
    private Long reportMadeById;

    public String getClaimSubject() {
        return claimSubject;
    }

    public SaveClaimTicketResource setClaimSubject(String claimSubject) {
        this.claimSubject = claimSubject;
        return this;
    }

    public String getClaimDescription() {
        return claimDescription;
    }

    public SaveClaimTicketResource setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
        return this;
    }

    public Calendar getIncidentDate() {
        return incidentDate;
    }

    public SaveClaimTicketResource setIncidentDate(Calendar incidentDate) {
        this.incidentDate = incidentDate;
        return this;
    }

    public Long getReportedPersonId() {
        return reportedPersonId;
    }

    public SaveClaimTicketResource setReportedPersonId(Long reportedPersonId) {
        this.reportedPersonId = reportedPersonId;
        return this;
    }

    public Long getReportMadeById() {
        return reportMadeById;
    }

    public SaveClaimTicketResource setReportMadeById(Long reportMadeById) {
        this.reportMadeById = reportMadeById;
        return this;
    }
}
