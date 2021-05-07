package com.perustars.events.service;

import com.perustars.events.domain.model.Artwork;
import com.perustars.events.domain.repository.ArtistRepository;
import com.perustars.events.domain.repository.ArtworkRepository;
import com.perustars.events.domain.service.ArtworkService;
import com.perustars.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ArtistworkServiceImpl implements ArtworkService {
    @Autowired
    private ArtworkRepository artworkRepository;
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public Page<Artwork> getAllArtworks(Pageable pageable) {
        return artworkRepository.findAll(pageable);
    }

    @Override
    public Page<Artwork> getAllArtworksByArtistId(Long artistId, Pageable pageable) {
        return artworkRepository.findByArtistId(artistId, pageable);
    }

    @Override
    public Artwork getArtworkByIdAndArtistId(Long artistId, Long artworkId) {
        return artworkRepository.findByIdAndArtistId(artworkId, artistId).orElseThrow(() -> new ResourceNotFoundException("Artwork not found with Id " + artworkId + " and ArtistId " + artistId));
    }

    @Override
    public Artwork createArtwork(Long artistId, Artwork artwork) {
        return artistRepository.findById(artistId).map(artist -> {
            artwork.setArtist(artist);
            return artworkRepository.save(artwork);
        }).orElseThrow(() -> new ResourceNotFoundException("Artist", "Id", artistId));
    }

    @Override
    public Artwork updateArtwork(Long artistId, Long artworkId, Artwork artworkRequest) {
        if (!artistRepository.existsById(artistId))
            throw new ResourceNotFoundException("Artist","Id",artistId);
        return artworkRepository.findById(artworkId).map(artwork -> {
            artwork.setTitle(artworkRequest.getTitle());
            artwork.setDescription(artworkRequest.getDescription());
            artwork.setCost(artworkRequest.getCost());
            artwork.setLinkInfo(artworkRequest.getLinkInfo());
            return artworkRepository.save(artwork);
        }).orElseThrow(() -> new ResourceNotFoundException("Artwork", "Id", artworkId));
    }

    @Override
    public ResponseEntity<?> deleteArtwork(Long artistId, Long artworkId) {
        if (!artistRepository.existsById(artistId))
            throw new ResourceNotFoundException("Artist","Id",artistId);
        return artworkRepository.findById(artworkId).map(artwork -> {
            artworkRepository.delete(artwork);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Artwork", "Id", artworkId));
    }

    @Override
    public Page<Artwork> getAllArtworksByTitle(String title, Pageable pageable) {
        return artworkRepository.findByTitle(title, pageable);
    }

    @Override
    public Page<Artwork> getAllArtworksByCost(double cost, Pageable pageable) {
        return artworkRepository.findByCost(cost, pageable);
    }
}
