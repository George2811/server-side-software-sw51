package com.perustars.events.domain.repository;

import com.perustars.events.domain.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    public Optional<Specialty> findByName(String name);
}
