package com.acme.perustars.domain.repository;

import com.acme.perustars.domain.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Optional<Specialty> findByName(String name);
    Boolean existsByName(String name);
}
