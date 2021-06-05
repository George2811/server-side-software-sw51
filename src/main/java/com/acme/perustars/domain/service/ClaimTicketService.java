package com.acme.perustars.domain.service;

import com.acme.perustars.domain.model.ClaimTicket;
import com.acme.perustars.domain.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ClaimTicketService {
    Page<ClaimTicket> getAllClaimTickets(Pageable pageable);
    Page<ClaimTicket> getClaimTicketsByReportedPerson(Long personId, Pageable pageable);
    Page<ClaimTicket> getClaimTicketsByReportedMadeBy(Long personId, Pageable pageable);
    ClaimTicket getClaimTicketByIdAndReportedMadeBy(Long id, Long personId);
    ClaimTicket createClaimTicket(ClaimTicket claimTicket);
    ClaimTicket updateClaimTicket(Long id, ClaimTicket claimTicketRequest);
    ResponseEntity<?> deleteClaimTicket(Long id);
}
