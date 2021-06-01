package com.acme.perustars.domain.repository;

import com.acme.perustars.domain.model.Event;
import com.acme.perustars.domain.model.TypeOfEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByArtistId(Long artistId, Pageable pageable);
    Page<Event> findByTitle(String title, Pageable pageable);
    Optional<Event> findByIdAndArtistId(Long id, Long artistId);
    Page<Event> findByCost(double cost, Pageable pageable);
    Page<Event> findByDateStart(Calendar dateStart, Pageable pageable);
    Page<Event> findByType(TypeOfEvent type, Pageable pageable);
}
