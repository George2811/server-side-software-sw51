package com.perustars.events.domain.repository;

import com.perustars.events.domain.model.Artwork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
    Page<Artwork> findByArtistId(Long artistsId, Pageable pageable);
    Optional<Artwork> findByIdAndArtistId(Long id, Long artistId);
    public Optional<Artwork> findByTitle(String title);
    public Optional<Artwork> findByCost(double cost);
}
