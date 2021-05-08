package com.perustars.events.controller;

import com.perustars.events.domain.model.Event;
import com.perustars.events.domain.model.TypeOfEvent;
import com.perustars.events.domain.service.EventService;
import com.perustars.events.resource.EventResource;
import com.perustars.events.resource.SaveEventResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EventsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EventService eventService;



    @Operation(summary = "Get Events", description = "Get All Events by Pages", tags = {"events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Events returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/events")
    public Page<EventResource> getAllEvents(Pageable pageable){
        Page<Event> eventsPage = eventService.getAllEvents(pageable);
        List<EventResource> resources = eventsPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get Events", description = "Get All Events by ArtistId", tags = {"events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/artists/{artistId}/events")
    public Page<EventResource> getAllEventsByArtistId(@PathVariable Long artistId, Pageable pageable){
        Page<Event> eventsPage = eventService.getAllEventsByArtistId(artistId, pageable);
        List<EventResource> resources = eventsPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get Events", description = "Get All Events by HobbyistId", tags = {"events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/hobbyists/{hobbyistId}/events")
    public Page<EventResource> getAllEventsByHobbyistId(@PathVariable Long hobbyistId, Pageable pageable){
        Page<Event> eventsPage = eventService.getAllEventsByHobbyistId(hobbyistId, pageable);
        List<EventResource> resources = eventsPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get Events", description = "Get All Events by Title", tags = {"events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/events/{eventTitle}")
    public Page<EventResource> getAllEventsByTitle(@PathVariable String eventTitle, Pageable pageable){
        Page<Event> eventsPage = eventService.getAllEventsByTitle(eventTitle, pageable);
        List<EventResource> resources = eventsPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get Events", description = "Get All Events by Cost", tags = {"events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/events/{eventCost}")
    public Page<EventResource> getAllEventsByCost(@PathVariable Double eventCost, Pageable pageable){
        Page<Event> eventsPage = eventService.getAllEventsByCost(eventCost, pageable);
        List<EventResource> resources = eventsPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get Events", description = "Get All Events by DateStart", tags = {"events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/events/{eventDateStart}")
    public Page<EventResource> getAllEventsByDateStart(@PathVariable Calendar eventDateStart, Pageable pageable){
        Page<Event> eventsPage = eventService.getAllEventsByDateStart(eventDateStart, pageable);
        List<EventResource> resources = eventsPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get Events", description = "Get All Events by Type", tags = {"events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/events/{eventTypeOfEvent}")
    public Page<EventResource> getAllEventsByType(TypeOfEvent eventTypeOfEvent, Pageable pageable){
        Page<Event> eventsPage = eventService.getAllEventsByType(eventTypeOfEvent, pageable);
        List<EventResource> resources = eventsPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get Event", description = "Get a Event By ArtistId", tags = {"event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/artists/{artistId}/events/{eventId}")
    public EventResource getEventByIdAndArtistId(@PathVariable(name = "artistId") Long artistId, @PathVariable(name = "eventId") Long eventId){
        return convertToResource(eventService.getEventByIdAndArtistId(artistId, eventId));
    }



    @Operation(summary = "Post Event", description = "Create a Event", tags = {"event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/artists/{artistId}/events")
    public EventResource createEvent(@PathVariable Long artistId, @Valid @RequestBody SaveEventResource resource){
        return convertToResource(eventService.createEvent(artistId, convertToEntity(resource)));
    }



    @Operation(summary = "Put Event", description = "Update a Event", tags = {"event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/artists/{artistId}/events/{eventId}")
    public EventResource updateEvent(@PathVariable Long artistId, @PathVariable Long eventId, @Valid @RequestBody SaveEventResource resource){
        return convertToResource(eventService.updateEvent(artistId, eventId, convertToEntity(resource)));
    }



    @Operation(summary = "Delete Event", description = "Delete a Event", tags = {"event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event deleted", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/artists/{artistId}/events/{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable Long artistId, @PathVariable Long eventId){
        return eventService.deleteEvent(artistId, eventId);
    }



    private Event convertToEntity(SaveEventResource resource){
        return mapper.map(resource, Event.class);
    }
    private EventResource convertToResource(Event hobbyist){
        return mapper.map(hobbyist, EventResource.class);
    }
}
