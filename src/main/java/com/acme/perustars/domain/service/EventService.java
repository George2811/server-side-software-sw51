package com.acme.perustars.domain.service;

import com.acme.perustars.domain.model.Event;
import com.acme.perustars.domain.model.TypeOfEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;

public interface EventService {
    Page<Event> getAllEvents(Pageable pageable);

    Page<Event> getAllEventsByArtistId(Long artistId, Pageable pageable);

    Page<Event> getAllEventsByHobbyistId(Long hobbyistId, Pageable pageable);

    Event getEventByIdAndArtistId(Long artistId, Long eventId);

    Event createEvent(Long artistId, Event event);

    Event updateEvent(Long artistId, Long eventId, Event eventRequest);

    ResponseEntity<?> deleteEvent(Long artistId, Long eventId);

    Page<Event> getAllEventsByTitle(String title, Pageable pageable);

    Page<Event> getAllEventsByCost(double cost, Pageable pageable);

    Page<Event> getAllEventsByDateStart(Calendar dateStart, Pageable pageable);

    Page<Event> getAllEventsByType(TypeOfEvent typeOfEvent, Pageable pageable);
}
