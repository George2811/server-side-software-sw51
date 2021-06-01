package com.acme.perustars.domain.repository;

import com.acme.perustars.domain.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Page<Artist> findByBrandName(String brandName, Pageable pageable);
}
