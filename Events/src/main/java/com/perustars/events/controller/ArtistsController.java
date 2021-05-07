package com.perustars.events.controller;

import com.perustars.events.domain.model.Artist;
import com.perustars.events.domain.service.ArtistService;
import com.perustars.events.resource.ArtistResource;
import com.perustars.events.resource.SaveArtistResource;
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

    @GetMapping("/artists")
    public Page<ArtistResource> getAllArtists(Pageable pageable){
        Page<Artist> artistPage = artistService.getAllArtists(pageable);
        List<ArtistResource> resources = artistPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/artists/{artistId}")
    public ArtistResource getArtistById(@PathVariable Long artistId){
        return convertToResource(artistService.getArtistById(artistId));
    }
    @PostMapping("/artists")
    public ArtistResource createArtist(@Valid @RequestBody SaveArtistResource resource){
        Artist artist = convertToEntity(resource);
        return convertToResource(artistService.createArtist(artist));
    }

    @PutMapping("/artists/{artistId}")
    public ArtistResource updateArtist(@PathVariable Long artistId, @RequestBody SaveArtistResource resource){
        Artist artist = convertToEntity(resource);
        return convertToResource(artistService.updateArtist(artistId,artist));
    }

    @DeleteMapping("/artists/{artistId}")
    public ResponseEntity<?> deleteArtist(@PathVariable Long artistId){
        return artistService.deleteArtist(artistId);
    }

    @GetMapping("/artists/{artistBrandName}")
    public Page<ArtistResource> getAllArtistByBrandName(@PathVariable String artistBrandName, Pageable pageable){
        Page<Artist> artistPage = artistService.getAllArtistsByBrandName(artistBrandName, pageable);
        List<ArtistResource> resources = artistPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Artist convertToEntity(SaveArtistResource resource){
        return mapper.map(resource, Artist.class);
    }
    private ArtistResource convertToResource(Artist entity){
        return mapper.map(entity,ArtistResource.class);
    }
}
