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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FavoriteArtworksController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;


    @Operation(summary = "Post Hobbyist Association", description = "Associate a Hobbyist with an Artwork", tags = {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist associated", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/hobbyists/{hobbyistId}/artworks/{artworkId}")
    public HobbyistResource associateHobbyistWithArtwork(@PathVariable Long hobbyistId,@PathVariable Long artworkId){
        return convertToResource(hobbyistService.associateHobbyistWithArtwork(hobbyistId, artworkId));
    }


    @Operation(summary = "Delete Hobbyist Association", description = "Disassociate a Hobbyist with an Artwork", tags = {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist disassociated", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/hobbyists/{hobbyistId}/artworks/{artworkId}")
    public HobbyistResource disassociateHobbyistWithArtwork(@PathVariable Long hobbyistId,@PathVariable Long artworkId){
        return convertToResource(hobbyistService.disassociateHobbyistWithArtwork(hobbyistId, artworkId));
    }



    private HobbyistResource convertToResource(Hobbyist hobbyist){
        return mapper.map(hobbyist, HobbyistResource.class);
    }
}
