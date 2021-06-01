package com.acme.perustars.controller;

import com.acme.perustars.domain.model.Event;
import com.acme.perustars.domain.model.TypeOfEvent;
import com.acme.perustars.domain.service.EventService;
import com.acme.perustars.resource.EventResource;
import com.acme.perustars.resource.SaveEventResource;
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


    @Operation(summary = "Get All Events", description = "Get All Events by Pages", tags = {"Events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Events returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/events")
    public Page<EventResource> getAllEvents(Pageable pageable) {
        Page<Event> eventsPage = eventService.getAllEvents(pageable);
        List<EventResource> resources = eventsPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get All Events by ArtistId", description = "Get All Events by ArtistId", tags = {"Events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/artists/{artistId}/events")
    public Page<EventResource> getAllEventsByArtistId(@PathVariable Long artistId, Pageable pageable) {
        Page<Event> eventsPage = eventService.getAllEventsByArtistId(artistId, pageable);
        List<EventResource> resources = eventsPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get All Events by Title", description = "Get All Events by Title", tags = {"Events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/events/title/{eventTitle}")
    public Page<EventResource> getAllEventsByTitle(@PathVariable String eventTitle, Pageable pageable) {
        Page<Event> eventsPage = eventService.getAllEventsByTitle(eventTitle, pageable);
        List<EventResource> resources = eventsPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get All Events by Cost", description = "Get All Events by Cost", tags = {"Events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/events/cost/{eventCost}")
    public Page<EventResource> getAllEventsByCost(@PathVariable Double eventCost, Pageable pageable) {
        Page<Event> eventsPage = eventService.getAllEventsByCost(eventCost, pageable);
        List<EventResource> resources = eventsPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get All Events by DateStart", description = "Get All Events by DateStart", tags = {"Events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/events/dateStart/{eventDateStart}")
    public Page<EventResource> getAllEventsByDateStart(@PathVariable Calendar eventDateStart, Pageable pageable) {
        Page<Event> eventsPage = eventService.getAllEventsByDateStart(eventDateStart, pageable);
        List<EventResource> resources = eventsPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get All Events by Type", description = "Get All Events by Type", tags = {"Events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/events/type/{eventTypeOfEvent}")
    public Page<EventResource> getAllEventsByType(@PathVariable TypeOfEvent eventTypeOfEvent, Pageable pageable) {
        Page<Event> eventsPage = eventService.getAllEventsByType(eventTypeOfEvent, pageable);
        List<EventResource> resources = eventsPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get Event By ArtistId And By Id", description = "Get a Event By ArtistId", tags = {"Events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/artists/{artistId}/events/{eventId}")
    public EventResource getEventByIdAndArtistId(@PathVariable(name = "artistId") Long artistId, @PathVariable(name =
            "eventId") Long eventId) {
        return convertToResource(eventService.getEventByIdAndArtistId(artistId, eventId));
    }



    @Operation(summary = "Post Event", description = "Create a Event", tags = {"Events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event created", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping("/artists/{artistId}/events")
    public EventResource createEvent(@PathVariable Long artistId, @Valid @RequestBody SaveEventResource resource) {
        return convertToResource(eventService.createEvent(artistId, convertToEntity(resource)));
    }



    @Operation(summary = "Put Event", description = "Update a Event", tags = {"Events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated", content = @Content(mediaType =
                    "application/json"))
    })
    @PutMapping("/artists/{artistId}/events/{eventId}")
    public EventResource updateEvent(@PathVariable Long artistId, @PathVariable Long eventId,
                                     @Valid @RequestBody SaveEventResource resource) {
        return convertToResource(eventService.updateEvent(artistId, eventId, convertToEntity(resource)));
    }



    @Operation(summary = "Delete Event", description = "Delete a Event", tags = {"Events"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event deleted", content = @Content(mediaType =
                    "application/json"))
    })
    @DeleteMapping("/artists/{artistId}/events/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long artistId, @PathVariable Long eventId) {
        return eventService.deleteEvent(artistId, eventId);
    }



    private Event convertToEntity(SaveEventResource resource) {
        return mapper.map(resource, Event.class);
    }

    private EventResource convertToResource(Event hobbyist) {
        return mapper.map(hobbyist, EventResource.class);
    }
}
