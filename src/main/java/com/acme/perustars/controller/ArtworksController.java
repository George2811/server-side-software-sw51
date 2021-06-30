package com.acme.perustars.controller;

import com.acme.perustars.domain.model.Artwork;
import com.acme.perustars.domain.service.ArtworkService;
import com.acme.perustars.resource.ArtworkResource;
import com.acme.perustars.resource.SaveArtworkResource;
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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ArtworksController {

    @Autowired
    private ArtworkService artworkService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get All Artworks", description = "Get All Artworks by Pages", tags = {"Artworks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Artworks returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/artworks")
    public Page<ArtworkResource> getAllArtworks(Pageable pageable) {
        Page<Artwork> artworkPage = artworkService.getAllArtworks(pageable);
        List<ArtworkResource> resources = artworkPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get All Artworks by Artist Id", description = "Get All Artworks by Artist Id per Pages", tags = {"Artworks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Artworks returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/artists/{artistId}/artworks")
    public Page<ArtworkResource> getAllArtworksByArtistId(@PathVariable Long artistId, Pageable pageable) {
        Page<Artwork> artworkPage = artworkService.getAllArtworksByArtistId(artistId, pageable);
        List<ArtworkResource> resources = artworkPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Artwork by Id and Artist Id", description = "Get Artwork by Id and Artist Id", tags = {"Artworks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artwork returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/artists/{artistId}/artworks/{artworkId}")
    public ArtworkResource getArtworkByIdAndArtistId(@PathVariable Long artistId, @PathVariable Long artworkId) {
        return convertToResource(artworkService.getArtworkByIdAndArtistId(artistId, artworkId));
    }

    @Operation(summary = "Post Artwork", description = "Create an Artwork", tags = {"Artworks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artwork created", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping("/artists/{artistId}/artworks")
    public ArtworkResource createArtwork(@PathVariable Long artistId,
                                         @Valid @RequestBody SaveArtworkResource resource) {
        Artwork artwork = convertToEntity(resource);
        return convertToResource(artworkService.createArtwork(artistId, artwork));
    }

    @Operation(summary = "Put Artwork", description = "Update an Artwork", tags = {"Artworks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artist updated", content = @Content(mediaType =
                    "application/json"))
    })
    @PutMapping("/artists/{artistId}/artworks/{artworkId}")
    public ArtworkResource updateArtwork(@PathVariable Long artistId, @PathVariable Long artworkId,
                                         @RequestBody SaveArtworkResource resource) {
        Artwork artwork = convertToEntity(resource);
        return convertToResource(artworkService.updateArtwork(artistId, artworkId, artwork));
    }

    @Operation(summary = "Delete Artwork", description = "Delete an Artwork", tags = {"Artworks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artwork deleted", content = @Content(mediaType =
                    "application/json"))
    })
    @DeleteMapping("/artists/{artistId}/artworks/{artworkId}")
    public ResponseEntity<?> deleteArtwork(@PathVariable Long artistId, @PathVariable Long artworkId) {
        return artworkService.deleteArtwork(artistId, artworkId);
    }

    @Operation(summary = "Get All Artworks by Title", description = "Get All Artworks by Title per Pages", tags = {"Artworks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artworks by title returned", content =
            @Content(mediaType = "application/json"))
    })
    @GetMapping("/artworks/title/{artworkTitle}")
    public Page<ArtworkResource> getAllArtworksByTitle(@PathVariable String artworkTitle, Pageable pageable) {
        Page<Artwork> artworkPage = artworkService.getAllArtworksByTitle(artworkTitle, pageable);
        List<ArtworkResource> resources = artworkPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get All Artworks by Cost", description = "Get All Artworks by Cost per Pages", tags = {"Artworks"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artworks by cost returned", content =
            @Content(mediaType = "application/json"))
    })
    @GetMapping("/artworks/cost/{artworkCost}")
    public Page<ArtworkResource> getAllArtworksByCost(@PathVariable double artworkCost, Pageable pageable) {
        Page<Artwork> artworkPage = artworkService.getAllArtworksByCost(artworkCost, pageable);
        List<ArtworkResource> resources = artworkPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Artwork convertToEntity(SaveArtworkResource resource) {
        return mapper.map(resource, Artwork.class);
    }

    private ArtworkResource convertToResource(Artwork artwork) {
        return mapper.map(artwork, ArtworkResource.class);
    }
}
