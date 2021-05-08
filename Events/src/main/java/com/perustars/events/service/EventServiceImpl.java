package com.perustars.events.service;

import com.perustars.events.domain.model.Event;
import com.perustars.events.domain.model.TypeOfEvent;
import com.perustars.events.domain.repository.ArtistRepository;
import com.perustars.events.domain.repository.EventRepository;
import com.perustars.events.domain.repository.HobbyistRepository;
import com.perustars.events.domain.service.EventService;
import com.perustars.events.domain.service.HobbyistService;
import com.perustars.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private HobbyistRepository hobbyistRepository;

    @Override
    public Page<Event> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Override
    public Page<Event> getAllEventsByArtistId(Long artistId, Pageable pageable) {
        return eventRepository.findByArtistId(artistId, pageable);
    }

    @Override
    public Page<Event> getAllEventsByHobbyistId(Long hobbyistId, Pageable pageable) {
        return hobbyistRepository.findById(hobbyistId)
                .map(hobbyist -> {
                    List<Event> events = hobbyist.getEvents();
                    int eventCount = events.size();
                    return new PageImpl<>(events, pageable, eventCount);
                }).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
    }

    @Override
    public Event getEventByIdAndArtistId(Long artistId, Long eventId) {
        return eventRepository.findByIdAndArtistId(eventId, artistId).orElseThrow(() -> new ResourceNotFoundException("Event not found with Id " + eventId + " and ArtistId " + artistId));
    }

    @Override
    public Event createEvent(Long artistId, Event event) {
        return artistRepository.findById(artistId).map(artist -> {
            event.setArtist(artist);
            return eventRepository.save(event);
        }).orElseThrow(() -> new ResourceNotFoundException("Artist", "Id", artistId));
    }

    @Override
    public Event updateEvent(Long artistId, Long eventId, Event eventRequest) {
        if (!artistRepository.existsById(artistId))
            throw new ResourceNotFoundException("Artist","Id",artistId);
        return eventRepository.findById(eventId).map(eve -> {
            eve.setTitle(eventRequest.getTitle());
            eve.setDescription(eventRequest.getDescription());
            eve.setCost(eventRequest.getCost());
            eve.setDateStart(eventRequest.getDateStart());
            eve.setDateEnd(eventRequest.getDateEnd());
            return eventRepository.save(eve);
        }).orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
    }

    @Override
    public ResponseEntity<?> deleteEvent(Long artistId, Long eventId) {
        if (!artistRepository.existsById(artistId))
            throw new ResourceNotFoundException("Artist","Id",artistId);
        return eventRepository.findById(eventId).map(eve -> {
            eventRepository.delete(eve);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
    }

    @Override
    public Page<Event> getAllEventsByTitle(String title, Pageable pageable) {
        return eventRepository.findByTitle(title, pageable);
    }

    @Override
    public Page<Event> getAllEventsByCost(double cost, Pageable pageable) {
        return eventRepository.findByCost(cost, pageable);
    }

    @Override
    public Page<Event> getAllEventsByDateStart(Calendar dateStart, Pageable pageable) {
        return eventRepository.findByDateStart(dateStart, pageable);
    }

    @Override
    public Page<Event> getAllEventsByType(TypeOfEvent typeOfEvent, Pageable pageable) {
        return eventRepository.findByType(typeOfEvent, pageable);
    }
}
