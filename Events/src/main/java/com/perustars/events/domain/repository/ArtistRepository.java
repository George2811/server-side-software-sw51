package com.perustars.events.domain.repository;

import com.perustars.events.domain.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    public Optional<Artist> findAllByBrandName();
}
