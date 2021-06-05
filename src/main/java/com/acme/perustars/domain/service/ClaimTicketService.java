package com.acme.perustars.domain.service;

import com.acme.perustars.domain.model.ClaimTicket;
import com.acme.perustars.domain.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ClaimTicketService {
    Page<ClaimTicket> getAllClaimTickets(Pageable pageable);
    ClaimTicket getClaimTicketsByReportedPerson(Person reportedPerson);
    ClaimTicket getClaimTicketsByReportedMadeBy(Person reportedMadeBy);
    ClaimTicket getClaimTicketsByIdAndReportedMadeBy(Long id, Person reportedMadeBy);
    ClaimTicket createClaimTicket(ClaimTicket claimTicket);
    ClaimTicket updateClaimTicket(Long id, ClaimTicket claimTicketRequest);
    ResponseEntity<?> deleteClaimTicket(Long id);

}
