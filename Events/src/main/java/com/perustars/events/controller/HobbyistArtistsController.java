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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HobbyistArtistsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;



    @Operation(summary = "Post Hobbyist Association", description = "Associate a Hobbyist with an Artist", tags = {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist associated", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/hobbyists/{hobbyistId}/artists/{artistId}")
    public HobbyistResource associateHobbyistWithArtist(Long hobbyistId, Long artistId){
        return convertToResource(hobbyistService.associateHobbyistWithArtist(hobbyistId, artistId));
    }



    @Operation(summary = "Delete Hobbyist Association", description = "Disassociate a Hobbyist with an Artist", tags = {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist disassociated", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/hobbyists/{hobbyistId}/artists/{artistId}")
    public HobbyistResource disassociateHobbyistWithArtist(Long hobbyistId, Long artistId){
        return convertToResource(hobbyistService.disassociateHobbyistWithArtist(hobbyistId, artistId));
    }



    private HobbyistResource convertToResource(Hobbyist hobbyist){
        return mapper.map(hobbyist, HobbyistResource.class);
    }
}
