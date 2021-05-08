package com.acme.perustars.domain.repository;

import com.acme.perustars.domain.model.Artwork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
    Page<Artwork> findByArtistId(Long artistsId, Pageable pageable);

    Optional<Artwork> findByIdAndArtistId(Long id, Long artistId);

    Page<Artwork> findByTitle(String title, Pageable pageable);

    Page<Artwork> findByCost(double cost, Pageable pageable);
}
