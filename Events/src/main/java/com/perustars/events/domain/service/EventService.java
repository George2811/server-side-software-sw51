package com.perustars.events.domain.service;

import com.perustars.events.domain.model.Event;
import com.perustars.events.domain.model.TypeOfEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;

public interface EventService {
    Page<Event> getAllEvents(Pageable pageable);
    Page<Event> getAllEventsByArtistId(Long artistId, Pageable pageable);
    Event getEventByIdAndArtistId(Long artistId, Long eventId);
    Event createEvent(Long artistId, Event event);
    Event updateEvent(Long artistId, Long eventId, Event eventRequest);
    ResponseEntity<?> deleteEvent(Long artistId, Long eventId);
    Page<Event> getAllEventsByTitle(String title);
    Page<Event> getAllEventsByCost(double cost);
    Page<Event> getAllEventsByDateStart(Calendar dateStart);
    Page<Event> getAllEventsByType(TypeOfEvent typeOfEvent);
}
