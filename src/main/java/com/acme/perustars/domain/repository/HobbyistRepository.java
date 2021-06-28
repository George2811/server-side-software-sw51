package com.acme.perustars.domain.repository;

import com.acme.perustars.domain.model.Hobbyist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HobbyistRepository extends JpaRepository<Hobbyist, Long> {
    Optional<Hobbyist> findByUserId(Long userId);
}
