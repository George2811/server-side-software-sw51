package com.perustars.events.controller;

import com.perustars.events.domain.model.Artist;
import com.perustars.events.domain.model.Artwork;
import com.perustars.events.domain.service.ArtworkService;
import com.perustars.events.resource.ArtistResource;
import com.perustars.events.resource.ArtworkResource;
import com.perustars.events.resource.SaveArtistResource;
import com.perustars.events.resource.SaveArtworkResource;
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
public class ArtworksController {

    @Autowired
    private ArtworkService artworkService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/artworks")
    public Page<ArtworkResource> getAllArtworks(Pageable pageable){
        Page<Artwork> artworkPage = artworkService.getAllArtworks(pageable);
        List<ArtworkResource> resources = artworkPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/artists/{artistId}/artworks")
    public Page<ArtworkResource> getAllArtworksByArtistId(@PathVariable Long artistId, Pageable pageable){
        Page<Artwork> artworkPage = artworkService.getAllArtworksByArtistId(artistId, pageable);
        List<ArtworkResource> resources = artworkPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/artists/{artistId}/artworks/{artworkId}")
    public ArtworkResource getAllArtworksByArtistId(@PathVariable Long artistId, @PathVariable Long artworkId){
        return convertToResource(artworkService.getArtworkByIdAndArtistId(artistId, artworkId));
    }

    @PostMapping("/artists/{artistId}/artworks")
    public ArtworkResource createArtwork(@PathVariable Long artistId, @Valid @RequestBody SaveArtworkResource resource){
        Artwork artwork = convertToEntity(resource);
        return convertToResource(artworkService.createArtwork(artistId, artwork));
    }

    @PutMapping("/artists/{artistId}/artworks/{artworkId}")
    public ArtworkResource updateArtwork(@PathVariable Long artistId, @PathVariable Long artworkId, @RequestBody SaveArtworkResource resource){
        Artwork artwork = convertToEntity(resource);
        return convertToResource(artworkService.updateArtwork(artistId,artworkId,artwork));
    }

    @DeleteMapping("/artists/{artistId}/artworks/{artworkId}")
    public ResponseEntity<?> deleteArtwork(@PathVariable Long artistId, @PathVariable Long artworkId){
        return artworkService.deleteArtwork(artistId, artworkId);
    }

    @GetMapping("/artworks/{artworkTitle}")
    public Page<ArtworkResource> getAllArtworksByTitle(@PathVariable String artworkTitle, Pageable pageable){
        Page<Artwork> artworkPage = artworkService.getAllArtworksByTitle(artworkTitle, pageable);
        List<ArtworkResource> resources = artworkPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/artworks/{artworkCost}")
    public Page<ArtworkResource> getAllArtworksByCost(@PathVariable double artworkCost, Pageable pageable){
        Page<Artwork> artworkPage = artworkService.getAllArtworksByCost(artworkCost, pageable);
        List<ArtworkResource> resources = artworkPage.getContent().
                stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    private Artwork convertToEntity(SaveArtworkResource resource){
        return mapper.map(resource, Artwork.class);
    }
    private ArtworkResource convertToResource(Artwork artwork){
        return mapper.map(artwork, ArtworkResource.class);

    }

}
