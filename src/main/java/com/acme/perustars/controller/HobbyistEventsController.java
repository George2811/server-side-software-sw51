package com.acme.perustars.controller;

import com.acme.perustars.domain.model.Event;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

public class HobbyistEventsController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ModelMapper mapper;


    @Operation(summary = "Get Events", description = "Get All Events by HobbyistId", tags = {"events"})
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

    private Event convertToEntity(SaveEventResource resource) {
        return mapper.map(resource, Event.class);
    }

    private EventResource convertToResource(Event hobbyist) {
        return mapper.map(hobbyist, EventResource.class);
    }
}
