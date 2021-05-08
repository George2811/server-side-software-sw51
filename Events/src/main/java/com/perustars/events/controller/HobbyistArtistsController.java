package com.perustars.events.controller;

import com.perustars.events.domain.model.Hobbyist;
import com.perustars.events.domain.service.ArtistService;
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
import com.perustars.events.domain.model.Artist;
import com.perustars.events.resource.ArtistResource;
import com.perustars.events.resource.SaveArtistResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HobbyistArtistsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HobbyistService hobbyistService;
    @Autowired
    private ArtistService artistService;


    @Operation(summary = "Post Hobbyist Association", description = "Associate a Hobbyist with an Artist", tags = {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist associated", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/hobbyists/{hobbyistId}/artists/{artistId}")
    public HobbyistResource associateHobbyistWithArtist(Long hobbyistId, Long artistId) {
        return convertToResource(hobbyistService.associateHobbyistWithArtist(hobbyistId, artistId));
    }


    @Operation(summary = "Delete Hobbyist Association", description = "Disassociate a Hobbyist with an Artist", tags = {"hobbyist"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hobbyist disassociated", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/hobbyists/{hobbyistId}/artists/{artistId}")
    public HobbyistResource disassociateHobbyistWithArtist(Long hobbyistId, Long artistId) {
        return convertToResource(hobbyistService.disassociateHobbyistWithArtist(hobbyistId, artistId));
    }


    @GetMapping("/hobbyists/{hobbyistId}/artists")
    public Page<ArtistResource> getAllArtistByHobbyistId(@PathVariable Long hobbyistId, Pageable pageable) {
        Page<Artist> artistPage = artistService.getAllArtistByHobbyistId(hobbyistId, pageable);
        List<ArtistResource> resources = artistPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    private Artist convertToEntity(SaveArtistResource resource) {
        return mapper.map(resource, Artist.class);
    }
    private ArtistResource convertToResource(Artist entity) {
        return mapper.map(entity, ArtistResource.class);
    }
    private HobbyistResource convertToResource(Hobbyist entity) {
        return mapper.map(entity, HobbyistResource.class);
    }
}
