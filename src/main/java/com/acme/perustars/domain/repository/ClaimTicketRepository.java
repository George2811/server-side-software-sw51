package com.acme.perustars.domain.repository;

import com.acme.perustars.domain.model.ClaimTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClaimTicketRepository extends JpaRepository<ClaimTicket, Long> {
    Page<ClaimTicket> findAllByReportedMadeById(Long personId, Pageable pageable);
    Page<ClaimTicket> findAllByReportedPersonId(Long personId, Pageable pageable);
    Optional<ClaimTicket> findByIdAndReportedMadeById(Long id, Long personId);
}
