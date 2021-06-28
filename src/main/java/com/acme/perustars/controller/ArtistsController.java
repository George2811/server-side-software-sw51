package com.acme.perustars.controller;

import com.acme.perustars.domain.model.Artist;
import com.acme.perustars.domain.model.Person;
import com.acme.perustars.domain.service.ArtistService;
import com.acme.perustars.resource.ArtistResource;
import com.acme.perustars.resource.SaveArtistResource;
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
public class ArtistsController {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ModelMapper mapper;



    @Operation(summary = "Get All Artists", description = "Get All Artists by Pages", tags = {"Artists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Artists returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/artists")
    public Page<ArtistResource> getAllArtists(Pageable pageable) {
        Page<Artist> artistPage = artistService.getAllArtists(pageable);
        List<ArtistResource> resources = artistPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    @Operation(summary = "Get Artist By Id", description = "Get Artist by Id", tags = {"Artists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artist returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/artists/id/{artistId}")
    public ArtistResource getArtistById(@PathVariable Long artistId) {
        return convertToResource(artistService.getArtistById(artistId));
    }



    @Operation(summary = "Get Artist By User Id", description = "Get Artist by User Id", tags = {"Artists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artist returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/artists/userid/{userId}")
    public ArtistResource getArtistByUserId(@PathVariable Long userId) {
        return convertToResource(artistService.getArtistByUserId(userId));
    }



    @Operation(summary = "Post Artist", description = "Create an Artist", tags = {"Artists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artist created", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping("/artists")
    public ArtistResource createArtist(@Valid @RequestBody SaveArtistResource resource) {
        Artist artist = convertToEntity(resource);
        return convertToResource(artistService.createArtist(artist));
    }



    @Operation(summary = "Put Artist", description = "Update an Artist", tags = {"Artists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artist updated", content = @Content(mediaType =
                    "application/json"))
    })
    @PutMapping("/artists/{artistId}")
    public ArtistResource updateArtist(@PathVariable Long artistId, @RequestBody SaveArtistResource resource) {
        Artist artist = convertToEntity(resource);
        return convertToResource(artistService.updateArtist(artistId, artist));
    }



    @Operation(summary = "Delete Artist", description = "Delete an Artist", tags = {"Artists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artist deleted", content = @Content(mediaType =
                    "application/json"))
    })
    @DeleteMapping("/artists/{artistId}")
    public ResponseEntity<?> deleteArtist(@PathVariable Long artistId) {
        return artistService.deleteArtist(artistId);
    }



    @Operation(summary = "Get Artists By Brand Name", description = "Get All Artists by Brand Name per Page", tags = {"Artists"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artists by brand name returned", content =
            @Content(mediaType = "application/json"))
    })
    @GetMapping("/artists/brandName/{artistBrandName}")
    public Page<ArtistResource> getAllArtistByBrandName(@PathVariable String artistBrandName, Pageable pageable) {
        Page<Artist> artistPage = artistService.getAllArtistsByBrandName(artistBrandName, pageable);
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
}
