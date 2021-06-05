package com.acme.perustars.service;

import com.acme.perustars.domain.model.ClaimTicket;
import com.acme.perustars.domain.model.Person;
import com.acme.perustars.domain.repository.ClaimTicketRepository;
import com.acme.perustars.domain.service.ClaimTicketService;
import com.acme.perustars.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class ClaimTicketServiceImpl implements ClaimTicketService {
    @Autowired
    private ClaimTicketRepository claimTicketRepository;

    @Override
    public Page<ClaimTicket> getAllClaimTickets(Pageable pageable) {
        return claimTicketRepository.findAll(pageable);
    }

    @Override
    public Page<ClaimTicket> getClaimTicketsByReportedPerson(Long personId, Pageable pageable) {
        return claimTicketRepository.findAllByReportedPersonId(personId, pageable);
    }

    @Override
    public Page<ClaimTicket> getClaimTicketsByReportedMadeBy(Long personId, Pageable pageable) {
        return claimTicketRepository.findAllByReportedMadeById(personId, pageable);
    }

    @Override
    public ClaimTicket getClaimTicketByIdAndReportedMadeBy(Long id, Long personId) {
        return claimTicketRepository.findByIdAndReportedMadeById(personId,id).orElseThrow(() -> new ResourceNotFoundException("Claim Ticket not found with Id"+id+"and ReportedMadeById"+personId));
        
    }

    @Override
    public ClaimTicket createClaimTicket(ClaimTicket claimTicket) {
        return claimTicketRepository.save(claimTicket);
    }

    @Override
    public ClaimTicket updateClaimTicket(Long id, ClaimTicket claimTicketRequest) {
        ClaimTicket claimTicket = claimTicketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ClaimTicket", "Id", id));
        claimTicket.setSubject(claimTicketRequest.getSubject())
                .setDescription(claimTicketRequest.getDescription())
                .setIncidentDate(claimTicketRequest.getIncidentDate());
        return claimTicketRepository.save(claimTicket);
    }

    @Override
    public ResponseEntity<?> deleteClaimTicket(Long id) {
        ClaimTicket claimTicket = claimTicketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ClaimTicket", "Id", id));
        claimTicketRepository.delete(claimTicket);
        return ResponseEntity.ok().build();
    }
}
