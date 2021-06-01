package com.acme.perustars.controller;

import com.acme.perustars.domain.model.Artwork;
import com.acme.perustars.domain.model.Hobbyist;
import com.acme.perustars.domain.service.ArtworkService;
import com.acme.perustars.domain.service.HobbyistService;
import com.acme.perustars.resource.ArtworkResource;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FavoriteArtworksController {
    @Autowired
    private ArtworkService artworkService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;


    @Operation(summary = "Post Hobbyist Association", description = "Associate a Hobbyist with an Artwork", tags = {"Favorite Artworks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist associated", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping("/hobbyists/{hobbyistId}/artworks/{artworkId}")
    public HobbyistResource associateHobbyistWithArtwork(@PathVariable Long hobbyistId, @PathVariable Long artworkId) {
        return convertToResource(hobbyistService.associateHobbyistWithArtwork(hobbyistId, artworkId));
    }



    @Operation(summary = "Delete Hobbyist Association", description = "Disassociate a Hobbyist with an Artwork", tags = {"Favorite Artworks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist disassociated", content = @Content(mediaType =
                    "application/json"))
    })
    @DeleteMapping("/hobbyists/{hobbyistId}/artworks/{artworkId}")
    public HobbyistResource disassociateHobbyistWithArtwork(@PathVariable Long hobbyistId,
                                                            @PathVariable Long artworkId) {
        return convertToResource(hobbyistService.disassociateHobbyistWithArtwork(hobbyistId, artworkId));
    }



    @Operation(summary = "Get All Artworks by Hobbyist Id", description = "Get All Artworks by Hobbyist Id per Page", tags = {"Favorite Artworks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Artworks returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/hobbyists/{hobbyistId}/artworks")
    public Page<ArtworkResource> getAllArtworksByHobbyistId(@PathVariable Long hobbyistId, Pageable pageable) {
        Page<Artwork> artworkPage = artworkService.getAllArtworksByHobbyistId(hobbyistId, pageable);
        List<ArtworkResource> resources = artworkPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    private HobbyistResource convertToResource(Hobbyist hobbyist) {
        return mapper.map(hobbyist, HobbyistResource.class);
    }

    private ArtworkResource convertToResource(Artwork artwork) {
        return mapper.map(artwork, ArtworkResource.class);
    }
}
