package com.perustars.events.domain.service;

import com.perustars.events.domain.model.Artwork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ArtworkService {
    Page<Artwork> getAllArtworks(Pageable pageable);
    Page<Artwork> getAllArtworksByArtistId(Long artistId, Pageable pageable);
    Artwork getArtworkById(Long artworkId);
    Artwork createArtwork(Artwork artwork);
    Artwork updateArtwork(Long artworkId, Artwork artworkRequest);
    ResponseEntity<?> deleteArtwork(Long artistId, Long artworkId);
    Page<Artwork> getAllArtworksByTitle(String title);
    Page<Artwork> getAllArtworksByCost(double cost);
}
