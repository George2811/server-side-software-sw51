package com.acme.perustars.controller;

import com.acme.perustars.domain.model.Event;
import com.acme.perustars.domain.model.Hobbyist;
import com.acme.perustars.domain.service.EventService;
import com.acme.perustars.domain.service.HobbyistService;
import com.acme.perustars.resource.EventResource;
import com.acme.perustars.resource.HobbyistResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

public class EventAssistancesController {
    @Autowired
    private EventService eventService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;

    @Operation(summary = "Post Hobbyist Association", description = "Associate a Hobbyist with an Event", tags = {"Event Assistances"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist associated", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping("/hobbyists/{hobbyistId}/events/{eventId}")
    public HobbyistResource associateHobbyistWithEvent(@PathVariable Long hobbyistId, @PathVariable Long eventId) {
        return convertToResource(hobbyistService.associateHobbyistWithEvent(hobbyistId, eventId));
    }



    @Operation(summary = "Delete Hobbyist Association", description = "Disassociate a Hobbyist with an Event", tags = {"Event Assistances"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist disassociated", content = @Content(mediaType =
                    "application/json"))
    })
    @DeleteMapping("/hobbyists/{hobbyistId}/events/{eventId}")
    public HobbyistResource disassociateHobbyistWithEvent(@PathVariable Long hobbyistId, @PathVariable Long eventId) {
        return convertToResource(hobbyistService.disassociateHobbyistWithEvent(hobbyistId, eventId));
    }



    @Operation(summary = "Get Events", description = "Get All Events by HobbyistId", tags = {"Event Assistances"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/hobbyists/{hobbyistId}/events")
    public Page<EventResource> getAllEventsByHobbyistId(@PathVariable Long hobbyistId, Pageable pageable) {
        Page<Event> eventsPage = eventService.getAllEventsByHobbyistId(hobbyistId, pageable);
        List<EventResource> resources = eventsPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    private HobbyistResource convertToResource(Hobbyist hobbyist) {
        return mapper.map(hobbyist, HobbyistResource.class);
    }

    private EventResource convertToResource(Event hobbyist) {
        return mapper.map(hobbyist, EventResource.class);
    }
}
