package com.perustars.events.domain.repository;

import com.perustars.events.domain.model.Event;
import com.perustars.events.domain.model.TypeOfEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByArtistId(Long artistId, Pageable pageable);
    Optional<Event> findByIdAndArtistId(Long id, Long artistId);
    public Optional<Event> findByTitle(String title);
    public Optional<Event> findByCost(double cost);
    public Optional<Event> findByDateStart(Calendar dateStart);
    public Optional<Event> findByType(TypeOfEvent type);
}
