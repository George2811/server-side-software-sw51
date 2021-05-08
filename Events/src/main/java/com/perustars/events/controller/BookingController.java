package com.perustars.events.controller;

import com.perustars.events.domain.model.Hobbyist;
import com.perustars.events.domain.service.HobbyistService;
import com.perustars.events.resource.HobbyistResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class BookingController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;

    @Operation(summary = "Post Hobbyist Association", description = "Associate a Hobbyist with an Event", tags = {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist associated", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/hobbyists/{hobbyistId}/events/{eventId}")
    public HobbyistResource associateHobbyistWithEvent(@PathVariable Long hobbyistId, @PathVariable Long eventId){
        return convertToResource(hobbyistService.associateHobbyistWithEvent(hobbyistId, eventId));
    }

    @Operation(summary = "Delete Hobbyist Association", description = "Disassociate a Hobbyist with an Event", tags = {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist disassociated", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/hobbyists/{hobbyistId}/events/{eventId}")
    public HobbyistResource disassociateHobbyistWithEvent(@PathVariable Long hobbyistId, @PathVariable Long eventId){
        return convertToResource(hobbyistService.disassociateHobbyistWithEvent(hobbyistId, eventId));
    }



    private HobbyistResource convertToResource(Hobbyist hobbyist){
        return mapper.map(hobbyist, HobbyistResource.class);
    }
}
