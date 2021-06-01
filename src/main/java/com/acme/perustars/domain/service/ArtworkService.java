package com.acme.perustars.domain.service;

import com.acme.perustars.domain.model.Artwork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ArtworkService {
    Page<Artwork> getAllArtworks(Pageable pageable);
    Page<Artwork> getAllArtworksByArtistId(Long artistId, Pageable pageable);
    Page<Artwork> getAllArtworksByHobbyistId(Long hobbyistId, Pageable pageable);
    Artwork getArtworkByIdAndArtistId(Long artistId, Long artworkId);
    Artwork createArtwork(Long artistId, Artwork artwork);
    Artwork updateArtwork(Long artistId, Long artworkId, Artwork artworkRequest);
    ResponseEntity<?> deleteArtwork(Long artistId, Long artworkId);
    Page<Artwork> getAllArtworksByTitle(String title, Pageable pageable);
    Page<Artwork> getAllArtworksByCost(double cost, Pageable pageable);
}
