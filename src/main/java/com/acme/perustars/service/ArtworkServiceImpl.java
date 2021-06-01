package com.acme.perustars.service;

import com.acme.perustars.domain.model.Artwork;
import com.acme.perustars.domain.repository.ArtistRepository;
import com.acme.perustars.domain.repository.ArtworkRepository;
import com.acme.perustars.domain.repository.HobbyistRepository;
import com.acme.perustars.domain.service.ArtworkService;
import com.acme.perustars.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ArtworkServiceImpl implements ArtworkService {
    @Autowired
    private ArtworkRepository artworkRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private HobbyistRepository hobbyistRepository;

    @Override
    public Page<Artwork> getAllArtworks(Pageable pageable) {
        return artworkRepository.findAll(pageable);
    }

    @Override
    public Page<Artwork> getAllArtworksByArtistId(Long artistId, Pageable pageable) {
        return artworkRepository.findByArtistId(artistId, pageable);
    }

    @Override
    public Page<Artwork> getAllArtworksByHobbyistId(Long hobbyistId, Pageable pageable) {
        return hobbyistRepository.findById(hobbyistId)
                .map(hobbyist -> {
                    List<Artwork> artworks = hobbyist.getFavoriteArtworks();
                    int artworkCount = artworks.size();
                    return new PageImpl<>(artworks, pageable, artworkCount);
                }).orElseThrow(() -> new ResourceNotFoundException("Hobbyist", "Id", hobbyistId));
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
            throw new ResourceNotFoundException("Artist", "Id", artistId);
        return artworkRepository.findById(artworkId).map(artwork -> {
            artwork.setTitle(artworkRequest.getTitle());
            artwork.setDescription(artworkRequest.getDescription());
            artwork.setCost(artworkRequest.getCost());
            artwork.setLinkInfo(String.join(",", artworkRequest.getLinkInfo()));
            return artworkRepository.save(artwork);
        }).orElseThrow(() -> new ResourceNotFoundException("Artwork", "Id", artworkId));
    }

    @Override
    public ResponseEntity<?> deleteArtwork(Long artistId, Long artworkId) {
        if (!artistRepository.existsById(artistId))
            throw new ResourceNotFoundException("Artist", "Id", artistId);
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
